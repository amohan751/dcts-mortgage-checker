package com.amohan.mortymeter.service;

import com.amohan.mortymeter.model.MortgageRequest;
import com.amohan.mortymeter.model.MortgageResponse;
import com.amohan.mortymeter.model.entity.MortgageRate;
import com.amohan.mortymeter.repository.MortgageRateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.math.RoundingMode;


@Service
public class MortgageCalculator {

    @Autowired
    private MortgageRateRepository mortgageRateRepository;

    public MortgageResponse checkMortgageAndEligibility(MortgageRequest mortgageRequest){

        BigDecimal income = mortgageRequest.getIncome();
        BigDecimal loanValue = mortgageRequest.getLoanValue();
        BigDecimal homeValue = mortgageRequest.getHomeValue();
        BigDecimal monthlyCost = BigDecimal.ZERO;
        int maturityPeriod = mortgageRequest.getMaturityPeriod();

        final int INCOME_MULTIPLIER = 4;
        boolean feasible = loanValue.compareTo(income.multiply(BigDecimal.valueOf(INCOME_MULTIPLIER))) <=0 &&
                            loanValue.compareTo(homeValue) <=0;
        if(feasible){
            MortgageRate rate = mortgageRateRepository.findByMaturityPeriod(mortgageRequest.getMaturityPeriod());
            BigDecimal monthlyInterestRate = rate.getInterestRate();
            monthlyCost = loanValue.multiply(
                            monthlyInterestRate.multiply((BigDecimal.ONE.add(monthlyInterestRate)).pow(maturityPeriod))
                            .divide((BigDecimal.ONE.add(monthlyInterestRate).pow(maturityPeriod)).subtract(BigDecimal.ONE),2, RoundingMode.HALF_UP)
                            );
        }

        MortgageResponse mortgageResponse = new MortgageResponse();
        mortgageResponse.setFeasible(feasible);
        mortgageResponse.setMonthlyCost(monthlyCost);
        return mortgageResponse;
    }
}
