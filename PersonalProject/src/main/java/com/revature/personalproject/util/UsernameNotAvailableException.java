package com.revature.personalproject.util;

public class UsernameNotAvailableException extends RuntimeException {
    public UsernameNotAvailableException() {super();}

    public UsernameNotAvailableException(String message, Throwable cause, boolean enableSuppresion, boolean writeableStackTrace)
    {
        super(message, cause, enableSuppresion, writeableStackTrace);
    }
    public UsernameNotAvailableException(String message, Throwable cause) {super(message, cause);}

    public UsernameNotAvailableException(String message) {super(message);}

    public UsernameNotAvailableException(Throwable cause) {super(cause);}

    public void printStaceTrace() {
    }
}
