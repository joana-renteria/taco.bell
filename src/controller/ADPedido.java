package controller;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import controller.factorias.MenuADFactory;
import controller.interfaces.Pedible;
import datos.Menu;
import datos.Pedido;
import resources.Util;

public class ADPedido extends MasterConnection implements Pedible {

    private final String insertar = "INSERT INTO pedido VALUES (?, ?, ?, ?, ?)";
    private final String borrar = "DELETE FROM pedido WHERE codPed = ?";
    private final String modificar = "UPDATE FROM pedido WHERE codPed = ? SET nombre = ?, loc = ?";
    private final String buscar = "SELECT * FROM pedido";

    @Override
    public void crearPedido() {
        String pCodPed = "ES";
        String numPed = String.valueOf(listarPedidos().size());
        for (int i = 0; i < 5 - numPed.length(); i++)
            pCodPed += "0";

        pCodPed += numPed;
        // TODO Leer a traves de la ventana (factorías para aislar) los valores que
        // introduce el usuarie.
        String pCodCle = Util.introducirCadena();
        String pCodRep = Util.introducirCadena();
        String pCodMnu = Util.introducirCadena();
        LocalDate pFechaPed = Util.leerFechaDMA();

        openConnection();

        try {
            stmt = con.prepareStatement(insertar);
            stmt.setString(1, pCodPed);
            stmt.setString(2, pCodCle);
            stmt.setString(3, pCodRep);
            stmt.setString(4, pCodMnu);
            stmt.setObject(5, pFechaPed);
            // ejecución del comando.
            stmt.executeUpdate();
        } catch (SQLException e) {
            // TODO tratar excepción.
        }
        closeConnection();
    }

    @Override
    public void borrarPedido(String pCodPed) {
        openConnection();

        try {
            stmt = con.prepareStatement(borrar);
            stmt.setString(1, pCodPed);
            // ejecución del comando.
            stmt.executeUpdate();
        } catch (SQLException e) {
            // TODO tratar excepción.
        }

        closeConnection();
    }

    @Override
    public void modificarPedido(Pedido pPedido) {
        openConnection();

        try {
            stmt = con.prepareStatement(modificar);
            stmt.setString(1, pPedido.getCodPed());
            stmt.setString(2, pPedido.getCodCle());
            stmt.setString(3, pPedido.getCodRep());
            stmt.setString(4, pPedido.getMenu().getCodMnu());
            stmt.setObject(5, pPedido.getFechaPed());
            // ejecución del comando.
            stmt.executeUpdate();
        } catch (SQLException e) {
            // TODO tratar excepción.
        }

        closeConnection();

    }

    @Override
    public ArrayList<Pedido> listarPedidos() {
        ArrayList<Pedido> pListaPedidos = new ArrayList<Pedido>();
        openConnection();
        try {
            stmt = con.prepareStatement(buscar);
            rs = stmt.executeQuery();
            while (rs.next())
                pListaPedidos.add(
                        new Pedido(
                                rs.getString(1),
                                (LocalDate) rs.getObject(2),
                                rs.getString(3), 
                                rs.getString(5),
                                MenuADFactory.getAccessMenus().buscarMenu(rs.getString(4))));
        } catch (SQLException e) {
            // TODO tratar la excepción.
        }
        return pListaPedidos;
    }    
}
