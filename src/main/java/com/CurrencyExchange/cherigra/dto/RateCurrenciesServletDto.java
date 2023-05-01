package com.CurrencyExchange.cherigra.dto;

import com.CurrencyExchange.cherigra.entity.Currencies;

import java.math.BigDecimal;

public class RateCurrenciesServletDto {
    private Currencies baseCurrencyId;
    private Currencies targetCurrencyId;
    private BigDecimal rate;
    private BigDecimal amount;
    private BigDecimal convertedAmount;


    public RateCurrenciesServletDto(Currencies baseCurrencyId, Currencies targetCurrencyId, BigDecimal rate, BigDecimal amount, BigDecimal convertedAmount) {
        this.baseCurrencyId = baseCurrencyId;
        this.targetCurrencyId = targetCurrencyId;
        this.rate = rate;
        this.amount = amount;
        this.convertedAmount = convertedAmount;
    }

    public Currencies getBaseCurrencyId() {
        return baseCurrencyId;
    }

    public void setBaseCurrencyId(Currencies baseCurrencyId) {
        this.baseCurrencyId = baseCurrencyId;
    }

    public Currencies getTargetCurrencyId() {
        return targetCurrencyId;
    }

    public void setTargetCurrencyId(Currencies targetCurrencyId) {
        this.targetCurrencyId = targetCurrencyId;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getConvertedAmount() {
        return convertedAmount;
    }

    public void setConvertedAmount(BigDecimal convertedAmount) {
        this.convertedAmount = convertedAmount;
    }
}
