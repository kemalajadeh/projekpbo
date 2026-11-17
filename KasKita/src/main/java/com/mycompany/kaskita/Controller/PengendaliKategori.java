package com.mycompany.kaskita.Controller;

import com.mycompany.kaskita.Model.Kategori;
import com.mycompany.kaskita.Model.Transaksi;
import java.util.ArrayList;
import java.util.List;

/**
 * Method yang gagal validasi melempar IllegalArgumentException,
 * pesannya bisa langsung ditampilkan ke user.
 */
public class PengendaliKategori {

    public List<Kategori> ambilSemua() {
        return new ArrayList<>(DataDummy.KATEGORI);
    }

    public void tambah(String namaKategori, String jenis) {
        if (namaKategori == null || namaKategori.isBlank()) {
            throw new IllegalArgumentException("Nama kategori wajib diisi.");
        }
        if (!Kategori.PEMASUKAN.equals(jenis) && !Kategori.PENGELUARAN.equals(jenis)) {
            throw new IllegalArgumentException("Jenis harus Pemasukan atau Pengeluaran.");
        }
        String nama = namaKategori.trim();
        for (Kategori k : DataDummy.KATEGORI) {
            if (k.getNamaKategori().equalsIgnoreCase(nama)) {
                throw new IllegalArgumentException("Kategori \"" + nama + "\" sudah ada.");
            }
        }
        int idBaru = DataDummy.KATEGORI.stream().mapToInt(Kategori::getIdKategori).max().orElse(0) + 1;
        DataDummy.KATEGORI.add(new Kategori(idBaru, nama, jenis));
    }

    public void hapus(int idKategori) {
        for (Transaksi t : DataDummy.TRANSAKSI) {
            if (t.getKategori().getIdKategori() == idKategori) {
                throw new IllegalArgumentException("Kategori masih dipakai transaksi, tidak bisa dihapus.");
            }
        }
        DataDummy.KATEGORI.removeIf(k -> k.getIdKategori() == idKategori);
    }
}
