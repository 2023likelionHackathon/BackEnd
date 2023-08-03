package com.project.market.service;

import com.project.market.domain.Market;
import com.project.market.dto.MarketDTO;
import com.project.market.exception.NonExistentMarketException;
import com.project.market.exception.NonExistentRegionException;
import com.project.market.repository.MarketRepository;
import com.project.market.repository.RegionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
public class MarketService {
    private final RegionRepository regionRepository;
    private final MarketRepository marketRepository;

    public List<MarketDTO.Summary> viewAllByRegion(Long regionId) {
        List<Market> marketList = marketRepository.findAllByRegion(
                regionRepository.findById(regionId).orElseThrow(()->new NonExistentRegionException()));
        List<MarketDTO.Summary> res = new ArrayList<>();
        marketList.forEach(v->{
            res.add(v.toSummaryDto());
        });
        return res;
    }

    public MarketDTO.Detail viewMarket(Long marketId) {
        Market market = marketRepository.findMarketWithStores(marketId)
                .orElseThrow(()-> new NonExistentMarketException());
        return market.toDetailDto();
    }
}
