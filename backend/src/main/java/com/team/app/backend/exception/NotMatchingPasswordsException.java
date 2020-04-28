package com.team.app.backend.exception;

public class NotMatchingPasswordsException extends Exception {
    public NotMatchingPasswordsException() {}

    public NotMatchingPasswordsException(String message) {
        super(message);
    }

    public NotMatchingPasswordsException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotMatchingPasswordsException(Throwable cause) {
        super(cause);
    }

    public NotMatchingPasswordsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
