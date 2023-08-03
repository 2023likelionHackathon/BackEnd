package com.project.market.market;
import com.project.market.market.dto.MarketDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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

    @OneToMany(mappedBy = "market")
    private List<Store> storeList;

    public MarketDTO.Main toDTO() {
        return MarketDTO.Main.builder()
                .id(id)
                .name(name)
                .intro(intro)
                .address(address).build();
    }
}
