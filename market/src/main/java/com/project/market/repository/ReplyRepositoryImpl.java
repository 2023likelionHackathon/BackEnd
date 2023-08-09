package com.project.market.repository;

import com.project.market.domain.QReply;
import com.project.market.domain.Reply;
import com.project.market.dto.ReplyDTO;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.project.market.domain.QReply.reply1;

@Slf4j
@RequiredArgsConstructor
public class ReplyRepositoryImpl implements ReplyRepositoryCustom{
    private final JPAQueryFactory jpaQueryFactory;
    @Override
    public List<ReplyDTO.Response> getReplyListByBoard(Long boardId) {
        List<Reply> replyList = jpaQueryFactory.selectFrom(reply1)
                .leftJoin(reply1.parent)
                .fetchJoin()
                .where(reply1.board.id.eq(boardId))
                .orderBy(reply1.parent.id.asc().nullsFirst(), reply1.createdDate.asc())
                .fetch();
        List<ReplyDTO.Response> res = new ArrayList<>();
        Map<Long, ReplyDTO.Response> replyDTOHashMap = new HashMap<>();
        replyList.forEach(v->{
            ReplyDTO.Response replyDTO = new ReplyDTO.Response(v);
            log.info("replyDTO = {}", replyDTO.getReplyId());
            replyDTOHashMap.put(replyDTO.getReplyId(), replyDTO);
            if(v.getParent()!=null) {
                replyDTOHashMap.get(v.getParent().getId()).getChildren().add(replyDTO);
            }else{
                res.add(replyDTO);
            }
        });
        return res;
    }
}
