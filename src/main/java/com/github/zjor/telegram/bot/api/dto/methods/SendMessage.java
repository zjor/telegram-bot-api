package com.github.zjor.telegram.bot.api.dto.methods;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SendMessage extends AbstractSend {

    private String text;

    private String parseMode;

    private Boolean disableWebPageView;

    public SendMessage(int chatId, String text) {
        setChatId("" + chatId);
        this.text = text;
    }

    public SendMessage(int chatId, String text, String parseMode) {
        setChatId("" + chatId);
        this.text = text;
        this.parseMode = parseMode;
    }

}
