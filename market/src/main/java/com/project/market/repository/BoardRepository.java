package com.project.market.repository;

import com.project.market.domain.Board;
import com.project.market.dto.BoardWithAvg;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {

    @Query("select distinct b from Board b " +
            "left join fetch b.replyList r " +
            "where b.id = :boardId")
    Optional<Board> findBoardWithReply(@Param("boardId")Long boardId);

    @Query("select b from Board b " +
            "left join fetch b.user u " +
            "left join fetch b.store " +
            "where b.id = :boardId")
    Optional<Board> findBoardById(Long boardId);

    @Query("select b from Board b " +
            "left join fetch b.user u " +
            "where u.id = :userId ")
    List<Board> findByUserId(Long userId);

    @Query(" select new com.project.market.dto.BoardWithAvg(b, avg(b.score)) " +
            "from Board b " +
            //"left join fetch b.user u " + // Board 엔티티를 직접 access 할 때만 fetch join 할 수 있나봄
            "where b.store.id =:storeId ")
    List<BoardWithAvg> findBoardWithAvgByStoreId(@Param("storeId") Long storeId);

}
