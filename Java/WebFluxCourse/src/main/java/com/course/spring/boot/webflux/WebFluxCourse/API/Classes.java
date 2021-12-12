package com.course.spring.boot.webflux.WebFluxCourse.API;

import com.course.spring.boot.webflux.WebFluxCourse.API.model.Comentario;
import com.course.spring.boot.webflux.WebFluxCourse.API.model.Usuario;
import com.course.spring.boot.webflux.WebFluxCourse.API.model.UsuarioConComentario;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.*;
import java.util.stream.Stream;

@Component
public class Classes {

    private static final Logger logger = LoggerFactory.getLogger(Classes.class);

    //Backpressure
    public void class16() {
        Flux.range(1, 10)
                .log()
                .limitRate(3)
                .subscribe(i -> logger.info(i.toString()));
    }

    //Create
    public void class15() {
        Flux.create(emitter -> {
                    Timer timer = new Timer();
                    timer.schedule(new TimerTask() {

                        private Integer contador = 0;

                        @Override
                        public void run() {
                            emitter.next(++contador);
                            if (contador == 5) {
                                timer.cancel();
                                emitter.complete();
                            } else if (contador == 3)
                                emitter.error(new RuntimeException("Error!"));
                        }
                    }, 1000, 1000);
                })
                .subscribe(i -> logger.info(i.toString()), error -> logger.info(error.getMessage()));
    }

    //Retry
    public void class14() {
        Flux.just(new Random().nextBoolean())
                .flatMap(result -> {
                    if (result)
                        return Flux.just(true);
                    else
                        return Flux.error(new RuntimeException("False!"));
                })
                .retry(1)
                .subscribe(result -> logger.info(result.toString()), error -> logger.error(error.getMessage()));
        //Si sale false, se lanza la excepcion y se activa el retry a la siguiente si es false se devuelve error porque no quedan mas retries
    }

    //Intervalo infinito
    public void class13() {
        Flux.interval(Duration.ofSeconds(1))
                .flatMap(i -> {
                    if (i <= 5)
                        return Flux.error(new InterruptedException("Solo hasta 5!"));
                    else
                        return Flux.just(i);
                })
                .map(i -> "Hola " + i)
                .subscribe(logger::info, error -> logger.error(error.getMessage()));
    }

    //Intervalos
    public void class12() {
        Flux<Integer> rango = Flux.range(1, 5);
        Flux<Long> retraso = Flux.interval(Duration.ofSeconds(1));

        rango.zipWith(retraso, (rang, retr) -> rang)
                .subscribe(i -> logger.info(i.toString()));

        rango.delayElements(Duration.ofSeconds(1))
                .subscribe(i -> logger.info(i.toString()));
    }

    //Rangos
    public void class11() {
        Flux.just(1, 2, 3, 4)
                .map(i -> i * 2)
                .zipWith(Flux.range(1, 4), (i, j) -> String.format("Primer flux: %d, Segundo flux: %d", i, j))
                .subscribe(logger::info);
        /*
            Primer flux: 2, Segundo flux: 1
            Primer flux: 4, Segundo flux: 2
            Primer flux: 6, Segundo flux: 3
            Primer flux: 8, Segundo flux: 4
        */
    }

    //Combinar dos flujos con zipWith
    public void class10() {
        Mono<Usuario> usuarioMono = Mono.fromCallable(() -> new Usuario("Pablo", "Barrio"));
        Mono<Comentario> comentarioMono = Mono.fromCallable(() -> new Comentario(List.of("Comentario 1", "Comentario 2")));

        //usuarioMono.zipWith(comentarioMono, (usuario, comentario) -> new UsuarioConComentario(usuario, comentario));
        usuarioMono.zipWith(comentarioMono, UsuarioConComentario::new)
                .subscribe(usuarioComentario -> logger.info(usuarioComentario.toString()));

        //Otra forma
        usuarioMono.zipWith(comentarioMono)
                .map(tuple -> new UsuarioConComentario(tuple.getT1(), tuple.getT2()))
                .subscribe(usuarioComentario -> logger.info(usuarioComentario.toString()));
    }

    //Combinar dos flujos con flatMap
    public void class9() {
        Mono<Usuario> usuarioMono = Mono.fromCallable(() -> new Usuario("Pablo", "Barrio"));
        Mono<Comentario> comentarioMono = Mono.fromCallable(() -> new Comentario(List.of("Comentario 1", "Comentario 2")));

        usuarioMono.flatMap(usuario -> comentarioMono.map(comentario -> new UsuarioConComentario(usuario, comentario)))
                .subscribe(usuarioComentario -> logger.info(usuarioComentario.toString()));
    }

    //collectList
    public void class8() {
        Flux.fromIterable(List.of(new Usuario("Pablo", "Barrio"), new Usuario("Carlos", "Perez"), new Usuario("Maria", "Alvarez")))
                .collectList()
                .subscribe(list -> logger.info(list.toString()));
    }

    //FlatMap 2
    public void class7() {
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
    public void class6() {
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
    public void class5() {
        Flux.fromIterable(List.of("Pablo", "Juan", "Pedro"));
        Flux.fromStream(Stream.of("Pablo", "Juan", "Pedro"));
        Flux.fromArray(new String[]{"Pablo", "Juan", "Pedro"});
    }

    //Inmutabilidad
    public void class4() {
        Flux<String> nombres = Flux.just("Pablo Barrio", "Carlos Perez", "Maria Alvarez", "Andres 2", "Juan 1");

        nombres.map(String::toUpperCase);

        nombres.subscribe(logger::info, error -> logger.error(error.getMessage()), () -> logger.info("Se terminó sin errores"));
        //Los flux son inmutables, esto devuelve los nombres en minuscula, el la operacion map esta creando un nuevo Flux que se pierde al no estar asignado.
    }

    //Filter
    public void class3() {
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
    public void class2() {
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
    public void class1() {
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
 flatMap(Funcition) -> ejecuta la funcion y el aplanamiento para todos los elementos ordenadamente
 filter(Predicate) -> ejecuta el predicado para todos los elementos ordenadamente
 zipWith(Mono/Flux, Function) -> Combina dos flujos, si combinas un flujo con un flujo de tiempo, los elementos de primero se ejecutan en ese intervalo lo mismo que usar delayElements
 delayElements(Duration) -> Añade retardo entre los nodos del flujo
 retry(int) -> rejecuta tantas veces como le pidamos si aparece un error en el flujo
 limitRate(int) -> tamanio del batch para backpressure
 log() -> muestra traza de debug
 doOnNext(Consumer) -> sería un foreach, se ejecuta ordenado para todos los elementos (parecido al map pero para side effects)
 doOnError(Consumer) -> se ejecuta cuando se produce un error corta la ejecuión
 doOnComplete(Runnable) -> se ejecuta cuando termina de procesarse completamente el flujo
 doFinally(Runnable) -> como el finally de un try aunque falle o se cancele se ejecuta
 collectList -> transforma de un flux de objetos a un mono de lista de objetos
 subscribe() -> se suscribe a la operación, puede agrupar doOnNext, doOnComplete, doOnError
 */