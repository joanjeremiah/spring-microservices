package com.joan.microservices.currencyexchangeservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import io.github.resilience4j.ratelimiter.annotation.RateLimiter;

@RestController
public class CurrencyExchangeController {
	
	@Autowired
	private CurrencyExchangeRepository repository;
	
	@Autowired
	private Environment environment;
	
	@GetMapping("/currency-exchange/from/{from}/to/{to}")
//	@RateLimiter(name="currency-exchange")
	public CurrencyExchange retrieveExchangeValue(
			@PathVariable String from,
			@PathVariable String to
			) {
		String port = environment.getProperty("local.server.port");
		CurrencyExchange currencyExchange = repository.findByFromAndTo(from, to);
		currencyExchange.setEnvironment(port);
		return currencyExchange;
	}
}
