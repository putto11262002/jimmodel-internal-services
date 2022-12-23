package com.jimmodel.services.exception;

import lombok.Getter;
import lombok.Setter;

import java.util.Collection;

@Getter
@Setter
public class ValidationException extends RuntimeException {
    public ValidationException(String message){
        super(message);
    }
}
