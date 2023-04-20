package com.CurrencyExchange.cherigra.service;

import com.CurrencyExchange.cherigra.dao.CurrenciesDao;
import com.CurrencyExchange.cherigra.dto.CurrenciesDto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class CurrenciesService {
	
	private static final CurrenciesService INSTANCE = new CurrenciesService();
	
	private final CurrenciesDao currenciesDao = CurrenciesDao.getInstance();
	
	
	public List<CurrenciesDto> findAll() {
		return currenciesDao.findAll().stream()
				.map(currencies -> new CurrenciesDto(
						currencies.getId(),
						currencies.getCode(),
						currencies.getFullName(),
						currencies.getSign()
				)).collect(toList());
	}
	
	
	public static CurrenciesService getInstance() {
		return INSTANCE;
	}
}
