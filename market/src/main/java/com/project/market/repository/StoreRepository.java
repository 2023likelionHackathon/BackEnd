package com.project.market.repository;

import com.project.market.domain.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface StoreRepository extends JpaRepository<Store, Long> {
    @Query(" select s from Store s " +
            "where s.id = :shopId")
    Optional<Store> findByShopId(@Param("shopId") Long shopId);

    Optional<Store> findByCode(String code);
}
