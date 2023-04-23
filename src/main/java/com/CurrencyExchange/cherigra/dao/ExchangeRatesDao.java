package com.CurrencyExchange.cherigra.dao;

import com.CurrencyExchange.cherigra.entity.ExchangeRates;

import java.util.List;
import java.util.Optional;

public class ExchangeRatesDao implements Dao<Integer, ExchangeRates>{
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
