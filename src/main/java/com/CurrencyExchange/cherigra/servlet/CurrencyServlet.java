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
import java.util.List;

@WebServlet("/currency/*")
public class CurrencyServlet extends HttpServlet {
    private final CurrencyService currencyService = CurrencyService.getInstance();
    private final ObjectMapper mapper = new ObjectMapper();

    private final CurrenciesDto currenciesDto = new CurrenciesDto();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String code = req.getPathInfo().replaceAll("/", "");
        currenciesDto.setCode(req.getPathInfo().replaceAll("/", ""));

                                                                    // TODO нужно на всех сервлетах сделать валидацию
        List<CurrenciesDto> optionalCurrencies = currencyService.findByCode(currenciesDto);
        mapper.writeValue(resp.getWriter(), optionalCurrencies);
    }
}
