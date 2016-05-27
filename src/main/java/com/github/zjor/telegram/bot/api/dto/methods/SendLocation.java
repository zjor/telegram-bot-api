package com.github.zjor.telegram.bot.api.dto.methods;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SendLocation extends AbstractSend {

    private double latitude;

    private double longitude;

}
