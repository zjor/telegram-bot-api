package com.github.zjor.telegram.bot.api.dto;

import lombok.Data;

@Data
public class InlineQueryResultArticle extends InlineQueryResult {

    /**
     * Type of the result, must be article
     */
    private String type = "article";

    /**
     * Unique identifier for this result, 1-64 Bytes
     */
    private String id;

    /**
     * Title of the result
     */
    private String title;

    /**
     * Content of the message to be sent
     */
    private InputMessageContent inputMessageContent;

    /**
     * Optional. Inline keyboard attached to the message
     */
    private Object replyMarkup;

    /**
     * Optional. URL of the result
     */
    private String url;

    /*
hide_url	Boolean	Optional. Pass True, if you don't want the URL to be shown in the message
description	String	Optional. Short description of the result
thumb_url	String	Optional. Url of the thumbnail for the result
thumb_width	Integer	Optional. Thumbnail width
thumb_height	Integer	Optional. Thumbnail height
     */

}
