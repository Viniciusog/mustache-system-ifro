package dao;

import configuration.MustacheMySQLConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.Client;
import model.Employee;
import model.Person;
import model.Scheduling;
import model.Service;

public class SchedulingDAO {

    public static void insert(Scheduling schedule) {
        Connection con = MustacheMySQLConnection.getConnection();
        String sql = "INSERT INTO Agendamento "
                + "(id_servico, "
                + "data_agendamento, "
                + "horario_inicio_agendamento, "
                + "horario_termino_agendamento, "
                + "id_funcionario, "
                + "id_cliente) "
                + "values (?,str_to_date(?, '%d/%m/%Y'),?,?,?,?)";
        try (PreparedStatement stm = con.prepareStatement(sql)) {
            stm.setInt(1, schedule.getService().getIdService());
            stm.setString(2, schedule.getDate());
            stm.setString(3, schedule.getStartTime());
            stm.setString(4, schedule.getEndTime());
            stm.setInt(5, schedule.getFunctionary().getId());
            stm.setInt(6, schedule.getClient().getId());
            stm.executeUpdate();
            stm.close();
            JOptionPane.showMessageDialog(null, "Agendamente marcado com sucesso!");
            con.close();
        } catch (Exception e) {
            System.out.println("Erro ao salvar agendamento: " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar agendamento, tente novamente", null, JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void update(Scheduling schedule) {
        Connection con = MustacheMySQLConnection.getConnection();
        String sql = "UPDATE Agendamento SET id_servico =? , data_agendamento =?, horario_agendamento= str_to_date(?, '%d/%m/%Y') , id_funcionario=?, id_cliente=? where id_agendamento=?";
        try (PreparedStatement stm = con.prepareStatement(sql)) {
            stm.setInt(1, schedule.getService().getIdService());
            stm.setString(2, schedule.getDate());
            stm.setString(3, schedule.getStartTime());
            stm.setString(4, schedule.getEndTime());
            stm.setInt(5, schedule.getFunctionary().getId());
            stm.setInt(6, schedule.getClient().getId());
            stm.setInt(7, schedule.getId());
            stm.executeUpdate();
            stm.close();
            JOptionPane.showMessageDialog(null, "Agendamente alterado com sucesso!");
            con.close();
        } catch (Exception e) {
            System.out.println("Erro ao alterar agendamento: " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Erro ao alterar agendamento, tente novamente", null, JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void delete(Scheduling schedule) {
        Connection con = MustacheMySQLConnection.getConnection();
        String sql = "Delete from Agendamento where id_agendamento = ?";
        try (PreparedStatement stm = con.prepareStatement(sql)) {
            stm.setInt(1, schedule.getId());
            stm.executeUpdate();
            stm.close();

            JOptionPane.showMessageDialog(null, "Agendamento desmarcado com sucesso!");
            con.close();
        } catch (Exception e) {
            System.out.println("Erro ao deletar produto: " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Erro ao deletar o agendamento, tente novamente", null, JOptionPane.ERROR_MESSAGE);

        }
    }

    public List<Scheduling> getListSchedule(String date) {
        Connection con = MustacheMySQLConnection.getConnection();
        List<Scheduling> scheduleList = new ArrayList();
        String sql = "SELECT "
                + "Agendamento.id_agendamento, "
                + "servico.nome_servico, "
                + "Agendamento.id_servico, "
                + "Agendamento.data_agendamento, "
                + "Agendamento.horario_inicio_agendamento, "
                + "Agendamento.horario_termino_agendamento, "
                + "Agendamento.id_funcionario,"
                + "Agendamento.id_cliente, "
                + "pessoa.nome_pessoa "
                + "FROM Agendamento "
                + "JOIN Servico ON (Agendamento.id_servico = Servico.id_servico) "
                + "JOIN Pessoa ON (Agendamento.id_funcionario = pessoa.id_pessoa)"
                + "WHERE Agendamento.data_agendamento = str_to_date(?, '%d/%m/%Y')"
                + "ORDER BY Agendamento.horario_inicio_agendamento ASC";

        try (PreparedStatement stm = con.prepareStatement(sql)) {
            stm.setString(1, date);
            ResultSet result = stm.executeQuery();
            while (result.next()) {
                Scheduling sc = new Scheduling();
                Service sv = new Service();
                Person ps = new Person();
                Client c = new Client();
                Employee e = new Employee();

                sc.setId(result.getInt("id_agendamento"));
                sv.setName(result.getString("nome_servico"));
                sv.setIdService(result.getInt("id_servico"));
                sc.setService(sv);
                sc.setDate(result.getString("data_agendamento"));
                sc.setStartTime(result.getString("horario_inicio_agendamento"));
                sc.setEndTime(result.getString("horario_termino_agendamento"));
                ps.setName(result.getString("nome_pessoa"));
                e.setPerson(ps);
                e.setId(result.getInt("Agendamento.id_funcionario"));
                sc.setFunctionary(e);
                c.setId(result.getInt("Agendamento.id_cliente"));
                
                String nomeCliente = ClientDAO.getClientById(c.getId()).getPerson().getName();
                Person p1 = new Person();
                p1.setName(nomeCliente);
                
                c.setPerson(p1);
                sc.setClient(c);

                scheduleList.add(sc);
            }
        } catch (Exception e) {
            System.out.println("Erro ao listar agendamentos: " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Erro ao listar os agendamentos, tente novamente", null, JOptionPane.ERROR_MESSAGE);
        }
        return scheduleList;
    }

}
