package com.project.market.domain;
import javax.persistence.*;

import com.project.market.dto.MenuDTO;
import com.project.market.dto.StoreDTO;
import lombok.*;

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
    @ManyToOne
    @JoinColumn(name = "market_id")
    private Market market;

    private String name;

    private String intro;

    private String address;

    private String num;

    private String time;
    private String imgUrl;
    @OneToMany(mappedBy = "store")
    private List<Menu> menuList;

    @OneToMany(mappedBy = "store")
    private List<Board> boardList;

    public StoreDTO.Summary toSummaryDto() {
        return StoreDTO.Summary.builder()
                .id(id)
                .name(name)
                .intro(intro)
                .imgUrl(imgUrl).build();
    }

    public StoreDTO.Detail toDetailDto() {
        List<MenuDTO> menuDtos = new ArrayList<>();
        menuList.forEach(v->{
            menuDtos.add(v.toDTO());
        });
        return StoreDTO.Detail.builder()
                .id(id)
                .name(name)
                .intro(intro)
                .imgUrl(imgUrl)
                .menuList(menuDtos)
                .build();
    }
}
