package com.amohan.mortymeter.service;

import com.amohan.mortymeter.model.entity.MortgageRate;
import com.amohan.mortymeter.repository.MortgageRateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ListMortgageRate {

    @Autowired
    MortgageRateRepository mortgageRateRepository;

    public List<MortgageRate> fetchMortgageRates(){
        return mortgageRateRepository.findAll();
    }
}
