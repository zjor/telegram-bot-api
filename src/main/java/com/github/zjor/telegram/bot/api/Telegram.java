package com.github.zjor.telegram.bot.api;

import com.github.zjor.telegram.bot.api.dto.Message;
import com.github.zjor.telegram.bot.api.dto.Response;
import com.github.zjor.telegram.bot.api.dto.ResponseParametrizedType;
import com.github.zjor.telegram.bot.api.dto.Update;
import com.github.zjor.telegram.bot.api.dto.User;
import com.github.zjor.telegram.bot.api.dto.methods.AbstractSend;
import com.github.zjor.telegram.bot.api.dto.methods.AnswerInlineQuery;
import com.github.zjor.telegram.bot.api.dto.methods.GetUpdates;
import com.github.zjor.telegram.bot.api.dto.methods.SendLocation;
import com.github.zjor.telegram.bot.api.dto.methods.SendMessage;
import com.github.zjor.telegram.bot.api.dto.methods.SendPhoto;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.text.MessageFormat;
import java.util.List;

@Slf4j
public class Telegram {

    public static final String TELEGRAM_API_URL = "https://api.telegram.org/bot{0}/{1}";

    public static final String METHOD_GET_ME = "getMe";
    public static final String METHOD_GET_UPDATES = "getUpdates";
    public static final String METHOD_SEND_MESSAGE = "sendMessage";
    public static final String METHOD_SEND_LOCATION = "sendLocation";
    public static final String METHOD_SEND_PHOTO = "sendPhoto";
    public static final String METHOD_ANSWER_INLINE_QUERY = "answerInlineQuery";

    private String token;
    private HttpClient httpClient;

    private Gson gson;

    public Telegram(String token) {
        this(token, HttpClientBuilder.create().build());
    }

    public Telegram(String token, HttpClient httpClient) {
        this.token = token;
        this.httpClient = httpClient;
        gson = createGson();
    }

    public static Gson createGson() {
        return (new GsonBuilder()).setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();
    }

    public static Telegram getDefault(String token) {
        return new Telegram(token, HttpClientBuilder.create().build());
    }

    public User getMe() throws TelegramException {
        return get(METHOD_GET_ME, new ResponseParametrizedType(User.class));
    }

    public List<Update> getUpdates(Integer offset, Integer limit, Integer timeout) throws TelegramException {
        Type updatesType = new TypeToken<List<Update>>() {
        }.getType();
        return post(METHOD_GET_UPDATES, new GetUpdates(offset, limit, timeout), new ResponseParametrizedType(updatesType));
    }

    public Message sendMessage(SendMessage req) throws TelegramException {
        return post(METHOD_SEND_MESSAGE, req, new ResponseParametrizedType(Message.class));
    }

    public Message sendLocation(SendLocation req) throws TelegramException {
        return post(METHOD_SEND_LOCATION, req, new ResponseParametrizedType(Message.class));
    }

    public Message sendPhoto(SendPhoto req) throws TelegramException {
        return post(METHOD_SEND_PHOTO, req, new ResponseParametrizedType(Message.class));
    }

    /**
     * Sends photo with uploading
     * @param req
     * @param photo photo input stream
     * @param filename filename including extension
     * @return
     * @throws TelegramException
     */
    public Message sendPhoto(SendPhoto req, InputStream photo, String filename) throws TelegramException {
        MultipartEntityBuilder builder = MultipartEntityBuilder.create();

        builder.addTextBody(AbstractSend.FIELD_CHAT_ID, req.getChatId());
        builder.addBinaryBody(SendPhoto.FIELD_PHOTO, photo, ContentType.APPLICATION_OCTET_STREAM, filename);

        if (StringUtils.isNotEmpty(req.getCaption())) {
            builder.addTextBody(SendPhoto.FIELD_CAPTION, req.getCaption(), ContentType.create("text/plain", StandardCharsets.UTF_8));
        }

        if (req.getDisableNotification() != null) {
            builder.addTextBody(AbstractSend.FIELD_DISABLE_NOTIFICATION, "" + req.getDisableNotification());
        }

        if (req.getReplyToMessageId() != null) {
            builder.addTextBody(AbstractSend.FIELD_REPLY_TO_MESSAGE_ID, "" + req.getReplyToMessageId());
        }

        if (req.getReplyMarkup() != null) {
            builder.addTextBody(AbstractSend.FIELD_REPLY_MARKUP, gson.toJson(req.getReplyMarkup()));
        }
        HttpPost post = new HttpPost(buildUri(METHOD_SEND_PHOTO));
        post.setEntity(builder.build());

        return executeRequest(post, new ResponseParametrizedType(Message.class));
    }


    public Boolean answerInlineQuery(AnswerInlineQuery req) throws TelegramException {
        return post(METHOD_ANSWER_INLINE_QUERY, req, new ResponseParametrizedType(Boolean.class));
    }

    private <T> T get(String method, Type responseType) throws TelegramException {
        HttpGet req = new HttpGet(buildUri(method));
        return executeRequest(req, responseType);
    }

    private <T> T post(String method, Object requestBody, Type responseType) throws TelegramException {
        HttpPost post = new HttpPost(buildUri(method));
        post.setHeader(new BasicHeader(HttpHeaders.CONTENT_TYPE, "application/json"));

        try {
            post.setEntity(new StringEntity(gson.toJson(requestBody), "UTF-8"));

        } catch (UnsupportedEncodingException e) {
            throw new TelegramException("Unable to construct request", e);
        }
        return executeRequest(post, responseType);
    }

    private <T> T executeRequest(HttpUriRequest req, Type responseType) throws TelegramException {
        try {
            log.debug("=> {}", req.getURI());

            HttpResponse httpResponse = httpClient.execute(req);
            String content = EntityUtils.toString(httpResponse.getEntity());
            log.debug("<= {}: {}", httpResponse.getStatusLine(), content);

            Response<T> response = gson.fromJson(content, responseType);
            if (!response.isOk()) {
                throw new TelegramException(response.getErrorCode(), response.getDescription());
            }
            return response.getResult();
        } catch (Exception e) {
            throw new TelegramException("Unable to execute request", e);
        }
    }

    private String buildUri(String method) {
        return MessageFormat.format(TELEGRAM_API_URL, token, method);
    }


}
