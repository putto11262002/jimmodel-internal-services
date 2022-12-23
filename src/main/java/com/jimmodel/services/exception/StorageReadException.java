package com.jimmodel.services.exception;

public class StorageReadException extends RuntimeException{

    public StorageReadException(String message){
        super(message);
    }
}
