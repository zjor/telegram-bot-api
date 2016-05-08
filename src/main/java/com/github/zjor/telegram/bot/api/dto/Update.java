package com.github.zjor.telegram.bot.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Optional;

/**
 * This object represents an incoming update.
 * Only one of the optional parameters can be present in any given update.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Update {

    /**
     * The update‘s unique identifier.
     * Update identifiers start from a certain positive number and increase sequentially.
     * This ID becomes especially handy if you’re using Webhooks, since it allows you
     * to ignore repeated updates or to restore the correct update sequence,
     * should they get out of order.
     */
    private int updateId;

    /**
     * New incoming message of any kind — text, photo, sticker, etc.
     */
    private Optional<Message> message;

    /**
     * New incoming inline query
     */
    private Optional<InlineQuery> inlineQuery;

    private Optional<Object> chosenInlineResult;

    private Optional<Object> callbackQuery;
}
