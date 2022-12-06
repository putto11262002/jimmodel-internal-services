package com.jimmodel.internalServices.exception;

import com.jimmodel.internalServices.model.BaseEntity;

import javax.validation.ConstraintViolation;
import java.util.Set;

public class ValidationException extends RuntimeException {
    private Set<ConstraintViolation<BaseEntity>> violations;
    public ValidationException(String message, Set<ConstraintViolation<BaseEntity>> violations){
        super(message);
        this.violations = violations;
    }

    public ValidationException(String message){
        super(message);
    }
}
