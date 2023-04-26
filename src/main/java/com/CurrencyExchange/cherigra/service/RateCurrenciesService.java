package com.CurrencyExchange.cherigra.service;

import com.CurrencyExchange.cherigra.dao.CurrenciesDao;
import com.CurrencyExchange.cherigra.dao.ExchangeRatesDao;
import com.CurrencyExchange.cherigra.dto.ExchangeRatesDto;
import com.CurrencyExchange.cherigra.entity.ExchangeRates;

import java.math.BigDecimal;
import java.math.MathContext;
import java.sql.SQLException;
import java.util.Optional;

import static java.math.MathContext.DECIMAL64;

public class RateCurrenciesService {
    private static final ExchangeRatesTargetService INSTANCE = new ExchangeRatesTargetService();

    private final ExchangeRatesDao exchangeRatesDao = ExchangeRatesDao.getInstance();

    private final CurrenciesDao currenciesDao = new CurrenciesDao();


//     return exchangeRatesDao.findByCodes(baseCode, targetCode).stream()
//                .map(exchangeRates -> new ExchangeRatesDto(
//            exchangeRates.getId(),
//                        exchangeRates.getBaseCurrencyId(),
//                                exchangeRates.getTargetCurrencyId(),
//                                exchangeRates.getRate()
//                                )).collect(toList());
    public ExchangeRatesDto findAmount(String baseCode, String targetCode, String amount) throws SQLException {
        ExchangeRates exchangeRates = getExchangeRate(baseCode, targetCode).orElseThrow();
        var BigDecimalAmount = BigDecimal.valueOf(Double.parseDouble(amount));
        BigDecimal convertedAmount = BigDecimalAmount.multiply(exchangeRates.getRate());
        return new ExchangeRatesDto(
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
            byCodes = getReverseExchangeRate(baseCurrencyCode, targetCurrencyCode);
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

        ExchangeRates exchangeRates1 = new ExchangeRates(
                exchangeRates.getTargetCurrencyId(),
                exchangeRates.getBaseCurrencyId(),
                BigDecimal.ONE.divide(exchangeRates.getRate(), DECIMAL64)
        );
        return Optional.of(exchangeRates1);
    }

    private Optional<ExchangeRates> getCrossExchangeRate(String baseCurrencyCode, String targetCurrencyCode) throws SQLException {
        return null;
    }
}
