package com.gaiaanalytics.database;

import com.gaiaanalytics.model.DatoSuelo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatoSueloDAO {
    // Método para añadir un nuevo dato de suelo a la base de datos
    public void addDatoSuelo(DatoSuelo datoSuelo) {
        String sql = "INSERT INTO datos_suelo (fecha, ubicacion, ph, nutrientes) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setDate(1, Date.valueOf(datoSuelo.getFecha()));
            pstmt.setString(2, datoSuelo.getUbicacion());
            pstmt.setFloat(3, datoSuelo.getPh());
            pstmt.setString(4, datoSuelo.getNutrientes());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para obtener todos los datos de suelo desde la base de datos
    public List<DatoSuelo> getAllDatosSuelo() {
        List<DatoSuelo> datosSuelo = new ArrayList<>();
        String sql = "SELECT * FROM datos_suelo";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                DatoSuelo datoSuelo = new DatoSuelo(
                        rs.getInt("dato_id"),
                        rs.getDate("fecha").toLocalDate(),
                        rs.getString("ubicacion"),
                        rs.getFloat("ph"),
                        rs.getString("nutrientes")
                );
                datosSuelo.add(datoSuelo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return datosSuelo;
    }
    //Añadiremos un método en DatoSueloDAO para verificar si un datoId existe.
    public boolean datoIdExists(int datoId) {
        String sql = "SELECT COUNT(*) FROM datos_suelo WHERE dato_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, datoId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public List<Integer> obtenerIdsDatosSuelo() {
        List<Integer> ids = new ArrayList<>();
        String sql = "SELECT dato_id FROM datos_suelo";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                ids.add(rs.getInt("dato_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ids;
    }

}

