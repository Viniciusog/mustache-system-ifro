/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import configuration.MustacheMySQLConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import model.Sex;

/**
 *
 * @author vinij
 */
public class SexDAO {

    public static Sex getSexByName(String name) {
        //Do jeito que está, caso a pessoa insira mais um sexo com nome igual, o programa irá pegar o id do ultimo inserido
        //, por conta do NEXT do result
        Connection con = MustacheMySQLConnection.getConnection();
        String sql = "SELECT * FROM Sexo WHERE nome_sexo LIKE ?";
        Sex s = new Sex();
        try (PreparedStatement stm = con.prepareStatement(sql)) {
            stm.setString(1, "%" + name + "%");
            ResultSet result = stm.executeQuery();

            while (result.next()) {
                s.setId(result.getInt("id_sexo"));
                s.setName(result.getString("nome_sexo"));
            }

        } catch (Exception e) {
            System.out.println("Erro ao retornar do banco de dados sexo com nome: " + name + " - " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Erro ao retornar do banco de dados \n nome de sexo: "
                    + name + "\n" + e.getMessage());
        }
        return s;
    }
}
