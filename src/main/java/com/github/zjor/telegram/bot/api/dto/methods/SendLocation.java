package com.github.zjor.telegram.bot.api.dto.methods;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SendLocation extends AbstractSend {

    private double latitude;

    private double longitude;

    public SendLocation(String chatId, double latitude, double longitude, Object replyMarkup) {
        setChatId(chatId);
        setReplyMarkup(replyMarkup);
        this.latitude = latitude;
        this.longitude = longitude;
    }

}
