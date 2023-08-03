package com.project.market.exception;

public class NonExistentUserException extends MarketException{
    private static final String MESSAGE = "존재하지 않는 사용자입니다.";
    public NonExistentUserException(){
        super(MESSAGE);
    }
    @Override
    public int getStatusCode() {
        return 400;
    }
}
