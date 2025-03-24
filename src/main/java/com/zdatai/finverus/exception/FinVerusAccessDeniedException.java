package com.zdatai.finverus.exception;

public class FinVerusAccessDeniedException extends RuntimeException{
    public FinVerusAccessDeniedException() {
        super();
    }

    public FinVerusAccessDeniedException(String message) {
        super(message);
    }
}
