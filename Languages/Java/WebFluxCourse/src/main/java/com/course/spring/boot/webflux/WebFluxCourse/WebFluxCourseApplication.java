package com.course.spring.boot.webflux.WebFluxCourse;

import com.course.spring.boot.webflux.WebFluxCourse.WebFlux.document.Product;
import com.course.spring.boot.webflux.WebFluxCourse.WebFlux.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import reactor.core.publisher.Flux;

import java.time.LocalDate;

@SpringBootApplication
public class WebFluxCourseApplication implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(WebFluxCourseApplication.class);

    /*@Autowired
    private Classes classes;*/

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ReactiveMongoTemplate reactiveMongoTemplate;

    public static void main(String[] args) {
        SpringApplication.run(WebFluxCourseApplication.class, args);
    }

    @Override
    public void run(String... args) {

        reactiveMongoTemplate.dropCollection("Products").subscribe();

        Flux.just(
                        new Product("TV Panasonic Pantalla LCD", 700d, LocalDate.now()),
                        new Product("Sony Camara HD", 500d, LocalDate.now()),
                        new Product("Sony Notebook", 499.99d, LocalDate.now()),
                        new Product("TV Sony Bravia OLED 4K", 1000d, LocalDate.now()),
                        new Product("HP Omen Notebook 17'", 249.99d, LocalDate.now()))
                .flatMap(productRepository::save)
                .subscribe(savedProduct -> logger.info("Insert: {}", savedProduct));

    }
}