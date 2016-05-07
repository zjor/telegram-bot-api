package com.github.zjor.telegram.bot.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InputTextMessageContentDTO extends InputMessageContentDTO {

    /**
     * Text of the message to be sent, 1-4096 characters
     */
    private String messageText;

    /**
     * Optional.
     * Send Markdown or HTML, if you want Telegram apps to show bold, italic,
     * fixed-width text or inline URLs in your bot's message.
     */
    private String parseMode;

    /**
     * Optional. Disables link previews for links in the sent message
     */
    private Boolean disableWebPagePreview;

}
