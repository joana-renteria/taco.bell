package controller;

import java.sql.SQLException;
import java.util.ArrayList;

import controller.interfaces.Establecimientable;
import datos.Establecimiento;
import resources.Util;

public class ADEstablecimiento extends MasterConnection implements Establecimientable {

    private final String insertar = "INSERT INTO establecimiento VALUES (?, ?, ?)";
    private final String borrar = "DELETE FROM establecimiento WHERE codEst = ?";
    private final String modificar = "UPDATE FROM establecimiento WHERE codEst = ? SET nombre = ?, loc = ?";
    private final String buscar = "SELECT * FROM establecimiento";

    @Override
    public void crearEstablecimiento() {
        String pCodEst = "ES";
        String numEst = String.valueOf(listarEstablecimientos().size()+1);
        for (int i = 0; i < 5 - numEst.length(); i++)
            pCodEst += "0";

        pCodEst += numEst;
        // TODO Leer a traves de la ventana (factorías para aislar) los valores que
        // introduce el usuarie.
        String pNombre = Util.introducirCadena();
        String pLoc = Util.introducirCadena();

        openConnection();

        try {
            stmt = con.prepareStatement(insertar);
            stmt.setString(1, pCodEst);
            stmt.setString(2, pNombre);
            stmt.setString(3, pLoc);
            // ejecución del comando.
            stmt.executeUpdate();
        } catch (SQLException e) {
            // TODO tratar excepción.
        }
        closeConnection();
    }

    @Override
    public void borrarEstablecimiento(String pCodEst) {
        openConnection();

        try {
            stmt = con.prepareStatement(borrar);
            stmt.setString(1, pCodEst);

            // ejecución del comando.
            stmt.executeUpdate();
        } catch (SQLException e) {
            // TODO tratar excepción.
        }
        closeConnection();
    }

    @Override
    public void modificarEstablecimiento(Establecimiento pEstablecimiento) {
        openConnection();

        try {
            stmt = con.prepareStatement(modificar);
            stmt.setString(1, pEstablecimiento.getCodEst());
            stmt.setString(2, pEstablecimiento.getLoc());
            stmt.setString(3, pEstablecimiento.getNombre());
            // ejecución del comando.
            stmt.executeUpdate();
        } catch (SQLException e) {
            // TODO tratar excepción.
        }
        closeConnection();
    }

    @Override
    public ArrayList<Establecimiento> listarEstablecimientos() {
        ArrayList<Establecimiento> pListaEstableciento = new ArrayList<Establecimiento>();
        openConnection();
        try {
            stmt = con.prepareStatement(buscar);
            rs = stmt.executeQuery();
            while (rs.next())
                pListaEstableciento.add(
                        new Establecimiento(
                                rs.getString(1),
                                rs.getString(2),
                                rs.getString(3)));
        } catch (SQLException e) {
            // TODO tratar la excepción.
        }
        return pListaEstableciento;
    }

}
