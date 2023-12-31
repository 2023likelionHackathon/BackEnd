package com.project.market.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.market.dto.BoardDTO;
import com.project.market.dto.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@DynamicInsert
public class User extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="user_id")
    private String userId;
    @Column(name="pw", nullable = true)
    private String pw;
    @Column(name="email", nullable = false)
    private String email;
    @Column(name="name", nullable = false)
    private String name;
    @Column(name="nickname", nullable = true)
    private String nickname;
    @Column(name="picture")
    private String picture;
    @Enumerated(EnumType.STRING)
    @Column(name="role", nullable = false)
    private Role role;
    @Column(nullable = false)
    private int reward;
    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    @OrderBy("id asc") // board 정렬
    private List<Board> boardList;

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

    public UserDTO.Profile toProfileDto() {
        return UserDTO.Profile.builder()
                .id(id)
                .userId(userId)
                .nickname(nickname)
                .reward(reward)
                .role(role.getTitle()).build();
    }

    public void updateReward() {
        this.reward += 500;
    }
}
