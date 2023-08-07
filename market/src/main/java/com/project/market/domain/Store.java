package com.project.market.domain;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.market.dto.MenuDTO;
import com.project.market.dto.StoreDTO;
import lombok.*;
import org.hibernate.annotations.BatchSize;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "market_id")
    private Market market;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "merchant_id")
    private User user;

    private String name;

    private String intro;

    private String address;

    private String num;

    private String time;
    private String imgUrl;
    private String code;
    @JsonIgnore
    @BatchSize(size = 1000)
    @OneToMany(mappedBy = "store")
    private List<Menu> menuList;
    @JsonIgnore
    @BatchSize(size = 1000)
    @OneToMany(mappedBy = "store")
    private List<Board> boardList;

    public Store setMerchant(User user){
        this.user = user;
        return this;
    }

    public StoreDTO.Summary toSummaryDto() {
        return StoreDTO.Summary.builder()
                .id(id)
                .name(name)
                .intro(intro)
                .imgUrl(imgUrl).build();
    }

    public StoreDTO.Detail toDetailDto(Double avg) {
        List<MenuDTO> menuDtos = new ArrayList<>();
        menuList.forEach(v->{
            menuDtos.add(v.toDTO());
        });
        return StoreDTO.Detail.builder()
                .id(id)
                .name(name)
                .intro(intro)
                .imgUrl(imgUrl)
                .score(avg)
                .menuList(menuDtos)
                .build();
    }
}
