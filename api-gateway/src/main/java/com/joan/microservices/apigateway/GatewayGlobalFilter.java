package com.joan.microservices.apigateway;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;

@Component
public class GatewayGlobalFilter implements GlobalFilter {

	private Logger logger = LoggerFactory.getLogger(GatewayGlobalFilter.class);
	
	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		
		logger.info("Global filter: {}",exchange.getRequest().getPath());
		logger.info("Headers: {}",exchange.getRequest().getHeaders());
		logger.info("Authorization: {}",exchange.getRequest().getHeaders().AUTHORIZATION);
		return chain.filter(exchange);
	}
	
}
