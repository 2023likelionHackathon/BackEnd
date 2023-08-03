package com.project.market.service;

import com.project.market.domain.Store;
import com.project.market.dto.StoreDTO;
import com.project.market.exception.NonExistentStoreException;
import com.project.market.repository.BoardRepository;
import com.project.market.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional
public class StoreService {
    private final StoreRepository storeRepository;
    private final BoardRepository boardRepository;

    public StoreDTO.Detail viewStore(Long shopId) {
        Store store = storeRepository.findByShopId(shopId)
                .orElseThrow(()-> new NonExistentStoreException());
        Double avg = boardRepository.findAvg(shopId);
        return store.toDetailDto(avg);
    }
}
