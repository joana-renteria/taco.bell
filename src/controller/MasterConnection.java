package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import exceptions.GestorExcepciones;

public abstract class MasterConnection {
    protected Connection con;
    protected PreparedStatement stmt;
    protected ResultSet rs;
    // access to the configuration file.
    protected ResourceBundle configFile = 
        ResourceBundle.getBundle("controller.config");;
    protected String 
        url = configFile.getString("URL"),
        user = configFile.getString("USER"),
        pass = configFile.getString("PASSWORD");
    

    protected void openConnection() throws GestorExcepciones {
        con = null;
        try {
            con = DriverManager.getConnection(url, user, pass);
        } catch (SQLException e) {
            throw new GestorExcepciones(1);
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
    protected int cantidadTotal(String database) throws GestorExcepciones {
        int pTotal = -1;
        database = "SELECT COUNT(*) FROM " + database;
        
        try {
            openConnection();
            stmt = con.prepareStatement(database);
            rs = stmt.executeQuery();
                rs.next();
            pTotal = rs.getInt(1);
        } catch (GestorExcepciones | SQLException sqle) {
            throw new GestorExcepciones(1);
        } finally {
            closeConnection();
        }
        
        return pTotal;
    }
}
