package com.bcnc.pricemanager.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bcnc.pricemanager.model.PriceList;

@Repository
public interface PriceListRepository extends JpaRepository<PriceList, Long> {
	List<PriceList> findByProductProductIdAndBrandBrandIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(Long productId, Integer brandId, LocalDateTime startDate, LocalDateTime endDate);
	
    @Query("""
            SELECT pl 
            FROM PriceList pl
            INNER JOIN pl.brand b
            INNER JOIN pl.product p
            INNER JOIN pl.currency c
            WHERE 
                p.id = :productId
                AND b.id = :brandId
                AND :applicationDate BETWEEN pl.startDate AND pl.endDate
            ORDER BY 
                pl.priority DESC
            LIMIT 1
        """)
		Optional<PriceList> findApplicablePrice(
		        @Param("productId") Long productId,
		        @Param("brandId") Integer brandId,
		        @Param("applicationDate") LocalDateTime applicationDate);
}
