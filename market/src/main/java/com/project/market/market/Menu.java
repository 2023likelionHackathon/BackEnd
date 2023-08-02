package com.project.market.market;

import javax.persistence.*;
import lombok.*;
import java.io.Serializable;
@Entity
@Table(name = "Menu")
@Getter @Setter
@NoArgsConstructor
@IdClass(Menu.MenuId.class)
public class Menu {

    @Id
    private int id;

    @Id
    @ManyToOne
    @JoinColumn(name = "store_id")
    private Store store;

    @Column(name = "menu_name")
    private String menu_name;

    @Column(name = "menu_intro")
    private String menu_intro;

    @AllArgsConstructor
    @NoArgsConstructor
    public static class MenuId implements Serializable {

        private int id;
        private int storeId;


    }
}
