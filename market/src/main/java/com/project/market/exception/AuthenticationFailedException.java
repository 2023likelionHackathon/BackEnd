package com.project.market.exception;

public class AuthenticationFailedException extends MarketException{
    @Override
    public int getStatusCode() {
        return 400;
    }

    public AuthenticationFailedException(String message){
        super(message);
    }
}
