package com.mycompany.kaskita.Model;

public class Kategori {

    public static final String PEMASUKAN = "Pemasukan";
    public static final String PENGELUARAN = "Pengeluaran";

    private final int idKategori;
    private final String namaKategori;
    private final String jenis;

    public Kategori(int idKategori, String namaKategori, String jenis) {
        this.idKategori = idKategori;
        this.namaKategori = namaKategori;
        this.jenis = jenis;
    }

    public int getIdKategori() {
        return idKategori;
    }

    public String getNamaKategori() {
        return namaKategori;
    }

    public String getJenis() {
        return jenis;
    }

    @Override
    public String toString() {
        return namaKategori;
    }
}
