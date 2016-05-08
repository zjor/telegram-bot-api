package com.github.zjor.telegram.bot.api.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AnswerInlineQuery {

    /**
     * Unique identifier for the answered query
     */
    private String inlineQueryId;

    /**
     * A JSON-serialized array of results for the inline query
     */
    private InlineQueryResult[] results;

    public AnswerInlineQuery(String inlineQueryId, InlineQueryResult... results) {
        this.inlineQueryId = inlineQueryId;
        this.results = results;
    }

}
