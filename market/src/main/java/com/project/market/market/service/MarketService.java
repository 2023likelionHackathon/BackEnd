package com.project.market.market.service;

import com.project.market.market.Market;
import com.project.market.market.Region;
import com.project.market.market.dto.MarketDTO;
import com.project.market.market.repository.MarketRepository;
import com.project.market.market.repository.RegionRepository;
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

    public List<MarketDTO.Main> viewAllByRegion(Long regionId) {
        List<Market> marketList = marketRepository.findAllByRegion(
                regionRepository.findById(regionId).orElseThrow(()->new RuntimeException("해당 지역에 대한 데이터는 존재하지 않습니다.")));
        List<MarketDTO.Main> res = new ArrayList<>();
        marketList.forEach(v->{
            res.add(v.toDTO());
        });
        return res;
    }
}
