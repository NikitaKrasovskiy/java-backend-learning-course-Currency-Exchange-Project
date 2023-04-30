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

import static jakarta.servlet.http.HttpServletResponse.SC_INTERNAL_SERVER_ERROR;

@WebServlet("/currencies")
public class CurrenciesServlet extends HttpServlet {
	
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
		} else  {
			try {
				Integer id = currenciesService.save(currenciesDto);
				List<CurrenciesDto> optionalCurrencies = currenciesService.findById(id);
				mapper.writeValue(resp.getWriter(), optionalCurrencies);
			} catch (SQLException e) {
				resp.sendError(HttpServletResponse.SC_CONFLICT, e.getMessage());
			}
		}

	}
}

