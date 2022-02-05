package application;

import view.LoginView;

import configuration.MustacheMySQLConnection;
import dao.ClientDAO;
import java.util.List;
import javax.swing.JOptionPane;

import model.Client;

public class main {

    public static void main(String[] args) {
        LoginView lv = new LoginView(null, true);
        lv.setVisible(true);

        //JOptionPane.showInputDialog(null, "Insira uma quantidade", "Oioi");

    }
}
