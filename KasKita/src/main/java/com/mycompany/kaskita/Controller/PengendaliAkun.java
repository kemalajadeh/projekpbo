package com.mycompany.kaskita.Controller;

import com.mycompany.kaskita.Model.Pengguna;

public class PengendaliAkun {

    /** ngembaliin user kalo username dan password cocok, atau null kalau gagal. */
    public Pengguna login(String username, String password) {
        if (username == null || password == null || username.isBlank() || password.isEmpty()) {
            return null;
        }
        for (Pengguna p : DataDummy.PENGGUNA) {
            if (p.getUsername().equals(username.trim()) && p.getPassword().equals(password)) {
                return p;
            }
        }
        return null;
    }
}
