package com.CurrencyExchange.cherigra.service;

import com.CurrencyExchange.cherigra.dao.CurrenciesDao;
import com.CurrencyExchange.cherigra.dto.CurrenciesDto;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class CurrencyService {

    private static final CurrencyService INSTANCE = new CurrencyService();

    private final CurrenciesDao currenciesDao = CurrenciesDao.getInstance();


    	public List<CurrenciesDto> findById(String code) {
            return currenciesDao.findByCode(code).stream()
                    .map(currencies -> new CurrenciesDto(
                            currencies.getId(),
                            currencies.getCode(),
                            currencies.getFullName(),
                            currencies.getSign()
                    )).collect(toList());
	}
    public static CurrencyService getInstance() {
        return INSTANCE;
    }
}
