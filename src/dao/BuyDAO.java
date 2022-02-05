package dao;

import configuration.MustacheMySQLConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.Buy;
import model.ProductSupplier;

public class BuyDAO {
    
    public static void insert (Buy buy){
        Connection con = MustacheMySQLConnection.getConnection();
        String sql = "insert into Compra (id_fornecedor_compra,forma_pagamento_compra, valor_compra, data_emissao_compra, qtd_parcelas_compra) values (?,?,?,?,?)";
        try (PreparedStatement stm = con.prepareStatement(sql)){
            stm.setInt(1, buy.getId_supplier().getId());
            stm.setString(2, buy.getPaymentMethod());
            stm.setDouble(3, buy.getTotalValue());
            stm.setString(4, buy.getDate());
            stm.setInt(5, buy.getInstallments());
            
            stm.executeUpdate();
            stm.close();
            
            JOptionPane.showMessageDialog(null, "Compra inserida com sucesso!");
            con.close();
        } catch (Exception e) {
            System.out.println("Erro ao inserir produto: " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar compra, tente novamente", null, JOptionPane.ERROR_MESSAGE);
        }
        
    }
    
    public static void update (Buy buy){
        Connection con = MustacheMySQLConnection.getConnection();
        String sql = "update Compra set id_fornecedor_compra=? , forma_pagamento_compra=?, valor_compra=?, data_emissao_compra=?, qtd_parcelas_compra=?  where id_compra = ?";
        try(PreparedStatement stm = con.prepareStatement(sql)){
            stm.setInt(1, buy.getId_supplier().getId());
            stm.setString(2, buy.getPaymentMethod());
            stm.setDouble(3, buy.getTotalValue());
            stm.setString(4, buy.getDate());
            stm.setInt(5, buy.getInstallments());
            stm.setInt(6, buy.getId());
            stm.executeUpdate();
            stm.close();
            
            JOptionPane.showMessageDialog(null, "Compra atualizada com sucesso!");
            con.close();
            
        } catch (Exception e) {
            System.out.println("Erro ao atualizar compra: " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Erro ao atualizar compra, tente novamente", null, JOptionPane.ERROR_MESSAGE);
            
        }
    }
    
    public static void delete (Buy buy){
        Connection con = MustacheMySQLConnection.getConnection();
        String sql = "Delete from Compra where id_compra = ?";
        try (PreparedStatement stm = con.prepareStatement(sql)){
            stm.setInt(1, buy.getId());
            stm.executeUpdate();
            stm.close();
            
            JOptionPane.showMessageDialog(null, "Compra excluída com sucesso!");
            con.close();
        } catch (Exception e) {
         System.out.println("Erro ao deletar compra: " + e.getMessage());
         JOptionPane.showMessageDialog(null, "Erro ao deletar a compra, tente novamente", null, JOptionPane.ERROR_MESSAGE);  
            
        }
    }
    
   public List<Buy> getListBuy(String name){
        Connection con = MustacheMySQLConnection.getConnection();
        List<Buy> buyList = new ArrayList();
        String sql = "select * from Compra where id_compra like ?";
        try(PreparedStatement stm = con.prepareStatement(sql)){
            stm.setString(1, "%" + name + "%");
            ResultSet result = stm.executeQuery();
            while(result.next()){
                Buy buy = new Buy ();
                buy.setId(result.getInt("id_compra"));
                ProductSupplier ps = new ProductSupplier();
                ps.setId(result.getInt("id_fornecedor_compra"));
                buy.setId_supplier(ps);
                buy.setPaymentMethod(result.getString("forma_pagamento_compra"));
                buy.setTotalValue(result.getDouble("valor_compra"));
                buy.setDate(result.getString("data_emissao_compra"));
                buy.setInstallments(result.getInt("qtd_parcelas_compra"));
                buyList.add(buy);
               }
        } catch (Exception e) {
            System.out.println("Erro ao listar compras: " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Erro ao listar as compras, tente novamente", null, JOptionPane.ERROR_MESSAGE);  
        }
    return buyList;
    }
    
}
