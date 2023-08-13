package com.project.market.controller;

import com.project.market.dto.BoardDTO;
import com.project.market.dto.StoreDTO;
import com.project.market.security.UserPrincipal;
import com.project.market.service.BoardService;
import com.project.market.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
    public ResponseEntity viewStore(@PathVariable("id") Long id, @AuthenticationPrincipal UserPrincipal loginUser){
        Map<String, Object> res = new HashMap<>();
        Map<String, Object> map = boardService.selectByStore(id, loginUser);
        res.put("boardList", map.get("boardList"));
        StoreDTO.Detail store = StoreDTO.Detail.builder()
                .id(id)
                .score((Double) map.get("store_avg")).build();
        res.put("store", store);
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }

    @GetMapping("/viewList")
    public ResponseEntity viewStoreList(){
        List<StoreDTO.Select> res = storeService.viewStoreList();
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }
}
