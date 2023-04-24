package com.CurrencyExchange.cherigra.service;

import com.CurrencyExchange.cherigra.dao.CurrenciesDao;
import com.CurrencyExchange.cherigra.dao.ExchangeRatesDao;
import com.CurrencyExchange.cherigra.dto.CurrenciesDto;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class ExchangeRatesService {

    private static final ExchangeRatesService INSTANCE = new ExchangeRatesService();

    private final ExchangeRatesDao currenciesDao = ExchangeRatesDao.getInstance();


    public static ExchangeRatesService getInstance() {
        return INSTANCE;
    }
}
