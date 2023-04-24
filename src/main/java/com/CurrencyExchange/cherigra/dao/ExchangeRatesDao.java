package com.CurrencyExchange.cherigra.dao;

import com.CurrencyExchange.cherigra.entity.ExchangeRates;

import java.util.List;
import java.util.Optional;

public class ExchangeRatesDao implements Dao<Integer, ExchangeRates> {

    private final String FIND_ALL_SQL = """
            select ex_rates.id AS id,
                    currencies_1.id AS base_id
                    currencies_1.code AS base_code,
                    currencies_1.full_name AS base_full_name
                    currencies_1.sign AS base_sing,
                    currencies_2.id AS target_id,
                    currencies_2.code AS target_id_code,
                    currencies_2.full_name AS target_full_name,
                    currencies_2.sign AS target_sign
                    ex_rates.rate AS rate
                    from exchange_rates AS ex_rates
                    JOIN currencies currencies_1 ON ex_rates.base_currency_id = currencies_1.id
                    JOIN currencies currencies_2 ON ex_rates.target_currency_id = currencies_2.id
            """;

    @Override
    public List<ExchangeRates> findAll() {
        return null;
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
}
