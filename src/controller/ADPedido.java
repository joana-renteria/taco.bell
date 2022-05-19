package controller;

import java.sql.SQLException;
import java.util.TreeMap;

import controller.factorias.MenuADFactory;
import controller.interfaces.Pedible;
import datos.Pedido;
import exceptions.GestorExcepciones;

public class ADPedido extends MasterConnection implements Pedible {

    @Override
    public void grabarPedido(Pedido pPedido) throws GestorExcepciones {
        try {
        openConnection();
            stmt = con.prepareStatement(insertar);
            stmt.setString(1, pPedido.getCodPed());
            stmt.setString(2, pPedido.getCodCle());
            stmt.setString(3, pPedido.getCodRep());
            stmt.setString(4, pPedido.getMenu().getCodMnu());
            stmt.setString(5, pPedido.getCodEst());
            stmt.setObject(6, pPedido.getFechaPed());
            // ejecución del comando.
            stmt.executeUpdate();
        } catch (SQLException | GestorExcepciones e) {
            throw new GestorExcepciones(3);
        } finally {
            closeConnection();
        }
    }

    @Override
    public void borrarPedido(String pCodPed) throws GestorExcepciones {
        try {
            openConnection();
            stmt = con.prepareStatement(borrar);
            stmt.setString(1, pCodPed);
            // ejecución del comando.
            stmt.executeUpdate();
        } catch (SQLException | GestorExcepciones e) {
            throw new GestorExcepciones(3);
        } finally {
            closeConnection();
        }
    }

    @Override
    public void modificarPedido(Pedido pPedido) throws GestorExcepciones {
        try {
            openConnection();
            stmt = con.prepareStatement(modificar);
            stmt.setString(6, pPedido.getCodPed());
            stmt.setString(1, pPedido.getCodCle());
            stmt.setString(2, pPedido.getCodRep());
            stmt.setString(3, pPedido.getMenu().getCodMnu());
            stmt.setString(4, pPedido.getCodEst());
            stmt.setObject(5, pPedido.getFechaPed());
            stmt.executeUpdate();
        } catch (SQLException | GestorExcepciones e) {
            throw new GestorExcepciones(3);
        } finally {
            closeConnection();
        }
    }

    @Override
    public Pedido buscarPorCodigo(String pCodPed) throws GestorExcepciones {
        Pedido pPedido = null;
        try {
            openConnection();
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
        } catch (SQLException | GestorExcepciones e) {
            throw new GestorExcepciones(3);
        } finally {
            closeConnection();
        }
        return pPedido;
    }

    @Override
    public TreeMap <String, Pedido> listarPedidos() throws GestorExcepciones {
        TreeMap <String, Pedido> pListaPedidos = 
            new TreeMap <String, Pedido>();

        try {
            openConnection();
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
        } catch (SQLException | GestorExcepciones e) {
            throw new GestorExcepciones(3);
        } finally {
            closeConnection();
        }
        return pListaPedidos;
    }

    @Override
    public String generateCodigo() throws GestorExcepciones {
        String pCodPed = "PE";
        String numPed = String.valueOf(listarPedidos().size()+1);
        for (int i = 0; i < 8 - numPed.length(); i++)
            pCodPed += "0";

        pCodPed += numPed;

        return pCodPed;
    }

    @Override
    public int totalPedidos() throws GestorExcepciones {
        return cantidadTotal("pedido");
    }

    private final String insertar = "INSERT INTO pedido VALUES (?, ?, ?, ?, ?, ?)";
    private final String borrar = "DELETE FROM pedido WHERE codPed = ?";
    private final String modificar = "UPDATE pedido SET codCle = ?, codRep = ?, codMnu = ?, codEst = ?, fechaPed = ? WHERE codPed = ? ";
    private final String buscar = "SELECT * FROM pedido WHERE codPed = ?";
    private final String listarTodo = "SELECT * FROM pedido";

}
