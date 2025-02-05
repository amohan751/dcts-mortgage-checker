package com.amohan.mortymeter.repository;

import com.amohan.mortymeter.model.entity.MortgageRate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MortgageRateRepository extends JpaRepository<MortgageRate, Long> {

    MortgageRate findByMaturityPeriod(int maturityPeriod);
}
