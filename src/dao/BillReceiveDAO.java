package dao;

import configuration.MustacheMySQLConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.BillReceive;
import util.Alert;

public class BillReceiveDAO {

    public static void insert(BillReceive breceive) {
        Connection con = MustacheMySQLConnection.getConnection();

        System.out.println(
                breceive.getDate_emission_bill_receive() + ","
                + breceive.getDescription_bill_receive() + ","
                + breceive.getDue_date_bill_receive() + ","
                + breceive.getId_bill_receive() + ","
                + breceive.getId_client_bill_receive() + ","
                + breceive.getId_sale_bill_receive() + ","
                + breceive.getType_bill_receive() + ","
                + breceive.getValueBill()+ "," +
                + breceive.getValueReceived() +"," +
                breceive.isSettled_bill_receive());
        
        String sql = "insert into Conta_receber "
                + "values (null, ?, ?, str_to_date(?, '%d/%m/%Y'),"
                + "str_to_date(?, '%d/%m/%Y'), ?, ? ,?, ?, ?)";
        try (PreparedStatement stm = con.prepareStatement(sql)) {
            stm.setInt(1, breceive.getId_client_bill_receive());
            stm.setInt(2, breceive.getId_sale_bill_receive());
            stm.setString(3, breceive.getDate_emission_bill_receive()); //
            stm.setString(4, breceive.getDue_date_bill_receive()); //
            stm.setString(5, breceive.getDescription_bill_receive()); //
            stm.setString(6, breceive.getType_bill_receive()); //]
            stm.setDouble(7, breceive.getValueBill());
            if (breceive.getType_bill_receive().equals("À Vista")) {
                stm.setDouble(8, breceive.getValueBill());
            } else if (breceive.getType_bill_receive().equals("À Prazo")) {
                stm.setDouble(8, 0);
            }         
            stm.setBoolean(9, breceive.isSettled_bill_receive());
            stm.executeUpdate();
            
            stm.close();
            JOptionPane.showMessageDialog(null, "Conta receber cadastrado com sucesso!");
            con.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao inserir conta, código do erro: " + e.getMessage());
        }
    }

    public static void update(BillReceive breceive) {
        Connection con = MustacheMySQLConnection.getConnection();
        String sql = "UPDATE Conta_Receber "
                + "id_cliente_conta_receber = ?, "
                + "id_venda_conta_receber = ?, "
                + "data_emissao_conta_receber = ?, "
                + "data_vencimento_conta_receber = ?, "
                + "descricao_conta_receber = ?, "
                + "tipo_conta_receber = ?, "
                + "valor_conta_receber = ?, "
                + "valor_recebido_conta_recebido = ?, "
                + "quitada_conta_receber = ?"
                + " WHERE id_conta_receber = ?";

        try (PreparedStatement stm = con.prepareStatement(sql)) {

            stm.setInt(1, breceive.getId_client_bill_receive());
            stm.setInt(2, breceive.getId_sale_bill_receive());
            stm.setString(3, breceive.getDate_emission_bill_receive());
            stm.setString(4, breceive.getDue_date_bill_receive());
            stm.setString(5, breceive.getDescription_bill_receive());
            stm.setString(6, breceive.getType_bill_receive());
            stm.setDouble(7, breceive.getValueBill());
            stm.setDouble(8, breceive.getValueReceived());
            stm.setBoolean(9, breceive.isSettled_bill_receive());
            stm.setInt(10, breceive.getId_bill_receive());
            stm.executeUpdate();
            stm.close();
            con.close();

            JOptionPane.showMessageDialog(null, "Conta atualizado com sucesso!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar conta, código do erro: " + e.getMessage());
        }

    }

    public static void delete(BillReceive breceive) {
        Connection con = MustacheMySQLConnection.getConnection();
        String sql = "Delete FROM Conta_Receber WHERE id_conta_receber = ?";
        try (PreparedStatement stm = con.prepareStatement(sql)) {
            stm.setInt(1, breceive.getId_bill_receive());

            Object[] options = {"Cancelar", "Sim"};
            int response = JOptionPane.showOptionDialog(null, "Deseja deletar o registro?", null, JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]);

            if (response == 1) {
                stm.executeUpdate();
                JOptionPane.showMessageDialog(null, "Conta deletada com sucesso!");
            }
            stm.close();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao deletar conta, código do erro: " + e.getMessage());

        }
    }

    public static List<BillReceive> getListBillReceive(String name) {
        Connection con = MustacheMySQLConnection.getConnection();
        String sql = "SELECT * FROM Conta_Receber ORDER BY id_conta_receber";
        List<BillReceive> breceiveList = new ArrayList<>();

        try (PreparedStatement stm = con.prepareStatement(sql)) {

            ResultSet result = stm.executeQuery();
            /**
             * id_conta_receber id_cliente_conta_receber id_venda_conta_receber
             * data_emissao_conta_receber data_vencimento_conta_receber
             * descricao_conta_receber tipo_conta_receber valor_conta_receber
             * valor_restante_conta_receber quitada_conta_receber
             */

            while (result.next()) {
                BillReceive breceive = new BillReceive();
                breceive.setId_bill_receive(result.getInt("id_conta_receber"));
                breceive.setId_client_bill_receive(result.getInt("id_cliente_conta_receber"));
                breceive.setId_sale_bill_receive(result.getInt("id_venda_conta_receber"));
                breceive.setDate_emission_bill_receive(result.getString("data_emissao_conta_receber"));
                breceive.setDue_date_bill_receive(result.getString("data_vencimento_conta_receber"));
                breceive.setDescription_bill_receive(result.getString("descricao_conta_receber"));
                breceive.setType_bill_receive(result.getString("tipo_conta_receber"));
                breceive.setValueBill(result.getDouble("valor_conta_receber"));
                breceive.setValueReceived(result.getDouble("valor_recebido_conta_receber"));
                breceive.setSettled_bill_receive(result.getBoolean("quitada_conta_receber"));
                breceiveList.add(breceive);

            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao listar as contas: \n" + e.getMessage());
        }
        return breceiveList;
    }

    public static BillReceive getBillReceiveById(int id) {

        Connection con = MustacheMySQLConnection.getConnection();

        String sql = "SELECT * FROM Conta_pagar WHERE id_conta_pagar = ?";
        BillReceive breceive = new BillReceive();

        try (PreparedStatement stm = con.prepareStatement(sql)) {
            stm.setInt(1, id);
            ResultSet result = stm.executeQuery();
            while (result.next()) {

                breceive.setId_bill_receive(result.getInt("id_conta_receber"));
                breceive.setId_client_bill_receive(result.getInt("id_client_conta_receber"));
                breceive.setId_sale_bill_receive(result.getInt("id_venda_conta_receber"));
                breceive.setDate_emission_bill_receive(result.getString("data_emissao_conta_receber"));
                breceive.setDue_date_bill_receive(result.getString("data_vencimento_conta_receber"));
                breceive.setDescription_bill_receive(result.getString("descricao_conta_receber"));
                breceive.setType_bill_receive(result.getString("tipo_conta_receber"));
                breceive.setValueBill(result.getDouble("valor_conta_receber"));
                breceive.setValueReceived(result.getDouble("valor_recebido_conta_receber"));
                breceive.setSettled_bill_receive(result.getBoolean("quitada_conta_receber"));
            }

        } catch (Exception e) {
            System.out.println("Erro ao retornar conta por id: " + e.getMessage());
            Alert.showMessageDialog("Erro ao retornar conta por id: \n" + e.getMessage());
        }

        return breceive;
    }
}
