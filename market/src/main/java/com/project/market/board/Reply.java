package com.project.market.board;

import com.project.market.domain.BaseTimeEntity;
import com.project.market.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Entity(name = "reply")
public class Reply extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "board_id")
    private Board board;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user; // 작성자
    @Column(name = "comment_content", nullable = false)
    private String commentContent;
    /* 댓글 수정 */
    public void update(String comment) {
        this.commentContent = comment;
    }

}
