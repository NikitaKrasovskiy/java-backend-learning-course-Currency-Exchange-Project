package com.CurrencyExchange.cherigra.util;

import com.CurrencyExchange.cherigra.dto.CurrenciesDto;

import java.util.ArrayList;

public class Utils{

    public static boolean isValidCurrenciesArgs(CurrenciesDto currenciesDto) {
        if (currenciesDto.getFullName() == null || currenciesDto.getFullName().isBlank()) return false;

        if (currenciesDto.getCode() == null || currenciesDto.getCode().isBlank())  return false;

        if (currenciesDto.getSign() == null || currenciesDto.getSign().isBlank()) return false;

        return true;
    }
}
