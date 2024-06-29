package com.gaiaanalytics.model;

public class AnalisisConcreto extends Analisis {
    private int datoId;
    private String resultado;

    public AnalisisConcreto(int datoId, String resultado) {
        super(datoId,resultado);
        this.datoId = datoId;
        this.resultado = resultado;
    }

    public int getDatoId() {
        return datoId;
    }

    public void setDatoId(int datoId) {
        this.datoId = datoId;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    @Override
    public String toString() {
        return "AnalisisConcreto{" +
                "datoId=" + datoId +
                ", resultado='" + resultado + '\'' +
                '}';
    }
}


