package com.week10.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator; 
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder; 
import org.springframework.context.annotation.Bean; 

@SpringBootApplication
public class GatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayApplication.class, args);
	}

	@Bean
    public RouteLocator routerBuilder(RouteLocatorBuilder routeLocatorBuilder){ 
        return routeLocatorBuilder.routes() 
                        .route("post",r->r.path("/api/v2/posts/**") 
                                .uri("http://localhost:8081/")) 
                        .route("comment",r->r.path("/api/v2/comments/**") 
                                .uri("http://localhost:8082/")).build(); 
    } 

}
