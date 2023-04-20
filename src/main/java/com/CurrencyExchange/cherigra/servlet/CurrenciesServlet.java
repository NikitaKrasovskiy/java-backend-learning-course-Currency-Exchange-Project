package com.CurrencyExchange.cherigra.servlet;

import com.CurrencyExchange.cherigra.service.CurrenciesService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

@WebServlet("/currencies")
public class CurrenciesServlet extends HttpServlet {
	
	private final CurrenciesService currenciesService = CurrenciesService.getInstance();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/json");
		resp.setCharacterEncoding(StandardCharsets.UTF_8.name());
		
		try (var printWriter = resp.getWriter()) {
			currenciesService.findAll().forEach(currenciesDto -> {
				printWriter.write(currenciesDto.getId());
				printWriter.write(currenciesDto.getCode());
				printWriter.write(currenciesDto.getFullName());
				printWriter.write(currenciesDto.getSign());
			});
		}
	}
}
