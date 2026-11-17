/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.kaskita;

import javax.swing.SwingUtilities;

/**
 *
 * @author chiyase
 */
public class KasKita {

    public static void main(String[] args) {
        // TODO: ganti MainUI dengan TampilanLogin kalau sudah dibuat tim JFrame
        SwingUtilities.invokeLater(() -> new MainUI().setVisible(true));
    }
}
