package com.CurrencyExchange.cherigra.service;

import com.CurrencyExchange.cherigra.dao.CurrenciesDao;
import com.CurrencyExchange.cherigra.dao.ExchangeRatesDao;
import com.CurrencyExchange.cherigra.dto.ExchangeRatesDto;
import com.CurrencyExchange.cherigra.entity.ExchangeRates;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

public class ExchangeRatesTargetService {
    private static final ExchangeRatesTargetService INSTANCE = new ExchangeRatesTargetService();

    private final ExchangeRatesDao exchangeRatesDao = ExchangeRatesDao.getInstance();

    private final CurrenciesDao currenciesDao = new CurrenciesDao();

    public List<ExchangeRatesDto> findByCodes(String baseCode, String targetCode) {
        return exchangeRatesDao.findByCodes(baseCode, targetCode).stream()
                .map(exchangeRates -> new ExchangeRatesDto(
                        exchangeRates.getId(),
                        exchangeRates.getBaseCurrencyId(),
                        exchangeRates.getTargetCurrencyId(),
                        exchangeRates.getRate()
                )).collect(toList());
    }
    public Integer updatee(String baseCurrencyCode, String targetCurrencyCode, String rate) {
        BigDecimal rates = BigDecimal.valueOf(Double.parseDouble(rate));
        ExchangeRates exchangeRates = new ExchangeRates(
                currenciesDao.findByCode(baseCurrencyCode).orElseThrow(),
                currenciesDao.findByCode(targetCurrencyCode).orElseThrow(),
                rates
        );
        exchangeRates.setRate(rates);
        exchangeRatesDao.update(exchangeRates);
        return exchangeRates.getId();
    }
    public static ExchangeRatesTargetService getInstance() {
        return INSTANCE;
    }
}
