package dao;

import configuration.MustacheMySQLConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.Brand;
import model.Product;

public class ProductDAO {

    public static void insert(Product prod) {
        Connection con = MustacheMySQLConnection.getConnection();
        String sql = "insert into Produto (id_marca,nome_produto,preco_produto,qtd_estoque) values (?,?,?,?)";
        try (PreparedStatement stm = con.prepareStatement(sql)) {
            stm.setInt(1, prod.getBrand().getId());
            stm.setString(2, prod.getName());
            stm.setDouble(3, prod.getPrice());
            stm.setInt(4, prod.getStock());
            stm.executeUpdate();
            stm.close();

            JOptionPane.showMessageDialog(null, "Produto inserido com sucesso!");
            con.close();
        } catch (Exception e) {
            System.out.println("Erro ao inserir produto: " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar produto, tente novamente", null, JOptionPane.ERROR_MESSAGE);
        }

    }

    public static void update(Product prod) {
        Connection con = MustacheMySQLConnection.getConnection();
        String sql = "update Produto set id_marca=? , nome_produto=?, preco_produto=?, qtd_estoque=? where id_produto = ?";
        try (PreparedStatement stm = con.prepareStatement(sql)) {
            stm.setInt(1, prod.getBrand().getId());
            stm.setString(2, prod.getName());
            stm.setDouble(3, prod.getPrice());
            stm.setInt(4, prod.getStock());
            stm.setInt(5, prod.getId());
            stm.executeUpdate();
            stm.close();

            JOptionPane.showMessageDialog(null, "Produto atualizado com sucesso!");
            con.close();

        } catch (Exception e) {
            System.out.println("Erro ao atualizar produto: " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Erro ao atualizar produto, tente novamente", null, JOptionPane.ERROR_MESSAGE);

        }
    }
    
    public static void refreshQtdStock(int prodId, int qtdRefreshed) {
        Connection con = MustacheMySQLConnection.getConnection();
        String sql = "update Produto set qtd_estoque = ? where id_produto = ?";
        try (PreparedStatement stm = con.prepareStatement(sql)) {
            stm.setInt(1, qtdRefreshed);    
             stm.setInt(2, prodId);    
            stm.executeUpdate();
            stm.close();

            JOptionPane.showMessageDialog(null, "Quantidade do produto atualizado com sucesso!");
            con.close();

        } catch (Exception e) {
            System.out.println("Erro ao atualizar qtd produto: " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Erro ao atualizar quantidade produto, tente novamente", null, JOptionPane.ERROR_MESSAGE);

        }
    }

    public static void delete(Product prod) {
        Connection con = MustacheMySQLConnection.getConnection();
        String sql = "Delete from Produto where id_produto = ?";
        try (PreparedStatement stm = con.prepareStatement(sql)) {
            stm.setInt(1, prod.getId());
            stm.executeUpdate();
            stm.close();

            JOptionPane.showMessageDialog(null, "Produto excluído com sucesso!");
            con.close();
        } catch (Exception e) {
            System.out.println("Erro ao deletar produto: " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Erro ao deletar o produto, tente novamente", null, JOptionPane.ERROR_MESSAGE);

        }
    }

    public Product getProductById(int idProduct) {
        Connection con = MustacheMySQLConnection.getConnection();
        Product p = new Product();
        String sql = "select Produto.id_produto, Marca.nome_marca, Produto.nome_produto, Produto.preco_produto, Produto.qtd_estoque from Produto inner join Marca on Produto.id_marca = Marca.id_marca;";
        try (PreparedStatement stm = con.prepareStatement(sql)) {
            stm.setInt(1, idProduct);
            ResultSet result = stm.executeQuery();
            while (result.next()) {
               
                Brand b = new Brand ();
                
                p.setBrandName(result.getString("nome_marca"));
                p.setId(result.getInt("id_produto"));
                b.setName(result.getString("nome_marca"));
                p.setBrand(b);
                p.setName(result.getString("nome_produto"));
                p.setPrice(result.getDouble("preco_produto"));
                p.setStock(result.getInt("qtd_estoque"));
               
            }
        } catch (Exception e) {
            System.out.println("Erro ao retornar produto por id: " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Erro ao retornar produto por id: ", null, JOptionPane.ERROR_MESSAGE);
        }
        return p;
    }

    public List<Product> getListProduct(String name) {
        Connection con = MustacheMySQLConnection.getConnection();
        List<Product> prodList = new ArrayList();
        String sql = "select produto.* , marca.id_marca, marca.nome_marca from Produto JOIN marca where produto.id_marca = marca.id_marca and produto.nome_produto like ? "
                + "order by produto.nome_produto";
        try (PreparedStatement stm = con.prepareStatement(sql)) {
            stm.setString(1, "%" + name + "%");
            ResultSet result = stm.executeQuery();
            while (result.next()) {
                Product prod = new Product();
                Brand b = new Brand ();
                
                prod.setBrandName(result.getString("nome_marca"));
                prod.setId(result.getInt("id_produto"));
                b.setName(result.getString("nome_marca"));
                b.setId(result.getInt("id_marca"));
                prod.setBrand(b);
                prod.setName(result.getString("nome_produto"));
                prod.setPrice(result.getDouble("preco_produto"));
                prod.setStock(result.getInt("qtd_estoque"));
                prodList.add(prod);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Erro ao listar produtos: " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Erro ao listar os produtos, tente novamente", null, JOptionPane.ERROR_MESSAGE);
        }
        return prodList;
    }

    public List<Product> getListProductByName(String name) {
        Connection con = MustacheMySQLConnection.getConnection();
        List<Product> prodList = new ArrayList();
        String sql = "select * from Produto where nome_produto like ? order by nome_produto";
        try (PreparedStatement stm = con.prepareStatement(sql)) {
            stm.setString(1, "%" + name + "%");
            ResultSet result = stm.executeQuery();
            while (result.next()) {
                Product prod = new Product();
                Brand b = new Brand ();
                
                prod.setId(result.getInt("id_produto"));
                b.setName(result.getString("nome_marca"));
                prod.setBrand(b);
                prod.setName(result.getString("nome_produto"));
                prod.setPrice(result.getDouble("preco_produto"));
                prod.setStock(result.getInt("qtd_estoque"));
                prodList.add(prod);
            }
        } catch (Exception e) {
            System.out.println("Erro ao listar produtos: " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Erro ao listar os produtos, tente novamente", null, JOptionPane.ERROR_MESSAGE);
        }
        return prodList;
    }
}
