package com.project.market.service;

import com.project.market.domain.Store;
import com.project.market.dto.StoreDTO;
import com.project.market.exception.NonExistentStoreException;
import com.project.market.repository.BoardRepository;
import com.project.market.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
public class StoreService {
    private final StoreRepository storeRepository;
    private final BoardRepository boardRepository;

    public StoreDTO.Detail viewStore(Long shopId, Double avg) {
        Store store = storeRepository.findByShopId(shopId)
                .orElseThrow(()-> new NonExistentStoreException());
        // Double avg = boardRepository.findAvg(shopId);
        return store.toDetailDto(avg);
    }

    public List<StoreDTO.Select> viewStoreList() {
        List<Store> storeList = storeRepository.findAll();
        List<StoreDTO.Select> res = new ArrayList<>();
        storeList.forEach(v->{
            res.add(new StoreDTO.Select(v));
        });
        return res;
    }
}
