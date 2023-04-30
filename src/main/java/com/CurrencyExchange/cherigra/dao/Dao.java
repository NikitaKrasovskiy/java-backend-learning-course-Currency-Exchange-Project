package com.CurrencyExchange.cherigra.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface Dao<K, T> { // k - айдишник T - тип ссущности
	
	List<T> findAll() throws SQLException; // возращает все
	
	Optional<T> findById(K id); // возращает сущность по айди
	
	boolean delete(K id); // удалили ли сщность
	
	void update(T entity); // вернуть сущность и обновить
	
	T save(T entity) throws SQLException; // возразаем сущность которую сохранили
}
