package com.CurrencyExchange.cherigra.servlet;

import com.CurrencyExchange.cherigra.service.ExchangeRatesTargetService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;


@WebServlet("/exchange")
public class RateCurrenciesServlet extends HttpServlet {

    private final ExchangeRatesTargetService exchangeRatesTargetService = ExchangeRatesTargetService.getInstance();

    private final ObjectMapper mapper = new ObjectMapper();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String baseCurrencyCode = req.getParameter("from");
        String targetCurrencyCode = req.getParameter("to");
        String amountToConvertParam = req.getParameter("amount");

//        mapper.writeValue(resp.getWriter(), "TEST");

//        ExchangeResponse exchangeResponse = exchangeService.convertCurrency(baseCurrencyCode, targetCurrencyCode, amount);
//        objectMapper.writeValue(resp.getWriter(), exchangeResponse);
    }
}
