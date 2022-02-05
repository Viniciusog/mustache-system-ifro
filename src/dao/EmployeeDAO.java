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
import model.Employee;
import model.Person;
import model.Role;
import util.Alert;

/**
 *
 * @author vinij
 */
public class EmployeeDAO {

    public void insert(Employee e) {
        Connection c = MustacheMySQLConnection.getConnection();
        String sql = "INSERT INTO funcionario (id_pessoa, "
                + "id_cargo_funcionario, "
                + "salario_funcionario, "
                + "estado_civil_funcionario,"
                + "grau_instrucao_funcionario,"
                + "carteira_de_trabalho_funcionario,"
                + "nome_mae_funcionario,"
                + "nome_pai_funcionario,"
                + "email_funcionario) VALUES (?,?,?,?,?,?,?,?,?)";
        try (PreparedStatement stm = c.prepareStatement(sql)) {
            stm.setInt(1, e.getPerson().getId());
            stm.setInt(2, e.getRole().getId());
            stm.setFloat(3, e.getSalary());
            stm.setString(4, e.getCivilState());
            stm.setString(5, e.getEducation());
            stm.setString(6, e.getCtps());
            stm.setString(7, e.getMothersName());
            stm.setString(8, e.getFathersName());
            stm.setString(9, e.getEmail());
            stm.executeUpdate();
            stm.close();

            Alert.showMessageDialog("Sucesso ao cadastrar funcionário!");
            c.close();
        } catch (Exception ex) {
            System.out.println("Erro ao cadastrar funcionario. " + ex.getMessage());
            Alert.showErrorMessageDialog("Erro ao cadastrar funcionário! \n " + ex.getMessage());
        }
    }

    public void update(Employee e) {
        Connection c = MustacheMySQLConnection.getConnection();
        String sql = "update funcionario SET id_pessoa = ?,"
                + "id_cargo_funcionario = ?, "
                + "salario_funcionario = ?, "
                + "estado_civil_funcionario = ?,"
                + "grau_instrucao_funcionario = ?,"
                + "carteira_de_trabalho_funcionario = ?,"
                + "nome_mae_funcionario = ?,"
                + "nome_pai_funcionario = ?,"
                + "email_funcionario = ? WHERE id_funcionario = ?";
        try (PreparedStatement stm = c.prepareStatement(sql)) {
            stm.setInt(1, e.getPerson().getId());
            stm.setInt(2, e.getRole().getId());
            stm.setFloat(3, e.getSalary());
            stm.setString(4, e.getCivilState());
            stm.setString(5, e.getEducation());
            stm.setString(6, e.getCtps());
            stm.setString(7, e.getMothersName());
            stm.setString(8, e.getFathersName());
            stm.setString(9, e.getEmail());
            stm.setInt(10, e.getId());
            stm.executeUpdate();
            stm.close();

            Alert.showMessageDialog("Sucesso ao editar funcionário!");
            c.close();
        } catch (Exception ex) {
            System.out.println("Erro ao editar funcionario. " + ex.getMessage());
            Alert.showErrorMessageDialog("Erro ao editar funcionário! \n " + ex.getMessage());
        }
    }

    public static Employee getEmployeeByPersonId(int id) {

        Connection con = MustacheMySQLConnection.getConnection();
        Employee e = new Employee();
        String sql = "SELECT funcionario.* , cargo.nome_cargo FROM funcionario JOIN cargo WHERE funcionario.id_pessoa = ? AND funcionario.id_cargo_funcionario = cargo.id_cargo";
        try (PreparedStatement stm = con.prepareStatement(sql)) {
            stm.setInt(1, id);
            ResultSet result = stm.executeQuery();
            while (result.next()) {
                Person p = new Person();
                p.setId(result.getInt("id_pessoa"));
                e.setPerson(p);
                e.setId(result.getInt("id_funcionario"));
                Role r = new Role();
                r.setId(result.getInt("id_cargo_funcionario"));
                r.setName(result.getString("nome_cargo"));
                e.setRole(r);
                e.setSalary(result.getFloat("salario_funcionario"));
                e.setCivilState(result.getString("estado_civil_funcionario"));
                e.setCtps(result.getString("carteira_de_trabalho_funcionario"));
                e.setEducation(result.getString("grau_instrucao_funcionario"));
                e.setMothersName(result.getString("nome_mae_funcionario"));
                e.setFathersName(result.getString("nome_pai_funcionario"));
                e.setEmail(result.getString("email_funcionario"));
            }
        } catch (Exception ex) {
            System.out.println("Erro ao retornar funcionario por id: " + ex.getMessage());
            JOptionPane.showMessageDialog(null, "Erro ao carregar funcionario por id, Tente Novamente \n" + ex.getMessage());
        }
        return e;
    }

    public static List<Employee> getListEmployee(String name) {

        Connection con = MustacheMySQLConnection.getConnection();
        List<Employee> employees = new ArrayList<>();
        String sql = "SELECT funcionario.id_funcionario, "
                + "pessoa.id_pessoa,"
                + "pessoa.cpf_pessoa, "
                + "pessoa.nome_pessoa "
                + "FROM funcionario JOIN pessoa "
                + "WHERE funcionario.id_pessoa = pessoa.id_pessoa AND pessoa.nome_pessoa LIKE ? AND pessoa.e_funcionario_pessoa = true";
        try (PreparedStatement stm = con.prepareStatement(sql)) {
            stm.setString(1, "%" + name + "%");
            ResultSet result = stm.executeQuery();
            while (result.next()) {
                Employee e = new Employee();
                Person p = new Person();
                p.setId(result.getInt("id_pessoa"));
                p.setName(result.getString("nome_pessoa"));
                p.setCpf(result.getString("cpf_pessoa"));
                e.setPerson(p);
                e.setId(result.getInt("id_funcionario"));
                employees.add(e);
            }
        } catch (Exception ex) {
            System.out.println("Erro ao listar funcionarios por nome de pessoa: " + ex.getMessage());
            JOptionPane.showMessageDialog(null, "Erro ao carregar funcionarios por nome de pessoa, \n Tente Novamente \n" + ex.getMessage());
        }
        return employees;
    }

    public static int getEmployeeIdByPersonId(int personId) {

        Connection con = MustacheMySQLConnection.getConnection();
        int employeeId = 0;
        String sql = "SELECT id_funcionario FROM funcionario WHERE id_pessoa = ?";
        try (PreparedStatement stm = con.prepareStatement(sql)) {
            stm.setInt(1, personId);
            ResultSet result = stm.executeQuery();
            while (result.next()) {
                employeeId = result.getInt("id_funcionario");
            }
        } catch (Exception ex) {
            System.out.println("Erro ao retornar funcionario por id: " + ex.getMessage());
            JOptionPane.showMessageDialog(null, "Erro ao carregar funcionario por id, Tente Novamente \n" + ex.getMessage());
        }
        return employeeId;
    }
}
