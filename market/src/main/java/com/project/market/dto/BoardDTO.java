package com.project.market.dto;

import com.project.market.domain.Board;
import com.project.market.domain.Store;
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
        private Long store_id;
        private String content;
        private Double score;

        public Board toEntity(User user, Store store) {
            return Board.builder()
                    .user(user)
                    .store(store)
                    .content(content)
                    .score(score).build();
        }
    }
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Response {
        private Long boardId;
        private Long userId;
        private String writer;
        private Long storeId;
        private String storeName;
        private String content;
        private Double score;
        private int likes;
        private List<String> imgUrlList;
        private int size_reply;

        private String createdDate;

    }

}
