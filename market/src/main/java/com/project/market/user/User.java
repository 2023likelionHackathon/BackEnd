package com.project.market.user;

import com.project.market.board.Board;
import com.project.market.board.BoardImg;
import com.project.market.board.dto.BoardDTO;
import com.project.market.domain.BaseTimeEntity;
import com.project.market.user.dto.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;


@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
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

    public UserDTO.Response toDTO() {
        return UserDTO.Response.builder()
                .id(id)
                .userId(userId)
                .nickname(nickname)
                .role(role.getTitle()).build();
    }
}
