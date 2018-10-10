package com.matthieurb.demosanteclair.services;

public class DateTooCloseException extends RuntimeException {

    public DateTooCloseException() {
    }

    public DateTooCloseException(String message) {
        super(message);
    }

    public DateTooCloseException(String message, Throwable cause) {
        super(message, cause);
    }

    public DateTooCloseException(Throwable cause) {
        super(cause);
    }

    public DateTooCloseException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
