package com.project.market.market.repository;

import com.project.market.market.Region;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegionRepository extends JpaRepository<Region, Long> {
    Region findByname(String region);
}
