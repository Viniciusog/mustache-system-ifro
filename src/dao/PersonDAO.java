/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import com.sun.org.apache.bcel.internal.generic.AALOAD;
import configuration.MustacheMySQLConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.naming.spi.DirStateFactory;
import javax.swing.JOptionPane;
import model.Service;
import model.Person;
import model.Sex;
import util.Alert;

/**
 *
 * @author vinij
 */
public class PersonDAO {

    public static void insert(Person p) {
        if (!verifyExistsPersonByCPF(p.getCpf())) {
            Connection con = MustacheMySQLConnection.getConnection();
            String sql = "INSERT INTO Pessoa (id_sexo, nome_pessoa, data_nascimento_pessoa, "
                    + "cpf_pessoa, telefone_pessoa, e_cliente_pessoa, e_funcionario_pessoa) "
                    + "values (?, ?, str_to_date(?, '%d/%m/%Y'), ?, ?, ?, ?)";
            try (PreparedStatement stm = con.prepareStatement(sql)) {
                stm.setInt(1, p.getSex().getId());
                stm.setString(2, p.getName());
                stm.setString(3, p.getBirthday());
                stm.setString(4, p.getCpf());
                stm.setString(5, p.getPhoneNumber());
                stm.setBoolean(6, p.getIsClient());
                stm.setBoolean(7, p.getIsEmployee());
                stm.executeUpdate();
                con.close();

                
                con.close();
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Erro ao cadastrar pessoa!: " + e.getMessage());
                
            }
        } else {
        }
    }

    public static void update(Person p) {
        Connection con = MustacheMySQLConnection.getConnection();
        String sql = "UPDATE Pessoa set "
                + "id_sexo = ?, "
                + "nome_pessoa = ?, "
                + "data_nascimento_pessoa = str_to_date(?, '%d/%m/%Y'), "
                + "cpf_pessoa=?, "
                + "telefone_pessoa=?, "
                + "e_cliente_pessoa = ?,"
                + "e_funcionario_pessoa = ?"
                + " WHERE id_pessoa = ?";
        try (PreparedStatement stm = con.prepareStatement(sql)) {
            stm.setInt(1, p.getSex().getId());
            stm.setString(2, p.getName());
            stm.setString(3, p.getBirthday());
            stm.setString(4, p.getCpf());
            stm.setString(5, p.getPhoneNumber());
            stm.setBoolean(6, p.getIsClient());
            stm.setBoolean(7, p.getIsEmployee());
            stm.setInt(8, p.getId());
            stm.executeUpdate();
            con.close();

            
            con.close();
        } catch (Exception e) {
            System.out.println("Error updating person!: " + e.getMessage());
            
        }
    }

    /*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*/
 /*LEMBRAR QUE PESSOA PODE TER RELAÇÃO COM OUTRAS TABELAS NA HORA DE DELETAR*/
    //PODE TER RELAÇÃO COM OUTRAS TABELAS COMO VENDAS E ENTRE OUTROS
    public static void delete(int idPerson) {
        Connection con = MustacheMySQLConnection.getConnection();
        String sql = "DELETE FROM Pessoa WHERE id_pessoa = ?";
        try (PreparedStatement stm = con.prepareStatement(sql)) {
            stm.setInt(1, idPerson);
            stm.executeUpdate();
            stm.close();
            
        } catch (Exception e) {
            System.out.println("Erro ao deletar pessoa: " + e.getMessage());
            
        }
    }

    public List<Person> getListPerson(String name) {
        Connection con = MustacheMySQLConnection.getConnection();
        List<Person> personList = new ArrayList();
        String sql = "SELECT pessoa.id_pessoa, pessoa.id_sexo, pessoa.nome_pessoa, pessoa.cpf_pessoa"
                + ", pessoa.telefone_pessoa, "
                + "date_format(data_nascimento_pessoa, '%d/%m/%Y') as data_nascimento_pessoa,"
                + "pessoa.e_cliente_pessoa, "
                + "pessoa.e_funcionario_pessoa,"
                + "sexo.nome_sexo from pessoa "
                + "JOIN sexo "
                + "WHERE pessoa.id_sexo = sexo.id_sexo AND pessoa.nome_pessoa LIKE ? ORDER BY pessoa.nome_pessoa";
        try (PreparedStatement stm = con.prepareStatement(sql)) {
            stm.setString(1, "%" + name + "%");
            ResultSet result = stm.executeQuery();
            while (result.next()) {
                Person p = new Person();
                p.setId(result.getInt("id_pessoa"));
                p.setBirthday(result.getString("data_nascimento_pessoa"));
                p.setCpf(result.getString("cpf_pessoa"));
                p.setPhoneNumber(result.getString("telefone_pessoa"));
                p.setIsClient(result.getBoolean("e_cliente_pessoa"));
                p.setIsEmployee(result.getBoolean("e_funcionario_pessoa"));
                p.setSex(new Sex(result.getInt("id_sexo"), result.getString("nome_sexo")));
                p.setName(result.getString("nome_pessoa"));
                personList.add(p);
            }
        } catch (Exception e) {
            System.out.println("Error list person: " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Erro ao carregar pessoas, Tente Novamente");
        }
        return personList;
    }

    //PAREI AQUI
    public Person getPersonByCPF(String cpf) {
        Connection con = MustacheMySQLConnection.getConnection();
        Person p = new Person();
        String sql = "SELECT pessoa.id_pessoa, pessoa.id_sexo, "
                + "pessoa.nome_pessoa, pessoa.cpf_pessoa, "
                + "pessoa.telefone_pessoa, "
                + "date_format(pessoa.data_nascimento_pessoa, '%d/%m/%Y') as data_nascimento_pessoa, "
                + "pessoa.e_cliente_pessoa,"
                + "pessoa.e_funcionario_pessoa,"
                + "sexo.nome_sexo FROM pessoa "
                + "JOIN sexo WHERE pessoa.cpf_pessoa = ? "
                + "AND pessoa.id_sexo = sexo.id_sexo";
        try (PreparedStatement stm = con.prepareStatement(sql)) {
            stm.setString(1, cpf);
            ResultSet result = stm.executeQuery();
            while (result.next()) {
                p.setId(result.getInt("id_pessoa"));
                p.setBirthday(result.getString("data_nascimento_pessoa"));
                p.setCpf(result.getString("cpf_pessoa"));
                p.setPhoneNumber(result.getString("telefone_pessoa"));
                Sex s = new Sex();
                s.setId(result.getInt("id_sexo"));
                s.setName(result.getString("nome_sexo"));
                p.setName(result.getString("nome_pessoa"));
                p.setIsClient(result.getBoolean("e_cliente_pessoa"));
                p.setIsEmployee(result.getBoolean("e_funcionario_pessoa"));
                p.setSex(s);
            }
        } catch (Exception e) {
            System.out.println("Erro ao retornar pessoa por CPF: " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Erro ao carregar pessoa por CPF, Tente Novamente \n" + e.getMessage());
        }
        return p;
    }

    public int getPersonIdByCPF(String cpf) {
        Connection con = MustacheMySQLConnection.getConnection();
        int idPerson = 0;
        String sql = "SELECT id_pessoa FROM pessoa WHERE cpf_pessoa = ?";
        try (PreparedStatement stm = con.prepareStatement(sql)) {
            stm.setString(1, cpf);
            ResultSet result = stm.executeQuery();
            while (result.next()) {
                idPerson = result.getInt("id_pessoa");
            }
        } catch (Exception e) {
            System.out.println("Erro ao retornar id pessoa por CPF: " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Erro ao carregar id de pessoa por CPF. \n Tente Novamente \n" + e.getMessage());
        }
        return idPerson;
    }

    public Person getPersonById(int id) {
        Connection con = MustacheMySQLConnection.getConnection();
        Person p = new Person();
        String sql = "SELECT pessoa.id_pessoa, pessoa.id_sexo, "
                + "pessoa.nome_pessoa, pessoa.cpf_pessoa, "
                + "pessoa.telefone_pessoa, "
                + "date_format(pessoa.data_nascimento_pessoa, '%d/%m/%Y') as data_nascimento_pessoa, "
                + "pessoa.e_cliente_pessoa,"
                + "pessoa.e_funcionario_pessoa,"
                + "sexo.nome_sexo FROM pessoa "
                + "JOIN sexo WHERE pessoa.id_pessoa = ? "
                + "AND pessoa.id_sexo = sexo.id_sexo";
        try (PreparedStatement stm = con.prepareStatement(sql)) {
            stm.setInt(1, id);
            ResultSet result = stm.executeQuery();
            while (result.next()) {
                p.setId(result.getInt("id_pessoa"));
                p.setBirthday(result.getString("data_nascimento_pessoa"));
                p.setCpf(result.getString("cpf_pessoa"));
                p.setPhoneNumber(result.getString("telefone_pessoa"));
                Sex s = new Sex();
                s.setId(result.getInt("id_sexo"));
                s.setName(result.getString("nome_sexo"));
                p.setName(result.getString("nome_pessoa"));
                p.setIsClient(result.getBoolean("e_cliente_pessoa"));
                p.setIsEmployee(result.getBoolean("e_funcionario_pessoa"));
                p.setSex(s);
            }
        } catch (Exception e) {
            System.out.println("Erro ao retornar pessoa por id: " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Erro ao carregar pessoa por id, Tente Novamente \n" + e.getMessage());
        }
        return p;
    }

    public static boolean verifyExistsPersonByCPF(String cpf) {
        Connection con = MustacheMySQLConnection.getConnection();
        String sql = "select id_pessoa FROM pessoa "
                + "WHERE pessoa.cpf_pessoa = ?;";
        boolean exists = false;
        try (PreparedStatement stm = con.prepareStatement(sql)) {
            stm.setString(1, cpf);
            ResultSet result = stm.executeQuery();

            while (result.next()) {
                if (result.getInt("id_pessoa") != -1) {
                    exists = true;
                }
            }
        } catch (Exception e) {
            System.out.println("Erro ao verificar se pessoa existe por cpf: " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Erro ao verificar existência de pessoa por CPF: \n " + e.getMessage());
        }
        return exists;
    }

    public boolean getIsEmployeeAttributeByPersonId(int id) {
        Connection con = MustacheMySQLConnection.getConnection();
        String sql = "select e_funcionario_pessoa FROM pessoa "
                + "WHERE pessoa.id_pessoa = ?;";
        boolean eEmployee = false;
        try (PreparedStatement stm = con.prepareStatement(sql)) {
            stm.setInt(1, id);
            ResultSet result = stm.executeQuery();

            while (result.next()) {
                eEmployee = result.getBoolean("e_funcionario_pessoa");
            }
        } catch (Exception e) {
            System.out.println("Erro ao verificar se pessoa e_funcionario: " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Erro ao verificar se pessoa é funcionário: \n " + e.getMessage());
        }
        return eEmployee;
    }
}
