package com.project.market.market;

import com.project.market.board.Board;
import com.project.market.board.dto.BoardDTO;
import com.project.market.board.dto.ReplyDTO;
import com.project.market.market.dto.response.MarketResponse;

import java.util.ArrayList;
import java.util.List;

public class MarketService {
    private MarketRepository marketRepository;


    public BoardDTO.Resposnse select(Long marketId){
        Market market = marketRepository.findMarketWithReply(marketId);
        log.info("market",market.getMk_name());



}
    public List<MarketResponse> selectAll() {
        List<Market> marketList = marketRepository.findAll();
        List<MarketResponse> result = new ArrayList<>();
        marketList.forEach(v->{
            result.add(select(v.getId()));
        });
        return result;


}
