package com.gaiaanalytics.database;
import com.gaiaanalytics.model.Usuario;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    // Método para añadir un nuevo usuario a la base de datos
    public void addUser(Usuario user) {
        String sql = "INSERT INTO usuarios (nombre_usuario, contrasena) VALUES (?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, user.getNombreUsuario());
            pstmt.setString(2, user.getContrasena());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para obtener todos los usuarios desde la base de datos
    public List<Usuario> getAllUsers() {
        List<Usuario> users = new ArrayList<>();
        String sql = "SELECT * FROM usuarios";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Usuario user = new Usuario(
                        rs.getInt("usuario_id"),
                        rs.getString("nombre_usuario"),
                        rs.getString("contrasena")
                );
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public Usuario getUserByUsernameAndPassword(String username, String password) {
        String sql = "SELECT * FROM usuarios WHERE nombre_usuario = ? AND contrasena = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Usuario(rs.getInt("usuario_id"), rs.getString("nombre_usuario"), rs.getString("contrasena"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
