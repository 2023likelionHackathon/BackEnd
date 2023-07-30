package com.project.market.exception;

public class ImageUploadException extends MarketException{
    private static final String MESSAGE = "이미지 업로드에 실패했습니다";
    public ImageUploadException(){
        super(MESSAGE);
    }

    @Override
    public int getStatusCode() {
        return 405;
    }
}
