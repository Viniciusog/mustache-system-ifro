package dao;

import configuration.MustacheMySQLConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.ProductSupplier;

public class ProductSupplierDAO {

    /*
    id_fornecedor int not null auto_increment primary key,
    razao_social_fornecedor varchar(200) not null,
    nome_fantasia_fornecedor varchar(200) not null,
    telefone varchar(30) not null,
    cnpj varchar(30) not null,
    email varchar(100) not null
     */
    public static void insert(ProductSupplier prodSupplier) {
        Connection con = MustacheMySQLConnection.getConnection();
        String sql = "INSERT INTO Fornecedor ("
                + "razao_social_fornecedor, "
                + "nome_fantasia_fornecedor, "
                + "telefone, "
                + "cnpj, "
                + "email) "
                + "values (?,?,?,?,?)";
        try (PreparedStatement stm = con.prepareStatement(sql)) {
            stm.setString(1, prodSupplier.getCorporateName());
            stm.setString(2, prodSupplier.getName());
            stm.setString(3, prodSupplier.getPhoneNumber());
            stm.setString(4, prodSupplier.getCnpj());
            stm.setString(5, prodSupplier.getEmail());
            stm.executeUpdate();

            JOptionPane.showMessageDialog(null, "Fornecedor cadastrado com sucesso!");
            con.close();
            stm.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error saving product supplier!: " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar fornecedor!, Tente Novamente");
        }

    }

    public static void update(ProductSupplier prodSupplier) {
        Connection con = MustacheMySQLConnection.getConnection();
        String sql = "UPDATE Fornecedor set "
                + "razao_social_fornecedor = ?, "
                + "nome_fantasia_fornecedor = ?, "
                + "telefone = ?, "
                + "cnpj = ?, "
                + "email = ?"
                + "WHERE id_fornecedor = ?";
        try (PreparedStatement stm = con.prepareStatement(sql)) {
            stm.setString(1, prodSupplier.getCorporateName());
            stm.setString(2, prodSupplier.getName());
            stm.setString(3, prodSupplier.getPhoneNumber());
            stm.setString(4, prodSupplier.getCnpj());
            stm.setString(5, prodSupplier.getEmail());
            stm.setInt(6, prodSupplier.getId());
            stm.executeUpdate();

            JOptionPane.showMessageDialog(null, "Fornecedor atualizado com sucesso!");
            con.close();
            stm.close();

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error updating product supplier!: " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Erro ao atualizar fornecedor! \n Tente Novamente.");
        }
    }

    public static void delete(ProductSupplier prodSupplier) {
        Connection con = MustacheMySQLConnection.getConnection();
        String sql = "DELETE FROM Fornecedor WHERE id_fornecedor=?";
        try (PreparedStatement stm = con.prepareStatement(sql)) {
            stm.setInt(1, prodSupplier.getId());

            Object[] options = {"Cancelar", "Sim"};
            int response = JOptionPane.showOptionDialog(null, "Deseja deletar o registro?",
                    null, JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]);

            if (response == 1) {
                stm.executeUpdate();
                JOptionPane.showMessageDialog(null, "Fornecedor deletado com sucesso!");
            }
            con.close();
            stm.close();

        } catch (Exception e) {
            System.out.println("Error while trying to delete product supplier, error: " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Erro ao deletar fornecedor, Tente novamente");
        }
    }

    public List<ProductSupplier> getListProductSuppliers(String name) {
        Connection con = MustacheMySQLConnection.getConnection();
        List<ProductSupplier> usersList = new ArrayList<>();
        String sql = "SELECT * FROM Fornecedor "
                + "WHERE nome_fantasia_fornecedor LIKE ? ORDER BY nome_fantasia_fornecedor";
        try (PreparedStatement stm = con.prepareStatement(sql)) {
            stm.setString(1, "%" + name + "%");
            ResultSet result = stm.executeQuery();
            while (result.next()) {
                ProductSupplier productSupplier = new ProductSupplier();
                productSupplier.setId(result.getInt("id_fornecedor"));
                productSupplier.setCorporateName(result.getString("razao_social_fornecedor"));
                productSupplier.setName(result.getString("nome_fantasia_fornecedor"));
                productSupplier.setPhoneNumber(result.getString("telefone"));
                productSupplier.setCnpj(result.getString("cnpj"));
                productSupplier.setEmail(result.getString("email"));
                      
                usersList.add(productSupplier);
            }
            con.close();
            stm.close();
        } catch (Exception e) {
            System.out.println("Error list product suppliers: " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Erro ao carregar fornecedores, Tente Novamente");
        }
        return usersList;
    }
    
    public ProductSupplier getSupplierById(int id) {
        Connection con = MustacheMySQLConnection.getConnection();
        ProductSupplier ps = new ProductSupplier();
        String sql = "SELECT * FROM Fornecedor "
                + "WHERE id_fornecedor = ?";
        try (PreparedStatement stm = con.prepareStatement(sql)) {
            stm.setInt(1, id);
            ResultSet result = stm.executeQuery();
            while (result.next()) {             
                ps.setId(result.getInt("id_fornecedor"));
                ps.setCorporateName(result.getString("razao_social_fornecedor"));
                ps.setName(result.getString("nome_fantasia_fornecedor"));
                ps.setPhoneNumber(result.getString("telefone"));
                ps.setCnpj(result.getString("cnpj"));
                ps.setEmail(result.getString("email"));            
            }
            con.close();
            stm.close();
        } catch (Exception e) {
            System.out.println("Error get product supplier by id: " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Erro ao carregar fornecedor por id!, Tente Novamente");
        }
        return ps;
    }
}
