package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MasterConnection {
    protected Connection con;
    protected PreparedStatement stmt;
    protected ResultSet rs;
    protected String url = "jdbc:mysql://localhost:3306/taco_bell?serverTimezone=Europe/Madrid&useSSL=false";
    
    protected void openConnection() {
        try {
            con = DriverManager.getConnection(url, "root", "abcd*1234");
        } catch (SQLException e) {
            System.out.println("Error al intentar abrir la BD");
        }
    }

    protected void closeConnection() {
        try {
            if (stmt != null) 
            stmt.close();
        
            if (con != null)
                con.close();
        } catch (SQLException sqle) {}
    }
}
