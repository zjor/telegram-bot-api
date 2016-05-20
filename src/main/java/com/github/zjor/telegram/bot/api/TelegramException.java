package com.github.zjor.telegram.bot.api;

public class TelegramException extends RuntimeException {

    private Integer errorCode;

    public TelegramException(Integer errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public TelegramException(String message, Throwable cause) {
        super(message, cause);
    }
}
