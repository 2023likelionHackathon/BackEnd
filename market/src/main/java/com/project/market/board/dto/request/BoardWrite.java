package com.project.market.board.dto.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BoardWrite {
    private String title;
    private Long userId;
    private String writer;
    private String content;
}
