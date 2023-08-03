package com.project.market.exception;

public class AlreadyExistsException extends MarketException{
    public AlreadyExistsException(String message){
        super(message);
    }
    @Override
    public int getStatusCode() {
        return 400;
    }
}
