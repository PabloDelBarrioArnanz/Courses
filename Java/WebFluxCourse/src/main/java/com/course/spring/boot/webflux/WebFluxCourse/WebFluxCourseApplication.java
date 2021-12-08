package com.course.spring.boot.webflux.WebFluxCourse;

import com.course.spring.boot.webflux.WebFluxCourse.model.Comentario;
import com.course.spring.boot.webflux.WebFluxCourse.model.Usuario;
import com.course.spring.boot.webflux.WebFluxCourse.model.UsuarioConComentario;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Locale;
import java.util.stream.Stream;

@SpringBootApplication
public class WebFluxCourseApplication implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(WebFluxCourseApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(WebFluxCourseApplication.class, args);
    }

    @Override
    public void run(String... args) {
        class10();
    }

    //Combinar dos flujos con ZipWith
    private void class10() {
        Mono<Usuario> usuarioMono = Mono.fromCallable(() -> new Usuario("Pablo", "Barrio"));
        Mono<Comentario> comentarioMono = Mono.fromCallable(() -> new Comentario(List.of("Comentario 1", "Comentario 2")));

        usuarioMono.flatMap(usuario -> comentarioMono.map(comentario -> new UsuarioConComentario(usuario, comentario)))
                .subscribe(usuarioComentario -> logger.info(usuarioComentario.toString()));
    }

    //Combinar dos flujos con flatMap
    private void class9() {
        Mono<Usuario> usuarioMono = Mono.fromCallable(() -> new Usuario("Pablo", "Barrio"));
        Mono<Comentario> comentarioMono = Mono.fromCallable(() -> new Comentario(List.of("Comentario 1", "Comentario 2")));

        //usuarioMono.zipWith(comentarioMono, (usuario, comentario) -> new UsuarioConComentario(usuario, comentario));
        usuarioMono.zipWith(comentarioMono, UsuarioConComentario::new);
    }

    //collectList
    private void class8() {
        Flux.fromIterable(List.of(new Usuario("Pablo", "Barrio"), new Usuario("Carlos", "Perez"), new Usuario("Maria", "Alvarez")))
                .collectList()
                .subscribe(list -> logger.info(list.toString()));
    }

    //FlatMap 2
    private void class7() {
        Flux.fromIterable(List.of(new Usuario("Pablo", "Barrio"), new Usuario("Carlos", "Perez"), new Usuario("Maria", "Alvarez")))
                .map(usuario -> usuario.getNombre().toUpperCase(Locale.ROOT).toLowerCase(Locale.ROOT).concat(usuario.getApellido().toUpperCase(Locale.ROOT)))
                .flatMap(nombre -> {
                    if (nombre.contains("PABLO"))
                        return Mono.just(nombre);
                    else
                        return Mono.empty();
                })
                .subscribe(logger::info);
    }

    //FlatMap 1
    private void class6() {
        Flux.fromIterable(List.of("Pablo Barrio", "Carlos Perez", "Maria Alvarez", "Andres 2", "Juan 1"))
                .map(nombre -> nombre.split(" "))
                .map(nombre -> new Usuario(nombre[0].toUpperCase(Locale.ROOT), nombre[1].toUpperCase(Locale.ROOT)))
                .flatMap(usuario -> {
                    if (usuario.getNombre().equals("PABLO"))
                        return Mono.just(usuario);
                    else
                        return Mono.empty();
                })
                .subscribe(usuario -> logger.info(usuario.toString()));
    }

    //Flux from list, stream, array...
    private void class5() {
        Flux.fromIterable(List.of("Pablo", "Juan", "Pedro"));
        Flux.fromStream(Stream.of("Pablo", "Juan", "Pedro"));
        Flux.fromArray(new String[]{"Pablo", "Juan", "Pedro"});
    }

    //Inmutabilidad
    private void class4() {
        Flux<String> nombres = Flux.just("Pablo Barrio", "Carlos Perez", "Maria Alvarez", "Andres 2", "Juan 1");

        nombres.map(String::toUpperCase);

        nombres.subscribe(logger::info, error -> logger.error(error.getMessage()), () -> logger.info("Se terminó sin errores"));
        //Los flux son inmutables, esto devuelve los nombres en minuscula, el la operacion map esta creando un nuevo Flux que se pierde al no estar asignado.
    }

    //Filter
    private void class3() {
        Flux.just("Pablo Barrio", "Carlos Perez", "Maria Alvarez", "Andres 2", "Juan 1")
                .map(nombre -> nombre.split(" "))
                .map(nombre -> new Usuario(nombre[0].toUpperCase(Locale.ROOT), nombre[1].toUpperCase(Locale.ROOT)))
                .filter(usuario -> usuario.getNombre().equals("Carlos"))
                .doOnNext(usuario -> {
                    if (usuario == null)
                        throw new RuntimeException("Los nombres no pueden ser vacios");
                    else
                        logger.info(usuario.toString());
                })
                .map(usuario -> usuario.getNombre().toLowerCase(Locale.ROOT))
                .doOnComplete(() -> logger.info("Se terminó sin errores"))
                .subscribe();
    }

    //Map
    private void class2() {
        Flux.just("Pablo", "Carlos", "Maria", "Andres", "Juan")
                .map(nombre -> new Usuario(nombre.toUpperCase(Locale.ROOT), null))
                .doOnNext(usuario -> {
                    if (usuario == null)
                        throw new RuntimeException("Los nombres no pueden ser vacios");
                    else
                        logger.info(usuario.toString());
                })
                .map(usuario -> usuario.getNombre().toLowerCase(Locale.ROOT))
                .doOnComplete(() -> logger.info("Se terminó sin errores"))
                .subscribe();
    }

    //Flux
    private void class1() {
        Flux.just("Pablo", "Carlos", "Maria", "Andres", "Juan")
                .doOnNext(nombre -> {
                    if (nombre.isEmpty())
                        throw new RuntimeException("Los nombres no pueden ser vacios");
                    else
                        logger.info(nombre);
                })
                .subscribe(logger::info, error -> logger.error(error.getMessage()), () -> logger.info("Se terminó sin errores"));

        /*Flux.just("Pablo", "Carlos", "M", "Andres", "Juan")
                .doOnNext(nombre -> {
                    if (nombre.isEmpty())
                        throw new RuntimeException("Los nombres no pueden ser vacios");
                    else
                        logger.info(nombre);
                })
                .doOnNext(logger::info)
                .doOnError(error -> logger.error(error.getMessage()))
                .doOnComplete(() -> logger.info("Se terminó sin errores"))
                .subscribe();*/
    }
}


/*
 map(Funcition) -> ejecuta la funcion para todos los elementos ordenadamente
 flatMap(Funcition) -> ejecuta la funcion para todos los elementos ordenadamente
 filter(Predicate) -> ejecuta el predicado para todos los elementos ordenadamente
 zipWith(
 doOnNext(Consumer) -> sería un foreach, se ejecuta ordenado para todos los elementos (parecido al map pero para side effects)
 doOnError(Consumer) -> se ejecuta cuando se produce un error corta la ejecuión
 doOnComplete(Runnable) -> se ejecuta cuando termina de procesarse completamente el flujo
 collectList -> transforma de un flux de objetos a un mono de lista de objetos
 subscribe() -> se suscribe a la operación, puede agrupar doOnNext, doOnComplete, doOnError


 */
