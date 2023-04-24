package com.CurrencyExchange.cherigra.dao;

import com.CurrencyExchange.cherigra.entity.Currencies;
import com.CurrencyExchange.cherigra.entity.ExchangeRates;
import com.CurrencyExchange.cherigra.util.ConnectionManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ExchangeRatesDao implements Dao<Integer, ExchangeRates> {


    private static final ExchangeRatesDao INSTANCE = new ExchangeRatesDao();

    private void ExchangeRatesDao() {

    }

    private final String FIND_ALL_SQL = """
            select ex_rates.id AS id,
                                currencies_1.id AS base_id,
                                currencies_1.code AS base_code,
                                currencies_1.full_name AS base_full_name,
                                currencies_1.sign AS base_sing,
                                currencies_2.id AS target_id,
                                currencies_2.code AS target_code,
                                currencies_2.full_name AS target_full_name,
                                currencies_2.sign AS target_sign,
                                ex_rates.rate AS rate
                                from exchange_rates AS ex_rates
                                JOIN currencies currencies_1 ON ex_rates.base_currency_id = currencies_1.id
                                JOIN currencies currencies_2 ON ex_rates.target_currency_id = currencies_2.id
            """;

    private final String FIND_BY_CODES_SQL = """
            select ex_rates.id AS id,
                                currencies_1.id AS base_id,
                                currencies_1.code AS base_code,
                                currencies_1.full_name AS base_full_name,
                                currencies_1.sign AS base_sing,
                                currencies_2.id AS target_id,
                                currencies_2.code AS target_code,
                                currencies_2.full_name AS target_full_name,
                                currencies_2.sign AS target_sign,
                                ex_rates.rate AS rate
                                from exchange_rates AS ex_rates
                                JOIN currencies currencies_1 ON ex_rates.base_currency_id = currencies_1.id
                                JOIN currencies currencies_2 ON ex_rates.target_currency_id = currencies_2.id
                                 WHERE (
                                                    base_currency_id = (SELECT c.id FROM currencies c WHERE c.code = ?) AND
                                                    target_currency_id = (SELECT c2.id FROM currencies c2 WHERE c2.code = ?)
                                                )
            """;

    @Override
    public List<ExchangeRates> findAll() {
        try (var connection = ConnectionManager.get();
             var prepareStatement = connection.prepareStatement(FIND_ALL_SQL)) {
            var resultSet = prepareStatement.executeQuery();
            List<ExchangeRates> exchangeRates = new ArrayList<>();
            while (resultSet.next()) {
                exchangeRates.add(getExchange(resultSet));
            }
            return exchangeRates;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private ExchangeRates getExchange(ResultSet resultSet) throws SQLException {  // TODO нужно переместить в низ, нужно рефактор названия
        return new ExchangeRates(resultSet.getInt("id"),
        new Currencies(
                resultSet.getObject("base_id", Integer.class),
                resultSet.getObject("base_code", String.class),
                resultSet.getObject("base_full_name", String.class),
                resultSet.getObject("base_sing", String.class)
        ),
                new Currencies(
                        resultSet.getObject("target_id", Integer.class),
                        resultSet.getObject("target_code", String.class),
                        resultSet.getObject("target_full_name", String.class),
                        resultSet.getObject("target_sign", String.class)
                ), resultSet.getBigDecimal("rate"));
    }

    public Optional<ExchangeRates> findByCodes(String baseCode, String targetCode) {
        try (var connection = ConnectionManager.get();
             var prepareStatement = connection.prepareStatement(FIND_BY_CODES_SQL)) {

            prepareStatement.setObject(1, baseCode);
            prepareStatement.setObject(2, targetCode);

            ExchangeRates exchangeRates = null;

            var resultSet = prepareStatement.executeQuery();

            if (resultSet.next()) {
                exchangeRates = getExchange(resultSet);
            }
            return Optional.ofNullable(exchangeRates);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
        @Override
    public Optional<ExchangeRates> findById(Integer id) {
        return Optional.empty();
    }

    @Override
    public boolean delete(Integer id) {
        return false;
    }

    @Override
    public void update(ExchangeRates entity) {

    }

    @Override
    public ExchangeRates save(ExchangeRates entity) {
        return null;
    }

    public static ExchangeRatesDao getInstance() {
        return INSTANCE;
    }
}
