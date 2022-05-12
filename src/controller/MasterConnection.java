package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class MasterConnection {
    protected ResourceBundle configFile;
    protected Connection con;
    protected PreparedStatement stmt;
    protected ResultSet rs;
    protected String url;
    protected String user;
    protected String pass;
    
    
    public MasterConnection() {
        configFile = ResourceBundle.getBundle("controller.config");
        url = configFile.getString("URL");
        user = configFile.getString("USER");
        pass = configFile.getString("PASSWORD");
    }

    protected void openConnection() {
        con = null;
        try {
            con = DriverManager.getConnection(url, user, pass);
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
