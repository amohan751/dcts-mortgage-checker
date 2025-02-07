package com.amohan.mortymeter.service;

import com.amohan.mortymeter.model.MortgageRequest;
import com.amohan.mortymeter.model.MortgageResponse;
import com.amohan.mortymeter.model.entity.MortgageRate;
import com.amohan.mortymeter.repository.MortgageRateRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.math.RoundingMode;

import static com.amohan.mortymeter.constants.MortgageConstants.INCOME_MULTIPLIER;
import static com.amohan.mortymeter.constants.MortgageConstants.ONE_HUNDRED;
import static com.amohan.mortymeter.constants.MortgageConstants.MONTH_IN_AN_YEAR;


@Service
@Slf4j
public class MortgageCalculator {

    @Autowired
    private MortgageRateRepository mortgageRateRepository;

    public MortgageResponse checkMortgageAndEligibility(MortgageRequest mortgageRequest){

        boolean feasible = false;
        BigDecimal monthlyCost = BigDecimal.ZERO;
        try {
            BigDecimal income = mortgageRequest.getIncome();
            BigDecimal loanValue = mortgageRequest.getLoanValue();
            BigDecimal homeValue = mortgageRequest.getHomeValue();
            int maturityPeriod = mortgageRequest.getMaturityPeriod();

            feasible = loanValue.compareTo(income.multiply(BigDecimal.valueOf(INCOME_MULTIPLIER))) <= 0 &&
                    loanValue.compareTo(homeValue) <= 0;
            if (feasible) {
                MortgageRate rate = mortgageRateRepository.findByMaturityPeriod(mortgageRequest.getMaturityPeriod());
                BigDecimal interestRate = rate.getInterestRate().divide(ONE_HUNDRED);
                BigDecimal monthlyInterestRate = interestRate.divide(MONTH_IN_AN_YEAR,15, RoundingMode.HALF_UP);
                
                BigDecimal numerator = monthlyInterestRate.multiply((BigDecimal.ONE.add(monthlyInterestRate)).pow(maturityPeriod));
                BigDecimal denominator = (BigDecimal.ONE.add(monthlyInterestRate)).pow(maturityPeriod).subtract(BigDecimal.ONE);
                monthlyCost = loanValue.multiply(numerator).divide(denominator, 2, RoundingMode.HALF_UP);
            }
        } catch (Exception e) {
            log.error("An error occurred while calculating the mortgage: {}", e.getMessage(), e);
        }
            MortgageResponse mortgageResponse = new MortgageResponse();
            mortgageResponse.setFeasible(feasible);
            mortgageResponse.setMonthlyCost(monthlyCost);
            return mortgageResponse;
    }
}
