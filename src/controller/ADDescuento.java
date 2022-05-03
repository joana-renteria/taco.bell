package controller;

import java.sql.SQLException;
import java.util.ArrayList;

import controller.interfaces.Descontable;
import datos.Descuento;

public class ADDescuento extends MasterConnection implements Descontable {

    @Override
    public void grabarDescuento(Descuento pDescuento) {
        openConnection();   

        try {
            stmt = con.prepareStatement(insertar);
            stmt.setString(1, pDescuento.getCodDsc());
            stmt.setInt(2, pDescuento.getUsos());
            stmt.setFloat(3, pDescuento.getCantidadDsc());
            stmt.setObject(4, pDescuento.getFechaInicio());
            stmt.setObject(5, pDescuento.getFechaFin());
            // ejecución del comando.
            stmt.executeUpdate();
        } catch (SQLException sqle) {
            // TODO tratar excepción.
        }

        closeConnection();
    }

    @Override
    public void borrarDescuento(String pCodDsc) {
        openConnection();

        try {
            stmt = con.prepareStatement(borrar);
            stmt.setString(1, pCodDsc);

            // ejecución del comando.
            stmt.executeUpdate();
        } catch (SQLException sqle) {
            // TODO tratar excepción.
        }

        closeConnection();
    }

    @Override
    public void modificarDescuento(Descuento pDescuento) {
        openConnection();

        try {
            stmt = con.prepareStatement(modificar);
            stmt.setString(1, pDescuento.getCodDsc());
            stmt.setInt(2, pDescuento.getUsos());
            stmt.setFloat(3, pDescuento.getCantidadDsc());
            stmt.setObject(4, pDescuento.getFechaInicio());
            stmt.setObject(5, pDescuento.getFechaFin());
            // ejecución del comando.
            stmt.executeUpdate();
        } catch (SQLException sqle) {
            // TODO tratar excepción.
        }

        closeConnection();
    }

    @Override
    public Descuento buscarPorCodigo(String pCodDsc) {
        openConnection();
        Descuento pDescuento = null;
        try {
            stmt = con.prepareStatement(buscar);
            stmt.setString(1, pCodDsc);
            rs = stmt.executeQuery();
            rs.next();
            pDescuento = new Descuento(
                rs.getString(1),
                rs.getInt(2),
                rs.getFloat(3),
                rs.getDate(4).toLocalDate(),
                rs.getDate(5).toLocalDate());
        } catch (Exception e) {
            //TODO: handle exception
        }
        return pDescuento;
    }

    @Override
    public ArrayList<Descuento> listarDescuentos() {
        ArrayList<Descuento> pListaDescuento = 
            new ArrayList<Descuento>();

        openConnection();
        
        try {
            stmt = con.prepareStatement(listarTodo);
            rs = stmt.executeQuery();
            while (rs.next())
                pListaDescuento.add(
                        new Descuento(
                                rs.getString(1),
                                rs.getInt(2),
                                rs.getFloat(3),
                                rs.getDate(4).toLocalDate(),
                                rs.getDate(5).toLocalDate()));

        } catch (SQLException e) {
            // TODO tratar la excepción.
        }   

        return pListaDescuento;
    }

    @Override
    public String generateCodigo() {
        String pCodDsc = "DE";
        String numDsc = String.valueOf(listarDescuentos().size() + 1);
        for (int i = 0; i < 8 - numDsc.length(); i++)
            pCodDsc += "0";

        pCodDsc += numDsc;

        return pCodDsc;
    }

    private final String insertar = "INSERT INTO descuento VALUES (?, ?, ?, ?, ?)";
    private final String borrar = "DELETE FROM descuento WHERE codDsc = ?";
    private final String modificar = "UPDATE FROM descuento WHERE codDsc = ? SET usos = ?, cantidadDesc = ?, fechaInicio = ?, fechaFin = ?";
    private final String listarTodo = "SELECT * FROM descuento";
    private final String buscar = "SELECT * FROM descuento WHERE codDsc = ?";
}
