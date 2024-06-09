package com.gaiaanalytics.model;

import java.time.LocalDate;

// Clase que representa los datos del suelo.
public class DatoSuelo {
    private int id;
    private LocalDate fecha;
    private String ubicacion;
    private float ph;
    private String nutrientes;

    public DatoSuelo(int id, LocalDate fecha, String ubicacion, float ph, String nutrientes) {
        this.id = id;
        this.fecha = fecha;
        this.ubicacion = ubicacion;
        this.ph = ph;
        this.nutrientes = nutrientes;
    }

    public int getId() {
        return id;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public float getPh() {
        return ph;
    }

    public String getNutrientes() {
        return nutrientes;
    }

    @Override
    public String toString() {
        return "DatoSuelo{" +
                "id=" + id +
                ", fecha=" + fecha +
                ", ubicacion='" + ubicacion + '\'' +
                ", ph=" + ph +
                ", nutrientes='" + nutrientes + '\'' +
                '}';
    }
}
