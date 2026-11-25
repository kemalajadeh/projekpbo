package com.mycompany.kaskita.Controller;

import com.mycompany.kaskita.Model.Pengguna;
import com.mycompany.kaskita.Database.koneksiDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class PengendaliAkun {

    /** ngembaliin user kalo username dan password cocok, atau null kalau gagal. */
    public Pengguna login(String username, String password) {
        if (username == null || password == null || username.isBlank() || password.isEmpty()) {
            return null;
        }
        String sql = "SELECT * FROM tabel_pengguna WHERE username = ? AND password = ?";
        try (Connection conn = koneksiDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, username.trim());
            ps.setString(2, password);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Pengguna(
                        rs.getInt("id_pengguna"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("role")
                    );
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public boolean daftarUser(String username, String password) {
        String sql = "INSERT INTO tabel_pengguna (username, password, role) VALUES (?, ?, 'User')";
        try (Connection conn = koneksiDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, username.trim());
            ps.setString(2, password);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
