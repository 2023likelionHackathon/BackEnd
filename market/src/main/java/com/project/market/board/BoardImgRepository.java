package com.project.market.board;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BoardImgRepository extends JpaRepository<BoardImg, Long> {
    @Query(" select distinct i from BoardImg i " +
            "where i.board.id = :boardId ")
    List<BoardImg> findAllByBoardId(@Param("boardId") Long boardId);
}
