/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import configuration.MustacheMySQLConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;

/**
 *
 * @author vinij
 */
public class ServiceSale {
    //Precisa pegar o id da última venda
    public static void insert(int saleId, int serviceId, int employeeId) {
        Connection con = MustacheMySQLConnection.getConnection();
        String sql = "insert into venda_servico values (null, ?,?,?)";
        try (PreparedStatement stm = con.prepareStatement(sql)) {
            System.out.println("Venda id: " + saleId + "\n" + "Serviço id: " + serviceId + 
                    "\n" + "Funcionario id: " + employeeId);
            stm.setInt(1, saleId);
            stm.setInt(2, serviceId);
            stm.setInt(3, employeeId);
            stm.executeUpdate();
            stm.close();

            System.out.println("Sucesso ao salvar serviço de id: " + serviceId + ", da venda de id: " + saleId);
            con.close();
        } catch (Exception e) {
            
            System.out.println("Erro ao salvar serviço de id: " + serviceId + ", da venda de id: " + saleId
            + e.getMessage());
            JOptionPane.showMessageDialog(null, "Sucesso ao salvar serviço de id: " + serviceId + ",\n da venda de id: " + saleId, null, JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
}
