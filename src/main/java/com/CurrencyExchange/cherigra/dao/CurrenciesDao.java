package com.CurrencyExchange.cherigra.dao;

import com.CurrencyExchange.cherigra.entity.Currencies;
import com.CurrencyExchange.cherigra.util.ConnectionManager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CurrenciesDao implements Dao<Integer, Currencies>{
	
	private static final CurrenciesDao INSTANCE = new CurrenciesDao();
	public static final String FIND_ALL = """
						SELECT id,
						code,
						full_name,
						sign
						FROM currencies
			""";
	
	private CurrenciesDao() {
	}
	
	@Override
	public List<Currencies> findAll() {
		try (var connection = ConnectionManager.get();
			 var prepareStatement = connection.prepareStatement(FIND_ALL)) {
			var resultSet = prepareStatement.executeQuery();
			List<Currencies> currencies = new ArrayList<>();
			
			while (resultSet.next()) {
				currencies.add(new Currencies(
						resultSet.getObject("id", Integer.class),
						resultSet.getObject("code", String.class),
						resultSet.getObject("full_name", String.class),
						resultSet.getObject("sign", String.class))
				);
			}
			return currencies;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public static CurrenciesDao getInstance() {
		return INSTANCE;
	}
	
	@Override
	public Optional<Currencies> findById(Integer id) {
		return Optional.empty();
	}
	
	@Override
	public boolean delete(Integer id) {
		return false;
	}
	
	@Override
	public void update(Currencies entity) {
	
	}
	
	@Override
	public Currencies save(Currencies entity) {
		return null;
	}
}
