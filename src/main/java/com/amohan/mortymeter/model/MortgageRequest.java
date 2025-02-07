package com.amohan.mortymeter.model;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class MortgageRequest {

    private BigDecimal income;
    private int maturityPeriod;
    private BigDecimal loanValue;
    private BigDecimal homeValue;
}
