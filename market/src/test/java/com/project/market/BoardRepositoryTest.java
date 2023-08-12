package com.project.market;

import com.project.market.domain.Board;
import com.project.market.exception.NonExistentBoardException;
import com.project.market.repository.BoardRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@Slf4j
@DataJpaTest
public class BoardRepositoryTest {
    @Autowired
    private BoardRepository boardRepository;


    @Test
    @DisplayName("board에 조회되는 값 확인")
    void saveMember() {
        // given
        Board board = boardRepository.findBoardById(2L)
                .orElseThrow(()-> new NonExistentBoardException());
        log.info("userId = {}",board.getUser().getUserId());
        log.info("reply - {}", board.getReplyList().size());
        log.info("img = {}", board.getBoardImgList().size());
    }
}
