package com.amohan.mortymeter.utils;

import com.amohan.mortymeter.model.MortgageRequest;

import java.math.BigDecimal;

public class MortgageRequestValidator {

    public static boolean isValidMortgageRequest(MortgageRequest request) {
        if (request == null) {
            return false;
        }

        BigDecimal income = request.getIncome();
        Integer maturityPeriod = request.getMaturityPeriod();
        BigDecimal loanValue = request.getLoanValue();
        BigDecimal homeValue = request.getHomeValue();

        return income != null && income.compareTo(BigDecimal.ZERO) > 0 &&
                maturityPeriod != null && maturityPeriod > 0 &&
                loanValue != null && loanValue.compareTo(BigDecimal.ZERO) > 0 &&
                homeValue != null && homeValue.compareTo(BigDecimal.ZERO) > 0;
    }
}
