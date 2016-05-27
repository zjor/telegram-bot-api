package com.github.zjor.telegram.bot.api.dto.methods;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetUpdates {

    private Integer offset;

    private Integer limit;

    private Integer timeout;

}
