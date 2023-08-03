package com.project.market.repository;

import com.project.market.domain.Market;
import com.project.market.domain.Region;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MarketRepository extends JpaRepository<Market, Long> {
    List<Market> findAllByRegion(Region region);

    @Query(" select m from Market m " +
            "left join fetch m.storeList " +
            "where m.id = :marketId")
    Optional<Market> findMarketWithStores(@Param("marketId") Long marketId);
}
