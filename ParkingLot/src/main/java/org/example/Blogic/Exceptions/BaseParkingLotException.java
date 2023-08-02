package org.example.Blogic.Exceptions;

public class BaseParkingLotException extends Exception {

    private final ErrorCode errorCode;

    public BaseParkingLotException(ErrorCode errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }
}
