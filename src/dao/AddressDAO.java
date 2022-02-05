package dao;

import configuration.MustacheMySQLConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.Address;
import model.Person;
import util.Alert;

public class AddressDAO {

    public static void insert(Address adr) {
        Connection con = MustacheMySQLConnection.getConnection();
        String sql = "INSERT into endereco (id_pessoa_endereco,id_fornecedor_endereco,cep,rua,numero,bairro,estado,cidade,complemento) values (?,?,?,?,?,?,?,?,?)";
        try (PreparedStatement stm = con.prepareStatement(sql)) {
            if (adr.getPerson() != null) {
                stm.setInt(1, adr.getPerson().getId());
            } else {
                stm.setNull(1, Types.INTEGER);
            }

            if (adr.getSupplier() != null) {
                stm.setInt(2, adr.getSupplier().getId());
            } else {
                stm.setNull(2, Types.INTEGER);
            }

            stm.setString(3, adr.getCep());
            stm.setString(4, adr.getStreet());
            stm.setInt(5, adr.getNumber());
            stm.setString(6, adr.getNeighborhood());
            stm.setString(7, adr.getUf());
            stm.setString(8, adr.getCity());
            stm.setString(9, adr.getComplement());
            stm.executeUpdate();
            stm.close();
            
        } catch (Exception e) {
            System.out.println("Erro ao cadastrar endereço dao");
            
        }
    }

    public static void update(Address adr) {
        Connection con = MustacheMySQLConnection.getConnection();
        String sql = "UPDATE endereco set id_pessoa_endereco=?, "
                + "id_fornecedor_endereco=?, "
                + "cep=?, "
                + "rua=?, "
                + "numero=?, "
                + "bairro=?, "
                + "estado=?, "
                + "cidade=?, "
                + "complemento = ? where id_endereco=?";
        try (PreparedStatement stm = con.prepareStatement(sql)) {
            if (adr.getPerson() != null) {
                stm.setInt(1, adr.getPerson().getId());
            } else {
                stm.setNull(1, Types.INTEGER);
            }

            if (adr.getSupplier() != null) {
                stm.setInt(2, adr.getSupplier().getId());
            } else {
                stm.setNull(2, Types.INTEGER);
            }

            stm.setString(3, adr.getCep());
            stm.setString(4, adr.getStreet());
            stm.setInt(5, adr.getNumber());
            stm.setString(6, adr.getNeighborhood());
            stm.setString(7, adr.getUf());
            stm.setString(8, adr.getCity());
            stm.setString(9, adr.getComplement());
            stm.setInt(10, adr.getId());
            stm.executeUpdate();
            stm.close();
            
        } catch (Exception e) {
            System.out.println("Erro ao atualizar endereço dao");
            
        }
    }

    public static void delete(Address adr) {
        Connection con = MustacheMySQLConnection.getConnection();
        String sql = "DELETE from endereco where id_endereco=?";
        try (PreparedStatement stm = con.prepareStatement(sql)) {
            stm.setInt(1, adr.getId());
            stm.executeUpdate();
            stm.close();
            
        } catch (Exception e) {
            System.out.println("Erro ao deletar endereço");
           
        }
    }

    public static Address getAddressByPersonId(int id) {
        Connection con = MustacheMySQLConnection.getConnection();

        String sql = "select * from endereco where id_pessoa_endereco = ?";

        Address ads = new Address();

        try (PreparedStatement stm = con.prepareStatement(sql)) {
            stm.setInt(1, id);
            ResultSet result = stm.executeQuery();

            while (result.next()) {
                ads.setId(result.getInt("id_endereco"));
                PersonDAO psDAO = new PersonDAO();
                ProductSupplierDAO psd = new ProductSupplierDAO();

                ads.setPerson(psDAO.getPersonById(result.getInt("id_pessoa_endereco")));
                ads.setSupplier(psd.getSupplierById(result.getInt("id_fornecedor_endereco")));
                ads.setCep(result.getString("cep"));
                ads.setStreet(result.getString("rua"));
                ads.setNumber(result.getInt("numero"));
                ads.setNeighborhood(result.getString("bairro"));
                ads.setUf(result.getString("estado"));
                ads.setCity(result.getString("cidade"));
                ads.setComplement(result.getString("complemento"));

            }
        } catch (Exception e) {
            System.out.println("Erro ao retornar endereço por id: " + e.getMessage());
            Alert.showMessageDialog("Erro ao retornar endereço por id: \n" + e.getMessage());
        }

        return ads;
    }

    public static Address getAddressById(int id) {
        Connection con = MustacheMySQLConnection.getConnection();

        String sql = "select * from endereco where id_endereco = ?";

        Address ads = new Address();

        try (PreparedStatement stm = con.prepareStatement(sql)) {
            stm.setInt(1, id);
            ResultSet result = stm.executeQuery();

            while (result.next()) {
                ads.setId(result.getInt("id_endereco"));
                PersonDAO psDAO = new PersonDAO();
                ProductSupplierDAO psd = new ProductSupplierDAO();
                Person ps = new Person();

                ads.setPerson(psDAO.getPersonById(result.getInt("id_pessoa_endereco")));
                ads.setSupplier(psd.getSupplierById(result.getInt("id_fornecedor_endereco")));
                ads.setCep(result.getString("cep"));
                ads.setStreet(result.getString("rua"));
                ads.setNumber(result.getInt("numero"));
                ads.setNeighborhood(result.getString("bairro"));
                ads.setUf(result.getString("estado"));
                ads.setCity(result.getString("cidade"));
                ads.setComplement(result.getString("complemento"));

            }
        } catch (Exception e) {
            System.out.println("Erro ao retornar endereço por id: " + e.getMessage());
            Alert.showMessageDialog("Erro ao retornar endereço por id: \n" + e.getMessage());
        }
        return ads;
    }

    public static int getAddressIdByPersonId(int personId) {
        Connection con = MustacheMySQLConnection.getConnection();

        String sql = "select id_endereco from endereco where id_pessoa_endereco = ?";

        int addressId = 0;

        try (PreparedStatement stm = con.prepareStatement(sql)) {
            stm.setInt(1, personId);
            ResultSet result = stm.executeQuery();

            while (result.next()) {
                addressId = (result.getInt("id_endereco"));
            }
        } catch (Exception e) {
            System.out.println("Erro ao retornar id de endereço por id de pessoa: " + e.getMessage());
            Alert.showMessageDialog("Erro ao retornar ide de endereço por id de pessoa: \n" + e.getMessage());
        }
        return addressId;
    }
}
