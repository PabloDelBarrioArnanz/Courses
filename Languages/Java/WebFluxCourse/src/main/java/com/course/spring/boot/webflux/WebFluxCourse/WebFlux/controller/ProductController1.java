package com.course.spring.boot.webflux.WebFluxCourse.WebFlux.controller;

import com.course.spring.boot.webflux.WebFluxCourse.WebFlux.document.Product;
import com.course.spring.boot.webflux.WebFluxCourse.WebFlux.service.ProductService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@RestController
@RequestMapping("/api/v1/products")
@AllArgsConstructor
public class ProductController1 {

    private static final Logger logger = LoggerFactory.getLogger(ProductController1.class);

    private final ProductService productService;


    //Bloquea hasta que tengamos todos los elementos
    @GetMapping({"/list1"})
    public Flux<Product> listProducts() {
        return productService.findAll();
    }

    @GetMapping("/list2")
    public Flux<Product> list2() {
        return productService.findAll()
                .delayElements(Duration.ofSeconds(1));
    }

    //Si obtener los resultados es un proceso largo es mejor no esperar e ir devolviendo cuando los tengamos
    @GetMapping(value = "/list3", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Product> list3() {
       return productService.findAll()
               .delayElements(Duration.ofSeconds(1));
    }

    @GetMapping("/detail")
    public Mono<Product> getDetail(@RequestParam String id) {
        /*return productRepository.findAll()
                .filter(product -> product.getId().equals(id))
                .next();*/ //Encontrar y quedarse con el primero
        return productService.findById(id);
    }

    @PostMapping
    public Mono<Product> save(@RequestBody Product product) {
        return productService.save(product);
    }

    @DeleteMapping
    public Mono<Void> deleteById(@RequestParam String id) {
        return productService.deleteById(id);
    }
}
