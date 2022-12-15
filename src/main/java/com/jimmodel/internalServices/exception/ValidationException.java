package com.jimmodel.internalServices.exception;

import lombok.Getter;
import lombok.Setter;

import java.util.Collection;

@Getter
@Setter
public class ValidationException extends RuntimeException {
    private Collection<String> violations;
    public ValidationException(String message, Collection<String> violations){
        super(message);
        this.violations = violations;
    }

    public ValidationException(String message){
        super(message);
    }
}
