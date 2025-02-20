package com.bcnc.pricemanager.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Currency {
    @Id
    private String currencyCode;
    private String currencyName;

}