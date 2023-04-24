package com.CurrencyExchange.cherigra.service;

import com.CurrencyExchange.cherigra.dao.CurrenciesDao;
import com.CurrencyExchange.cherigra.dao.ExchangeRatesDao;
import com.CurrencyExchange.cherigra.dto.CurrenciesDto;
import com.CurrencyExchange.cherigra.dto.ExchangeRatesDto;
import com.CurrencyExchange.cherigra.entity.Currencies;
import com.CurrencyExchange.cherigra.entity.ExchangeRates;

import java.math.BigDecimal;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class ExchangeRatesService {

    private static final ExchangeRatesService INSTANCE = new ExchangeRatesService();

    private final ExchangeRatesDao exchangeRatesDao = ExchangeRatesDao.getInstance();

    private final CurrenciesDao currenciesDao = new CurrenciesDao();

    public List<ExchangeRatesDto> findAll() {
        return exchangeRatesDao.findAll().stream()
                .map(exchangeRates -> new ExchangeRatesDto(
                        exchangeRates.getId(),
                        exchangeRates.getBaseCurrencyId(),
                        exchangeRates.getTargetCurrencyId(),
                        exchangeRates.getRate()
                )).collect(toList());
    }

    public Integer save(String baseCurrencyCode, String targetCurrencyCode, String rate) {
//        var exchangeRates = new ExchangeRates(baseCurrencyCode, targetCurrencyCode, rate);
//        currenciesDao.save(currencies);
//        return currencies.getId();
            var exchangeRates = new ExchangeRates(currenciesDao.findByCode(targetCurrencyCode).orElseThrow(),
                    currenciesDao.findByCode(baseCurrencyCode).orElseThrow(),
                    BigDecimal.valueOf(Double.parseDouble(rate)));
            exchangeRatesDao.save(exchangeRates);
            return exchangeRates.getId();
    }

    public List<ExchangeRatesDto> findById(Integer id) {
        return exchangeRatesDao.findById(id).stream()
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
