package com.course.spring.boot.webflux.WebFluxCourse.WebFlux.handler;

import com.course.spring.boot.webflux.WebFluxCourse.WebFlux.document.Product;
import com.course.spring.boot.webflux.WebFluxCourse.WebFlux.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserter;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.net.URI;

@Component
@RequiredArgsConstructor
public class ProductHandler {

  private static final String API = "/api/v3/products";

  private final ProductService productService;

  public Mono<ServerResponse> list(ServerRequest request) {
    return ServerResponse.ok()
        .contentType(MediaType.TEXT_EVENT_STREAM)
        .body(productService.findAll(), Product.class);
  }

  public Mono<ServerResponse> detail(ServerRequest request) {
    return Mono.just(request.queryParam("id")
            .orElse("NONE"))
        .map(productService::findById)
        .flatMap(product -> ServerResponse.ok()
            .body(product, Product.class))
        .switchIfEmpty(ServerResponse.notFound().build());
  }

  public Mono<ServerResponse> create(ServerRequest request) {
    return request.bodyToMono(Product.class)
        .flatMap(productService::save)
        .flatMap(product -> ServerResponse.created(URI.create(API.concat("/detail?id=").concat(product.getId())))
            //Con body da error porque no es un Mono<Product> si no el product
            //bodyValue automaticamente hace Mono.just(product)
            .bodyValue(product));
  }
}
