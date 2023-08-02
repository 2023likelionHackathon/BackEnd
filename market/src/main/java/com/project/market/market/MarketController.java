package com.project.market.market;

import com.project.market.board.dto.response.BoardResponse;
import com.project.market.market.dto.response.MarketResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/market")
public class MarketController {
    private final MarketRepository marketRepository;
    private final MarketResponse marketResponse;
    private final MarketService marketService;
    @GetMapping("/viewAll")
    public ResponseEntity viewAll(){
        List<MarketResponse> marketList = marketService.selectAll();
        return ResponseEntity.status(HttpStatus.OK).body(marketList);
    }

}
