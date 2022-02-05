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
import model.Client;
import model.Person;
import model.Sex;
import util.Alert;

/**
 *
 * @author vinij
 */
public class ClientDAO {

    public static void insert(Client cli) {
        //Precisamos colocar verificação de cliente já cadastrado
        Connection con = MustacheMySQLConnection.getConnection();
        String sql = "INSERT INTO Cliente (id_pessoa) VALUES (?)";
        try (PreparedStatement stm = con.prepareStatement(sql)) {
            stm.setInt(1, cli.getPerson().getId());
            stm.executeUpdate();
            stm.close();
            JOptionPane.showMessageDialog(null, "Cliente " + cli.getPerson().getName() + " cadastrado com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao cadastrar cliente dao");
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar cliente: \n " + e.getMessage(), null, JOptionPane.ERROR_MESSAGE);
        }
    }

    //Achei o erro AAAAAAAAAAAAAAAAAAAAA
    public static void update(Client cli) {
        Connection con = MustacheMySQLConnection.getConnection();
        String sql = "UPDATE Cliente SET id_pessoa = ?";
        try (PreparedStatement stm = con.prepareStatement(sql)) {
            stm.setInt(1, cli.getPerson().getId());          
            stm.executeUpdate();
            stm.close();
            JOptionPane.showMessageDialog(null, "Cliente " + cli.getPerson().getName() + " atualizado com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao atualizar cliente dao: " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Erro a atualizar cliente, tente novamente. \n" + e.getMessage());
        }

    }

    public static void delete(Client cli) {
        Connection con = MustacheMySQLConnection.getConnection();
        //Para deletar um cliente, deleta-se primeiro a pessoa que é o cliente
        PersonDAO.delete(cli.getPerson().getId());
        String sql = "DELETE FROM Cliente WHERE id_cliente = ?";
        try (PreparedStatement stm = con.prepareStatement(sql)) {
            stm.setInt(1, cli.getId());
            stm.executeUpdate();
            stm.close();
            JOptionPane.showMessageDialog(null, "Cliente " + cli.getPerson().getName() + " deletado com sucesso!");

        } catch (Exception e) {
            System.out.println("Erro ao deletar cliente: " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Erro ao deletar cliente: " + e.getMessage(), null,
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public static List<Client> getListClient(String name) {
        Connection con = MustacheMySQLConnection.getConnection();
        String sql = "SELECT pessoa.id_pessoa, pessoa.id_sexo, "
                + "pessoa.nome_pessoa, pessoa.cpf_pessoa, "
                + "pessoa.telefone_pessoa, date_format(pessoa.data_nascimento_pessoa, '%d/%m/%Y') as data_nascimento_pessoa,"
                + "cliente.id_cliente, sexo.nome_sexo FROM Pessoa JOIN Cliente, Sexo WHERE "
                + " Cliente.id_pessoa = Pessoa.id_pessoa AND Pessoa.id_sexo = Sexo.id_sexo AND pessoa.nome_pessoa LIKE ? ORDER BY pessoa.nome_pessoa";
        List<Client> clients = new ArrayList<>();
        try (PreparedStatement stm = con.prepareStatement(sql)) {
            stm.setString(1, "%" + name + "%");
            ResultSet result = stm.executeQuery();
            while (result.next()) {
                Client cli = new Client();
                cli.setId(result.getInt("id_cliente"));

                Person p = new Person();
                p.setId(result.getInt("id_pessoa"));
                p.setBirthday(result.getString("data_nascimento_pessoa"));
                p.setCpf(result.getString("cpf_pessoa"));
                p.setName(result.getString("nome_pessoa"));
                p.setPhoneNumber(result.getString("telefone_pessoa"));

                Sex s = new Sex();
                //Pega o id_sexo presente na tabela Pessoa
                s.setId(result.getInt("id_sexo"));
                s.setName(result.getString("nome_sexo"));
                p.setSex(s);

                cli.setPerson(p);
                clients.add(cli);
            }
        } catch (Exception e) {
            System.out.println("Erro ao listar clientes: " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Erro ao listar os clientes: \n" + e.getMessage());
        }
        return clients;
    }

    public static Client getClientById(int id) {
        Connection con = MustacheMySQLConnection.getConnection();

        String sql = "SELECT pessoa.id_pessoa, pessoa.nome_pessoa, pessoa.cpf_pessoa, pessoa.telefone_pessoa,"
                + "pessoa.id_sexo, date_format(pessoa.data_nascimento_pessoa, '%d/%m/%Y') as data_nascimento_pessoa, "
                + "cliente.id_cliente, "
                + "sexo.nome_sexo FROM pessoa "
                + "JOIN Cliente, Sexo "
                + "WHERE "
                + "Cliente.id_pessoa = Pessoa.id_pessoa "
                + "AND Pessoa.id_sexo = Sexo.id_sexo "
                + "AND cliente.id_cliente = ?";

        Client cli = new Client();

        try (PreparedStatement stm = con.prepareStatement(sql)) {
            stm.setInt(1, id);
            ResultSet result = stm.executeQuery();

            while (result.next()) {
                cli.setId(result.getInt("id_cliente"));

                Person p = new Person();
                p.setId(result.getInt("id_pessoa"));
                p.setName(result.getString("nome_pessoa"));
                p.setCpf(result.getString("cpf_pessoa"));
                p.setBirthday(result.getString("data_nascimento_pessoa"));
                p.setPhoneNumber(result.getString("telefone_pessoa"));

                Sex s = new Sex();
                s.setId(result.getInt("id_sexo"));
                s.setName(result.getString("nome_sexo"));
                p.setSex(s);

                cli.setPerson(p);
            }
        } catch (Exception e) {
            System.out.println("Erro ao retornar cliente por id: " + e.getMessage());
            Alert.showMessageDialog("Erro ao retornar cliente por id: \n" + e.getMessage());
        }

        return cli;
    }

    public static int getClientIdByPersonId(int personId) {
        Connection con = MustacheMySQLConnection.getConnection();

        String sql = "SELECT id_cliente FROM Cliente where id_pessoa = ?";
        
        int clientId = 0;

        try (PreparedStatement stm = con.prepareStatement(sql)) {
            stm.setInt(1, personId);
            ResultSet result = stm.executeQuery();

            while (result.next()) {
                  clientId = result.getInt("id_cliente");
            }
        } catch (Exception e) {
            System.out.println("Erro ao retornar id de cliente por id de pessoa: " + e.getMessage());
            Alert.showMessageDialog("Erro ao retornar id de cliente por id de pessoa: \n" + e.getMessage());
        }

        return clientId;
    }
}
