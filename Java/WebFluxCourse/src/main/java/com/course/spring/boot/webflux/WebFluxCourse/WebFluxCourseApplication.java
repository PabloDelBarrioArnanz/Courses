package com.course.spring.boot.webflux.WebFluxCourse;

import com.course.spring.boot.webflux.WebFluxCourse.API.Classes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WebFluxCourseApplication implements CommandLineRunner {

    @Autowired
    private Classes classes;

    public static void main(String[] args) {
        SpringApplication.run(WebFluxCourseApplication.class, args);
    }

    @Override
    public void run(String... args) {
        classes.class16();
    }
}