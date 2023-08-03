package com.project.market.market.repository;

import com.project.market.market.Market;
import com.project.market.market.Region;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface MarketRepository extends JpaRepository<Market, Long> {
    List<Market> findAllByRegion(Region region);
}
