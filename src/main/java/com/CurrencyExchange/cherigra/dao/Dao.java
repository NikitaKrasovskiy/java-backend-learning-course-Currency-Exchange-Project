package com.CurrencyExchange.cherigra.dao;

import java.util.List;
import java.util.Optional;

public interface Dao<K, T> { // k - айдишник T - тип ссущности
	
	List<T> findAll(); // возращает все
	
	Optional<T> findById(K id); // возращает сущность по айди
	
	boolean delete(K id); // удалили ли сщность
	
	void update(T entity); // вернуть сущность и обновить
	
	T save(T entity); // возразаем сущность которую сохранили
}
