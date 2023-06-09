package com.CurrencyExchange.cherigra.service;

import com.CurrencyExchange.cherigra.dao.CurrenciesDao;
import com.CurrencyExchange.cherigra.dao.ExchangeRatesDao;
import com.CurrencyExchange.cherigra.dto.ExchangeRatesDto;
import com.CurrencyExchange.cherigra.dto.RateCurrenciesServletDto;
import com.CurrencyExchange.cherigra.entity.ExchangeRates;

import java.math.BigDecimal;
import java.math.MathContext;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import static java.math.MathContext.DECIMAL64;

public class RateCurrenciesService { // TODO  переделать !!!
    private static final RateCurrenciesService INSTANCE = new RateCurrenciesService();

    private final ExchangeRatesDao exchangeRatesDao = ExchangeRatesDao.getInstance();

    public RateCurrenciesServletDto findAmounts(String baseCode, String targetCode, String amount) throws SQLException {

        var exchangeRates = getExchangeRate(baseCode, targetCode).orElseThrow();

        var BigDecimalAmount = BigDecimal.valueOf(Double.parseDouble(amount));

        var convertedAmount = BigDecimalAmount.multiply(exchangeRates.getRate());

        return new RateCurrenciesServletDto(
                exchangeRates.getBaseCurrencyId(),
                exchangeRates.getTargetCurrencyId(),
                exchangeRates.getRate(),
                BigDecimalAmount,
                convertedAmount
        );
    }

    private Optional<ExchangeRates> getExchangeRate(String baseCurrencyCode, String targetCurrencyCode) throws SQLException {
        var byCodes = exchangeRatesDao.findByCodes(baseCurrencyCode, targetCurrencyCode);

        if (byCodes.isEmpty()) {
            byCodes = getReverseExchangeRate(baseCurrencyCode, targetCurrencyCode);   // TODO рефактор в функциональном ввиде
        }
        if (byCodes.isEmpty()) {
            byCodes = getCrossExchangeRate(baseCurrencyCode, targetCurrencyCode);
        }
        return byCodes;
    }

    private Optional<ExchangeRates> getReverseExchangeRate(String baseCurrencyCode, String targetCurrencyCode) {
        var byCodes = exchangeRatesDao.findByCodes(baseCurrencyCode, targetCurrencyCode);

        if (byCodes.isEmpty()) {
            return Optional.empty();
        }
        ExchangeRates exchangeRates = byCodes.get();

        ExchangeRates exchangeRates1 = new ExchangeRates(       // TODO рефактор в функциональном ввиде
                exchangeRates.getTargetCurrencyId(),
                exchangeRates.getBaseCurrencyId(),
                BigDecimal.ONE.divide(exchangeRates.getRate(), DECIMAL64)
        );
        return Optional.of(exchangeRates1);
    }

    private Optional<ExchangeRates> getCrossExchangeRate(String baseCurrencyCode, String targetCurrencyCode) {
        var exchangeRatesDaoAmount = exchangeRatesDao.findAmount(baseCurrencyCode, targetCurrencyCode);

        if (exchangeRatesDaoAmount.size() != 2) {
            return Optional.empty();
        }
        ExchangeRates usdToBaseExchangeRate = exchangeRatesDaoAmount.get(0);     // TODO рефактор в функциональном ввиде
        ExchangeRates usdToTargetExchangeRate = exchangeRatesDaoAmount.get(1);

        BigDecimal usdToBaseRate = usdToBaseExchangeRate.getRate();
        BigDecimal usdToTargetRate = usdToTargetExchangeRate.getRate();

        BigDecimal baseToTargetRate = usdToTargetRate.divide(usdToBaseRate, DECIMAL64);


        ExchangeRates exchangeRates = new ExchangeRates(      // TODO рефактор в функциональном ввиде
                usdToBaseExchangeRate.getTargetCurrencyId(),
                usdToTargetExchangeRate.getTargetCurrencyId(),
                baseToTargetRate
                );
        return Optional.of(exchangeRates);
    }

    public static RateCurrenciesService getInstance() {
        return INSTANCE;
    }
}
