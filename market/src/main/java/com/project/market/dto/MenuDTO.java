package com.project.market.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class MenuDTO {
    private Long id;
    private String name;
    private String intro;
    private String imgUrl;
}
