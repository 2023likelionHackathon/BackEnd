package com.project.market.exception;

public class NonExistentMarketException extends MarketException{
    private static final String MESSAGE = "해당 시장에 대한 데이터는 존재하지 않습니다.";
    public NonExistentMarketException(){
        super(MESSAGE);
    }
    @Override
    public int getStatusCode() {
        return 400;
    }

}
