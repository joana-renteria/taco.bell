package controller;

import java.sql.SQLException;
import java.util.TreeMap;

import controller.interfaces.Establecimientable;
import datos.Establecimiento;
import exceptions.GestorExcepciones;

public class ADEstablecimiento extends MasterConnection implements Establecimientable {
    /**El método recibe como parámetro un objeto de tipo
     * establecimiento, y después prepara una sentencia SQL
     * con los datos correspondientes.
     * @param pEstablecimiento objeto de tipo Establecimiento
     * a grabar en la tabla.
     */
    @Override
    public void grabarEstablecimiento(Establecimiento pEstablecimiento) throws GestorExcepciones {
        try {
            openConnection();
            stmt = con.prepareStatement(insertar);
            stmt.setString(1, pEstablecimiento.getCodEst());
            stmt.setString(2, pEstablecimiento.getNombre());
            stmt.setString(3, pEstablecimiento.getLoc());
            // ejecución del comando.
            stmt.executeUpdate();
        } catch (SQLException | GestorExcepciones e) {
            throw new GestorExcepciones(3);
        } finally {
            closeConnection();
        }
    }

    @Override
    public void borrarEstablecimiento(String pCodEst) throws GestorExcepciones {
        try {
            openConnection();
            stmt = con.prepareStatement(borrar);
            stmt.setString(1, pCodEst);

            // ejecución del comando.
            stmt.executeUpdate();
        } catch (SQLException | GestorExcepciones e) {
            throw new GestorExcepciones(3);
        } finally {
            closeConnection();
        }
    }

    @Override
    public void modificarEstablecimiento(Establecimiento pEstablecimiento) throws GestorExcepciones {
        try {
            openConnection();
            stmt = con.prepareStatement(modificar);
            stmt.setString(1, pEstablecimiento.getNombre());
            stmt.setString(2, pEstablecimiento.getLoc());
            stmt.setString(3, pEstablecimiento.getCodEst());
                stmt.executeUpdate();
        } catch (SQLException | GestorExcepciones e) {
            throw new GestorExcepciones(3);
        } finally {
            closeConnection();
        }
    }

    @Override
    public Establecimiento buscarEstablecimientoPorCodigo(String pCodEst) throws GestorExcepciones {
        Establecimiento pEstablecimiento = null;
        try {
            openConnection();
            stmt = con.prepareStatement(buscar);
            stmt.setString(1, pCodEst);
            rs = stmt.executeQuery();
            rs.next();
            pEstablecimiento = new Establecimiento(
                rs.getString(1),
                rs.getString(2),
                rs.getString(3));
        } catch (SQLException | GestorExcepciones e) {
            throw new GestorExcepciones(3);
        } finally {
            closeConnection();
        }
        return pEstablecimiento;
    }

    @Override
    public TreeMap <String, Establecimiento> listarEstablecimientos() throws GestorExcepciones {
        Establecimiento pEstablecimiento = null;
        TreeMap <String, Establecimiento> pListaEstablecientos = 
            new TreeMap <String, Establecimiento>();
        try {
            openConnection();
            stmt = con.prepareStatement(listar);
            rs = stmt.executeQuery();
            while (rs.next()) {
                pEstablecimiento = 
                    new Establecimiento(
                    rs.getString(1),
                    rs.getString(2),
                    rs.getString(3));
                pListaEstablecientos.put(
                    pEstablecimiento.getCodEst(),
                    pEstablecimiento);
            }
        } catch (SQLException | GestorExcepciones e) {
            throw new GestorExcepciones(3);
        } finally {
            closeConnection();
        }
        return pListaEstablecientos;
    }

    @Override
    public String generateCodigo() throws GestorExcepciones {
        String pCodEst = "ES";
        String numEst = String.valueOf(totalEstablecimientos() + 1);
        for (int i = 0; i < 5 - numEst.length(); i++)
            pCodEst += "0";

        pCodEst += numEst;

        return pCodEst;
    }

    @Override
    public int totalEstablecimientos() throws GestorExcepciones {
        return cantidadTotal("establecimiento");
    }

    private final String insertar = "INSERT INTO establecimiento VALUES (?, ?, ?)";
    private final String borrar = "DELETE FROM establecimiento WHERE codEst = ?";
    private final String modificar = "UPDATE establecimiento SET nombre = ?, loc = ? WHERE codEst = ?";
    private final String listar = "SELECT * FROM establecimiento";
    private final String buscar = "SELECT * FROM establecimiento WHERE codEst = ?";
}
