package com.CurrencyExchange.cherigra.dao;

import com.CurrencyExchange.cherigra.entity.Currencies;
import com.CurrencyExchange.cherigra.util.ConnectionManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CurrenciesDao implements Dao<Integer, Currencies>{
	
	private static final CurrenciesDao INSTANCE = new CurrenciesDao();
	public static final String FIND_ALL = """
						SELECT *
						FROM currencies
			""";

	public static final String UPDATE_SQL = """
   			update currencies
						set code = ?,
							full_name = ?,
							sign = ?
						where id = ?
			""";
	public static final String FIND_BY_ID_SQL = """
   			select id,
								code,
								full_name,
								sign
						from currencies
						where id = ?
			""";
	public static final String FIND_BY_CODE_SQL = """
   			select id,
								code,
								full_name,
								sign
						from currencies
						where code = ?
			""";

	public static final String INSERT_SQL = """
   			insert into currencies (code, full_name, sign)
   			values (?, ?, ?)
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
		try (var connection = ConnectionManager.get();
			 var prepareStatement = connection.prepareStatement(FIND_BY_ID_SQL)) {
			prepareStatement.setObject(1, id);
			var resultSet = prepareStatement.executeQuery();
			Currencies currencies = null;
			if (resultSet.next()) {
				currencies = new Currencies(   //TODO нужен рефактор
				resultSet.getInt("id"),
				resultSet.getString("code"),
				resultSet.getString("full_name"),
				resultSet.getString("sign")
				);
			}
			return Optional.ofNullable(currencies);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	@Override
	public boolean delete(Integer id) {
		return false;
	}
	
	@Override
	public void update(Currencies entity) {
		try (var connection = ConnectionManager.get();
			 var prepareStatement = connection.prepareStatement(UPDATE_SQL)) {

			prepareStatement.setObject(1, entity.getCode());  //TODO нужен рефактор
			prepareStatement.setObject(2, entity.getFullName());
			prepareStatement.setObject(3, entity.getSign());

			prepareStatement.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	@Override
	public Currencies save(Currencies entity) {
		try (var connection = ConnectionManager.get();
			 var prepareStatement = connection.prepareStatement(INSERT_SQL, Statement.RETURN_GENERATED_KEYS)) {

			prepareStatement.setObject(1, entity.getCode());  //TODO нужен рефактор
			prepareStatement.setObject(2, entity.getFullName());
			prepareStatement.setObject(3, entity.getSign());

			prepareStatement.executeUpdate();

			var generatedKeys = prepareStatement.getGeneratedKeys();
			if (generatedKeys.next()) {
				entity.setId(generatedKeys.getInt("id"));
			}
			return entity;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	public Optional<Currencies> findByCode(String code) {
		try (var connection = ConnectionManager.get();
			 var prepareStatement = connection.prepareStatement(FIND_BY_CODE_SQL)) {
			prepareStatement.setObject(1, code);
			Currencies currencies = null;

			var resultSet = prepareStatement.executeQuery();

			if (resultSet.next()) {
				currencies = new Currencies(   //TODO нужен рефактор
						resultSet.getInt("id"),
						resultSet.getString("code"),
						resultSet.getString("full_name"),
						resultSet.getString("sign")
				);
			}
			return Optional.ofNullable(currencies);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
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
