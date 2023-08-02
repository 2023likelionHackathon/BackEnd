package com.project.market.exception;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public abstract class MarketException extends RuntimeException{
    public final Map<String, String> validation = new HashMap<>();

    public MarketException(String message){
        super(message);
    }
    public MarketException(String message, Throwable cause){
        super(message, cause);
    }
    public abstract int getStatusCode();

    public void addValidation(String fieldName, String message){
        validation.put(fieldName, message);
    }
}
