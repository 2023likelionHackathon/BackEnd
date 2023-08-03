package com.project.market.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

public class StoreDTO {
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @Data
    public static class Summary{
        private Long id;
        private String name;
        private String intro;
        private String imgUrl;
    }
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @Data
    public static class Detail{
        private Long id;
        private String name;
        private String intro;
        private String address;
        private String imgUrl;
        private Double score;
        private List<MenuDTO> menuList;
    }
}
