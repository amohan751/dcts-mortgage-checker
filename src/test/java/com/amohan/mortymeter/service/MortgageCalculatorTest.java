package com.amohan.mortymeter.service;

import com.amohan.mortymeter.model.MortgageRequest;
import com.amohan.mortymeter.model.MortgageResponse;
import com.amohan.mortymeter.model.entity.MortgageRate;
import com.amohan.mortymeter.repository.MortgageRateRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MortgageCalculatorTest {

    @Mock
    private MortgageRateRepository mortgageRateRepository;

    @InjectMocks
    private MortgageCalculator mortgageCalculator;


    @Test
    public void testCheckMortgageFeasible() {
        MortgageRequest request = new MortgageRequest();
        request.setIncome(BigDecimal.valueOf(100000));
        request.setMaturityPeriod(10);
        request.setLoanValue(BigDecimal.valueOf(200000));
        request.setHomeValue(BigDecimal.valueOf(250000));

        MortgageResponse response = mortgageCalculator.checkMortgageAndEligibility(request);
        assertTrue(response.isFeasible());
        assertNotEquals(BigDecimal.ZERO, response.getMonthlyCost());
    }

    @Test
    public void testCheckMortgageNotFeasible() {
        MortgageRequest request = new MortgageRequest();
        request.setIncome(BigDecimal.valueOf(100000));
        request.setMaturityPeriod(15);
        request.setLoanValue(BigDecimal.valueOf(500000));
        request.setHomeValue(BigDecimal.valueOf(450000));

        MortgageResponse response = mortgageCalculator.checkMortgageAndEligibility(request);
        assertFalse(response.isFeasible());
        assertEquals(BigDecimal.ZERO, response.getMonthlyCost());
    }
}
