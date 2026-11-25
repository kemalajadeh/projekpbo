/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.kaskita.Main;

import javax.swing.SwingUtilities;
import com.mycompany.kaskita.UI.*;

/**
 *
 * @author chiyase
 */
public class KasKita {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginUI().setVisible(true));
    }
}
