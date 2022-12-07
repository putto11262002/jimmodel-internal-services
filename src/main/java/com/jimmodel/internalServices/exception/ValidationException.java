package com.jimmodel.internalServices.exception;

import com.jimmodel.internalServices.model.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.validation.ConstraintViolation;
import java.util.Collection;
import java.util.Set;

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
