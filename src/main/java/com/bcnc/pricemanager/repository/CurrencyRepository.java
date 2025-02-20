package com.bcnc.pricemanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bcnc.pricemanager.model.Currency;

@Repository
public interface CurrencyRepository extends JpaRepository<Currency, String> {
}
