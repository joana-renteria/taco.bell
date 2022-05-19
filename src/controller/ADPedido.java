package controller;

import java.sql.SQLException;
import java.util.Optional;
import java.util.Set;
import java.util.TreeMap;

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
            stmt.setString(6, pPedido.getCodPed());
            stmt.setString(1, pPedido.getCodCle());
            stmt.setString(2, pPedido.getCodRep());
            stmt.setString(3, pPedido.getMenu().getCodMnu());
            stmt.setString(4, pPedido.getCodEst());
            stmt.setObject(5, pPedido.getFechaPed());
                stmt.executeUpdate();
        } catch (SQLException esql) {
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
                MenuADFactory
                    .getAccessMenu()
                        .buscarMenuPorCodigo(
                            rs.getString(4)));
        } catch (SQLException sqle) {
            
        }
            
        return pPedido;
    }

    @Override
    public TreeMap <String, Pedido> listarPedidos() {
        TreeMap <String, Pedido> pListaPedidos = 
            new TreeMap <String, Pedido>();

        openConnection();
        try {
            stmt = con.prepareStatement(listarTodo);
            rs = stmt.executeQuery();
            while (rs.next()) {
                pListaPedidos.put(
                    rs.getString(1),
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
        Set <String> keys = listarPedidos().keySet();

        Optional <String> lastKey = 
            keys.stream()
            .max((k1, k2) -> 
                k1.substring(2)
                .compareTo(k2.substring(2)));

        int k = 0;

        if (lastKey.isPresent())
            k = Integer.parseInt(lastKey.get().substring(2));

        String pCodPed = "PE";
        String numPed = String.valueOf(k + 1);
        for (int i = 0; i < 8 - numPed.length(); i++)
            pCodPed += "0";

        pCodPed += numPed;

        return pCodPed;
    }

    @Override
    public int totalPedidos() {
        return cantidadTotal("pedido");
    }

    private final String insertar = "INSERT INTO pedido VALUES (?, ?, ?, ?, ?, ?)";
    private final String borrar = "DELETE FROM pedido WHERE codPed = ?";
    private final String modificar = "UPDATE pedido SET codCle = ?, codRep = ?, codMnu = ?, codEst = ?, fechaPed = ? WHERE codPed = ? ";
    private final String buscar = "SELECT * FROM pedido WHERE codPed = ?";
    private final String listarTodo = "SELECT * FROM pedido";
}
