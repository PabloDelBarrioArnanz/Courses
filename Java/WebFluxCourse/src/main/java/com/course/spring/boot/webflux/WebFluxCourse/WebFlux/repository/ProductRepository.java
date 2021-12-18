package com.course.spring.boot.webflux.WebFluxCourse.WebFlux.repository;

import com.course.spring.boot.webflux.WebFluxCourse.WebFlux.document.Product;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface ProductRepository extends ReactiveMongoRepository<Product, String> { }
