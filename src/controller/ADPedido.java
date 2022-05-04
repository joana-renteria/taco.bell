package controller;

import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;

import controller.factorias.MenuADFactory;
import controller.interfaces.Pedible;
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
            stmt.setObject(5, pPedido.getFechaPed());
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
    public Pedido buscarPorCodigo(String pCodPed) {
        openConnection();
        Pedido pPedido = null;
        try {
            stmt = con.prepareStatement(buscar);
            System.out.println(1);
            stmt.setString(1, pCodPed);
            System.out.println(2);
            rs = stmt.executeQuery();
            System.out.println(3);
            rs.next();
            LocalDate auxDate;
            ZoneId defaultZoneId = ZoneId.systemDefault();
            Instant instant = rs.getDate(2).toInstant();
            auxDate = instant.atZone(defaultZoneId).toLocalDate();
            pPedido = new Pedido(
                rs.getString(1),
                auxDate,
                rs.getString(3),
                rs.getString(4),
                (Menu)rs.getObject(5)
                );
        } catch (Exception e) {
            System.out.println(6);
            //TODO: handle exception
        }
        return pPedido;
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
                                MenuADFactory.getAccessMenu().buscarMenu(rs.getString(4))));
        } catch (SQLException e) {
            // TODO tratar la excepción.
        }
        return pListaPedidos;
    }    
    
    @Override
    public String generateCodigo() {
        String pCodPed = "ES";
        String numPed = String.valueOf(listarPedidos().size()+1);
        for (int i = 0; i < 5 - numPed.length(); i++)
            pCodPed += "0";

        pCodPed += numPed;
        return pCodPed;
    }

    private final String insertar = "INSERT INTO pedido VALUES (?, ?, ?, ?, ?)";
    private final String borrar = "DELETE FROM pedido WHERE codPed = ?";
    private final String modificar = "UPDATE FROM pedido WHERE codPed = ? SET nombre = ?, loc = ?";
    private final String buscar = "SELECT * FROM pedido";
    
}
