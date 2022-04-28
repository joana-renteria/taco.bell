package controller;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import controller.interfaces.Descontable;
import datos.Descuento;
import resources.Util;

public class ADDescuento extends MasterConnection implements Descontable {

    private final String insertar = "INSERT INTO descuento VALUES (?, ?, ?, ?, ?)";
    private final String borrar = "DELETE FROM descuento WHERE codDsc = ?";
    private final String modificar = "UPDATE FROM descuento WHERE codDsc = ? SET usos = ?, cantidadDesc = ?, fechaInicio = ?, fechaFin = ?";
    private final String buscar = "SELECT * FROM descuento";

    @Override
    public void crearDescuento() {
        String pCodDsc = "DE";
        String numDsc = String.valueOf(listarDescuentos().size());
        for (int i = 0; i < 8 - numDsc.length(); i++)
            pCodDsc += "0";

        pCodDsc += numDsc;

        // TODO Leer a traves de la ventana (factorías para aislar) los valores que introduce el usuarie.
        int pUsos = Util.leerInt();
        float pCantidadDsc = Util.leerFloat();
        LocalDate pFechaInicio = Util.leerFechaDMA(), pFechaFin = Util.leerFechaDMA();

        openConnection();

        try {
            stmt = con.prepareStatement(insertar);
            stmt.setString(1, pCodDsc);
            stmt.setInt(2, pUsos);
            stmt.setFloat(3, pCantidadDsc);
            stmt.setObject(4, pFechaInicio);
            stmt.setObject(5, pFechaFin);
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
    public ArrayList<Descuento> listarDescuentos() {
        ArrayList<Descuento> pListaDescuento = new ArrayList<Descuento>();
        openConnection();
        try {
            stmt = con.prepareStatement(buscar);
            rs = stmt.executeQuery();
            while (rs.next())
                pListaDescuento.add(
                        new Descuento(
                                rs.getString(1),
                                rs.getInt(2),
                                rs.getFloat(3),
                                (LocalDate) rs.getObject(4),
                                (LocalDate) rs.getObject(5)));

        } catch (SQLException e) {
            // TODO tratar la excepción.
        }

        return pListaDescuento;
    }

}
