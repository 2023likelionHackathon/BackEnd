package com.project.market.dto;

import com.project.market.domain.Board;
import com.project.market.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

public class BoardDTO {
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Request{
        private Long userId;
        private String title;
        private String content;
        private Double score;

        public Board toEntity(User user) {
            return Board.builder()
                    .user(user)
                    .title(title)
                    .content(content)
                    .score(score).build();
        }
    }
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Resposnse{
        private Long boardId;
        private Long userId;
        private String title;
        private String content;
        private Double score;
        private int likes;
        private List<String> imgUrlList;
        private List<ReplyDTO.Response> replyList;

        private String createdDate;

    }
}
