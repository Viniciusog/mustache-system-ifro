package dao;

import configuration.MustacheMySQLConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.Role;

public class RoleDAO {

    public static void insert(Role role) {
        Connection con = MustacheMySQLConnection.getConnection();
        String sql = "INSERT INTO Cargo (nome_cargo, perm_pag_contas, perm_rec_contas, perm_cad_marca, perm_comprar, perm_depositar, perm_abrir_caixa, perm_sangria, perm_cad_recebimento, perm_cad_pessoa, perm_cad_forn, perm_cad_prod, perm_receber, perm_cad_cargo, perm_vender, perm_cad_serv, perm_imprimir_relatorio) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stm = con.prepareStatement(sql)) {
            stm.setString (1, role.getName());
            stm.setBoolean(2, role.isPayBill());
            stm.setBoolean (3, role.isReceiveBill());
            stm.setBoolean (4, role.isAddBrand());
            stm.setBoolean (5, role.isBuy());
            stm.setBoolean (6, role.isDepositCashier());
            stm.setBoolean (7, role.isOpenCashier());
            stm.setBoolean (8, role.isWithdrawCashier());
            stm.setBoolean (9, role.isAddReceivement());
            stm.setBoolean (10, role.isAddPerson());
            stm.setBoolean (11, role.isAddSupplier());
            stm.setBoolean (12, role.isAddProd());
            stm.setBoolean (13, role.isReceive());
            stm.setBoolean (14, role.isAddRole());
            stm.setBoolean (15, role.isSell());
            stm.setBoolean (16, role.isAddServ());
            stm.setBoolean(17, role.isUseReport());
            stm.executeUpdate();
            stm.close();

            JOptionPane.showMessageDialog(null, "Cargo inserido com sucesso!");
            con.close();
        } catch (Exception e) {
            System.out.println("Erro ao salvar serviço: " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar cargo, tente novamente", null, JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void update(Role role) {
        Connection con = MustacheMySQLConnection.getConnection();
        String sql = "UPDATE Cargo set nome_cargo = ?, perm_pag_contas = ?, perm_rec_contas = ?, perm_cad_marca = ?, perm_comprar = ?, perm_depositar = ?, perm_abrir_caixa = ?, perm_sangria = ?, perm_cad_recebimento = ?, perm_cad_pessoa = ?, perm_cad_forn = ?,  perm_cad_prod = ?, perm_receber = ?, perm_cad_cargo = ?, perm_vender = ?, perm_cad_serv = ?, perm_imprimir_relatorio=? WHERE id_cargo = ?";
        try (PreparedStatement stm = con.prepareStatement(sql)) {
            
            stm.setString (1, role.getName());
            stm.setBoolean(2, role.isPayBill());
            stm.setBoolean (3, role.isReceiveBill());
            stm.setBoolean (4, role.isAddBrand());
            stm.setBoolean (5, role.isBuy());
            stm.setBoolean (6, role.isDepositCashier());
            stm.setBoolean (7, role.isOpenCashier());
            stm.setBoolean (8, role.isWithdrawCashier());
            stm.setBoolean (9, role.isAddReceivement());
            stm.setBoolean (10, role.isAddPerson());
            stm.setBoolean (11, role.isAddSupplier());
            stm.setBoolean (12, role.isAddProd());
            stm.setBoolean (13, role.isReceive());
            stm.setBoolean (14, role.isAddRole());
            stm.setBoolean (15, role.isSell());
            stm.setBoolean (16, role.isAddServ());
             stm.setBoolean(17, role.isUseReport());
            stm.setInt(18, role.getId());

            Object[] options = {"Cancelar", "Sim"};
            int answer = JOptionPane.showOptionDialog(null, "Deseja atualizar o registro?", null, JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]);

            if (answer == 1) {
                stm.executeUpdate();
            }
            stm.close();

            JOptionPane.showMessageDialog(null, "Cargo atualizado com sucesso!");
            con.close();
        } catch (Exception e) {
            System.out.println("Error while updating roles: " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Erro ao atualizar os cargos, tente novamente", null, JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void delete(Role role) {
        Connection con = MustacheMySQLConnection.getConnection();
        String sql = "DELETE FROM Cargo where id_cargo = ?";
        try (PreparedStatement stm = con.prepareStatement(sql)) {
            stm.setInt(1, role.getId());

            Object[] options = {"Cancelar", "Sim"};
            int answer = JOptionPane.showOptionDialog(null, "Deseja deletar o registro?", null, JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]);

            if (answer == 1) {
                stm.executeUpdate();
            }
            stm.close();

            JOptionPane.showMessageDialog(null, "Cargo excluído com sucesso!");
            con.close();
        } catch (Exception e) {
            System.out.println("Error while deleting roles: " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Erro ao deletar os cargos, tente novamente", null, JOptionPane.ERROR_MESSAGE);
        }
    }

    public List<Role> getListRole(String name) {
        Connection con = MustacheMySQLConnection.getConnection();
        List<Role> roleList = new ArrayList();
        String sql = "SELECT * FROM Cargo where id_cargo LIKE ?";
        try (PreparedStatement stm = con.prepareStatement(sql)) {
            stm.setString(1, "%" + name + "%");
            ResultSet result = stm.executeQuery();
            while (result.next()) {
                Role role = new Role();
                role.setId(result.getInt("id_cargo"));
                role.setName(result.getString("nome_cargo"));
                role.setPayBill(result.getBoolean("perm_pag_contas"));
                role.setReceiveBill(result.getBoolean("perm_rec_contas"));
                role.setAddBrand(result.getBoolean("perm_cad_marca"));
                role.setBuy(result.getBoolean("perm_comprar"));
                role.setDepositCashier(result.getBoolean("perm_depositar"));
                role.setOpenCashier(result.getBoolean("perm_abrir_caixa"));
                role.setWithdrawCashier(result.getBoolean("perm_sangria"));
                role.setAddReceivement(result.getBoolean("perm_cad_recebimento"));
                role.setAddPerson(result.getBoolean("perm_cad_pessoa"));
                role.setAddSupplier(result.getBoolean("perm_cad_forn"));
                role.setAddProd(result.getBoolean("perm_cad_prod"));
                role.setReceive(result.getBoolean("perm_receber"));
                role.setAddRole(result.getBoolean("perm_cad_cargo"));
                role.setSell(result.getBoolean("perm_vender"));
                role.setAddServ(result.getBoolean("perm_cad_serv"));
                role.setUseReport(result.getBoolean("perm_imprimir_relatorio"));
                roleList.add(role);
            }
        } catch (Exception e) {
            System.out.println("Error list role: " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Erro ao carregar cargos, tente novamente", null, JOptionPane.ERROR_MESSAGE);
        }
        return roleList;
    }

    public static Role getRoleByName(String name) {
        Connection con = MustacheMySQLConnection.getConnection();
        Role role = new Role();
        String sql = "SELECT * FROM Cargo where nome_cargo = ?";
        try (PreparedStatement stm = con.prepareStatement(sql)) {
            stm.setString(1, name);
            ResultSet result = stm.executeQuery();
            while (result.next()) {
                
                role.setId(result.getInt("id_cargo"));
                role.setName(result.getString("nome_cargo"));
                role.setPayBill(Boolean.getBoolean("perm_pag_contas"));
                role.setReceiveBill(Boolean.getBoolean("perm_rec_contas"));
                role.setAddBrand(Boolean.getBoolean("perm_cad_marca"));
                role.setBuy(Boolean.getBoolean("perm_comprar"));
                role.setDepositCashier(Boolean.getBoolean("perm_depositar"));
                role.setOpenCashier(Boolean.getBoolean("perm_abrir_caixa"));
                role.setWithdrawCashier(Boolean.getBoolean("perm_sangria"));
                role.setAddReceivement(Boolean.getBoolean("perm_cad_recebimento"));
                role.setAddPerson(Boolean.getBoolean("perm_cad_pessoa"));
                role.setAddSupplier(Boolean.getBoolean("perm_cad_forn"));
                role.setAddProd(Boolean.getBoolean("perm_cad_prod"));
                role.setReceive(Boolean.getBoolean("perm_receber"));
                role.setAddRole(Boolean.getBoolean("perm_cad_cargo"));
                role.setSell(Boolean.getBoolean("perm_vender"));
                role.setAddServ(Boolean.getBoolean("perm_cad_serv"));
                role.setUseReport(result.getBoolean("perm_imprimir_relatorio"));
                
            }
        } catch (Exception e) {
            System.out.println("Erro ao carregar cargo por nome: " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Erro ao carregar cargo por nome, tente novamente", null, JOptionPane.ERROR_MESSAGE);
        }
        return role;
    }
}
