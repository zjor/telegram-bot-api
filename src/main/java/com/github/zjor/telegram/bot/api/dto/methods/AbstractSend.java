package com.github.zjor.telegram.bot.api.dto.methods;

import lombok.Data;

@Data
public abstract class AbstractSend {

    public static final String FIELD_CHAT_ID = "chat_id";

    public static final String FIELD_DISABLE_NOTIFICATION = "disable_notification";

    public static final String FIELD_REPLY_TO_MESSAGE_ID = "reply_to_message_id";

    public static final String FIELD_REPLY_MARKUP = "reply_markup";

    /**
     * Unique identifier for the target chat or username of the target channel (in the format @channelusername)
     */
    private String chatId;

    /**
     * Optional	Sends the message silently. iOS users will not receive a notification, Android users will receive a notification with no sound.
     */
    private Boolean disableNotification;

    /**
     * If the message is a reply, ID of the original message
     */
    private Integer replyToMessageId;

    /**
     * InlineKeyboardMarkup or ReplyKeyboardMarkup or ReplyKeyboardHide or ForceReply
     */
    private Object replyMarkup;

}
