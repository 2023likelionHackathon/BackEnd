package com.project.market.board;

import com.project.market.board.dto.BoardDTO;
import com.project.market.board.dto.ReplyDTO;
import com.project.market.domain.BaseTimeEntity;
import com.project.market.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
@Slf4j
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Entity
public class Board extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "content")
    private String content;
    private Double score;
    private int likes;
    @OneToMany(mappedBy = "board", cascade = CascadeType.REMOVE)
    private List<BoardImg> boardImgList;

    @OneToMany(mappedBy = "board", cascade = CascadeType.REMOVE)
    @OrderBy("id asc") // 댓글 정렬
    private List<Reply> replyList;

    public void deletelike(){
        this.likes-=1;
    }
    public void addlike() {
        this.likes += 1;
    }

    public BoardDTO.Resposnse toDTO(List<ReplyDTO.Response> replylist, List<String> imgList) {
        log.info("createdDate = ", getCreatedDate().toString());
        return BoardDTO.Resposnse.builder()
                .boardId(id)
                .userId(user.getId())
                .title(title)
                .content(content)
                .score(score)
                .likes(likes)
                .imgUrlList(imgList)
                .createdDate(this.getCreatedDate().toString())
                .replyList(replylist).build();
    }
}
