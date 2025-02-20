package com.bcnc.pricemanager.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Product {
    @Id
    private Long productId;
    private String name;
    private String description;
}
