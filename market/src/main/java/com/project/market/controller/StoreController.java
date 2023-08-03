package com.project.market.controller;

import com.project.market.dto.BoardDTO;
import com.project.market.dto.StoreDTO;
import com.project.market.service.BoardService;
import com.project.market.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/store")
public class StoreController {
    private final StoreService storeService;
    private final BoardService boardService;
    @GetMapping("/view/{id}")
    public ResponseEntity viewStore(@PathVariable("id") Long id){
        Map<String, Object> res = new HashMap<>();
        StoreDTO.Detail store = storeService.viewStore(id);
        res.put("store", store);
        List<BoardDTO.Response> boardList = boardService.selectByStore(id);
        res.put("boardList", boardList);
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }
}
