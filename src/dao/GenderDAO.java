package dao;

import configuration.MustacheMySQLConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.Gender;

public class GenderDAO {
    public static void insert (Gender gender){
        Connection con = MustacheMySQLConnection.getConnection();
        String sql = "INSERT INTO Sexo (nome) values (?)";
        try(PreparedStatement stm = con.prepareStatement(sql)){
            stm.setString(1, gender.getName());
            stm.executeUpdate();
            stm.close();
            
            JOptionPane.showMessageDialog(null, "Sexo inserido com sucesso!");
            con.close();
        } catch (Exception e) {
            System.out.println("Error while saving gender: " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar sexo, tente novamente", null, JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public static void update (Gender gender){
        Connection con = MustacheMySQLConnection.getConnection();
        String sql = "UPDATE Sexo set nome = ? WHERE id_sexo = ?";
        try(PreparedStatement stm = con.prepareStatement(sql)){
            stm.setString(2, gender.getName());
            stm.setInt(1, gender.getId());
        
            Object[] options = {"Cancelar", "Sim"};
            int answer = JOptionPane.showOptionDialog(null, "Deseja atualizar o registro?", null, JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]);
            
             if (answer == 1) {
                stm.executeUpdate();
            }
            stm.close();
            
            JOptionPane.showMessageDialog(null, "Sexo atualizado com sucesso!");
            con.close();
        } catch (Exception e) {
            System.out.println("Error while updating gender: " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Erro ao atualizar o sexo, tente novamente", null, JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public static void delete (Gender gender){
        Connection con = MustacheMySQLConnection.getConnection();
        String sql= "DELETE FROM Sexo where id_sexo = ?";
            try(PreparedStatement stm = con.prepareStatement(sql)){
                stm.setInt(1, gender.getId());
            
                Object[] options = {"Cancelar", "Sim"};
                int answer = JOptionPane.showOptionDialog(null, "Deseja deletar o registro?", null, JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]);
            
            if (answer == 1) {
                stm.executeUpdate();
            }
            stm.close();
            
            JOptionPane.showMessageDialog(null, "Sexo excluído com sucesso!");
            con.close();
        } catch (Exception e) {
            System.out.println("Error while deleting gender: " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Erro ao deletar o sexo, tente novamente", null, JOptionPane.ERROR_MESSAGE);
        }
    }
   
    public List<Gender> getListRole(String name){
        Connection con = MustacheMySQLConnection.getConnection();
        List<Gender> genderList = new ArrayList();
        String sql = "SELECT * FROM Sexo where id_sexo LIKE ? order by nome";
        try(PreparedStatement stm = con.prepareStatement(sql)){
            stm.setString(1, "%" + name + "%");
            ResultSet result = stm.executeQuery();
            while(result.next()){
                Gender gender = new Gender();
                gender.setId(result.getInt("id_sexo"));
                gender.setName(result.getString("nome"));
                genderList.add(gender);
            }
        } catch (Exception e) {
            System.out.println("Error list gender: " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Erro ao carregar sexo, tente novamente", null, JOptionPane.ERROR_MESSAGE);
        }
        return genderList;
} 
}
