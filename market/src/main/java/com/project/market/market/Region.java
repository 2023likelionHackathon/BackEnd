package com.project.market.market;
import com.project.market.market.dto.RegionDTO;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Entity
public class Region {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "region")
    private List<Market> marketList;

    public RegionDTO toDTO() {
        return RegionDTO.builder()
                .id(id)
                .name(name).build();
    }
}
