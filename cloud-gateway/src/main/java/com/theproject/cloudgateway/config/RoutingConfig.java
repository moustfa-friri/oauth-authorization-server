package com.theproject.cloudgateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
public class RoutingConfig {

  //  @Bean
/*    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()

                .route("client-service", r -> r
                        .path(true,"/client-service/**")
                        .filters(f -> f.tokenRelay())
                        .uri("lb://CLIENT-SERVICE"))

                .build();
    }*/
}
