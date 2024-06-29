package com.gaiaanalytics.controller;

import com.gaiaanalytics.database.AnalisisDAO;
import com.gaiaanalytics.database.DatoSueloDAO;
import com.gaiaanalytics.database.InformeDAO;
import com.gaiaanalytics.model.AnalisisConcreto;
import com.gaiaanalytics.model.DatoSuelo;
import com.gaiaanalytics.model.Informe;

import java.sql.SQLException;
import java.util.List;

public class ControlCargaDatos {
    private DatoSueloDAO datoSueloDAO;
    private AnalisisDAO analisisDAO;
    private InformeDAO informeDAO;

    public ControlCargaDatos(DatoSueloDAO datoSueloDAO, AnalisisDAO analisisDAO, InformeDAO informeDAO) {
        this.datoSueloDAO = datoSueloDAO;
        this.analisisDAO = analisisDAO;
        this.informeDAO = informeDAO;
    }

    public void cargarDatoSuelo(DatoSuelo datoSuelo) throws SQLException {
        datoSueloDAO.addDatoSuelo(datoSuelo);
    }

    public List<DatoSuelo> obtenerDatosSuelo() throws SQLException {
        return datoSueloDAO.getAllDatosSuelo();
    }

    public void cargarAnalisis(AnalisisConcreto analisis) throws SQLException {
        analisisDAO.addAnalisis(analisis);
    }

    public List<AnalisisConcreto> obtenerAnalisis() throws SQLException {
        return analisisDAO.getAllAnalisis();
    }

    public void cargarInforme(Informe informe) throws SQLException {
        informeDAO.addInforme(informe);
    }

    public List<Informe> obtenerInformes() throws SQLException {
        return informeDAO.getAllInformes();
    }

    public boolean datoSueloExiste(int id) throws SQLException {
        return datoSueloDAO.datoIdExists(id);
    }
}

