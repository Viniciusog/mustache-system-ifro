package dao;

import configuration.MustacheMySQLConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.Service;

public class ServiceDAO {

    public static void insert(Service service) {
        Connection con = MustacheMySQLConnection.getConnection();
        String sql = "INSERT INTO Servico (nome_servico, descricao_servico, duracao_servico, preco_servico) values (?, ?, ?, ?)";
        try (PreparedStatement stm = con.prepareStatement(sql)) {
            stm.setString(1, service.getName());
            stm.setString(2, service.getDescription());
            stm.setInt(3, service.getDurationService());
            stm.setFloat(4, service.getPrice());
            stm.executeUpdate();
            con.close();

            JOptionPane.showMessageDialog(null, "Serviço cadastrado com sucesso!");
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error saving service!: " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar serviço, Tente Novamente");
        }
    }

    public static void update(Service service) {
        Connection con = MustacheMySQLConnection.getConnection();
        String sql = "UPDATE Servico set nome_servico=?, descricao_servico=?, duracao_servico=?, preco_servico=? WHERE id_servico=?";
        try (PreparedStatement stm = con.prepareStatement(sql)) {
            stm.setString(1, service.getName());
            stm.setString(2, service.getDescription());
            stm.setInt(3, service.getDurationService());
            stm.setFloat(4, service.getPrice());
            stm.setInt(5, service.getIdService());

            Object[] options = {"Cancelar", "Sim"};
            int response = JOptionPane.showOptionDialog(null, "Deseja atualizar o registro?", null, JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]);

            if (response == 1) {
                stm.executeUpdate();
            }
            stm.close();

            JOptionPane.showMessageDialog(null, "Serviço atualizado com sucesso!");
            con.close();
        } catch (Exception e) {
            System.out.println("Error while updation service, error: " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Erro ao atualizar item, Tente novamente");
        }
    }

    public static void delete(Service service) {
        Connection con = MustacheMySQLConnection.getConnection();
        String sql = "DELETE FROM Servico WHERE id_servico=?";
        try (PreparedStatement stm = con.prepareStatement(sql)) {
            stm.setInt(1, service.getIdService());

            Object[] options = {"Cancelar", "Sim"};
            int response = JOptionPane.showOptionDialog(null, "Deseja deletar o registro?", null, JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]);

            if (response == 1) {
                stm.executeUpdate();
                JOptionPane.showMessageDialog(null, "Serviço deletado com sucesso!");
            }
            stm.close();

        } catch (Exception e) {
            System.out.println("Error while trying to delete service, error: " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Erro ao deletar item, Tente novamente");
        }
    }

    public static List<Service> getListService(String name) {
        Connection con = MustacheMySQLConnection.getConnection();
        List<Service> serviceList = new ArrayList();
        String sql = "SELECT * FROM Servico WHERE nome_servico LIKE ? ORDER BY nome_servico";
        try (PreparedStatement stm = con.prepareStatement(sql)) {
            stm.setString(1, "%" + name + "%");
            ResultSet result = stm.executeQuery();
            while (result.next()) {
                Service service = new Service();
                service.setIdService(result.getInt("id_servico"));
                service.setName(result.getString("nome_servico"));
                service.setDurationService(result.getInt("duracao_servico"));
                service.setDescription(result.getString("descricao_servico"));
                service.setPrice(result.getFloat("preco_servico"));
                serviceList.add(service);
            }
        } catch (Exception e) {
            System.out.println("Error list service: " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Erro ao carregar itens, Tente Novamente");
        }
        return serviceList;
    }
}
