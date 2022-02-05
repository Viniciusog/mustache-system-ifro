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
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.Employee;
import model.Person;
import model.User;

/**
 *
 * @author vinij
 */
public class UserDAO {

    public static void insert(User u) {

        //Se não existir usuário com mesmo nome no banco de dados, cadastre
        if (!existsUserByName(u.getName())) {
            Connection con = MustacheMySQLConnection.getConnection();
            String sql = "INSERT INTO Usuario (id_funcionario_usuario, nome_usuario, senha_usuario) values (?, ?, MD5(?))";
            try (PreparedStatement stm = con.prepareStatement(sql)) {
                stm.setInt(1, u.getEmployee().getId());
                stm.setString(2, u.getName());
                stm.setString(3, u.getPassword());
                stm.executeUpdate();

                JOptionPane.showMessageDialog(null, "Usuário cadastrado com sucesso!");
                con.close();
                stm.close();
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Error saving user!: " + e.getMessage());
                JOptionPane.showMessageDialog(null, "Erro ao cadastrar usuário!, Tente Novamente");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Já existe usuário cadastrado \n com esse nome!, "
                    + "Tente Novamente");
        }
    }

    public static void update(User u) {

        Connection con = MustacheMySQLConnection.getConnection();
        String sql = "UPDATE Usuario set id_funcionario_usuario = ?, nome_usuario = ?, senha_usuario = MD5(?) WHERE id_usuario = ?";
        try (PreparedStatement stm = con.prepareStatement(sql)) {
            stm.setInt(1, u.getEmployee().getId());
            stm.setString(2, u.getName());
            stm.setString(3, u.getPassword());
            stm.setInt(3, u.getId());
            stm.executeUpdate();

            JOptionPane.showMessageDialog(null, "Usuário atualizado com sucesso!");
            con.close();
            stm.close();

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error updating user!: " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Erro ao atualizar usuário! \n Tente Novamente.");
        }
    }

    public static boolean existsUserByName(String name) {
        Connection con = MustacheMySQLConnection.getConnection();
        int cont = 0;
        String sql = "SELECT id_usuario from usuario WHERE nome_usuario = ?";
        try (PreparedStatement stm = con.prepareStatement(sql)) {
            stm.setString(1, name);
            ResultSet result = stm.executeQuery();
            while (result.next()) {
                cont++;
            }
            con.close();
            stm.close();
        } catch (Exception ex) {
            System.out.println("Erro verificando se já existe usuário cadastrado com mesmo nome");
            JOptionPane.showMessageDialog(null, "Erro ao criar usuário. \n Verifique sua conexão com o banco de dados \n e"
                    + " tente novamente!");
        }
        if (cont > 0) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean existsUserByNameAndPassword(String name, String password) {
        Connection con = MustacheMySQLConnection.getConnection();
        int cont = 0;
        String sql = "SELECT id_usuario from usuario WHERE nome_usuario = ? AND senha_usuario = MD5(?)";
        try (PreparedStatement stm = con.prepareStatement(sql)) {
            stm.setString(1, name);
            stm.setString(2, password);
            ResultSet result = stm.executeQuery();
            while (result.next()) {
                cont++;
            }
            con.close();
            stm.close();
        } catch (Exception e) {
            System.out.println("Error verify user: " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Erro ao verificar seu usuário, Tente Novamente");
        }
        if (cont > 0) {
            return true;
        } else {
            return false;
        }
    }

    public static void delete(int id) {
        Connection con = MustacheMySQLConnection.getConnection();
        String sql = "DELETE FROM Usuario WHERE id_usuario=?";
        try (PreparedStatement stm = con.prepareStatement(sql)) {
            stm.setInt(1, id);

            stm.executeUpdate();
            JOptionPane.showMessageDialog(null, "Usuário deletado com sucesso!");

            con.close();
            stm.close();

        } catch (Exception e) {
            System.out.println("Error while trying to delete user, error: " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Erro ao deletar usuário, Tente novamente");
        }
    }

    public List<User> getListUser(String name) {
        Connection con = MustacheMySQLConnection.getConnection();
        List<User> usersList = new ArrayList<>();

        String sql = "SELECT Usuario.id_usuario, "
                + "Usuario.nome_usuario, Usuario.id_funcionario_usuario, Pessoa.nome_pessoa FROM Usuario JOIN "
                + "Funcionario, Pessoa WHERE (Usuario.nome_usuario LIKE ?"
                + " AND Usuario.id_funcionario_usuario = Funcionario.id_funcionario "
                + "AND Funcionario.id_pessoa = Pessoa.id_pessoa) "
                + "ORDER BY Usuario.nome_usuario";

        try (PreparedStatement stm = con.prepareStatement(sql)) {
            stm.setString(1, "%" + name + "%");
            ResultSet result = stm.executeQuery();
            while (result.next()) {
                User u = new User();
                u.setId(result.getInt("id_usuario"));
                u.setName(result.getString("nome_usuario"));

                //Procura o funcionário no banco de dados, apartir do id de funcionário encontrado no usuário
                Employee e = new Employee();
                e.setId(result.getInt("id_funcionario_usuario"));
                Person p = new Person();
                p.setName(result.getString("nome_pessoa"));
                e.setPerson(p);
                u.setEmployee(e);

                usersList.add(u);
            }
            con.close();
            stm.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error list users: " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Erro ao carregar usuários, Tente Novamente" + e.getMessage());
        }
        return usersList;
    }
}
