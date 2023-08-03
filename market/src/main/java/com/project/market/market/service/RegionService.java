package com.project.market.market.service;

import com.project.market.market.Region;
import com.project.market.market.dto.RegionDTO;
import com.project.market.market.repository.RegionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
public class RegionService {
    private final RegionRepository regionRepository;


    public List<RegionDTO> viewAll() {
        List<Region> regions = regionRepository.findAll();
        List<RegionDTO> res = new ArrayList<>();
        regions.forEach(v->{
            res.add(v.toDTO());
        });
        return res;
    }
}
