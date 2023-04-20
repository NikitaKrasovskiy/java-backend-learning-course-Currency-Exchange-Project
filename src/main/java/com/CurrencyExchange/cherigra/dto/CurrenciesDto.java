package com.CurrencyExchange.cherigra.dto;

import java.util.Objects;

public class CurrenciesDto {
	
	private final Integer id;
	private final String code;
	private final String fullName;
	private final String sign;
	
	public CurrenciesDto(Integer id, String code, String fullName, String sign) {
		this.id = id;
		this.code = code;
		this.fullName = fullName;
		this.sign = sign;
	}
	
	public Integer getId() {
		return id;
	}
	
	public String getCode() {
		return code;
	}
	
	public String getFullName() {
		return fullName;
	}
	
	public String getSign() {
		return sign;
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		CurrenciesDto that = (CurrenciesDto) o;
		return Objects.equals(id, that.id);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
}
