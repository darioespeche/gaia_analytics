package com.gaiaanalytics.database;

import com.gaiaanalytics.model.Informe;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InformeDAO {
    // Método para añadir un nuevo informe a la base de datos
    public void addInforme(Informe informe) {
        String sql = "INSERT INTO informes (usuario_id, fecha, contenido) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, informe.getUsuarioId());
            pstmt.setDate(2, Date.valueOf(informe.getFecha()));
            pstmt.setString(3, informe.getContenido());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para obtener todos los informes desde la base de datos
    public List<Informe> getAllInformes() {
        List<Informe> informes = new ArrayList<>();
        String sql = "SELECT * FROM informes";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Informe informe = new Informe(
                        rs.getInt("informe_id"),
                        rs.getInt("usuario_id"),
                        rs.getDate("fecha").toLocalDate(),
                        rs.getString("contenido")
                );
                informes.add(informe);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return informes;
    }
}
