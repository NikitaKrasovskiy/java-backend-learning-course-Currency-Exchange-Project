package com.CurrencyExchange.cherigra.dto;

import java.util.Objects;

public record CurrenciesDto(Integer id, String code, String fullName, String sign) {


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

	@Override
	public String toString() {
		return "CurrenciesDto{" +
				"id=" + id +
				", code='" + code + '\'' +
				", fullName='" + fullName + '\'' +
				", sign='" + sign + '\'' +
				'}';
	}
}
