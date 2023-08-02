package com.project.market.board.dto;

import com.project.market.board.Board;
import com.project.market.board.Reply;
import com.project.market.user.User;
import lombok.*;

public class ReplyDTO {
    /** 댓글 Service 요청을 위한 DTO 클래스 */
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Request {
        private Long userId;
        private Long boardId;
        private String comment;
        /* Dto -> Entity */
        public Reply toEntity(User user, Board board) {
            Reply reply = Reply.builder()
                    .user(user)
                    .board(board)
                    .reply(comment)
                    .build();
            return reply;
        }
    }
    /**
     * 댓글 정보를 리턴할 응답(Response) 클래스
     * Entity 클래스를 생성자 파라미터로 받아 데이터를 Dto로 변환하여 응답
     * 별도의 전달 객체를 활용해 연관관계를 맺은 엔티티간의 무한참조를 방지
     */
    @RequiredArgsConstructor
    @Getter
    public static class Response {
        private Long replyId;
        private String comment;
        private String createdDate;
        private String nickname;
        private Long userId;
        private Long boardId;
        /* Entity -> Dto*/
        public Response(Reply reply) {
            this.replyId = reply.getId();
            this.boardId = reply.getBoard().getId();
            this.nickname = reply.getUser().getName();
            this.userId = reply.getUser().getId();
            this.comment = reply.getReply();
            this.createdDate = reply.getCreatedDate().toString();
        }
    }
}
