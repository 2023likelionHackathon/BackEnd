package com.project.market.service;

import com.project.market.domain.Board;
import com.project.market.domain.Reply;
import com.project.market.dto.ReplyDTO;
import com.project.market.repository.BoardRepository;
import com.project.market.repository.board.ReplyRepository;
import com.project.market.domain.User;
import com.project.market.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Slf4j
@AllArgsConstructor
@Transactional
@Service
public class ReplyService {
    private ReplyRepository replyRepository;
    private BoardRepository boardRepository;
    private UserRepository userRepository;

    public String post(ReplyDTO.Request request) {
        Board board = boardRepository.findById(request.getBoardId())
                .orElseThrow(()-> new RuntimeException("존재하지 않는 게시글입니다."));
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(()-> new RuntimeException("존재하지 않는 사용자입니다."));
        Reply reply = request.toEntity(user, board);
        replyRepository.save(reply);
        return "SUCCESS";
    }

    public String delete(Long id) {
        Reply reply = replyRepository.findById(id)
                .orElseThrow(()->new RuntimeException("존재하지 않는 게시글입니다."));
        replyRepository.delete(reply);
        return "SUCCESS";
    }
}
