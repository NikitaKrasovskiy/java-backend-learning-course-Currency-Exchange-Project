package com.CurrencyExchange.cherigra.servlet;

import com.CurrencyExchange.cherigra.dto.ExchangeRatesDto;
import com.CurrencyExchange.cherigra.service.ExchangeRatesTargetService;
import com.CurrencyExchange.cherigra.service.RateCurrenciesService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;


@WebServlet("/exchange")
public class RateCurrenciesServlet extends HttpServlet {
    private final RateCurrenciesService exchangeRatesTargetService = RateCurrenciesService.getInstance();
    private final ObjectMapper mapper = new ObjectMapper();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String baseCurrencyCode = req.getParameter("from");
        String targetCurrencyCode = req.getParameter("to");
        String amountToConvertParam = req.getParameter("amount");
        try {
            ExchangeRatesDto amounts = exchangeRatesTargetService.findAmounts(baseCurrencyCode, targetCurrencyCode, amountToConvertParam);
            mapper.writeValue(resp.getWriter(), amounts);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
