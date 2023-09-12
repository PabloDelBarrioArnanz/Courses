package com.course.spring.boot.webflux.WebFluxCourse.WebFlux.controller;

import com.course.spring.boot.webflux.WebFluxCourse.WebFlux.document.Product;
import com.course.spring.boot.webflux.WebFluxCourse.WebFlux.service.ProductService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.WebExchangeBindException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.time.Duration;

@RestController
@RequestMapping("/api/v2/products")
@AllArgsConstructor
public class ProductController2 {

    private static final Logger logger = LoggerFactory.getLogger(ProductController2.class);

    private final ProductService productService;

    @GetMapping("/list1") //blocking
    public Mono<ResponseEntity<Flux<Product>>> list1() {
        return Mono.just(ResponseEntity.ok(productService.findAll()
                .delayElements(Duration.ofSeconds(1))));
    }

    @GetMapping("/list2")
    public Mono<ResponseEntity<Flux<Product>>> list2() {
        return Mono.just(ResponseEntity.ok()
                .contentType(MediaType.TEXT_EVENT_STREAM)
                .body(productService.findAll()
                        .delayElements(Duration.ofSeconds(1))));
    }

    @GetMapping("/detail")
    public Mono<ResponseEntity<Product>> getDetail(@RequestParam String id) {
        //Si queremos saber si el producto existe o no para devolver 200 o 404 no podemos devolver un mono de product porque
        //lo hemos bloqueado hasta que se ha completado

        /*Mono<ResponseEntity<Mono<Product>>> just = Mono.just(ResponseEntity.ok()
                .body(productService.findById(id)));*/

        return productService.findById(id)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Mono<ResponseEntity<Product>> save(@RequestBody Product product) {
        return productService.save(product)
                .map(prod -> ResponseEntity.created(URI.create("/api/v2/products/detail?id=".concat(prod.getId())))
                        .body(prod))
                .onErrorResume(throwable -> Mono.just(throwable)
                        .cast(WebExchangeBindException.class)
                        .map(error -> ResponseEntity.badRequest().build()));
    }

    @PutMapping("/edit")
    public Mono<ResponseEntity<Product>> edit(@RequestParam String id, @RequestBody Product product) {
        return productService.findById(id)
                .map(prod -> {
                    prod.setName(product.getName());
                    prod.setPrice(product.getPrice());
                    return prod;
                })
                .flatMap(productService::save)
                .map(prod -> ResponseEntity.created(URI.create("/api/v2/products/detail?id=".concat(prod.getId())))
                        .body(prod))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @DeleteMapping
    public Mono<ResponseEntity<Void>> delete(@RequestParam String id) {
        return productService.deleteById(id)
                .map(none -> ResponseEntity.noContent().build());
    }
}
