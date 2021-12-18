package com.course.spring.boot.webflux.WebFluxCourse.API;

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