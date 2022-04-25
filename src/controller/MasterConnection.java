package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MasterConnection {
    private Connection con;
    private PreparedStatement stmt;
    private String url = "jdbc:mysql://localhost:3306/taco_bell?serverTimezone=Europe/Madrid&useSSL=false";
    
    protected void openConnection() {
        try {
            con = DriverManager.getConnection(url, "root", "abcd*1234");
        } catch (SQLException e) {
            System.out.println("Error al intentar abrir la BD");
        }
    }

    protected void closeConnection() throws SQLException {
        if (stmt != null) 
            stmt.close();
        
        if (con != null)
            con.close();
    }
}
