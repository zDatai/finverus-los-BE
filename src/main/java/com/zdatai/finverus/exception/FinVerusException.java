package com.zdatai.finverus.exception;

public class FinVerusException extends RuntimeException{

    public FinVerusException(String message) {
        super(message);
    }

    public FinVerusException(){
        super();
    }
}
