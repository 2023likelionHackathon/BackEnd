package com.project.market.controller;

import com.project.market.dto.StoreDTO;
import com.project.market.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RequiredArgsConstructor
@RestController
@RequestMapping("/store")
public class StoreController {
    private final StoreService storeService;
    @GetMapping("/view/{id}")
    public ResponseEntity viewStore(@PathVariable("id") Long id){
        StoreDTO.Detail res = storeService.viewStore(id);
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }
}
