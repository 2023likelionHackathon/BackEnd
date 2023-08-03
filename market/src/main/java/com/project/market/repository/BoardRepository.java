package com.project.market.repository;

import com.project.market.domain.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {
    @Query("select distinct b from Board b " +
            "left join fetch b.replyList r " +
            "where b.id = :boardId")
    Board findBoardWithReply(@Param("boardId")Long boardId);
}