package controller;

import java.sql.SQLException;
import java.util.ArrayList;

import controller.interfaces.Establecimientable;
import datos.Establecimiento;

public class ADEstablecimiento extends MasterConnection implements Establecimientable {

    @Override
    public void grabarEstablecimiento(Establecimiento pEstablecimiento) {
        openConnection();

        try {
            stmt = con.prepareStatement(insertar);
            stmt.setString(1, pEstablecimiento.getCodEst());
            stmt.setString(2, pEstablecimiento.getNombre());
            stmt.setString(3, pEstablecimiento.getLoc());
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
    public Establecimiento buscarPorCodigo(String pCodEst) {
        openConnection();
        Establecimiento pEstablecimiento = null;
        try {
            stmt = con.prepareStatement(buscar);
            stmt.setString(1, pCodEst);
            rs = stmt.executeQuery();
            rs.next();
            pEstablecimiento = new Establecimiento(
                rs.getString(1),
                rs.getString(2),
                rs.getString(3));
        } catch (Exception e) {
            //TODO: handle exception
        }
        return pEstablecimiento;
    }

    @Override
    public ArrayList<Establecimiento> listarEstablecimientos() {
        ArrayList<Establecimiento> pListaEstableciento = new ArrayList<Establecimiento>();
        openConnection();
        try {
            stmt = con.prepareStatement(listar);
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
    public String generateCodigo() {
        String pCodEst = "ES";
        String numEst = String.valueOf(listarEstablecimientos().size()+1);
        for (int i = 0; i < 5 - numEst.length(); i++)
            pCodEst += "0";

        pCodEst += numEst;

        return pCodEst;
    }

    private final String insertar = "INSERT INTO establecimiento VALUES (?, ?, ?)";
    private final String borrar = "DELETE FROM establecimiento WHERE codEst = ?";
    private final String modificar = "UPDATE FROM establecimiento WHERE codEst = ? SET nombre = ?, loc = ?";
    private final String listar = "SELECT * FROM establecimiento";
    private final String buscar = "SELECT * FROM establecimiento WHERE codEst = ?";
}
