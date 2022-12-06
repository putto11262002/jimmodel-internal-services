package com.jimmodel.internalServices.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class ResourceNotFoundException extends RuntimeException {
    private HttpStatus errorCode;
    public ResourceNotFoundException(String message) {
        super(message);
        this.errorCode = HttpStatus.BAD_REQUEST;

    }
}
