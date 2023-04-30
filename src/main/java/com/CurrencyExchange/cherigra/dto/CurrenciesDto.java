package com.CurrencyExchange.cherigra.dto;

import java.util.Objects;

public class CurrenciesDto {  // TODO рефактор lombok
	private Integer id;
	private String code;
	private String fullName;
	private String sign;

	public CurrenciesDto(Integer id, String code, String fullName, String sign) {
		this.id = id;
		this.code = code;
		this.fullName = fullName;
		this.sign = sign;
	}

	public CurrenciesDto(String code, String fullName, String sign) {
		this.code = code;
		this.fullName = fullName;
		this.sign = sign;
	}

	public CurrenciesDto(String code) {
		this.code = code;
	}
	public CurrenciesDto() {

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

	@Override
	public String toString() {
		return "CurrenciesDto{" +
				"id=" + id +
				", code='" + code + '\'' +
				", fullName='" + fullName + '\'' +
				", sign='" + sign + '\'' +
				'}';
	}

	public Integer getId() {

		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}
}
