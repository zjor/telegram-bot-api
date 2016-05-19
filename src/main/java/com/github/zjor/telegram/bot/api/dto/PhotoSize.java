package com.github.zjor.telegram.bot.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PhotoSize {

    private String fileId;
    private int width;
    private int height;
    private Integer fileSize;

}
