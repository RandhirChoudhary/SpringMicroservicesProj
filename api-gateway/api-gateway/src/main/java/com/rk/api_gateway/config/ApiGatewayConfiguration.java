package com.rk.api_gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiGatewayConfiguration {
	@Bean
	public RouteLocator getewayRouter(RouteLocatorBuilder builder) {
		return builder.routes()
				.route(p -> p.path("/get")
				.filters(f -> f
						.addRequestHeader("MyHeader", "MyURI")
						.addRequestParameter("param", "MyValue"))
				.uri("http://httpbin.org:80"))
				.route(p -> p.path("/currency-exchange/**")
				.uri("lb://microserviceproj1-service"))
				.route(p -> p.path("/currency-conversion-feign/**")
						.uri("lb://microserviceproj2-service"))
				.route(p -> p.path("/currency-conversion-new/**")
						.filters(f -> f.rewritePath("/currency-conversion-new/(?<segment>.*)", "/currency-conversion-feign/${segment}"))
						.uri("lb://microserviceproj2-service"))
				.build();
	}
}
