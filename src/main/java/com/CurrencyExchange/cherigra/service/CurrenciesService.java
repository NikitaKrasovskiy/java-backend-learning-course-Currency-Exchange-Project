package com.CurrencyExchange.cherigra.service;

import com.CurrencyExchange.cherigra.dao.CurrenciesDao;
import com.CurrencyExchange.cherigra.dto.CurrenciesDto;
import com.CurrencyExchange.cherigra.entity.Currencies;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

public class CurrenciesService {
	
	private static final CurrenciesService INSTANCE = new CurrenciesService();
	
	private final CurrenciesDao currenciesDao = CurrenciesDao.getInstance();
	
	public CurrenciesService() {
	}
	public List<CurrenciesDto> findAll() throws SQLException {
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

	public Optional<Currencies> findByCode(CurrenciesDto currenciesDto) throws SQLException {
		return currenciesDao.findByCode(currenciesDto.getCode());
//				.stream()
//				.map(currencies -> new CurrenciesDto(
//						currencies.getId(),
//						currencies.getCode(),
//						currencies.getFullName(),
//						currencies.getSign()
//				)).collect(toList());
	}

	public Integer save(CurrenciesDto currenciesDto) throws SQLException { // TODO сервисы долны возращать только DTO или Model
		var currencies = new Currencies(currenciesDto.getCode(), currenciesDto.getFullName(), currenciesDto.getSign());
		currenciesDao.save(currencies);
		return currencies.getId();
	}

	public static CurrenciesService getInstance() {
		return INSTANCE;
	}
}
