package com.project.market.repository;

import com.project.market.domain.BoardLike;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardLikeRepository extends JpaRepository<BoardLike, Long> {
    BoardLike findByBoardIdAndUserId(Long id, long l);

    boolean existsByBoardIdAndUserId(Long id, Long userId);
}
