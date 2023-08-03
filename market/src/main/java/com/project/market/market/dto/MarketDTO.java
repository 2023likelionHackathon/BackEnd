package com.project.market.market.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

public class MarketDTO {
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @Data
    public static class Main{
        private Long id;
        private String name;
        private String intro;
        private String address;
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
        private List<StoreDTO.Main> storeList;
    }
}
