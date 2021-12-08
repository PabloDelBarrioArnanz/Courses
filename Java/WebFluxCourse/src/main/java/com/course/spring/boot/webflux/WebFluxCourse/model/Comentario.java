package com.course.spring.boot.webflux.WebFluxCourse.model;

import java.util.ArrayList;
import java.util.List;

public class Comentario {

    private List<String> comentarios;

    public Comentario(List<String> comentarios) {
        this.comentarios = comentarios;
    }

    public List<String> getComentarios() {
        return comentarios;
    }

    public void addComentario(String comentario) {
        comentarios.add(comentario);
    }

    @Override
    public String toString() {
        return "Comentario {" +
                "comentarios => " + comentarios +
                '}';
    }
}
