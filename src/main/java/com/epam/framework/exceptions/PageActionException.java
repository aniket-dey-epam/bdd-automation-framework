package com.epam.framework.exceptions;

public class PageActionException extends RuntimeException {

    public PageActionException(String message) {
        super(message);
    }

    public PageActionException(String message, Throwable cause) {
        super(message, cause);
    }
}
