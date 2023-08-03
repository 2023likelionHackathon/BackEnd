package com.project.market.service;

import com.project.market.domain.Board;
import com.project.market.domain.Reply;
import com.project.market.dto.ReplyDTO;
import com.project.market.exception.NonExistentBoardException;
import com.project.market.exception.NonExistentUserException;
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

    public String post(ReplyDTO.Request request, Long userId) {
        Board board = boardRepository.findById(request.getBoardId())
                .orElseThrow(()-> new NonExistentBoardException());
        User user = userRepository.findById(userId)
                .orElseThrow(()-> new NonExistentUserException());
        Reply reply = request.toEntity(user, board);
        replyRepository.save(reply);
        return "SUCCESS";
    }

    public String delete(Long id) {
        Reply reply = replyRepository.findById(id)
                .orElseThrow(()->new NonExistentBoardException());
        replyRepository.delete(reply);
        return "SUCCESS";
    }
}
