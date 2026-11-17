package com.mycompany.kaskita.Model;

import java.time.LocalDate;

public class Transaksi {

    private int idTransaksi;
    private Kategori kategori;
    private final Pengguna pengguna;
    private LocalDate tanggal;
    private double nominal;
    private String keterangan;

    // idTransaksi tidak diisi di sini, nanti diberikan oleh controller saat disimpan
    public Transaksi(Kategori kategori, Pengguna pengguna, LocalDate tanggal, double nominal, String keterangan) {
        this.kategori = kategori;
        this.pengguna = pengguna;
        this.tanggal = tanggal;
        this.nominal = nominal;
        this.keterangan = keterangan;
    }

    public int getIdTransaksi() {
        return idTransaksi;
    }

    public void setIdTransaksi(int idTransaksi) {
        this.idTransaksi = idTransaksi;
    }

    public Kategori getKategori() {
        return kategori;
    }

    public void setKategori(Kategori kategori) {
        this.kategori = kategori;
    }

    public Pengguna getPengguna() {
        return pengguna;
    }

    public LocalDate getTanggal() {
        return tanggal;
    }

    public void setTanggal(LocalDate tanggal) {
        this.tanggal = tanggal;
    }

    public double getNominal() {
        return nominal;
    }

    public void setNominal(double nominal) {
        this.nominal = nominal;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }
}
