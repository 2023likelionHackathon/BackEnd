package com.project.market.market;

import com.project.market.board.Board;
import com.project.market.board.dto.response.BoardResponse;
import com.project.market.market.dto.response.MarketResponse;

import java.util.ArrayList;
import java.util.List;

public class MarketService {
    private MarketRepository marketRepository;
    public List<MarketResponse> selectAll() {
        List<Market> MarketList = marketRepository.findAll();
        List<MarketResponse> result = new ArrayList<>();
        MarketList.forEach(v->{
            result.add(select(v.getId()));
        });
        return result;
    }

}
