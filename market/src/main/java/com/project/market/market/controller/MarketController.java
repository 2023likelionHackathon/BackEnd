package com.project.market.market.controller;

import com.project.market.market.dto.MarketDTO;
import com.project.market.market.dto.RegionDTO;
import com.project.market.market.service.MarketService;
import com.project.market.market.service.RegionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Controller
@RequestMapping("/market")
public class MarketController {
    private final MarketService marketService;
    private final RegionService regionService;
    @GetMapping("/viewAll")
    public ResponseEntity viewAllByRegion(@RequestParam("regionId") Long regionId){
        Map<String, Object> res = new HashMap<>();
        List<RegionDTO> regions = regionService.viewAll();
        res.put("regions", regions);
        List<MarketDTO.Main> markets = marketService.viewAllByRegion(regionId);
        res.put("markets", markets);
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }
}
