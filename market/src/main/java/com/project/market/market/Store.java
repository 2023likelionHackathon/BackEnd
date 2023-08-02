package com.project.market.market;
import javax.persistence.*;
import lombok.*;
import java.io.Serializable;
@Entity
@Table(name = "store")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@IdClass(Store.StoreId.class)
public class Store {

    @Id
    private int id;

    @Id
    @ManyToOne
    @JoinColumn(name = "mk_id")
    private Market market;

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

    @Column(name = "code")
    private String code;



    @AllArgsConstructor
    @NoArgsConstructor
    public static class StoreId implements Serializable {

        private int id;
        private int mkId;


    }
}
