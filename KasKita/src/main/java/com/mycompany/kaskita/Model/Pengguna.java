package com.mycompany.kaskita.Model;

public class Pengguna {

    public static final String ADMIN = "Admin";
    public static final String OWNER = "Owner";
    public static final String USER = "User";

    private final int idPengguna;
    private final String username;
    private final String password;
    private final String role;

    public Pengguna(int idPengguna, String username, String password, String role) {
        this.idPengguna = idPengguna;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public int getIdPengguna() {
        return idPengguna;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }
}
