package com.CurrencyExchange.cherigra.service;

import com.CurrencyExchange.cherigra.dao.ExchangeRatesDao;
import com.CurrencyExchange.cherigra.dto.ExchangeRatesDto;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class ExchangeRatesTargetService {
    private static final ExchangeRatesTargetService INSTANCE = new ExchangeRatesTargetService();

    private final ExchangeRatesDao currenciesDao = ExchangeRatesDao.getInstance();

    public List<ExchangeRatesDto> findByCodes(String baseCode, String targetCode) {
        return currenciesDao.findByCodes(baseCode, targetCode).stream()
                .map(exchangeRates -> new ExchangeRatesDto(
                        exchangeRates.getId(),
                        exchangeRates.getBaseCurrencyId(),
                        exchangeRates.getTargetCurrencyId(),
                        exchangeRates.getRate()
                )).collect(toList());
    }
    public static ExchangeRatesTargetService getInstance() {
        return INSTANCE;
    }
}
