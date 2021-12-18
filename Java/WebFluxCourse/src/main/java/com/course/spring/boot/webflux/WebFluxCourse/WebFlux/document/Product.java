package com.course.spring.boot.webflux.WebFluxCourse.WebFlux.document;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@Document(collection = "Products") //Nombre de la coleccion
public class Product {

    private String id;
    private String name;
    private Double price;
    private LocalDate createAt;

    public Product() { }

    public Product(String name, Double price, LocalDate createAt) {
        this.name = name;
        this.price = price;
        this.createAt = createAt;
    }
}
