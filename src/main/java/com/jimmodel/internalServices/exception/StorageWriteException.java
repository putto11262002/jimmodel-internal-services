package com.jimmodel.internalServices.exception;

public class StorageWriteException extends RuntimeException{
    public StorageWriteException(String message){
        super(message);
    }
}
