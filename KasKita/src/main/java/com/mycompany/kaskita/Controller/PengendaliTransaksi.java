package com.mycompany.kaskita.Controller;

import com.mycompany.kaskita.Model.Transaksi;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Method yang gagal validasi melempar IllegalArgumentException,
 * pesannya bisa langsung ditampilkan ke user.
 */
public class PengendaliTransaksi {

    /** Riwayat transaksi, yang terbaru di paling atas. */
    public List<Transaksi> ambilSemua() {
        List<Transaksi> hasil = new ArrayList<>(DataDummy.TRANSAKSI);
        hasil.sort(Comparator.comparing(Transaksi::getTanggal)
                .thenComparing(Transaksi::getIdTransaksi).reversed());
        return hasil;
    }

    public void tambah(Transaksi t) {
        validasi(t);
        int idBaru = DataDummy.TRANSAKSI.stream().mapToInt(Transaksi::getIdTransaksi).max().orElse(0) + 1;
        t.setIdTransaksi(idBaru);
        DataDummy.TRANSAKSI.add(t);
    }

    public void ubah(Transaksi t) {
        validasi(t);
        for (int i = 0; i < DataDummy.TRANSAKSI.size(); i++) {
            if (DataDummy.TRANSAKSI.get(i).getIdTransaksi() == t.getIdTransaksi()) {
                DataDummy.TRANSAKSI.set(i, t);
                return;
            }
        }
        throw new IllegalArgumentException("Transaksi tidak ditemukan.");
    }

    public void hapus(int idTransaksi) {
        if (!DataDummy.TRANSAKSI.removeIf(t -> t.getIdTransaksi() == idTransaksi)) {
            throw new IllegalArgumentException("Transaksi tidak ditemukan.");
        }
    }

    private void validasi(Transaksi t) {
        if (t.getKategori() == null) {
            throw new IllegalArgumentException("Kategori wajib dipilih.");
        }
        if (t.getPengguna() == null) {
            throw new IllegalArgumentException("Pengguna pencatat transaksi tidak boleh kosong.");
        }
        if (t.getTanggal() == null) {
            throw new IllegalArgumentException("Tanggal wajib diisi.");
        }
        if (t.getNominal() <= 0) {
            throw new IllegalArgumentException("Nominal harus lebih dari 0.");
        }
    }
}
