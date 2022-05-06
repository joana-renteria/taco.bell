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

    /**In case of an error, returns. Otherwise,
     * returns the number of rows of the full table 
     * in the database.
     * @throws SQLException in case something goes wrong.
     */
    protected int cantidadTotal(String database) {
        int pTotal = -1;
        database = "SELECT COUNT(*) FROM " + database;
        openConnection();
        
        try {
            stmt = con.prepareStatement(database);
            rs = stmt.executeQuery();
                rs.next();
            pTotal = rs.getInt(1);
        } catch (SQLException sqle) {
            // TODO Handle exception
        }
        
        closeConnection();
        return pTotal;
    }
}
