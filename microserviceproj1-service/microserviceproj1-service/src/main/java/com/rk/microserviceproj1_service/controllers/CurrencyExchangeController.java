package com.rk.microserviceproj1_service.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.rk.microserviceproj1_service.entity.CurrencyExchange;
import com.rk.microserviceproj1_service.repository.CurrencyExchangeRepository;

@RestController
public class CurrencyExchangeController {
	@Autowired
	private Environment environment; 
	@Autowired
	private CurrencyExchangeRepository repository;
	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	public CurrencyExchange getExchangeValue(@PathVariable String from, @PathVariable String to) {
		//CurrencyExchange currentExchange = new CurrencyExchange(100L,from,to,BigDecimal.valueOf(82));
		CurrencyExchange currentExchange =  repository.findByFromAndTo(from, to);
		if(currentExchange==null) {
			throw new RuntimeException("Unable to find data "+from +" to " +to);
		}
		String port = environment.getProperty("local.server.port");
		currentExchange.setEnvironment(port);
		
		return currentExchange;
	}
}
