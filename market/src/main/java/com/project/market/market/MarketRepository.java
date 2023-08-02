package com.project.market.market;

import com.project.market.board.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MarketRepository extends JpaRepository<Market, Long> {

    @Query("select distinct b from Board b " +
            "left join fetch b.replyList r " +
            "where b.id = :boardId")
    Market findMarketWithReply(@Param("marketId")Long marketId);

}
