package com.week10.gateway.filter;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class CustomGatewayFilter extends AbstractGatewayFilterFactory<CustomGatewayFilter.Config> {
    private final WebClient.Builder webClientBuilder;

    public CustomGatewayFilter(WebClient.Builder webClientBuilder) {
        super(Config.class);
        this.webClientBuilder = webClientBuilder;
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            String apiKey = exchange.getRequest().getHeaders().getFirst("api-key");

            if (apiKey == null) {
                log.error("Missing API Key");
                exchange.getResponse().setStatusCode(config.getErrorStatus());
                return exchange.getResponse().setComplete();
            }

            return validateApiKey(exchange, apiKey)
                .flatMap(valid -> {
                    if (!valid) {
                        log.error("Invalid API Key");
                        exchange.getResponse().setStatusCode(config.getErrorStatus());
                        return exchange.getResponse().setComplete();
                    }
                    return chain.filter(exchange);
                });
        };
    }

    private Mono<Boolean> validateApiKey(ServerWebExchange exchange, String apiKey) {
        return webClientBuilder.build()
                .get()
                .uri("http://localhost:8083/api/v2/authentication/validate?apiKey=" + apiKey)
                .retrieve()
                .bodyToMono(Boolean.class)
                .onErrorReturn(false);
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Config {
        private HttpHeaders headers;
        private org.springframework.http.HttpStatus errorStatus = org.springframework.http.HttpStatus.UNAUTHORIZED;
    }
}
