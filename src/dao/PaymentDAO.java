/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import configuration.MustacheMySQLConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.Payment;
import util.Alert;

/**
 *
 * @author daniel
 */
public class PaymentDAO {
       public static void insert(Payment pmnt) {
        Connection con = MustacheMySQLConnection.getConnection();
        String sql = "INSERT INTO Pagamento (id_conta_pagar, id_caixa_pagar, "
                + "data_pagamento, valor_pagamento) VALUES (?, ?, ?, ?)";
        try ( PreparedStatement stm = con.prepareStatement(sql)) {
            stm.setInt(1, pmnt.getId_acc_payment());
            stm.setInt(2, pmnt.getId_cashier_payment());
            stm.setString(3, pmnt.getDate_payment());
            stm.setFloat(4, pmnt.getTotal_payment_amount());
            stm.executeUpdate();
            stm.close();

            JOptionPane.showMessageDialog(null, "Pagamento cadastrado com sucesso!\n"
                    + "Código do pagamento: " + pmnt.getId_payment());
            con.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao inserir Pagamento: " + e.getMessage());
        }
    }

    public static void update(Payment pmnt) {
        Connection con = MustacheMySQLConnection.getConnection();
        String sql = "UPDATE Pagamento SET id_conta_pagar = ?, id_caixa_pagamento = ?,"
                + " data_pagamento = ?, valor_pagamento = ?"
                + " WHERE id_pagamento = ?";

        try ( PreparedStatement stm = con.prepareStatement(sql)) {

            stm.setInt(1, pmnt.getId_acc_payment());
            stm.setInt(2, pmnt.getId_cashier_payment());
            stm.setString(3, pmnt.getDate_payment());
            stm.setFloat(4, pmnt.getTotal_payment_amount());
            stm.setInt(5, pmnt.getId_payment());
            stm.executeUpdate();
            stm.close();
            con.close();

            JOptionPane.showMessageDialog(null, "Pagamento atualizado com sucesso!\n"
                    + "Código do pagamento: " + pmnt.getId_payment());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar Pagamento: " + e.getMessage());
        }

    }

    public static void delete(Payment pmnt) {
        Connection con = MustacheMySQLConnection.getConnection();
        String sql = "Delete FROM Pagamento WHERE id_pagamento = ?";
        try ( PreparedStatement stm = con.prepareStatement(sql)) {
            stm.setInt(1, pmnt.getId_payment());

            Object[] options = {"Cancelar", "Sim"};
            int response = JOptionPane.showOptionDialog(null, "Deseja deletar o registro?", null, JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]);

            if (response == 1) {
                stm.executeUpdate();
                JOptionPane.showMessageDialog(null, "Pagamento deletado com sucesso!");
            }
            stm.close();

        } catch (Exception e) {
            System.out.println("Error while trying to delete pagamento, error: " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Erro ao deletar pagamento, Tente novamente");

        }
    }

    public static List<Payment> getListPayment(String name) {
        Connection con = MustacheMySQLConnection.getConnection();
        String sql = "SELECT * FROM Pagamento ORDER BY id_pagamento";
        List<Payment> payments = new ArrayList<>();

        try ( PreparedStatement stm = con.prepareStatement(sql)) {

            ResultSet result = stm.executeQuery();

            while (result.next()) {
                Payment pmnt = new Payment();
                pmnt.setId_payment(result.getInt("id_pagamento"));
                pmnt.setId_acc_payment(result.getInt("id_conta_pagar"));
                pmnt.setId_cashier_payment(result.getInt("id_caixa_pagamento"));
                pmnt.setDate_payment(result.getString("data_pagamento"));
                pmnt.setTotal_payment_amount(result.getFloat("valor_pagamento"));
                payments.add(pmnt);

            }
        } catch (Exception e) {
            System.out.println("Erro ao listar Pagamentos: " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Erro ao listar os Pagamentos: \n" + e.getMessage());
        }
        return payments;
    }

    public static Payment getPaymentById(int id) {
        
        Connection con = MustacheMySQLConnection.getConnection();

        String sql = "SELECT * FROM Pagamento WHERE id_pagamento = ?";
        Payment pmnt = new Payment();
        
        try ( PreparedStatement stm = con.prepareStatement(sql)) {
            stm.setInt(1, id);
            ResultSet result = stm.executeQuery();
            while (result.next()) {

                pmnt.setId_payment(result.getInt("id_pagamento"));
                pmnt.setId_acc_payment(result.getInt("id_conta_pagar"));
                pmnt.setId_cashier_payment(result.getInt("id_caixa_pagamento"));
                pmnt.setDate_payment(result.getString("data_pagamento"));
                pmnt.setTotal_payment_amount(result.getFloat("valor_pagamento"));
            }

        } catch (Exception e) {
            System.out.println("Erro ao retornar pagamento por id: " + e.getMessage());
            Alert.showMessageDialog("Erro ao retornar pagamento por id: \n" + e.getMessage());
        }

        return pmnt;
    } 
}
