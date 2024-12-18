package com.rk.microserviceproj1_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rk.microserviceproj1_service.entity.CurrencyExchange;

public interface CurrencyExchangeRepository extends JpaRepository<CurrencyExchange, Long> {
	CurrencyExchange findByFromAndTo(String from, String to);
}
