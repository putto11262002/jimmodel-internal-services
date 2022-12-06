package com.jimmodel.internalServices.exception;

import java.util.Collection;

public class JwtException extends RuntimeException{
    public JwtException(String message){
        super(message);
    }
}
