package com.project.market.market;

import javax.persistence.*;
import lombok.*;

@Entity
@Table(name = "menu_img")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Menu_Img {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "menu_id", nullable = false)
    private Menu menu;

    @Column(name = "image_url")
    private String image_url;

    public Menu_Img(String image_url, Menu menu) {
        this.image_url = image_url;
        this.menu = menu;
    }
}
