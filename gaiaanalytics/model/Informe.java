package com.gaiaanalytics.model;
import java.time.LocalDate;

// Clase que representa un informe generado a partir de los análisis de suelo.
public class Informe {
    private int id;
    private int usuarioId;
    private LocalDate fecha;
    private String contenido;


    public Informe(int usuarioId, LocalDate fecha, String contenido) {
        this.usuarioId = usuarioId;
        this.fecha = fecha;
        this.contenido = contenido;
    }

    public Informe(int id, int usuarioId, String contenido) {
        this.id = id;
        this.usuarioId = usuarioId;
        this.contenido = contenido;
    }

    public Informe(int id, int usuarioId, LocalDate fecha, String contenido) {
        this.id = id;
        this.usuarioId = usuarioId;
        this.fecha = fecha;
        this.contenido = contenido;
    }

    public int getId() {
        return id;
    }

    public int getUsuarioId() {
        return usuarioId;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public String getContenido() {
        return contenido;
    }

    @Override
    public String toString() {
        return "Informe{" +
                "id=" + id +
                ", usuarioId=" + usuarioId +
                ", fecha=" + fecha +
                ", contenido='" + contenido + '\'' +
                '}';
    }
}
