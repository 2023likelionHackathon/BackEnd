package com.project.market.repository;

import com.project.market.domain.Reply;
import com.project.market.dto.ReplyDTO;

import java.util.List;

public interface ReplyRepositoryCustom {
    List<ReplyDTO.Response> getReplyListByBoard(Long boardId);
}
