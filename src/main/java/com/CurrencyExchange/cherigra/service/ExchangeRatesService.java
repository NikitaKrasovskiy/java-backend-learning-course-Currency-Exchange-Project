package com.CurrencyExchange.cherigra.service;

import com.CurrencyExchange.cherigra.dao.CurrenciesDao;
import com.CurrencyExchange.cherigra.dao.ExchangeRatesDao;
import com.CurrencyExchange.cherigra.dto.ExchangeRatesDto;
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

    public List<ExchangeRatesDto> findById(Integer id) {
        return exchangeRatesDao.findById(id).stream()
                .map(exchangeRates -> new ExchangeRatesDto(
                        exchangeRates.getId(),
                        exchangeRates.getBaseCurrencyId(),
                        exchangeRates.getTargetCurrencyId(),
                        exchangeRates.getRate()
                )).collect(toList());
    }
    public Integer savee(String baseCurrencyCode, String targetCurrencyCode, String rate) { // TODO сервисы долны возращать только DTO или Model
        BigDecimal rates = BigDecimal.valueOf(Double.parseDouble(rate)); // TODO рефактор в функциональном ввиде
        ExchangeRates exchangeRates = new ExchangeRates(
                currenciesDao.findByCode(targetCurrencyCode).orElseThrow(),
                currenciesDao.findByCode(baseCurrencyCode).orElseThrow(),
                rates);
        exchangeRatesDao.save(exchangeRates);
        return exchangeRates.getId();
    }
    public static ExchangeRatesService getInstance() {
        return INSTANCE;
    }
}
