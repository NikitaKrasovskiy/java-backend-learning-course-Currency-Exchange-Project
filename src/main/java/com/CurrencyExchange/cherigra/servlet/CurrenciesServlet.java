package com.CurrencyExchange.cherigra.servlet;

import com.CurrencyExchange.cherigra.dto.CurrenciesDto;
import com.CurrencyExchange.cherigra.service.CurrenciesService;

import com.CurrencyExchange.cherigra.util.Utils;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/currencies")
public class CurrenciesServlet extends HttpServlet {

    private static final String INTEGRITY_CONSTRAINT_VIOLATION_CODE = "23505";
    private final CurrenciesService currenciesService = CurrenciesService.getInstance();
    private final ObjectMapper mapper = new ObjectMapper();
    private final CurrenciesDto currenciesDto = new CurrenciesDto();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            List<CurrenciesDto> currencies = currenciesService.findAll();
            mapper.writeValue(resp.getWriter(), currencies);
        } catch (SQLException e) {
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        currenciesDto.setFullName(req.getParameter("name"));
        currenciesDto.setCode(req.getParameter("code"));
        currenciesDto.setSign(req.getParameter("sign"));
        if (!Utils.isValidCurrenciesArgs(currenciesDto)) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Веденны не все нужные данные");
            return;
        }
        try {
            Integer id = currenciesService.save(currenciesDto);
            List<CurrenciesDto> optionalCurrencies = currenciesService.findById(id);
            mapper.writeValue(resp.getWriter(), optionalCurrencies);
        } catch (SQLException e) {
            if (e.getSQLState().equals(INTEGRITY_CONSTRAINT_VIOLATION_CODE))
                resp.sendError(HttpServletResponse.SC_CONFLICT, e.getMessage());

            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Something happened with the database, try again later!");
        }
    }
}

