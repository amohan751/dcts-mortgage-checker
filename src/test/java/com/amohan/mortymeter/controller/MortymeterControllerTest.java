package com.amohan.mortymeter.controller;

import com.amohan.mortymeter.model.MortgageRequest;
import com.amohan.mortymeter.model.MortgageResponse;
import com.amohan.mortymeter.model.entity.MortgageRate;
import com.amohan.mortymeter.service.ListMortgageRate;
import com.amohan.mortymeter.service.MortgageCalculator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Arrays;

import static org.mockito.Mockito.when;

public class MortymeterControllerTest {

    @Mock
    private ListMortgageRate listMortgageRate;
    @Mock
    private MortgageCalculator mortgageCalculator;
    @InjectMocks
    private MortymeterController mortymeterController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(mortymeterController).build();
    }

    @Test
    public void testGetInterestRates() throws Exception {
        MortgageRate rate1 = new MortgageRate(1L,10, BigDecimal.valueOf(5.50), new Timestamp(System.currentTimeMillis()));
        MortgageRate rate2 = new MortgageRate(2L,15, BigDecimal.valueOf(4.70), new Timestamp(System.currentTimeMillis()));
        MortgageRate rate3 = new MortgageRate(3L,20, BigDecimal.valueOf(4.20), new Timestamp(System.currentTimeMillis()));
        when(listMortgageRate.fetchMortgageRates()).thenReturn(Arrays.asList(rate1, rate2, rate3));

        mockMvc.perform(get("/api/interest-rates")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("[{'id':1,'maturityPeriod':10,'interestRate':5.50},{'id':2,'maturityPeriod':15,'interestRate':4.70},{'id':3,'maturityPeriod':20,'interestRate':4.20}]"));
    }

    @Test
    public void testCheckMortgage() throws Exception {
        MortgageRequest request = new MortgageRequest();
        request.setIncome(BigDecimal.valueOf(100000));
        request.setMaturityPeriod(10);
        request.setLoanValue(BigDecimal.valueOf(200000));
        request.setHomeValue(BigDecimal.valueOf(250000));

//        MortgageResponse response = new MortgageResponse();
//        response.setFeasible(true);
//        response.setMonthlyCost(BigDecimal.valueOf(1500.00));
//
//        when(mortgageCalculator.checkMortgageAndEligibility(request)).thenReturn(response);

        mockMvc.perform(post("/api/mortgage-check")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"income\":100000,\"maturityPeriod\":10,\"loanValue\":200000,\"homeValue\":250000}")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{'feasible':true,'monthlyCost':1500.00}"));
    }


}
