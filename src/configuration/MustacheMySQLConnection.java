/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package configuration;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;


public class MustacheMySQLConnection {
    private static final String url = "jdbc:mysql://localhost/mustache?autoReconnect=true&useSSL=false";
    private static final String user = "root";
    private static final String password = "root";
    
    //Connect to mustache database on MySQL
    public static Connection getConnection(){
        Connection con = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = (Connection) DriverManager.getConnection(url, user, password); 
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Connection error" + e.getMessage());
        }
        return con;
    }
}
