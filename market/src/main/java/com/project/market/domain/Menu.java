package com.project.market.domain;

import javax.persistence.*;

import com.project.market.dto.MenuDTO;
import lombok.*;
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "store_id")
    private Store store;

    private String name;
    private String intro;
    private String imgUrl;

    public MenuDTO toDTO() {
        return MenuDTO.builder()
                .id(id)
                .name(name)
                .intro(intro)
                .imgUrl(imgUrl).build();
    }
}
