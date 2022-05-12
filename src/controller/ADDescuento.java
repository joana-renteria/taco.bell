package controller;

import java.sql.SQLException;
import java.util.TreeMap;

import controller.interfaces.Descontable;
import datos.Descuento;

public class ADDescuento extends MasterConnection implements Descontable {

    @Override
    public void grabarDescuento(Descuento pDescuento) {
        openConnection();   
        try {
            stmt = con.prepareStatement(insertar);
            stmt.setString(1, pDescuento.getCodDsc());
            stmt.setFloat(2, pDescuento.getUsos());
            stmt.setFloat(3, pDescuento.getCantidadDsc());
            stmt.setObject(4, pDescuento.getFechaInicio());
            stmt.setObject(5, pDescuento.getFechaFin());
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
            stmt.setString(5, pDescuento.getCodDsc());
            stmt.setFloat(1, pDescuento.getUsos());
            stmt.setFloat(2, pDescuento.getCantidadDsc());
            stmt.setObject(3, pDescuento.getFechaInicio());
            stmt.setObject(4, pDescuento.getFechaFin());
                stmt.executeUpdate();
        } catch (SQLException sqle) {
            // TODO tratar excepción.
        }
        closeConnection();
    }

    @Override
    public Descuento buscarDescuentoPorCodigo(String pCodDsc) {
        Descuento pDescuento = null;
        openConnection();
        try {
            stmt = con.prepareStatement(buscar);
            stmt.setString(1, pCodDsc);
            rs = stmt.executeQuery();
            rs.next();
            pDescuento = 
                new Descuento(
                rs.getString(1),
                rs.getInt(2),
                rs.getFloat(3),
                rs.getDate(4).toLocalDate(),
                rs.getDate(5).toLocalDate());
        } catch (SQLException e) {
            //TODO: handle exception
        }
        closeConnection();
        return pDescuento;
    }

    @Override
    public TreeMap <String, Descuento> listarDescuentos() {
        Descuento pDescuento = null;
        TreeMap <String, Descuento> pListaDescuentos = 
            new TreeMap<String, Descuento> ();
        openConnection();
        try {
            stmt = con.prepareStatement(listarTodo);
            rs = stmt.executeQuery();
            while (rs.next()) {
                pDescuento = 
                    new Descuento(
                    rs.getString(1),
                    rs.getInt(2),
                    rs.getFloat(3),
                    rs.getDate(4).toLocalDate(),
                    rs.getDate(5).toLocalDate());
                pListaDescuentos.put(
                    pDescuento.getCodDsc(),
                    pDescuento);
            }

        } catch (SQLException sqle) {
            // TODO tratar la excepción.
        }   
        return pListaDescuentos;
    }

    @Override
    public String generateCodigo() {
        String pCodDsc = "DE";
        String numDsc = String.valueOf(totalDescuentos() + 1);
        for (int i = 0; i < 8 - numDsc.length(); i++)
            pCodDsc += "0";

        pCodDsc += numDsc;

        return pCodDsc;
    }

    @Override
    public int totalDescuentos() {
        return cantidadTotal("descuento");
    }

    private final String insertar = "INSERT INTO descuento VALUES (?, ?, ?, ?, ?)";
    private final String borrar = "DELETE FROM descuento WHERE codDsc = ?";
    private final String modificar = "UPDATE descuento SET usos = ?, cantidadDesc = ?, fechaInicio = ?, fechaFin = ? WHERE codDsc = ?";
    private final String listarTodo = "SELECT * FROM descuento";
    private final String buscar = "SELECT * FROM descuento WHERE codDsc = ?";

}
