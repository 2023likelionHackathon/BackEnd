package com.project.market.user;

import com.project.market.domain.BaseTimeEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name = "user")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class User extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="user_id")
    private String userId;
    @Column(name="email", nullable = false)
    private String email;
    @Column(name="pw", nullable = true)
    private String pw;
    @Column(name="name", nullable = false)
    private String name;
    @Column(name="nickname", nullable = true)
    private String nickname;
    @Column(name="picture")
    private String picture;
    @Enumerated(EnumType.STRING)
    @Column(name="role", nullable = false)
    private Role role;

    @Builder
    public User(String name, String email, String picture, Role role){
        this.name = name;
        this.email = email;
        this.picture = picture;
        this.role = role;
    }

    public User update(String name, String picture){
        this.name = name;
        this.picture = picture;
        return this;
    }
    public String getRoleKey(){
        return this.role.getKey();
    }
}
