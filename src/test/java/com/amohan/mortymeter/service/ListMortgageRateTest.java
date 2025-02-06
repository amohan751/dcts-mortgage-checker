package com.amohan.mortymeter.service;

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
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ListMortgageRateTest {

    @Mock
    private MortgageRateRepository mortgageRateRepository;

    @InjectMocks
    private ListMortgageRate listMortgageRate;

    @BeforeEach
    public void setUp() {
        MortgageRate rate1 = new MortgageRate(1L,10, BigDecimal.valueOf(5.50), new Timestamp(System.currentTimeMillis()));
        MortgageRate rate2 = new MortgageRate(2L,15, BigDecimal.valueOf(4.70), new Timestamp(System.currentTimeMillis()));
        MortgageRate rate3 = new MortgageRate(3L,20, BigDecimal.valueOf(4.20), new Timestamp(System.currentTimeMillis()));
        when(mortgageRateRepository.findAll()).thenReturn(Arrays.asList(rate1, rate2, rate3));
        when(mortgageRateRepository.findByMaturityPeriod(10)).thenReturn(rate1);
        when(mortgageRateRepository.findByMaturityPeriod(15)).thenReturn(rate2);
        when(mortgageRateRepository.findByMaturityPeriod(20)).thenReturn(rate3);
    }

    @Test
    public void testGetMortgageRates() {
        List<MortgageRate> rates = listMortgageRate.fetchMortgageRates();
        assertEquals(3, rates.size());
    }
}
