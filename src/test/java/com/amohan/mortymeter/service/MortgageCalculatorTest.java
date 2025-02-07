package com.amohan.mortymeter.service;

import com.amohan.mortymeter.model.MortgageRequest;
import com.amohan.mortymeter.model.MortgageResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@ActiveProfiles("test")
public class MortgageCalculatorTest {

    @Autowired
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
