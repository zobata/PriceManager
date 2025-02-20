package com.bcnc.pricemanager.controller;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bcnc.pricemanager.model.PriceList;
import com.bcnc.pricemanager.service.PriceService;

@RestController
@RequestMapping("/prices")
public class PriceController {

    @Autowired
    private PriceService srv;

    @GetMapping
    public ResponseEntity<PriceList> getPrice(
            @RequestParam Long productId,
            @RequestParam Integer brandId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime applicationDate) {
    	
    	 Optional<PriceList> price = srv.getPrice(productId, brandId, applicationDate);
         
         return price.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    
    
}
