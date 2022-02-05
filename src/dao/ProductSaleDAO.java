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
public class ProductSaleDAO {

    //Precisa pegar o id da última venda
    public static void insert(int saleId, int productId) {
        Connection con = MustacheMySQLConnection.getConnection();
        System.out.println("saloeid: " + saleId + ", prod id: " +productId);
        String sql = "insert into venda_produto (id_produto_vp, id_venda_vp) values (?,?)";
        try (PreparedStatement stm = con.prepareStatement(sql)) {
            stm.setInt(1, productId);
            stm.setInt(2, saleId);
            stm.executeUpdate();
            stm.close();

            System.out.println("Sucesso ao salvar item de id: " + productId + ", da venda de id: " + saleId);
            con.close();
        } catch (Exception e) {
            System.out.println("Erro ao salvar item de id: " + productId + ", da venda de id: " + saleId);
            JOptionPane.showMessageDialog(null, "Erro ao salvar item de id: " + productId + ",\n da venda de id: " + saleId + "\n" + e.getMessage(), null, JOptionPane.ERROR_MESSAGE);
        }
    }
}
