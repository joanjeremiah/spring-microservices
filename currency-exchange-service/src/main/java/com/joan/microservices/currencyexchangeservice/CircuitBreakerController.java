package com.joan.microservices.currencyexchangeservice;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@RestController
public class CircuitBreakerController {
	
	@GetMapping("some-api")
//	@Retry(name="some-api",fallbackMethod="fallBackResponse")
	@CircuitBreaker(name="some-api",fallbackMethod="fallBackResponse")
//	@RateLimiter(name="some-api")
	public String retrieveSomeString() {
		ResponseEntity<String> forEntity = new RestTemplate().getForEntity("https://dssdsd.com", String.class);
		String body = forEntity.getBody();
		return body;
	}
	
	public String fallBackResponse(Exception ex) {
		return "This is the fallback response";
		
	}
}
