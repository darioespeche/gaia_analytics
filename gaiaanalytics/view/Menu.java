package com.gaiaanalytics.view;

import com.gaiaanalytics.controller.ControlCargaDatos;
import com.gaiaanalytics.database.UserDAO;
import com.gaiaanalytics.model.AnalisisConcreto;
import com.gaiaanalytics.model.DatoSuelo;
import com.gaiaanalytics.model.Informe;
import com.gaiaanalytics.model.Usuario;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Menu {
    private ControlCargaDatos controlCargaDatos;
    private UserDAO userDAO;
    private Scanner scanner;

    public Menu(ControlCargaDatos controlCargaDatos, UserDAO userDAO) {
        this.controlCargaDatos = controlCargaDatos;
        this.userDAO = userDAO;
        this.scanner = new Scanner(System.in);
    }

    private boolean login() {
        System.out.print("Ingrese nombre de usuario: ");
        String username = scanner.nextLine();
        System.out.print("Ingrese contraseña: ");
        String password = scanner.nextLine();
        Usuario usuario = userDAO.getUserByUsernameAndPassword(username, password);
        if (usuario != null) {
            System.out.println("Inicio de sesión exitoso. Bienvenido " + username + "!");
            return true;
        } else {
            System.out.println("Nombre de usuario o contraseña incorrectos. Inténtelo de nuevo.");
            return false;
        }
    }


    public void mostrarMenu() {
        // Intentar iniciar sesión antes de mostrar el menú principal
        boolean loggedIn = false;
        while (!loggedIn) {
            loggedIn = login();
        }

        int opcion;
        boolean salir = false;

        while (!salir) {
            System.out.println("Menú:");
            System.out.println("1. Cargar Datos de Suelo");
            System.out.println("2. Mostrar Datos de Suelo");
            System.out.println("3. Añadir Usuario");
            System.out.println("4. Mostrar Usuarios");
            System.out.println("5. Añadir Análisis");
            System.out.println("6. Mostrar Análisis");
            System.out.println("7. Añadir Informe");
            System.out.println("8. Mostrar Informes");
            System.out.println("9. Salir");
            System.out.print("Seleccione una opción: ");

            try {
                opcion = scanner.nextInt();
                scanner.nextLine(); // Consumir nueva línea

                switch (opcion) {
                    case 1:
                        cargarDatos();
                        break;
                    case 2:
                        mostrarDatos();
                        break;
                    case 3:
                        añadirUsuario();
                        break;
                    case 4:
                        mostrarUsuarios();
                        break;
                    case 5:
                        añadirAnalisis();
                        break;
                    case 6:
                        mostrarAnalisis();
                        break;
                    case 7:
                        añadirInforme();
                        break;
                    case 8:
                        mostrarInformes();
                        break;
                    case 9:
                        salir = true;
                        break;
                    default:
                        System.out.println("Opción no válida. Intente de nuevo.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada no válida. Por favor intente de nuevo.");
                scanner.nextLine(); // Limpiar el buffer
            }
        }
    }

    private void cargarDatos() {
        try {
            System.out.print("Ingrese ID de la muestra: ");
            int id = scanner.nextInt();
            scanner.nextLine(); // Consumir nueva línea
            System.out.print("Ingrese Fecha (YYYY-MM-DD): ");
            String fechaStr = scanner.nextLine();
            LocalDate fecha = LocalDate.parse(fechaStr);
            System.out.print("Ingrese Ubicación o Nombre del Lugar: ");
            String ubicacion = scanner.nextLine();
            System.out.print("Ingrese pH: ");
            float ph = scanner.nextFloat();
            scanner.nextLine(); // Consumir nueva línea
            System.out.print("Ingrese Nutrientes (separados por comas, por ejemplo, N,P,K): ");
            String nutrientes = scanner.nextLine();
            DatoSuelo datoSuelo = new DatoSuelo(id, fecha, ubicacion, ph, nutrientes);
            controlCargaDatos.cargarDatoSuelo(datoSuelo);
            System.out.println("Datos cargados exitosamente.");
        } catch (InputMismatchException | DateTimeParseException | SQLException e) {
            System.out.println("Entrada no válida. Por favor intente de nuevo.");
            scanner.nextLine(); // Limpiar el buffer
        }
    }

    private void mostrarDatos() {
        try {
            List<DatoSuelo> datosSuelo = controlCargaDatos.obtenerDatosSuelo();
            if (datosSuelo.isEmpty()) {
                System.out.println("No hay datos de suelo disponibles.");
            } else {
                for (DatoSuelo dato : datosSuelo) {
                    System.out.println(dato);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener los datos de suelo: " + e.getMessage());
        }
    }

    private void añadirUsuario() {
        System.out.print("Ingrese ID del usuario: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consumir nueva línea
        System.out.print("Ingrese Nombre de Usuario: ");
        String nombreUsuario = scanner.nextLine();
        System.out.print("Ingrese Contraseña: ");
        String contrasena = scanner.nextLine();
        Usuario usuario = new Usuario(id, nombreUsuario, contrasena);
        userDAO.addUser(usuario);
        System.out.println("Usuario añadido exitosamente.");
    }

    private void mostrarUsuarios() {
        List<Usuario> usuarios = userDAO.getAllUsers();
        if (usuarios.isEmpty()) {
            System.out.println("No hay usuarios disponibles.");
        } else {
            for (Usuario usuario : usuarios) {
                System.out.println(usuario);
            }
        }
    }

    private void añadirAnalisis() {
        try {
            System.out.print("Ingrese ID del Dato Suelo asociado: ");
            int datoId = scanner.nextInt();
            scanner.nextLine(); // Consumir nueva línea

            // Validar que el datoId exista
            if (!controlCargaDatos.datoSueloExiste(datoId)) {
                System.out.println("El ID del Dato Suelo no existe. Inténtelo de nuevo.");
                return;
            }

            System.out.print("Ingrese Resultado del análisis: ");
            String resultado = scanner.nextLine();
            AnalisisConcreto analisis = new AnalisisConcreto(datoId, resultado);
            controlCargaDatos.cargarAnalisis(analisis);
            System.out.println("Análisis añadido exitosamente.");
        } catch (InputMismatchException | SQLException e) {
            System.out.println("Entrada no válida o error de base de datos. Por favor intente de nuevo.");
            scanner.nextLine(); // Limpiar el buffer
        }
    }

    private void mostrarAnalisis() {
        try {
            List<AnalisisConcreto> analisis = controlCargaDatos.obtenerAnalisis();
            if (analisis.isEmpty()) {
                System.out.println("No hay análisis disponibles.");
            } else {
                for (AnalisisConcreto a : analisis) {
                    System.out.println(a);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener los análisis: " + e.getMessage());
        }
    }

    private void añadirInforme() {
        try {
            System.out.print("Ingrese ID del Usuario asociado: ");
            int usuarioId = scanner.nextInt();
            scanner.nextLine(); // Consumir nueva línea
            System.out.print("Ingrese Fecha del Informe (YYYY-MM-DD): ");
            String fechaStr = scanner.nextLine();
            LocalDate fecha = LocalDate.parse(fechaStr);
            System.out.print("Ingrese Contenido del Informe: ");
            String contenido = scanner.nextLine();
            Informe informe = new Informe(usuarioId, fecha, contenido);
            controlCargaDatos.cargarInforme(informe);
            System.out.println("Informe añadido exitosamente.");
        } catch (InputMismatchException | DateTimeParseException | SQLException e) {
            System.out.println("Entrada no válida. Por favor intente de nuevo.");
            scanner.nextLine(); // Limpiar el buffer
        }
    }

    private void mostrarInformes() {
        try {
            List<Informe> informes = controlCargaDatos.obtenerInformes();
            if (informes.isEmpty()) {
                System.out.println("No hay informes disponibles.");
            } else {
                for (Informe informe : informes) {
                    System.out.println(informe);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener los informes: " + e.getMessage());
        }
    }
}

