package com.CurrencyExchange.cherigra.service;

import com.CurrencyExchange.cherigra.dao.CurrenciesDao;
import com.CurrencyExchange.cherigra.dao.ExchangeRatesDao;
import com.CurrencyExchange.cherigra.dto.CurrenciesDto;
import com.CurrencyExchange.cherigra.dto.ExchangeRatesDto;
import com.CurrencyExchange.cherigra.entity.Currencies;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class ExchangeRatesService {

    private static final ExchangeRatesService INSTANCE = new ExchangeRatesService();

    private final ExchangeRatesDao currenciesDao = ExchangeRatesDao.getInstance();

    public List<ExchangeRatesDto> findAll() {
        return currenciesDao.findAll().stream()
                .map(exchangeRates -> new ExchangeRatesDto(
                        exchangeRates.getId(),
                        exchangeRates.getBaseCurrencyId(),
                        exchangeRates.getTargetCurrencyId(),
                        exchangeRates.getRate()
                )).collect(toList());
    }
    public static ExchangeRatesService getInstance() {
        return INSTANCE;
    }
}
