package com.project.market.market;

import javax.persistence.*;

import com.project.market.board.Board;
import lombok.*;

@Entity
@Table(name = "store_img")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Store_Img {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "st_id", nullable = false)
    private Store store;

    @Column(name = "image_url")
    private String image_url;
    public Store_Img(String image_url, Store store) {
        this.image_url = image_url;
        this.store = store;
    }


}
