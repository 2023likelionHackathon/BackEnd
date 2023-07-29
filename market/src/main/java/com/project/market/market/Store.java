package com.project.market.market;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
@Slf4j
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="store")
public class Store {
    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name = "mk_id", nullable = false)
    private Market mk_id;

    @Column(name = "st_name")
    private String st_name;

    @Column(name = "st_intro")
    private String st_intro;

    @Column(name = "st_address")
    private String st_address;

    @Column(name = "st_num")
    private String st_num;

    @Column(name = "st_time")
    private String st_time;
}
