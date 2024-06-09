package com.gaiaanalytics.model;

public abstract class Analisis {
    private int datoId;
    private String resultado;

    public Analisis(int datoId, String resultado) {
        this.datoId = datoId;
        this.resultado = resultado;
    }

    public int getDatoId() {
        return datoId;
    }

    public String getResultado() {
        return resultado;
    }
}

