package com.project.market.market;

import javax.persistence.*;

import com.project.market.board.Board;
import lombok.*;

@Entity
@Table(name = "market_img")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Market_Img {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "mk_id", nullable = false)
    private Market market;

    @Column(name = "image_url")
    private String image_url;

    public Market_Img(String image_url, Market market) {
        this.image_url = image_url;
        this.market = market;
    }


}
