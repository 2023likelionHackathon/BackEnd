package com.project.market.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.market.dto.BoardDTO;
import com.project.market.dto.ReplyDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.BatchSize;

import javax.persistence.*;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;
    @Column(name = "content")
    private String content;
    private Double score;
    private int likes;
    @BatchSize(size = 1000)
    @JsonIgnore
    @OneToMany(mappedBy = "board", cascade = CascadeType.REMOVE)
    private List<BoardImg> boardImgList;

    @BatchSize(size = 1000)
    @JsonIgnore
    @OneToMany(mappedBy = "board", cascade = CascadeType.REMOVE)
    @OrderBy("id asc") // 댓글 정렬
    private List<Reply> replyList;

    public void deletelike(){
        this.likes-=1;
    }
    public void addlike() {
        this.likes += 1;
    }

    public BoardDTO.Response toDTO(List<ReplyDTO.Response> replylist, List<String> imgList) {
        return BoardDTO.Response.builder()
                .boardId(id)
                .userId(user.getId())
                .writer(user.getNickname())
                .storeId(store.getId())
                .storeName(store.getName())
                .content(content)
                .score(score)
                .likes(likes)
                .imgUrlList(imgList)
                .createdDate(this.getCreatedDate().toString())
                .replyList(replylist).build();
    }
}
