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
import java.util.Map;

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
    private int size_reply;
    @BatchSize(size = 1000)
    @JsonIgnore
    @OneToMany(mappedBy = "board", cascade = CascadeType.REMOVE)
    private List<BoardImg> boardImgList;

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
    public void addReply(){this.size_reply+=1;}

    public BoardDTO.Response toDTO(Map<String, Object> likes, List<String> imgList) {
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
                .size_reply(size_reply).build();
    }
}
