package com.project.market.exception;

public class NonExistentBoardException extends MarketException {
    private static final String MESSAGE = "존재하지 않는 게시글입니다.";
    public NonExistentBoardException(){
        super(MESSAGE);
    }
    @Override
    public int getStatusCode() {
        return 400;
    }
}
