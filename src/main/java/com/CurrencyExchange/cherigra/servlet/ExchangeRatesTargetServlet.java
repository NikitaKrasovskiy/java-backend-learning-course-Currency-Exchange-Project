package com.CurrencyExchange.cherigra.servlet;

import com.CurrencyExchange.cherigra.service.ExchangeRatesTargetService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.nio.charset.StandardCharsets;


@WebServlet("/exchangeRates/*")
public class ExchangeRatesTargetServlet  extends HttpServlet {

    private final ExchangeRatesTargetService exchangeRatesTargetService = ExchangeRatesTargetService.getInstance();

    private final ObjectMapper mapper = new ObjectMapper();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = req.getPathInfo().replaceAll("/", "");
        resp.setContentType("text/json");
        resp.setCharacterEncoding(StandardCharsets.UTF_8.name()); // TODO добавить filter для сервлетов
        String baseCurrencyCode = url.substring(0, 3);
        String targetCurrencyCode = url.substring(3);
        var  byCodes = exchangeRatesTargetService.findByCodes(baseCurrencyCode, targetCurrencyCode);
        mapper.writeValue(resp.getWriter(), byCodes);
    }
}
