package com.CurrencyExchange.cherigra.service;

import com.CurrencyExchange.cherigra.dao.CurrenciesDao;
import com.CurrencyExchange.cherigra.dto.CurrenciesDto;
import com.CurrencyExchange.cherigra.entity.Currencies;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

public class CurrenciesService {
	
	private static final CurrenciesService INSTANCE = new CurrenciesService();
	
	private final CurrenciesDao currenciesDao = CurrenciesDao.getInstance();
	
	public CurrenciesService() {
	}
	public List<CurrenciesDto> findAll() {
		return currenciesDao.findAll().stream()
				.map(currencies -> new CurrenciesDto(
						currencies.getId(),
						currencies.getCode(),
						currencies.getFullName(),
						currencies.getSign()
				)).collect(toList());
	}

	public List<CurrenciesDto> findById(Integer id) {
		return currenciesDao.findById(id).stream()
				.map(currencies -> new CurrenciesDto(
						currencies.getId(),
						currencies.getCode(),
						currencies.getFullName(),
						currencies.getSign()
				)).collect(toList());
	}

	public Integer save(String code, String fullName, String sign) {
		var currencies = new Currencies(code, fullName, sign);
		currenciesDao.save(currencies);
		return currencies.getId();
	}

	public static CurrenciesService getInstance() {
		return INSTANCE;
	}
}
