package com.project.market.dto;

import com.project.market.domain.Board;
import com.project.market.domain.Reply;
import com.project.market.domain.User;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

public class ReplyDTO {
    /** 댓글 Service 요청을 위한 DTO 클래스 */
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Request {
        private Long boardId;
        private Long parentId;
        private String comment;
        /* Dto -> Entity */
        public Reply toEntity(User user, Board board, String role) {
            Reply reply = Reply.builder()
                    .user(user)
                    .board(board)
                    .reply(comment)
                    .role(role)
                    .build();
            return reply;
        }
    }
    /**
     * 댓글 정보를 리턴할 응답(Response) 클래스
     * Entity 클래스를 생성자 파라미터로 받아 데이터를 Dto로 변환하여 응답
     * 별도의 전달 객체를 활용해 연관관계를 맺은 엔티티간의 무한참조를 방지
     */
    @Data
    public static class Response {
        private Long replyId;
        private Long userId;
        private String writer;
        private String writer_pic;
        private String role;
        private String comment;
        private List<ReplyDTO.Response> children = new ArrayList<>();
        private String createdDate;
        /* Entity -> Dto*/
        public Response(Reply reply) {
            this.replyId = reply.getId();
            this.userId = reply.getUser().getId();
            this.writer = reply.getUser().getNickname();
            this.writer_pic = reply.getUser().getPicture();
            this.role = reply.getRole();
            this.comment = reply.getReply();
            this.createdDate = reply.getCreatedDate().toString();
        }
    }
}
