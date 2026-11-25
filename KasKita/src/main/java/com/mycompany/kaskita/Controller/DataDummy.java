package com.mycompany.kaskita.Controller;

import com.mycompany.kaskita.Model.Kategori;
import com.mycompany.kaskita.Model.Pengguna;
import com.mycompany.kaskita.Model.Transaksi;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

class DataDummy {
    static final List<Pengguna> PENGGUNA = List.of(
            new Pengguna(1, "laila", "admin123", Pengguna.ADMIN),
            new Pengguna(2, "owner", "owner123", Pengguna.OWNER));

    static final List<Kategori> KATEGORI = new ArrayList<>(List.of(
            new Kategori(1, "Penjualan", Kategori.PEMASUKAN),
            new Kategori(2, "Gaji Karyawan", Kategori.PENGELUARAN),
            new Kategori(3, "Operasional", Kategori.PENGELUARAN),
            new Kategori(4, "Listrik", Kategori.PENGELUARAN)));

    static final List<Transaksi> TRANSAKSI = new ArrayList<>();

    static {
        Pengguna laila = PENGGUNA.get(0);
        LocalDate hariIni = LocalDate.now();
        // Bulan ini: pemasukan 15.000.000, pengeluaran 8.500.000
        tambahAwal(1, KATEGORI.get(0), laila, hariIni.withDayOfMonth(1), 10_000_000, "Penjualan minggu 1");
        tambahAwal(2, KATEGORI.get(0), laila, hariIni, 5_000_000, "Penjualan hari ini");
        tambahAwal(3, KATEGORI.get(1), laila, hariIni.withDayOfMonth(1), 5_000_000, "Gaji karyawan");
        tambahAwal(4, KATEGORI.get(2), laila, hariIni, 2_000_000, "Beli bahan baku");
        tambahAwal(5, KATEGORI.get(3), laila, hariIni, 1_500_000, "Tagihan listrik");
        // Bulan lalu: tidak boleh ikut terhitung di dashboard
        tambahAwal(6, KATEGORI.get(0), laila, hariIni.minusMonths(1), 3_000_000, "Penjualan bulan lalu");
    }

    private static void tambahAwal(int id, Kategori kategori, Pengguna pengguna,
            LocalDate tanggal, double nominal, String keterangan) {
        Transaksi t = new Transaksi(kategori, pengguna, tanggal, nominal, keterangan);
        t.setIdTransaksi(id);
        TRANSAKSI.add(t);
    }
}
