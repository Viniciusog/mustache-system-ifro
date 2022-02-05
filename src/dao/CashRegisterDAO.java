package dao;

import configuration.MustacheMySQLConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.CashRegisterModel;

public class CashRegisterDAO {

    public static void open(CashRegisterModel cashRegister) {
        if (!isOpen()) {
            Connection con = MustacheMySQLConnection.getConnection();
            String sql = "INSERT INTO Caixa ("
                    + "valor_inicial_caixa, "
                    + "valor_final_caixa, "
                    + "datahora_abertura_caixa,"
                    + "datahora_fechamento_caixa,"
                    + "situacao) "
                    + "values (?,?,?, NULL, True)";
            try (PreparedStatement stm = con.prepareStatement(sql)) {
                //Date date = new Date(System.currentTimeMillis());
                //java.util.Date date = new java.util.Date();

                java.sql.Timestamp timestamp = new java.sql.Timestamp(System.currentTimeMillis());

                stm.setFloat(1, cashRegister.getInitialValue());
                stm.setFloat(2, cashRegister.getInitialValue());
                stm.setTimestamp(3, timestamp);
                stm.executeUpdate();

                JOptionPane.showMessageDialog(null, "Caixa aberto com sucesso!");
                con.close();
                stm.close();
            } catch (Exception e) {
                System.out.println("Error open new cash register!: " + e.getMessage());
                JOptionPane.showMessageDialog(null, "Erro ao abrir caixa!, Tente Novamente");
            }
        }
    }

    public static void close() {
        int id_cashRegister = getIdOpenedCashRegister();
        if (id_cashRegister != 0) {
            Connection con = MustacheMySQLConnection.getConnection();
            String sql = "UPDATE Caixa SET "
                    + "situacao = False,"
                    + "datahora_fechamento_caixa = ?"
                    + "WHERE id_caixa = ?";
            try (PreparedStatement stm = con.prepareStatement(sql)) {

                java.sql.Timestamp timestamp = new java.sql.Timestamp(System.currentTimeMillis());

                stm.setTimestamp(1, timestamp);
                stm.setInt(2, id_cashRegister);
                stm.executeUpdate();

                JOptionPane.showMessageDialog(null, "Caixa encerrado com sucesso!");
                con.close();
                stm.close();
            } catch (Exception e) {
                System.out.println("Error close cash register!: " + e.getMessage());
                JOptionPane.showMessageDialog(null, "Erro ao fechar o caixa!, Tente Novamente");
            }
        }
    }

    public static boolean isOpen() {
        Connection con = MustacheMySQLConnection.getConnection();
        String sql = "SELECT situacao FROM Caixa ORDER BY id_caixa DESC LIMIT 1";
        try (PreparedStatement stm = con.prepareStatement(sql)) {
            ResultSet result = stm.executeQuery();
            while (result.next()) {
                return result.getBoolean("situacao") == true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error isOpen cash: " + e.getMessage());
        }
        return false;
    }

    public static int getIdOpenedCashRegister() {
        Connection con = MustacheMySQLConnection.getConnection();
        String sql = "SELECT id_caixa,situacao FROM Caixa ORDER BY id_caixa DESC LIMIT 1";
        try (PreparedStatement stm = con.prepareStatement(sql)) {
            ResultSet result = stm.executeQuery();
            while (result.next()) {
                if (result.getBoolean("situacao") == true) {
                    System.out.println(result.getInt("id_caixa"));
                    return result.getInt("id_caixa");
                }
            }
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error isOpen cash: " + e.getMessage());
        }
        return 0;
    }

    public static void withdrawCash(float value) {
        int id_cashRegister = getIdOpenedCashRegister();
        if (id_cashRegister != 0) {
            Connection con = MustacheMySQLConnection.getConnection();
            String sql = "UPDATE Caixa SET "
                    + "valor_final_caixa = (valor_final_caixa - ?)"
                    + "WHERE id_caixa = ?";
            try (PreparedStatement stm = con.prepareStatement(sql)) {

                stm.setFloat(1, value);
                stm.setInt(2, id_cashRegister);
                stm.executeUpdate();

                JOptionPane.showMessageDialog(null, "Dinheiro depositado no caixa com sucesso!");
                con.close();
                stm.close();
            } catch (Exception e) {
                System.out.println("Error withdraw cash!: " + e.getMessage());
                JOptionPane.showMessageDialog(null, "Erro ao depositar dinheiro no caixa!, Tente Novamente");
            }
        }
    }

    public static void depositCash(float value) {
        int id_cashRegister = getIdOpenedCashRegister();
        if (id_cashRegister != 0) {
            Connection con = MustacheMySQLConnection.getConnection();
            String sql = "UPDATE Caixa SET "
                    + "valor_final_caixa = (valor_final_caixa + ?)"
                    + "WHERE id_caixa = ?";
            try (PreparedStatement stm = con.prepareStatement(sql)) {

                stm.setFloat(1, value);
                stm.setInt(2, id_cashRegister);
                stm.executeUpdate();

                JOptionPane.showMessageDialog(null, "Dinheiro depositado no caixa com sucesso!");
                con.close();
                stm.close();
            } catch (Exception e) {
                System.out.println("Error withdraw cash!: " + e.getMessage());
                JOptionPane.showMessageDialog(null, "Erro ao depositar dinheiro no caixa!, Tente Novamente");
            }
        }
    }

    public static float getValue() {
        int id_cashRegister = getIdOpenedCashRegister();
        if (id_cashRegister != 0) {
            Connection con = MustacheMySQLConnection.getConnection();
            String sql = "SELECT valor_final_caixa FROM Caixa WHERE id_caixa = ?";
            try (PreparedStatement stm = con.prepareStatement(sql)) {

                stm.setInt(1, id_cashRegister);

                ResultSet result = stm.executeQuery();
                while (result.next()) {
                    return result.getInt("valor_final_caixa");
                }
                return 0;
            } catch (Exception e) {
                System.out.println("Error withdraw cash!: " + e.getMessage());
                JOptionPane.showMessageDialog(null, "Erro ao depositar dinheiro no caixa!, Tente Novamente");
            }
        }
        return 0;
    }
    
     public List<CashRegisterModel> getListCash(String name) {
        Connection con = MustacheMySQLConnection.getConnection();
        List<CashRegisterModel> roleCashRegister = new ArrayList();
        String sql = "SELECT * FROM Caixa where situacao = '0'";
        try (PreparedStatement stm = con.prepareStatement(sql)) {
            ResultSet result = stm.executeQuery();
            while (result.next()) {
                CashRegisterModel cr = new CashRegisterModel ();
                cr.setId(result.getInt("id_caixa"));
                cr.setInitialValue(result.getFloat("valor_inicial_caixa"));
                cr.setEndValue(result.getFloat("valor_final_caixa"));
                cr.setOpenDateTime(result.getString("datahora_abertura_caixa"));
                cr.setCloseDateTime(result.getString("datahora_fechamento_caixa"));
                cr.setStatus(result.getBoolean("situacao"));
                roleCashRegister.add(cr);
            }
        } catch (Exception e) {
            System.out.println("Error list role: " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Erro ao carregar cargos, tente novamente", null, JOptionPane.ERROR_MESSAGE);
        }
        return roleCashRegister;
    }    
           
}
