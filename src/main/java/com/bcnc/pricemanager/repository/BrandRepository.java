package com.bcnc.pricemanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bcnc.pricemanager.model.Brand;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Integer> {
}
