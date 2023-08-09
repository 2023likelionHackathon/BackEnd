package com.project.market.exception;

public class SendMessageFailException extends MarketException{
    @Override
    public int getStatusCode() {
        return 400;
    }

    public SendMessageFailException(String message){
        super(message);
    }
}
