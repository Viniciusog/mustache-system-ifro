package dao;

import configuration.MustacheMySQLConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.Brand;

public class BrandDAO {
    
    public static void insert (Brand brd){
        Connection con = MustacheMySQLConnection.getConnection();
        String sql = "insert into Marca (nome_marca)values (?)";
        try (PreparedStatement stm = con.prepareStatement(sql)){
            stm.setString(1, brd.getName());
            stm.executeUpdate();
            stm.close();
            
            JOptionPane.showMessageDialog(null, "Marca inserida com sucesso!");
            con.close();
        } catch (Exception e) {
            System.out.println("Erro ao inserir marca: " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar marca, tente novamente", null, JOptionPane.ERROR_MESSAGE);
        }
        
    }
    
     public static void update (Brand brd){
        Connection con = MustacheMySQLConnection.getConnection();
        String sql = "update Marca set nome_marca = ? where id_marca = ?";
        try(PreparedStatement stm = con.prepareStatement(sql)){
            stm.setString(1, brd.getName());
            stm.setInt(2, brd.getId());
            stm.executeUpdate();
            stm.close();
            
            JOptionPane.showMessageDialog(null, "Marca atualizada com sucesso!");
            con.close();
            
        } catch (Exception e) {
            System.out.println("Erro ao atualizar marca: " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Erro ao atualizar marca, tente novamente", null, JOptionPane.ERROR_MESSAGE);
            
        }
    }
     
     public static void delete (Brand brd){
        Connection con = MustacheMySQLConnection.getConnection();
        String sql = "Delete from Marca where id_marca = ?";
        try (PreparedStatement stm = con.prepareStatement(sql)){
            stm.setInt(1, brd.getId());
            stm.executeUpdate();
            stm.close();
            
            JOptionPane.showMessageDialog(null, "Marca excluída com sucesso!");
            con.close();
        } catch (Exception e) {
         System.out.println("Erro ao deletar marca: " + e.getMessage());
         JOptionPane.showMessageDialog(null, "Erro ao deletar a marca, tente novamente", null, JOptionPane.ERROR_MESSAGE);  
            
        }
    }
     
     public List<Brand> getListBrand(String name){
        Connection con = MustacheMySQLConnection.getConnection();
        List<Brand> brdList = new ArrayList();
        String sql = "select * from Marca where id_marca like ? order by nome_marca";
        try(PreparedStatement stm = con.prepareStatement(sql)){
            stm.setString(1, "%" + name + "%");
            ResultSet result = stm.executeQuery();
            while(result.next()){
                Brand brd = new Brand ();
                brd.setId(result.getInt("id_marca"));
                brd.setName(result.getString("nome_marca"));
                brdList.add(brd);
               }
        } catch (Exception e) {
            System.out.println("Erro ao listar marcas: " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Erro ao listar as marcas, tente novamente", null, JOptionPane.ERROR_MESSAGE);  
        }
    return brdList;
    } 
     
     public Brand getBrandByName(String name) {
        Connection con = MustacheMySQLConnection.getConnection();
        Brand b = new Brand();
        String sql = "select * from Marca where nome_marca = ?";
        try (PreparedStatement stm = con.prepareStatement(sql)) {
            stm.setString(1, name);
            ResultSet result = stm.executeQuery();
            while(result.next()){
                b.setId(result.getInt("id_marca"));
                b.setName(result.getString("nome_marca"));
            }  
        } catch (Exception e) {
            System.out.println("Error list brand: " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Erro ao carregar itens, Tente Novamente");
        }
        return b;
    }
     
}
