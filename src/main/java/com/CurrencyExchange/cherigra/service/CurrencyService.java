package com.CurrencyExchange.cherigra.service;

import com.CurrencyExchange.cherigra.dao.CurrenciesDao;
import com.CurrencyExchange.cherigra.dto.CurrenciesDto;
import com.CurrencyExchange.cherigra.entity.Currencies;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

public class CurrencyService {

    private static final CurrencyService INSTANCE = new CurrencyService();

    private final CurrenciesDao currenciesDao = CurrenciesDao.getInstance();


    	public List<CurrenciesDto> findByCode(CurrenciesDto currenciesDto) throws SQLException {
            return currenciesDao.findByCode(currenciesDto.getCode()).stream()
                    .map(currencies -> new CurrenciesDto(
                            currencies.getId(),
                            currencies.getCode(),
                            currencies.getFullName(),
                            currencies.getSign()
                    )).collect(toList());
	}

    public Optional<Currencies> findByName(CurrenciesDto currenciesDto) throws SQLException {
            return currenciesDao.findByCode(currenciesDto.getCode());
    }
    public static CurrencyService getInstance() {
        return INSTANCE;
    }
}
