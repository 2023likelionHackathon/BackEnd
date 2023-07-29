package com.project.market.market.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MarketResponse {
    private Long id;
    private int rgId;
    private String mk_name;
    private String mk_intro;
    private String mk_address;
    private String mk_num;
    private String mk_close;
    private String mk_parking;
}
