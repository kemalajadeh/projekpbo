package com.mycompany.kaskita.Controller;

import com.mycompany.kaskita.Database.koneksiDB;
import com.mycompany.kaskita.Model.Kategori;
import com.mycompany.kaskita.Model.Pengguna;
import com.mycompany.kaskita.Model.Transaksi;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Method yang gagal validasi melempar IllegalArgumentException,
 * pesannya bisa langsung ditampilkan ke user.
 */
public class PengendaliTransaksi {

    /** Mengambil Riwayat transaksi, yang terbaru di paling atas. */
    public List<Transaksi> ambilSemua() {
        List<Transaksi> hasil = new ArrayList<>();
        String sql = "SELECT t.id_transaksi, t.tanggal, t.nominal, t.keterangan, " +
                     "k.id_kategori, k.nama_kategori, k.jenis, " +
                     "p.id_pengguna, p.username, p.role " +
                     "FROM tabel_transaksi t " +
                     "JOIN tabel_kategori k ON t.id_kategori = k.id_kategori " +
                     "JOIN tabel_pengguna p ON t.id_pengguna = p.id_pengguna " +
                     "ORDER BY t.tanggal DESC, t.id_transaksi DESC";

        try (Connection conn = koneksiDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Kategori k = new Kategori(
                    rs.getInt("id_kategori"),
                    rs.getString("nama_kategori"),
                    rs.getString("jenis")
                );
                Pengguna p = new Pengguna(
                    rs.getInt("id_pengguna"),
                    rs.getString("username"),
                    "",
                    rs.getString("role")
                );
                Transaksi t = new Transaksi(
                    k,
                    p,
                    rs.getDate("tanggal").toLocalDate(),
                    rs.getDouble("nominal"),
                    rs.getString("keterangan")
                );
                t.setIdTransaksi(rs.getInt("id_transaksi"));
                hasil.add(t);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return hasil;
    }

    public void tambah(Transaksi t) throws SQLException {
        validasi(t);
        try (Connection conn = koneksiDB.getConnection()) {
            int idKat = dapatkanAtauBuatKategori(conn, t.getKategori().getNamaKategori(), t.getKategori().getJenis());

            String sql = "INSERT INTO tabel_transaksi (id_kategori, id_pengguna, tanggal, nominal, keterangan) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                ps.setInt(1, idKat);
                ps.setInt(2, t.getPengguna().getIdPengguna());
                ps.setDate(3, Date.valueOf(t.getTanggal()));
                ps.setDouble(4, t.getNominal());
                ps.setString(5, t.getKeterangan());
                ps.executeUpdate();

                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        t.setIdTransaksi(rs.getInt(1));
                    }
                }
            }
        }
    }

    public void ubah(Transaksi t) throws SQLException {
       validasi(t);
        try (Connection conn = koneksiDB.getConnection()) {
            int idKat = dapatkanAtauBuatKategori(conn, t.getKategori().getNamaKategori(), t.getKategori().getJenis());

            String sql = "UPDATE tabel_transaksi SET id_kategori = ?, tanggal = ?, nominal = ?, keterangan = ? WHERE id_transaksi = ?";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setInt(1, idKat);
                ps.setDate(2, Date.valueOf(t.getTanggal()));
                ps.setDouble(3, t.getNominal());
                ps.setString(4, t.getKeterangan());
                ps.setInt(5, t.getIdTransaksi());

                int terubah = ps.executeUpdate();
                if (terubah == 0) {
                    throw new IllegalArgumentException("Transaksi tidak ditemukan di database.");
                }
            }
        }
    }

    public void hapus(int idTransaksi) throws SQLException {
        String sql = "DELETE FROM tabel_transaksi WHERE id_transaksi = ?";
        try (Connection conn = koneksiDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idTransaksi);
            int terhapus = ps.executeUpdate();
            if (terhapus == 0) {
                throw new IllegalArgumentException("Transaksi tidak ditemukan di database.");
            }
        }
    }

    private int dapatkanAtauBuatKategori(Connection conn, String namaKategori, String jenis) throws SQLException {
        String jenisValid = jenis.trim().equalsIgnoreCase(Kategori.PEMASUKAN) ? Kategori.PEMASUKAN : Kategori.PENGELUARAN;

        String cekSql = "SELECT id_kategori FROM tabel_kategori WHERE LOWER(nama_kategori) = LOWER(?) AND jenis = ?";
        try (PreparedStatement ps = conn.prepareStatement(cekSql)) {
            ps.setString(1, namaKategori.trim());
            ps.setString(2, jenisValid);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("id_kategori");
                }
            }
        }
        
        String insertSql = "INSERT INTO tabel_kategori (nama_kategori, jenis) VALUES (?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(insertSql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, namaKategori.trim());
            ps.setString(2, jenisValid);
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        }
        throw new SQLException("Gagal mendapatkan atau membuat kategori.");
    }
    
    private void validasi(Transaksi t) {
        if (t.getKategori() == null || t.getKategori().getNamaKategori().isBlank()) {
            throw new IllegalArgumentException("Kategori wajib diisi.");
        }
        String jenis = t.getKategori().getJenis();
        if (!Kategori.PEMASUKAN.equalsIgnoreCase(jenis) && !Kategori.PENGELUARAN.equalsIgnoreCase(jenis)) {
            throw new IllegalArgumentException("Jenis harus diisi 'Pemasukan' atau 'Pengeluaran'.");
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
