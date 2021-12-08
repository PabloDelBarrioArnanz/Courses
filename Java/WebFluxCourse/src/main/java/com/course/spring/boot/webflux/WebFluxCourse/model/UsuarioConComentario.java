package com.course.spring.boot.webflux.WebFluxCourse.model;

public class UsuarioConComentario {

    private Usuario usuario;
    private Comentario comentario;

    public UsuarioConComentario(Usuario usuario, Comentario comentario) {
        this.usuario = usuario;
        this.comentario = comentario;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public Comentario getComentario() {
        return comentario;
    }

    @Override
    public String toString() {
        return "UsuarioConComentario {" +
                "usuario => " + usuario +
                ", comentario => " + comentario +
                '}';
    }
}
