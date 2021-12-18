package com.course.spring.boot.webflux.WebFluxCourse.WebFlux.controller;

import com.course.spring.boot.webflux.WebFluxCourse.WebFlux.document.Product;
import com.course.spring.boot.webflux.WebFluxCourse.WebFlux.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.time.Duration;

@RestController
@AllArgsConstructor
public class ProductController {

    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    private final ProductRepository productRepository;


    //Bloquea hasta que tengamos todos los elementos
    @GetMapping("/list1")
    public Flux<Product> listProducts() {
        return productRepository.findAll();
    }

    @GetMapping("/list2")
    public Flux<Product> list2() {
        return productRepository.findAll()
                .delayElements(Duration.ofSeconds(1));
    }

    //Si obtener los resultados es un proceso largo es mejor no esperar e ir devolviendo cuando los tengamos
    @GetMapping(value = "/list3", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Product> list3() {
       return productRepository.findAll()
               .delayElements(Duration.ofSeconds(1));
    }
}
