package com.project.market.market;
import javax.persistence.*;
import lombok.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
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

}
