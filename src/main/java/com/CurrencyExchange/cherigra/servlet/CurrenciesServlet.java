package com.CurrencyExchange.cherigra.servlet;

import com.CurrencyExchange.cherigra.dto.CurrenciesDto;
import com.CurrencyExchange.cherigra.entity.Currencies;
import com.CurrencyExchange.cherigra.service.CurrenciesService;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
@WebServlet("/currencies")
public class CurrenciesServlet extends HttpServlet {
	
	private final CurrenciesService currenciesService = CurrenciesService.getInstance();
	private final ObjectMapper mapper = new ObjectMapper();
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/json");
		resp.setCharacterEncoding(StandardCharsets.UTF_8.name());
			List<CurrenciesDto> currencies = currenciesService.findAll();
			mapper.writeValue(resp.getWriter(), currencies);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name = req.getParameter("name");  ////TODO нужен рефактор
		String code = req.getParameter("code");
		String sign = req.getParameter("sign");

		Integer id = currenciesService.save(name, code, sign);

		List<CurrenciesDto> optionalCurrencies = currenciesService.findById(id);
		mapper.writeValue(resp.getWriter(), optionalCurrencies);
	}
}

