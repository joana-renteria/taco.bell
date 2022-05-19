package controller;

import java.sql.SQLException;
import java.util.Optional;
import java.util.Set;
import java.util.TreeMap;

import controller.interfaces.Establecimientable;
import datos.Establecimiento;

public class ADEstablecimiento extends MasterConnection implements Establecimientable {
    /**El método recibe como parámetro un objeto de tipo
     * establecimiento, y después prepara una sentencia SQL
     * con los datos correspondientes.
     * @param pEstablecimiento objeto de tipo Establecimiento
     * a grabar en la tabla.
     */
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
            stmt.setString(1, pEstablecimiento.getNombre());
            stmt.setString(2, pEstablecimiento.getLoc());
            stmt.setString(3, pEstablecimiento.getCodEst());
                stmt.executeUpdate();
        } catch (SQLException sqle) {
            // TODO tratar excepción.
        }
        closeConnection();
    }

    @Override
    public Establecimiento buscarEstablecimientoPorCodigo(String pCodEst) {
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
    public TreeMap <String, Establecimiento> listarEstablecimientos() {
        Establecimiento pEstablecimiento = null;
        TreeMap <String, Establecimiento> pListaEstablecientos = 
            new TreeMap <String, Establecimiento>();
        openConnection();
        try {
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
                
        } catch (SQLException sqle) {
            // TODO tratar la excepción.
        }
        return pListaEstablecientos;
    }

    @Override
    public String generateCodigo() {
        Set <String> keys = listarEstablecimientos().keySet();

        Optional <String> lastKey = 
            keys.stream()
            .max((k1, k2) -> 
                k1.substring(2)
                .compareTo(k2.substring(2)));

        int k = 0;

        if (lastKey.isPresent())
            k = Integer.parseInt(lastKey.get().substring(2));

        String pCodEst = "ES";
        String numEst = String.valueOf(k + 1);
        for (int i = 0; i < 5 - numEst.length(); i++)
            pCodEst += "0";

        pCodEst += numEst;

        return pCodEst;
    }

    @Override
    public int totalEstablecimientos() {
        return cantidadTotal("establecimiento");
    }

    private final String insertar = "INSERT INTO establecimiento VALUES (?, ?, ?)";
    private final String borrar = "DELETE FROM establecimiento WHERE codEst = ?";
    private final String modificar = "UPDATE establecimiento SET nombre = ?, loc = ? WHERE codEst = ?";
    private final String listar = "SELECT * FROM establecimiento";
    private final String buscar = "SELECT * FROM establecimiento WHERE codEst = ?";
}
