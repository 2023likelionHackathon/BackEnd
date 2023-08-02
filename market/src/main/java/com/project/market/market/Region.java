package com.project.market.market;
import lombok.*;

import javax.persistence.*;
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "region")
public class Region {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "rg_name")
    private String rg_name;
}
