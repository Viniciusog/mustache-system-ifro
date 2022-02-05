/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import javax.swing.JOptionPane;

/**
 *
 * @author vinij
 */
public class Alert {

    public static boolean showOptionDialog(String str, String title) {
        Object[] options = {"Cancelar", "Sim"};
        int response = JOptionPane.showOptionDialog(null, str,
                title, JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]);
        if (response == 1) {
            return true;
        } else {
            return false;
        }
    }
    
    public static void showMessageDialog(String str) {
        JOptionPane.showMessageDialog(null, str);
    }
    
    public static void showWarningMessageDialog(String str) {
          JOptionPane.showMessageDialog(null, str, null, JOptionPane.WARNING_MESSAGE);
    }
    
    public static void showErrorMessageDialog(String str) {
          JOptionPane.showMessageDialog(null, str, null, JOptionPane.ERROR_MESSAGE);
    }
}