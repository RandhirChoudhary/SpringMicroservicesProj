package com.rk.microserviceproj2_service.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.rk.microserviceproj2_service.entity.CurrencyConversion;


//@FeignClient(name="microserviceproj1-service", url="localhost:8080")
@FeignClient(name="microserviceproj1-service")
public interface CurrencyExchangeProxy {
	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	public CurrencyConversion retrieveExchangeValue(@PathVariable String from, @PathVariable String to);
}
