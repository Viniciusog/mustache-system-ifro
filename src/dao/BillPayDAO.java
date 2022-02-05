package dao;

import configuration.MustacheMySQLConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.BillPay;
import util.Alert;

public class BillPayDAO {

    public static void insert(BillPay bpay) {
        Connection con = MustacheMySQLConnection.getConnection();
        String sql = "INSERT INTO Conta_Pagar ("
                + "id_compra_conta_pagar, "
                + "id_funcionario_conta_pagar, "
                + "descricao_conta_pagar, "
                + "valor_pago_conta_pagar, "
                + "valor_total_conta_pagar, "
                + "data_emissao_conta_pagar, "
                + "quitada_conta_pagar, "
                + "tipo_conta_pagar"
                + ") VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try ( PreparedStatement stm = con.prepareStatement(sql)) {
            stm.setInt(1, bpay.getId_buy_bill_pay());
            stm.setInt(2, bpay.getId_employee_buy_bill_pay());
            stm.setString(3, bpay.getDescription_bill());
            stm.setDouble(4, bpay.getValue_paidOut_bill_pay());
            stm.setDouble(5, bpay.getTotal_amount_bill_pay());
            stm.setString(6, bpay.getDate_bill());
            stm.setBoolean(7, bpay.isSettled_bill_pay());
            stm.setString(8, bpay.getType_bill_pay());
            stm.close();
            JOptionPane.showMessageDialog(null, "Conta cadastrado com sucesso!");
            con.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao inserir conta, código do erro: " + e.getMessage());
        }
    }
    
    public static void update(BillPay bpay) {
        Connection con = MustacheMySQLConnection.getConnection();
        String sql = "UPDATE Pagamento SET "
                + "id_compra_conta_pagar = ?, "
                + "id_funcionario_conta_pagar = ?, "
                + "descricao_conta_pagar = ?, "
                + "valor_pago_conta_pagar = ?, "
                + "valor_total_conta_pagar = ?, "
                + "data_emissao_conta_pagar = ?, "
                + "quitada_conta_pagar = ?, "
                + "tipo_conta_pagar = ?"
                + " WHERE id_conta_pagar = ?";

        try ( PreparedStatement stm = con.prepareStatement(sql)) {

            stm.setInt(1, bpay.getId_buy_bill_pay());
            stm.setInt(2, bpay.getId_employee_buy_bill_pay());
            stm.setString(3, bpay.getDescription_bill());
            stm.setDouble(4, bpay.getValue_paidOut_bill_pay());
            stm.setDouble(5, bpay.getTotal_amount_bill_pay());
            stm.setString(6, bpay.getDate_bill());
            stm.setBoolean(7, bpay.isSettled_bill_pay());
            stm.setString(8, bpay.getType_bill_pay());;
            stm.setInt(9, bpay.getId_bill_pay());
            stm.executeUpdate();
            stm.close();
            con.close();

            JOptionPane.showMessageDialog(null, "Conta atualizado com sucesso!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar conta, código do erro: " + e.getMessage());
        }

    }

    public static void delete(BillPay bpay) {
        Connection con = MustacheMySQLConnection.getConnection();
        String sql = "Delete FROM Conta_pagar WHERE id_conta_pagar = ?";
        try ( PreparedStatement stm = con.prepareStatement(sql)) {
            stm.setInt(1, bpay.getId_bill_pay());

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

    public static List<BillPay> getListBillPay(String name) {
        Connection con = MustacheMySQLConnection.getConnection();
        String sql = "SELECT * FROM Conta_pagar ORDER BY id_conta_pagar";
        List<BillPay> bpayList = new ArrayList<>();

        try ( PreparedStatement stm = con.prepareStatement(sql)) {

            ResultSet result = stm.executeQuery();
 /**
     * 
     *  id_conta_pagar int not null auto_increment primary key,
     *  id_compra_conta_pagar int, 
     *  id_funcionario_conta_pagar int,
     *  descricao_conta_pagar varchar(200) not null,
     *  valor_pago_conta_pagar decimal(10,2) not null,
     *  valor_total_conta_pagar decimal(10,2) not null,
     *  data_emissao_conta_pagar date not null,
     *  quitada_conta_pagar boolean not null,
     *  tipo_conta_pagar varchar(100) not null,
     */
            while (result.next()) {
                BillPay bpay = new BillPay();
                bpay.setId_bill_pay(result.getInt("id_conta_pagar"));
                bpay.setId_buy_bill_pay(result.getInt("id_compra_conta_pagar"));
                bpay.setId_employee_buy_bill_pay(result.getInt("id_funcionario_conta_pagar"));
                bpay.setDescription_bill("descricao_conta_pagar");
                bpay.setValue_paidOut_bill_pay(result.getDouble("valor_pago_conta_pagar"));
                bpay.setTotal_amount_bill_pay(result.getDouble("valor_total_conta_pagar"));
                bpay.setDate_bill("data_emissao_conta_pagar");
                bpay.setSettled_bill_pay(result.getBoolean("quitada_conta_pagar"));
                bpay.setType_bill_pay("tipo_conta_pagar");
                bpayList.add(bpay);

            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao listar as contas: \n" + e.getMessage());
        }
        return bpayList;
    }

    public static BillPay getBillPayById(int id) {

        Connection con = MustacheMySQLConnection.getConnection();

        String sql = "SELECT * FROM Conta_pagar WHERE id_conta_pagar = ?";
        BillPay bpay = new BillPay();

        try ( PreparedStatement stm = con.prepareStatement(sql)) {
            stm.setInt(1, id);
            ResultSet result = stm.executeQuery();
            while (result.next()) {
                bpay.setId_bill_pay(result.getInt("id_conta_pagar"));
                bpay.setId_buy_bill_pay(result.getInt("id_compra_conta_pagar"));
                bpay.setId_employee_buy_bill_pay(result.getInt("id_funcionario_conta_pagar"));
                bpay.setDescription_bill("descricao_conta_pagar");
                bpay.setValue_paidOut_bill_pay(result.getDouble("valor_pago_conta_pagar"));
                bpay.setTotal_amount_bill_pay(result.getDouble("valor_total_conta_pagar"));
                bpay.setDate_bill("data_emissao_conta_pagar");
                bpay.setSettled_bill_pay(result.getBoolean("quitada_conta_pagar"));
                bpay.setType_bill_pay("tipo_conta_pagar");
            }

        } catch (Exception e) {
            System.out.println("Erro ao retornar conta por id: " + e.getMessage());
            Alert.showMessageDialog("Erro ao retornar conta por id: \n" + e.getMessage());
        }

        return bpay;
    }
}


