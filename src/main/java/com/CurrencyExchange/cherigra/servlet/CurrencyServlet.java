package com.CurrencyExchange.cherigra.servlet;

import com.CurrencyExchange.cherigra.dto.CurrenciesDto;
import com.CurrencyExchange.cherigra.service.CurrencyService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/currency/*")
public class CurrencyServlet extends HttpServlet {
    private final CurrencyService currencyService = CurrencyService.getInstance();
    private final ObjectMapper mapper = new ObjectMapper();

    private final CurrenciesDto currenciesDto = new CurrenciesDto();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        currenciesDto.setCode(req.getPathInfo().replaceAll("/", ""));
        // TODO нужно на всех сервлетах сделать валидации
        try {
//            if (currencyService.findByCode(currenciesDto).isPresent()) {
//                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Currency code must be in ISO 4217 format");
//                return;
//            }
            var optionalCurrencies = currencyService.findByCode(currenciesDto);

            if (optionalCurrencies.isEmpty()) {
                resp.sendError(HttpServletResponse.SC_NOT_FOUND, "There is no such currency in the database");
                return;
            }
            mapper.writeValue(resp.getWriter(), optionalCurrencies);
        } catch (SQLException e) {
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Something happened with the database, try again later!");
        }
    }
}
