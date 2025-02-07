package com.amohan.mortymeter.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
@Entity
@AllArgsConstructor
public class MortgageRate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private int maturityPeriod;
    private BigDecimal interestRate;
    private Timestamp lastUpdate;

    // No-argument constructor for H2
    public MortgageRate() {
    }
}
