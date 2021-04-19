package com.course.spring.boot.webflux.WebFluxCourse;

import com.course.spring.boot.webflux.WebFluxCourse.model.User;
import io.netty.util.internal.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.Locale;
import java.util.stream.Stream;

@SpringBootApplication
public class WebFluxCourseApplication implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(WebFluxCourseApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(WebFluxCourseApplication.class, args);
    }

    @Override
    public void run(String... args) {

    }

    public void firstLessons() {
        Flux<String> fluxFromList = Flux.fromIterable(Arrays.asList("Bob Marley", "John Conor", "Peter Jackson"));
        Flux<String> fluxFromStream = Flux.fromStream(Stream.of("Bob Marley", "John Conor", "Peter Jackson"));

        Flux<User> names = Flux.just("Bob Marley", "John Conor", "Peter Jackson")
                .map(completeName -> completeName.split(" "))
                .map(splitName -> new User(splitName[0].toUpperCase(), splitName[1].toLowerCase()))
                .doOnNext(user -> {
                    if (user == null) throw new RuntimeException("Names cannot be empty");
                    else System.out.println(user.getName());
                })
                .filter(user -> user.getName().equals("BOB"));

        names.subscribe(user -> log.info(user.getName()), error -> log.error(error.getMessage()), () -> log.info("Finish!"));
    }

    public void flatMapLesson1() {
        Flux<User> names = Flux.just("Bob Marley", "John Conor", "Peter Jackson")
                .map(completeName -> completeName.split(" "))
                .map(splitName -> new User(splitName[0].toUpperCase(), splitName[1].toLowerCase()))
                .flatMap(user -> {
                    if (user.getName().equals("BOB")) return Mono.just(user);
                    else return Mono.empty();
                });

        names.subscribe(user -> log.info(user.toString()));
    }

    public void flatMapLesson2() {
        Flux<User> names = Flux.just("Bob Marley", "John Conor", "Peter Jackson")
                .map(completeName -> completeName.split(" "))
                .map(splitName -> new User(splitName[0].toUpperCase(), splitName[1].toLowerCase()))
                .flatMap(user -> {
                    if (user.getName().equals("BOB")) return Mono.just(user);
                    else return Mono.empty();
                });

        names.subscribe(user -> log.info(user.toString()));
    }
}
