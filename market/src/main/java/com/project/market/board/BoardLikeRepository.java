package com.project.market.board;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardLikeRepository extends JpaRepository<BoardLike, Long> {
    BoardLike findByBoardIdAndUserId(Long id, long l);
}
