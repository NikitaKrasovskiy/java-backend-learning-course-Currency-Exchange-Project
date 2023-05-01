package com.CurrencyExchange.cherigra.servlet;

import com.CurrencyExchange.cherigra.dto.CurrenciesDto;
import com.CurrencyExchange.cherigra.entity.Currencies;
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
import java.util.Optional;

@WebServlet("/currency/*")
public class CurrencyServlet extends HttpServlet {
    private final CurrencyService currencyService = CurrencyService.getInstance();
    private final ObjectMapper mapper = new ObjectMapper();
    private final CurrenciesDto currenciesDto = new CurrenciesDto();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        currenciesDto.setCode(req.getPathInfo().replaceAll("/", ""));
        try {
            if (currenciesDto.getCode().isEmpty()) {// нечего не введенно
//                mapper.writeValue(resp.getWriter(), "error");
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                mapper.writeValue(resp.getWriter(),"нечего не введенно");
//                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "нечего не введенно");
            }
            var optionalCurrencies = currencyService.findByCode(currenciesDto);
            if (optionalCurrencies.isEmpty()) {// нет такой валюты
                resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                mapper.writeValue(resp.getWriter()," нет такой валюты");
//                resp.sendError(HttpServletResponse.SC_NOT_FOUND, "валюта не найдена!");
            }
            mapper.writeValue(resp.getWriter(), optionalCurrencies);
        } catch (SQLException e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            mapper.writeValue(resp.getWriter(),"Something happened with the database, try again later!");
//            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Something happened with the database, try again later!");
        }
    }
}
