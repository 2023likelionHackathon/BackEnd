package com.project.market.domain;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.market.dto.MarketDTO;
import com.project.market.dto.StoreDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Getter
public class Market {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "region_id")
    private Region region;

    private String name;

    private String intro;

    private String address;

    private String num;

    private String close;

    private String parking;

    private String imgUrl;
    @JsonIgnore
    @OneToMany(mappedBy = "market")
    private List<Store> storeList;

    public MarketDTO.Summary toSummaryDto() {
        return MarketDTO.Summary.builder()
                .id(id)
                .name(name)
                .intro(intro)
                .address(address).build();
    }

    public MarketDTO.Detail toDetailDto() {
        List<StoreDTO.Summary> summaryList = new ArrayList<>();
        storeList.forEach(v->{
            summaryList.add(v.toSummaryDto());
        });
        return MarketDTO.Detail.builder()
                .id(id)
                .name(name)
                .intro(intro)
                .address(address)
                .imgUrl(imgUrl)
                .storeList(summaryList).build();
    }
}
