package com.project.market.dto;

import com.project.market.domain.Store;
import lombok.*;

import java.util.List;

public class StoreDTO {
    @RequiredArgsConstructor
    @Data
    public static class Select{
        private Long id;
        private String name;
        private String market;

        public Select(Store store){
            this.id = store.getId();
            this.name = store.getName();
            this.market = store.getMarket().getName();
        }
    }
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
//        private String name;
//        private String intro;
//        private String address;
//        private String imgUrl;
        private Double score;
//        private List<MenuDTO> menuList;
    }
}
