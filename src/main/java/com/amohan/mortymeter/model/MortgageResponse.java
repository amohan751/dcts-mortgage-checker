package com.amohan.mortymeter.model;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class MortgageResponse {

    private boolean feasible;
    private BigDecimal monthlyCost;
}
