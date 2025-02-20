package com.bcnc.pricemanager.service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bcnc.pricemanager.model.PriceList;
import com.bcnc.pricemanager.repository.PriceListRepository;

@Service
public class PriceService {
	@Autowired
    private PriceListRepository repository;

    public Optional<PriceList> getApplicablePrice(Long productId, Integer brandId, LocalDateTime applicationDate) {
        List<PriceList> prices = repository.findByProductProductIdAndBrandBrandIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(productId, brandId, applicationDate, applicationDate);
        return prices.stream().max(Comparator.comparingInt(PriceList::getPriority));
    }
    
    public Optional<PriceList> getPrice(Long productId, Integer brandId, LocalDateTime applicationDate){
    	return repository.findApplicablePrice(productId, brandId, applicationDate);
    }
}
