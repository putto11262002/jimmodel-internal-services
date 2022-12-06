package com.jimmodel.internalServices.exception;

public class StorageReadException extends RuntimeException{

    public StorageReadException(String message){
        super(message);
    }
}
