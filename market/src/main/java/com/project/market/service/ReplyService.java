package com.project.market.service;

import com.project.market.domain.Board;
import com.project.market.domain.Reply;
import com.project.market.dto.ReplyDTO;
import com.project.market.exception.AuthenticationFailedException;
import com.project.market.exception.NonExistentBoardException;
import com.project.market.exception.NonExistentUserException;
import com.project.market.repository.BoardRepository;
import com.project.market.repository.ReplyRepository;
import com.project.market.domain.User;
import com.project.market.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.websocket.AuthenticationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@AllArgsConstructor
@Transactional
@Service
public class ReplyService {
    private ReplyRepository replyRepository;
    private BoardRepository boardRepository;
    private UserRepository userRepository;

    public List<ReplyDTO.Response> post(ReplyDTO.Request request, Long userId) {
        Board board = boardRepository.findById(request.getBoardId())
                .orElseThrow(()-> new NonExistentBoardException());
        User user = userRepository.findById(userId)
                .orElseThrow(()-> new NonExistentUserException());
        if(user.getRole().getTitle() == "상인"){
            Reply reply = request.toEntity(user, board, "사장님");
            replyRepository.save(reply);
        }else{
            Reply reply = request.toEntity(user, board, "이용자");
            replyRepository.save(reply);
        }
        return makeReplyList(board.getId());
    }

    public List<ReplyDTO.Response> delete(Long id, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(()-> new NonExistentUserException());
        Reply reply = replyRepository.findById(id)
                .orElseThrow(()->new NonExistentBoardException());
        if(!reply.getUser().equals(user)){
            throw new AuthenticationFailedException("자신이 작성한 글만 삭제 가능합니다.");
        }
        replyRepository.delete(reply);
        return makeReplyList(reply.getBoard().getId());
    }
    public List<ReplyDTO.Response> makeReplyList(Long boardId){
        List<Reply> replyList = replyRepository.findByBoardId(boardId);
        List<ReplyDTO.Response> res = new ArrayList<>();
        replyList.forEach(v->{
            res.add(new ReplyDTO.Response(v));
        });
        return res;
    }
}
