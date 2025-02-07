package com.amohan.mortymeter.controller;

import com.amohan.mortymeter.model.MortgageRequest;
import com.amohan.mortymeter.model.MortgageResponse;
import com.amohan.mortymeter.model.entity.MortgageRate;
import com.amohan.mortymeter.service.ListMortgageRate;
import com.amohan.mortymeter.service.MortgageCalculator;
import com.amohan.mortymeter.utils.MortgageRequestValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api")
public class MortymeterController {

    @Autowired
    private ListMortgageRate mortgageRate;
    @Autowired
    private MortgageCalculator mortgageCalculator;

    @GetMapping("/interest-rates")
    public List<MortgageRate> getAvailableMortgageRates(){
        return mortgageRate.fetchMortgageRates();
    }

    @PostMapping("/mortgage-check")
    public MortgageResponse checkMortgage(@RequestBody MortgageRequest request){
        if (!MortgageRequestValidator.isValidMortgageRequest(request)) {
            MortgageResponse response = new MortgageResponse();
            response.setFeasible(false);
            response.setMonthlyCost(BigDecimal.ZERO);
            return response;
        }
        return mortgageCalculator.checkMortgageAndEligibility(request);
    }
}
