package com.week10.gateway;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator; 
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder; 
import org.springframework.context.annotation.Bean;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import com.week10.gateway.filter.CustomGatewayFilter;

@SpringBootApplication
@EnableDiscoveryClient
public class GatewayApplication {

    @Autowired
    private CustomGatewayFilter customGatewayFilter;

	public static void main(String[] args) {
		SpringApplication.run(GatewayApplication.class, args);
	}

	@Bean
    public RouteLocator routerBuilder(RouteLocatorBuilder routeLocatorBuilder){ 
        return routeLocatorBuilder.routes()
                .route("post", r -> r.path("/api/v2/posts/**")
                        .filters(f -> f.filter(customGatewayFilter.apply(new CustomGatewayFilter.Config())))
                        .uri("http://localhost:8081"))
                .route("comment", r -> r.path("/api/v2/comments/**")
                        .filters(f -> f.filter(customGatewayFilter.apply(new CustomGatewayFilter.Config())))
                        .uri("http://localhost:8082"))
                .build();
    } 

}
