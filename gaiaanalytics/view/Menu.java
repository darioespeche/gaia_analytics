package com.gaiaanalytics.view;

import com.gaiaanalytics.controller.ControlCargaDatos;
import com.gaiaanalytics.model.*;
import com.gaiaanalytics.database.UserDAO;

import java.util.List;
import java.util.Scanner;
import java.util.InputMismatchException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Menu {
    private ControlCargaDatos controlCargaDatos;
    private UserDAO userDAO;
    private Scanner scanner;
    private boolean loggedIn;

    public Menu(ControlCargaDatos controlCargaDatos, UserDAO userDAO) {
        this.controlCargaDatos = controlCargaDatos;
        this.userDAO = userDAO;
        this.scanner = new Scanner(System.in);
        this.loggedIn = false;
    }



    //mostrarMenu proporciona las opciones principales del sistema y maneja la navegación a través de un bucle do-while.
    public void mostrarMenu() {
        while (!loggedIn) {
            loggedIn = login();
        }

        int opcion;
        do {
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
                        System.out.println("Saliendo...");
                        break;
                    default:
                        System.out.println("Opción no válida.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada no válida. Por favor ingrese un número.");
                scanner.nextLine(); // Limpiar el buffer
                opcion = -1;
            }
        } while (opcion != 9);

        scanner.close();
    }



    //Login valida las credenciales del usuario antes de permitir el acceso al menú principal.
    private boolean login() {
        System.out.println("Ingrese nombre de usuario:");
        String username = scanner.nextLine();
        System.out.println("Ingrese contraseña:");
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



    //cargarDatos recopila información del usuario y crea un objeto DatoSuelo que se guarda en la base de datos.
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
            controlCargaDatos.cargarDatos(datoSuelo);
            System.out.println("Datos cargados exitosamente.");
        } catch (InputMismatchException | DateTimeParseException e) {
            System.out.println("Entrada no válida. Por favor intente de nuevo.");
            scanner.nextLine(); // Limpiar el buffer
        }
    }


    //mostrarDatos Recupera y muestra todos los registros de DatoSuelo almacenados en la base de datos.
    private void mostrarDatos() {
        List<DatoSuelo> datosSuelo = controlCargaDatos.obtenerDatosSuelo();
        if (datosSuelo.isEmpty()) {
            System.out.println("No hay datos de suelo disponibles.");
        } else {
            for (DatoSuelo ds : datosSuelo) {
                System.out.println(ds);
            }
        }
    }


    //los metodos añadirUsuario y mostrarUsuario Manejan la creación de nuevos usuarios y la visualización de todos los usuarios existentes.
    private void añadirUsuario() {
        try {
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
        } catch (InputMismatchException e) {
            System.out.println("Entrada no válida. Por favor intente de nuevo.");
            scanner.nextLine(); // Limpiar el buffer
        }
    }

    private void mostrarUsuarios() {
        List<Usuario> usuarios = userDAO.getAllUsers();
        if (usuarios.isEmpty()) {
            System.out.println("No hay usuarios disponibles.");
        } else {
            for (Usuario user : usuarios) {
                System.out.println(user);
            }
        }
    }


    //los metodos añadirAnalisis y mostrarAnalisis Permite la creación de nuevos análisis y la visualización de todos los análisis almacenados.
    private void añadirAnalisis() {
        try {
            //System.out.print("Ingrese ID del análisis: ");
            //int id = scanner.nextInt();
            //scanner.nextLine();  //Consumir nueva línea
            System.out.print("Ingrese ID del Dato Suelo asociado: ");
            int datoId = scanner.nextInt();
            scanner.nextLine(); // Consumir nueva línea
            System.out.print("Ingrese Resultado del análisis: ");
            String resultado = scanner.nextLine();
            AnalisisConcreto analisis = new AnalisisConcreto(datoId, resultado);
            controlCargaDatos.cargarAnalisis(analisis);
            System.out.println("Análisis añadido exitosamente.");
        } catch (InputMismatchException e) {
            System.out.println("Entrada no válida. Por favor intente de nuevo.");
            scanner.nextLine(); // Limpiar el buffer
        }
    }

    private void mostrarAnalisis() {
        List<Analisis> analisisList = controlCargaDatos.obtenerAnalisis();
        if (analisisList.isEmpty()) {
            System.out.println("No hay análisis disponibles.");
        } else {
            for (Analisis analisis : analisisList) {
                System.out.println(analisis);
            }
        }
    }


    // Los metodos añadirInforme y mostrarInforme Facilita la creación de nuevos informes y la visualización de todos los informes existentes.
    private void añadirInforme() {
        try {
            System.out.print("Ingrese ID del informe: ");
            int id = scanner.nextInt();
            scanner.nextLine(); // Consumir nueva línea
            System.out.print("Ingrese ID del Usuario asociado: ");
            int usuarioId = scanner.nextInt();
            scanner.nextLine(); // Consumir nueva línea
            System.out.print("Ingrese contenido del informe: ");
            String contenido = scanner.nextLine();
            Informe informe = new Informe(id, usuarioId, contenido);
            controlCargaDatos.cargarInforme(informe);
            System.out.println("Informe añadido exitosamente.");
        } catch (InputMismatchException e) {
            System.out.println("Entrada no válida. Por favor intente de nuevo.");
            scanner.nextLine(); // Limpiar el buffer
        }
    }

    private void mostrarInformes() {
        List<Informe> informes = controlCargaDatos.obtenerInformes();
        if (informes.isEmpty()) {
            System.out.println("No hay informes disponibles.");
        } else {
            for (Informe informe : informes) {
                System.out.println(informe);
            }
        }
    }
}
