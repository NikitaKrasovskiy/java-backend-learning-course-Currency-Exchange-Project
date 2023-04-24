package com.CurrencyExchange.cherigra.servlet;

import com.CurrencyExchange.cherigra.dto.CurrenciesDto;
import com.CurrencyExchange.cherigra.dto.ExchangeRatesDto;
import com.CurrencyExchange.cherigra.entity.ExchangeRates;
import com.CurrencyExchange.cherigra.service.CurrenciesService;
import com.CurrencyExchange.cherigra.service.ExchangeRatesService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Objects;

@WebServlet("/exchangeRates")
public class ExchangeRatesServlet extends HttpServlet {

    private final ExchangeRatesService exchangeRatesService = ExchangeRatesService.getInstance();
    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/json");
        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());
        List<ExchangeRatesDto> exchangeList = exchangeRatesService.findAll();
        mapper.writeValue(resp.getWriter(), exchangeList);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String baseCurrencyCode = req.getParameter("baseCurrencyCode");
        String targetCurrencyCode = req.getParameter("targetCurrencyCode");
        String rate = req.getParameter("rate");

        var id = exchangeRatesService.save(baseCurrencyCode, targetCurrencyCode, rate);

        var exchangeList = exchangeRatesService.findById(id);

        mapper.writeValue(resp.getWriter(), exchangeList);
    }
}
