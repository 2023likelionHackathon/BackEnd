package com.project.market.controller;

import com.project.market.dto.MarketDTO;
import com.project.market.dto.RegionDTO;
import com.project.market.service.MarketService;
import com.project.market.service.RegionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/market")
public class MarketController {
    private final MarketService marketService;
    private final RegionService regionService;
    @GetMapping("/viewAll")
    public ResponseEntity viewAllByRegion(@RequestParam("regionId") Long regionId){
        Map<String, Object> res = new HashMap<>();
        List<RegionDTO> regions = regionService.viewAll();
        res.put("regions", regions);
        List<MarketDTO.Summary> markets = marketService.viewAllByRegion(regionId);
        res.put("markets", markets);
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }

    @GetMapping("/view/{id}")
    public ResponseEntity viewMarket(@PathVariable("id") Long id){
        MarketDTO.Detail res = marketService.viewMarket(id);
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }
}
