package com.amohan.mortymeter.controller;

import com.amohan.mortymeter.model.MortgageRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.math.BigDecimal;


@SpringBootTest
@AutoConfigureMockMvc
public class MortymeterControllerTest {

    @Autowired
    private MockMvc mockMvc;

   
    @Test
    public void testGetInterestRates() throws Exception {
        
        mockMvc.perform(get("/api/interest-rates")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("[{'id':1,'maturityPeriod':10,'interestRate':5.50},{'id':2,'maturityPeriod':15,'interestRate':4.70},{'id':3,'maturityPeriod':20,'interestRate':4.20}]"));
    }

    @Test
    public void testCheckMortgage() throws Exception {
        MortgageRequest request = new MortgageRequest();
        request.setIncome(BigDecimal.valueOf(100000));
        request.setMaturityPeriod(120);
        request.setLoanValue(BigDecimal.valueOf(200000));
        request.setHomeValue(BigDecimal.valueOf(250000));

        mockMvc.perform(post("/api/mortgage-check")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"income\":100000,\"maturityPeriod\":10,\"loanValue\":200000,\"homeValue\":250000}")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{'feasible':true,'monthlyCost':20507.62}"));
    }


}
