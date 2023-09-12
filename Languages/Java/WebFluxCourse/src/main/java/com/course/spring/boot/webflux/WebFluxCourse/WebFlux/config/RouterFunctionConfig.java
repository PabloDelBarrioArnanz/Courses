package com.course.spring.boot.webflux.WebFluxCourse.WebFlux.config;

import com.course.spring.boot.webflux.WebFluxCourse.WebFlux.handler.ProductHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
@RequiredArgsConstructor
public class RouterFunctionConfig {

  private static final String API = "/api/v3/products";

  private final ProductHandler productHandler;

  @Bean
  public RouterFunction<ServerResponse> routes() {
    return route(GET(API).or(GET(API.concat("/list"))), productHandler::list)
        .andRoute(GET(API.concat("/detail")), productHandler::detail)
        .andRoute(POST(API), productHandler::create);
  }
}
