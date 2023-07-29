package com.project.market.market.dto.response;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class RegionResponse {
    private Long id;
    private String rg_name;
}
