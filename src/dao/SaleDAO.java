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
import model.BillReceive;
import model.BillReceiveAndSale;
import model.Client;
import model.Employee;
import model.Person;
import model.Product;
import model.Sale;
import util.Alert;

/**
 *
 * @author vinij
 */
public class SaleDAO {

    public static void insert(Sale s) {
        Connection con = MustacheMySQLConnection.getConnection();
        String sql = "insert into venda (id_cliente_venda, "
                + "id_funcionario_venda, quantidade_parcelas, forma_pagamento, valor_venda, data_venda) values"
                + "(?,?,?,?,?, str_to_date(?, '%d/%m/%Y'))";
        try (PreparedStatement stm = con.prepareStatement(sql)) {
            stm.setInt(1, s.getClient().getId());
            stm.setInt(2, s.getEmployee().getId());
            stm.setInt(3, s.getQtdInstallments());
            stm.setString(4, s.getPaymentKind());
            stm.setDouble(5, s.getValue());
            stm.setString(6, s.getDate());
            stm.executeUpdate();
            stm.close();

            Alert.showMessageDialog("Venda cadastrada com sucesso!");
            con.close();
        } catch (Exception e) {
            System.out.println("Erro ao cadastrar venda: " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar venda, tente novamente", null, JOptionPane.ERROR_MESSAGE);
        }
    }

    public static Sale getLastSale() {
        Connection con = MustacheMySQLConnection.getConnection();
        Sale s = new Sale();
        String sql = "Select id_venda, id_cliente_venda,"
                + "id_funcionario_venda, quantidade_parcelas,"
                + "forma_pagamento, "
                + "valor_venda, date_format(data_venda, '%d/%m/%Y') as data_venda_r from venda where id_venda > 0 ORDER by id_venda desc limit 1 ;";
        try (PreparedStatement stm = con.prepareStatement(sql)) {
            ResultSet result = stm.executeQuery();
            while (result.next()) {
                s.setId(result.getInt("id_venda"));
                Client c = new Client();
                c.setId(result.getInt("id_cliente_venda"));                             
                s.setClient(c);
                Employee e = new Employee();
                e.setId(result.getInt("id_funcionario_venda"));
                s.setEmployee(e);
                s.setDate(result.getString("data_venda_r"));
                s.setValue(result.getFloat("valor_venda"));
                s.setQtdInstallments(result.getInt("quantidade_parcelas"));
                s.setPaymentKind(result.getString("forma_pagamento"));
            }
        } catch (Exception e) {
            System.out.println("Erro ao listar produtos: " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Erro ao listar os produtos, tente novamente", null, JOptionPane.ERROR_MESSAGE);
        }
        return s;
    }
    
   /* public static List<Sale> getListSales(String saleDate) {
        Connection con = MustacheMySQLConnection.getConnection();
        Sale s = new Sale();
        String sql = "Select id_venda, id_cliente_venda,"
                + "id_funcionario_venda, quantidade_parcelas,"
                + "forma_pagamento, "
                + "valor_venda, date_format(data_venda, '%d/%m/%Y') as data_venda_r from venda where str_to_date(?, '%d/%m/%Y')";
        try (PreparedStatement stm = con.prepareStatement(sql)) {
            stm.setString(1, saleDate);
            ResultSet result = stm.executeQuery();
            while (result.next()) {
                s.setId(result.getInt("id_venda"));
                Client c = new Client();
                c.setId(result.getInt("id_cliente_venda"));                             
                s.setClient(c);
                Employee e = new Employee();
                e.setId(result.getInt("id_funcionario_venda"));
                s.setEmployee(e);
                s.setDate("data_venda_r");
            }
        } catch (Exception e) {
            System.out.println("Erro ao listar produtos: " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Erro ao listar os produtos, tente novamente", null, JOptionPane.ERROR_MESSAGE);
        }
        return s;
    }*/
    
    
    public static List<BillReceiveAndSale> getListSales(int lastsSales) {
        Connection con = MustacheMySQLConnection.getConnection();
        
        List<BillReceiveAndSale> billsAndSales = new ArrayList<>();
        String sql = "Select venda.id_venda, pessoa.nome_pessoa, pessoa.cpf_pessoa, "
                + "venda.quantidade_parcelas,"
                + "venda.forma_pagamento, "
                + "venda.quantidade_parcelas,"
                + "venda.valor_venda, date_format(venda.data_venda, '%d/%m/%Y') as data_venda_r,"
                + "conta_receber.data_vencimento_conta_receber,"
                + "conta_receber.descricao_conta_receber,"
                + "conta_receber.valor_recebido_conta_receber,"
                + "conta_receber.quitada_conta_receber "
                + "from venda join pessoa, cliente, conta_receber where "
                + "venda.id_cliente_venda = cliente.id_cliente "
                + "and pessoa.id_pessoa = cliente.id_pessoa "
                + "and conta_receber.id_venda_conta_receber = venda.id_venda"
                + " ORDER by venda.id_venda desc limit ? ;";
        try (PreparedStatement stm = con.prepareStatement(sql)) {
            stm.setInt(1, lastsSales);
            ResultSet result = stm.executeQuery();
            while (result.next()) {
                Sale s = new Sale();
                s.setId(result.getInt("id_venda"));
                s.setDate(result.getString("data_venda_r"));
                s.setPaymentKind(result.getString("forma_pagamento"));
                s.setValue(result.getFloat("valor_venda"));
                Client c = new Client();
                Person p = new Person();
                p.setName(result.getString("nome_pessoa"));
                p.setCpf(result.getString("cpf_pessoa"));
                c.setPerson(p);
                s.setClient(c);
                
                BillReceive br = new BillReceive();
                br.setDescription_bill_receive(result.getString("descricao_conta_receber"));
                br.setDue_date_bill_receive(result.getString("data_vencimento_conta_receber"));
                br.setValueReceived(result.getDouble("valor_recebido_conta_receber"));
                br.setSettled_bill_receive(result.getBoolean("quitada_conta_receber"));
                
                BillReceiveAndSale b = new BillReceiveAndSale();
                b.setBillReceive(br);
                b.setSale(s);
                billsAndSales.add(b);
            }
        } catch (Exception e) {
            System.out.println("Erro ao listar vendas: " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Erro ao listar os vendas, tente novamente", null, JOptionPane.ERROR_MESSAGE);
        }
        return billsAndSales;
    }
}
