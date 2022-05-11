package controller;

import java.sql.SQLException;
import java.util.ArrayList;

import controller.factorias.MenuADFactory;
import controller.interfaces.Pedible;
import datos.Menu;
import datos.Pedido;

public class ADPedido extends MasterConnection implements Pedible {

    @Override
    public void grabarPedido(Pedido pPedido) {
        openConnection();

        try {
            stmt = con.prepareStatement(insertar);
            stmt.setString(1, pPedido.getCodPed());
            stmt.setString(2, pPedido.getCodCle());
            stmt.setString(3, pPedido.getCodRep());
            stmt.setString(4, pPedido.getMenu().getCodMnu());
            stmt.setString(5, pPedido.getCodEst());
            stmt.setObject(6, pPedido.getFechaPed());
            // ejecución del comando.
            stmt.executeUpdate();
        } catch (SQLException e) {
            // TODO tratar excepción.
            e.printStackTrace();
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
            stmt.setString(5, pPedido.getCodEst());
            stmt.setObject(6, pPedido.getFechaPed());
            // ejecución del comando.
            stmt.executeUpdate();
        } catch (SQLException e) {
            // TODO tratar excepción.
        }

        closeConnection();

    }

    @Override
    public Pedido buscarPorCodigo(String pCodPed) {
        openConnection();
        Pedido pPedido = null;
        try {
            stmt = con.prepareStatement(buscar);
            stmt.setString(1, pCodPed);
            rs = stmt.executeQuery();
            rs.next();
            pPedido = new Pedido(
                    rs.getString(1),
                    rs.getDate(6).toLocalDate(),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(5),
                    (Menu) MenuADFactory.getAccessMenu().buscarMenuPorCodigo(rs.getString(4)));
        } catch (SQLException sqle) {

        }
            
        return pPedido;
    }

    @Override
    public ArrayList<Pedido> listarPedidos() {
        ArrayList<Pedido> pListaPedidos = new ArrayList<Pedido>();
        openConnection();
        try {
            stmt = con.prepareStatement(listar);
            rs = stmt.executeQuery();
            while (rs.next()) {
                pListaPedidos.add(
                        new Pedido(
                                rs.getString(1),
                                rs.getDate(6).toLocalDate(),
                                rs.getString(2),
                                rs.getString(3),
                                rs.getString(5),
                                MenuADFactory.getAccessMenu().buscarMenuPorCodigo(rs.getString(4))));
            }
        } catch (SQLException e) {
            // TODO tratar la excepción.
        }
        return pListaPedidos;
    }

    @Override
    public String generateCodigo() {
        String pCodPed = "PE";
        String numPed = String.valueOf(listarPedidos().size()+1);
        System.out.println("a="+numPed);
        for (int i = 0; i < 8 - numPed.length(); i++)
            pCodPed += "0";

        pCodPed += numPed;
        return pCodPed;
    }

    private final String insertar = "INSERT INTO pedido VALUES (?, ?, ?, ?, ?, ?)";
    private final String borrar = "DELETE FROM pedido WHERE codPed = ?";
    private final String modificar = "UPDATE FROM pedido WHERE codPed = ? SET codCle = ?, codRep = ?, codMnu = ?, codEst = ?, fechaPed = ?";
    private final String buscar = "SELECT * FROM pedido WHERE codPed = ?";
    private final String listar = "SELECT * FROM pedido";

}
