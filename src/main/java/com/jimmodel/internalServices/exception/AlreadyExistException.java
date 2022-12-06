package com.jimmodel.internalServices.exception;

import org.springframework.http.HttpStatus;

public class AlreadyExistException extends RuntimeException{
    private HttpStatus statusCode;

    public AlreadyExistException(String message){
        super(message);
        this.statusCode = HttpStatus.CONFLICT;
    }
}
