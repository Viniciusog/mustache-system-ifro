package dao;

import model.Receivement;
import configuration.MustacheMySQLConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.Client;
import model.Person;
import model.Sex;
import util.Alert;

/**
 *
 * @author daniel
 */
/*
 # Seleciona conta a receber e clica em realizar recebimento, 
    coloca o valores do recebimento e desconta do valor da conta a receber
        
CREATE TABLE Recebimento (
    id_recebimento int not null auto_increment primary key,	
    id_conta_receber int not null,
    id_caixa_recebimento int not null,
    data_recebimento date not null,
    valor_total_recebimento decimal,
    foreign key (id_conta_receber) references Conta_Receber(id_conta_receber),
    foreign key (id_caixa_recebimento) references Caixa (id_caixa)
);
 */
public class ReceivementDAO {

    public static void insert(Receivement rcvmt) {
        Connection con = MustacheMySQLConnection.getConnection();
        String sql = "INSERT INTO Recebimento (id_conta_receber, id_caixa_recebimento, "
                + "data_recebimento, valor_total_recebimento) VALUES (?, ?, ?, ?)";
        try ( PreparedStatement stm = con.prepareStatement(sql)) {
            stm.setInt(1, rcvmt.getId_acc_receivement());
            stm.setInt(2, rcvmt.getId_cashier_receivement());
            stm.setString(3, rcvmt.getDate_receivement());
            stm.setFloat(4, rcvmt.getTotal_amount_receivable());
            stm.executeUpdate();
            stm.close();

            JOptionPane.showMessageDialog(null, "Recebimento cadastrado com sucesso!\n"
                    + "Código do recebimento: " + rcvmt.getId_receivement());
            con.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao inserir Recebimento: " + e.getMessage());
        }
    }

    public static void update(Receivement rcvmt) {
        Connection con = MustacheMySQLConnection.getConnection();
        String sql = "UPDATE Recebimento SET id_conta_receber = ?, id_caixa_recebimento = ?,"
                + " data_recebimento = ?, valor_total_recebimento = ?"
                + " WHERE id_recebimento = ?";

        try ( PreparedStatement stm = con.prepareStatement(sql)) {

            stm.setInt(1, rcvmt.getId_acc_receivement());
            stm.setInt(2, rcvmt.getId_cashier_receivement());
            stm.setString(3, rcvmt.getDate_receivement());
            stm.setFloat(4, rcvmt.getTotal_amount_receivable());
            stm.setInt(5, rcvmt.getId_receivement());
            stm.executeUpdate();
            stm.close();
            con.close();

            JOptionPane.showMessageDialog(null, "Recebimento atualizado com sucesso!\n"
                    + "Código do recebimento: " + rcvmt.getId_receivement());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar Recebimento: " + e.getMessage());
        }

    }

    public static void delete(Receivement rcvmt) {
        Connection con = MustacheMySQLConnection.getConnection();
        String sql = "Delete FROM Recebimento WHERE id_recebimento = ?";
        try ( PreparedStatement stm = con.prepareStatement(sql)) {
            stm.setInt(1, rcvmt.getId_receivement());

            Object[] options = {"Cancelar", "Sim"};
            int response = JOptionPane.showOptionDialog(null, "Deseja deletar o registro?", null, JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]);

            if (response == 1) {
                stm.executeUpdate();
                JOptionPane.showMessageDialog(null, "Recebimento deletado com sucesso!");
            }
            stm.close();

        } catch (Exception e) {
            System.out.println("Error while trying to delete service, error: " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Erro ao deletar item, Tente novamente");

        }
    }

    public static List<Receivement> getListReceivement(String name) {
        Connection con = MustacheMySQLConnection.getConnection();
        String sql = "SELECT * FROM Recebimento ORDER BY id_recebimento";
        List<Receivement> receivements = new ArrayList<>();

        try ( PreparedStatement stm = con.prepareStatement(sql)) {

            ResultSet result = stm.executeQuery();

            while (result.next()) {
                Receivement rcvmt = new Receivement();
                rcvmt.setId_receivement(result.getInt("id_recebimento"));
                rcvmt.setId_acc_receivement(result.getInt("id_conta_receber"));
                rcvmt.setId_cashier_receivement(result.getInt("id_caixa_recebimento"));
                rcvmt.setDate_receivement(result.getString("data_recebimento"));
                rcvmt.setTotal_amount_receivable(result.getFloat("valor_total_recebimento"));
                receivements.add(rcvmt);

            }
        } catch (Exception e) {
            System.out.println("Erro ao listar clientes: " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Erro ao listar os clientes: \n" + e.getMessage());
        }
        return receivements;
    }

    public static Receivement getReceivementById(int id) {
        
        Connection con = MustacheMySQLConnection.getConnection();

        String sql = "SELECT * FROM Recebimento WHERE id_recebimento = ?";
        Receivement rcvmt = new Receivement();
        
        try ( PreparedStatement stm = con.prepareStatement(sql)) {
            stm.setInt(1, id);
            ResultSet result = stm.executeQuery();
            while (result.next()) {

                rcvmt.setId_receivement(result.getInt("id_recebimento"));
                rcvmt.setId_acc_receivement(result.getInt("id_conta_receber"));
                rcvmt.setId_cashier_receivement(result.getInt("id_caixa_recebimento"));
                rcvmt.setDate_receivement(result.getString("data_recebimento"));
                rcvmt.setTotal_amount_receivable(result.getFloat("valor_total_recebimento"));
            }

        } catch (Exception e) {
            System.out.println("Erro ao retornar cliente por id: " + e.getMessage());
            Alert.showMessageDialog("Erro ao retornar cliente por id: \n" + e.getMessage());
        }

        return rcvmt;
    }

}
