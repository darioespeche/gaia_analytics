package com.gaiaanalytics.controller;

import com.gaiaanalytics.database.*;
import com.gaiaanalytics.model.*;

import java.util.List;

public class ControlCargaDatos {
    private DatoSueloDAO datoSueloDAO;
    private AnalisisDAO analisisDAO;
    private InformeDAO informeDAO;
    private UserDAO userDAO;


    public ControlCargaDatos(DatoSueloDAO datoSueloDAO, AnalisisDAO analisisDAO, InformeDAO informeDAO) {
        this.datoSueloDAO = datoSueloDAO;
        this.analisisDAO = analisisDAO;
        this.informeDAO = informeDAO;
    }
    public ControlCargaDatos() {
        datoSueloDAO = new DatoSueloDAO(); // Inicialización del DAO para DatoSuelo
        analisisDAO = new AnalisisDAO();   // Inicialización del DAO para Analisis
        informeDAO = new InformeDAO();     // Inicialización del DAO para Informes
        userDAO = new UserDAO();           // Inicialización del DAO para Usuarios
    }

    // Métodos para cargar datos en la base de datos utilizando los DAOs

    public void cargarDatos(DatoSuelo datoSuelo) {
        datoSueloDAO.addDatoSuelo(datoSuelo); // Carga un nuevo dato de suelo en la base de datos
    }

    public List<DatoSuelo> obtenerDatosSuelo() {
        return datoSueloDAO.getAllDatosSuelo(); // Obtiene todos los datos de suelo desde la base de datos
    }

    public void cargarAnalisis(AnalisisConcreto analisis) {
        analisisDAO.addAnalisis(analisis); // Carga un nuevo análisis en la base de datos
    }

    public List<Analisis> obtenerAnalisis() {
        return analisisDAO.getAllAnalisis(); // Obtiene todos los análisis desde la base de datos
    }

    public void cargarInforme(Informe informe) {
        informeDAO.addInforme(informe); // Carga un nuevo informe en la base de datos
    }

    public List<Informe> obtenerInformes() {
        return informeDAO.getAllInformes(); // Obtiene todos los informes desde la base de datos
    }

    public void cargarUsuario(Usuario usuario) {
        userDAO.addUser(usuario); // Carga un nuevo usuario en la base de datos
    }

    public List<Usuario> obtenerUsuarios() {
        return userDAO.getAllUsers(); // Obtiene todos los usuarios desde la base de datos
    }
}
