package com.github.zjor.telegram.bot.api.dto.methods;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SendPhoto extends AbstractSend {

    public static final String FIELD_PHOTO = "photo";

    public static final String FIELD_CAPTION = "caption";

    /**
     * InputFile or String	Photo to send. You can either pass a file_id as String to resend a photo
     * that is already on the Telegram servers, or upload a new photo using multipart/form-data.
     */
    private String photo;

    /**
     * Optional	Photo caption (may also be used when resending photos by file_id), 0-200 characters
     */
    private String caption;

    public SendPhoto(String chatId, String photoId, String caption, Object replyMarkup) {
        setChatId(chatId);
        this.photo = photoId;
        this.caption = caption;
        setReplyMarkup(replyMarkup);
    }


}
