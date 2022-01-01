package com.course.spring.boot.webflux.WebFluxCourse.WebFlux.service;

import com.course.spring.boot.webflux.WebFluxCourse.WebFlux.document.Product;
import com.course.spring.boot.webflux.WebFluxCourse.WebFlux.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public Flux<Product> findAll() {
        return productRepository.findAll();
    }

    public Mono<Product> findById(String id) {
        return productRepository.findById(id);
    }

    public Mono<Product> save(Product product) {
        return productRepository.save(product);
    }

    public Mono<Void> deleteById(String id) {
        return productRepository.deleteById(id);
    }
}
