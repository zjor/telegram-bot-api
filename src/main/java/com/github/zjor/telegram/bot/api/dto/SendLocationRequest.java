package com.github.zjor.telegram.bot.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SendLocationRequest {

    /**
     * Unique identifier for the target chat or username of the target channel (in the format @channelusername)
     */
    private String chatId;

    private double latitude;

    private double longitude;

    /**
     * Optional	Sends the message silently. iOS users will not receive a notification, Android users will receive a notification with no sound.
     */
    private Boolean disableNotification = true;

    /**
     * If the message is a reply, ID of the original message
     */
    private Integer replyToMessageId;

    /**
     * InlineKeyboardMarkup or ReplyKeyboardMarkup or ReplyKeyboardHide or ForceReply
     */
    private Object replyMarkup;

}
