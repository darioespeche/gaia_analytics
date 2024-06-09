package com.gaiaanalytics.database;

import com.gaiaanalytics.model.Analisis;
import com.gaiaanalytics.model.AnalisisConcreto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AnalisisDAO {
    // Método para añadir un nuevo análisis a la base de datos
    public void addAnalisis(AnalisisConcreto analisis) {
        String sql = "INSERT INTO analisis (dato_id, resultado) VALUES (?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, analisis.getDatoId());
            pstmt.setString(2, analisis.getResultado());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para obtener todos los análisis desde la base de datos
    public List<Analisis> getAllAnalisis() {
        List<Analisis> analisisList = new ArrayList<>();
        String sql = "SELECT * FROM analisis";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                AnalisisConcreto analisis = new AnalisisConcreto(
                        rs.getInt("analisis_id"),
                        //rs.getInt("dato_id"),
                        rs.getString("resultado")
                );
                analisisList.add(analisis);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return analisisList;
    }
}

