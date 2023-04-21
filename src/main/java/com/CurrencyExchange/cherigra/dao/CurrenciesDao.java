package com.CurrencyExchange.cherigra.dao;

import com.CurrencyExchange.cherigra.entity.Currencies;
import com.CurrencyExchange.cherigra.util.ConnectionManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CurrenciesDao implements Dao<Integer, Currencies>{
	
	private static final CurrenciesDao INSTANCE = new CurrenciesDao();
	public static final String FIND_ALL = """
						SELECT *
						FROM currencies
			""";
	
	
	@Override
	public List<Currencies> findAll() {
		try (var connection = ConnectionManager.get();
			 var prepareStatement = connection.prepareStatement(FIND_ALL)) {
			var resultSet = prepareStatement.executeQuery();
			List<Currencies> currencies = new ArrayList<>();
			while (resultSet.next()) {
				currencies.add(buildFlight(resultSet));
			}
			return currencies;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
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
	
	public static CurrenciesDao getInstance() {
		return INSTANCE;
	}
	private Currencies buildFlight(ResultSet resultSet) throws SQLException {
		return new Currencies(
				resultSet.getObject("id", Integer.class),
				resultSet.getObject("code", String.class),
				resultSet.getObject("full_name", String.class),
				resultSet.getObject("sign", String.class));
	}
}
