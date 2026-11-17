package com.mycompany.kaskita.Controller;

import com.mycompany.kaskita.Model.Kategori;
import com.mycompany.kaskita.Model.Transaksi;
import java.time.YearMonth;
import java.util.List;

/**
 * Pemakaian:
 *   Kalkulasi k = new Kalkulasi(new PengendaliTransaksi().ambilSemua());
 *   k.proses();
 *   k.getTotalPemasukan(); k.getTotalPengeluaran(); k.getLabaBersih();
 */
public class Kalkulasi {

    private final List<Transaksi> daftarTransaksi;
    private double totalPemasukan;
    private double totalPengeluaran;
    private double labaBersih;

    public Kalkulasi(List<Transaksi> daftarTransaksi) {
        this.daftarTransaksi = daftarTransaksi;
    }

    /** ngitung total pemasukan, pengeluaran, dan laba bersih untuk bulan berjalan. */
    public void proses() {
        YearMonth bulanIni = YearMonth.now();
        totalPemasukan = 0;
        totalPengeluaran = 0;
        for (Transaksi t : daftarTransaksi) {
            if (!YearMonth.from(t.getTanggal()).equals(bulanIni)) {
                continue;
            }
            if (Kategori.PEMASUKAN.equals(t.getKategori().getJenis())) {
                totalPemasukan += t.getNominal();
            } else {
                totalPengeluaran += t.getNominal();
            }
        }
        labaBersih = totalPemasukan - totalPengeluaran;
    }

    public double getTotalPemasukan() {
        return totalPemasukan;
    }

    public double getTotalPengeluaran() {
        return totalPengeluaran;
    }

    public double getLabaBersih() {
        return labaBersih;
    }
}
