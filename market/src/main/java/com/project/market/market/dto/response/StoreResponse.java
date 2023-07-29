package com.project.market.market.dto.response;

import com.project.market.market.Market;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StoreResponse {
    private Long id;
    private Market mk_id;
    private String st_name;
    private String st_intro;
    private String st_address;
    private String st_num;
    private String st_time;
}
