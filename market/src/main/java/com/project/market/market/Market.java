package com.project.market.market;
import javax.persistence.*;

@Entity
@Table(name = "market")
public class Market {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "rg_id", nullable = false)
    private int rg_Id;

    @Column(name = "mk_name")
    private String mk_name;

    @Column(name = "mk_intro")
    private String mk_intro;

    @Column(name = "mk_address")
    private String mk_address;

    @Column(name = "mk_num")
    private String mk_num;

    @Column(name = "mk_close")
    private String mk_close;

    @Column(name = "mk_parking")
    private String mk_parking;


}
