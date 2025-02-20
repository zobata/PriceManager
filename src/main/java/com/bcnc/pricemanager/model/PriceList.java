package com.bcnc.pricemanager.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class PriceList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "brand_id")
    private Brand brand;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Integer priority;
    private Double price;

    @ManyToOne
    @JoinColumn(name = "currency_code")
    private Currency currency;

}
