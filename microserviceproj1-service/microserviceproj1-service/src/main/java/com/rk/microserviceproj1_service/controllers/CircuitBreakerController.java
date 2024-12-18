package com.rk.microserviceproj1_service.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
/** 
 * This is class is for circuit breaker.
 * Here if the /sample-api url will fail and then it call the fallbackMethod="hardcodedResponse"
 * And this is possible using resilience4j
 * */
@RestController
public class CircuitBreakerController {
	private Logger logger = LoggerFactory.getLogger(CircuitBreakerController.class);
	
	/**
	 * @Retry is use to retry the request depends on maxRetryAttempts property configured 
	 * in application.properties file and then call hardcodedResponse method.
	 * 
	 *  @CircuitBreaker hit the api and if it see the API is failing every time
	 *  then it will return the default response without calling sampleAPI method 
	*/
	//@Retry(name="sample-api", fallbackMethod = "hardcodedResponse")
	@GetMapping("/sample-api")
	@CircuitBreaker(name="default", fallbackMethod = "hardcodedResponse")
	//RateLimiter is used to set numbers of calls to the api within the time period
	//@RateLimiter(name="default")
	
	//Bulkhead is used to set max concurrent call to the API
	@Bulkhead(name="default")
	public String sampleApi() {
		logger.info("Sample APi call received");
		ResponseEntity<String> forEntity = new RestTemplate().getForEntity("http://localhost:8080/some-dummy", String.class);
		return forEntity.getBody();
	}
	public String hardcodedResponse(Exception ex) {
		return "fallback-reponse";
	}
}
