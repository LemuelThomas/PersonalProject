package com.revature.personalproject.util;

public class InvalidCredentialsException extends RuntimeException {

    public InvalidCredentialsException() {super();}

    public InvalidCredentialsException(String message, Throwable cause, boolean enableSuppresion, boolean writeableStackTrace)
    {
        super(message, cause, enableSuppresion, writeableStackTrace);
    }
    public InvalidCredentialsException(String message, Throwable cause) {super(message, cause);}

    public InvalidCredentialsException(String message) {super(message);}

    public InvalidCredentialsException(Throwable cause) {super(cause);}
}
