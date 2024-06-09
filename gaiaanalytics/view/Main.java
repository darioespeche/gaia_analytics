package com.gaiaanalytics.view;

import com.gaiaanalytics.controller.ControlCargaDatos;
import com.gaiaanalytics.database.UserDAO;
import com.gaiaanalytics.database.DatoSueloDAO;
import com.gaiaanalytics.database.AnalisisDAO;
import com.gaiaanalytics.database.InformeDAO;

public class Main {
    public static void main(String[] args) {
        // Crear instancias de DAO para la conexión con la base de datos
        UserDAO userDAO = new UserDAO();
        DatoSueloDAO datoSueloDAO = new DatoSueloDAO();
        AnalisisDAO analisisDAO = new AnalisisDAO();
        InformeDAO informeDAO = new InformeDAO();

        // Crear instancia de ControlCargaDatos y pasar los DAOs
        ControlCargaDatos controlCargaDatos = new ControlCargaDatos(datoSueloDAO, analisisDAO, informeDAO);

        // Crear y mostrar el menú principal
        Menu menu = new Menu(controlCargaDatos, userDAO);
        menu.mostrarMenu();
    }
}
