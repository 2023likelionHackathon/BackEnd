package com.project.market.repository;

import com.project.market.domain.Reply;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReplyRepository extends JpaRepository<Reply, Long>, ReplyRepositoryCustom {
    List<Reply> findByBoardId(Long id);
}
