package controller.interfaces;

import java.util.ArrayList;
import datos.Pedido;

public interface Pedible {
    public void grabarPedido(Pedido pPedido);
    public void borrarPedido(String pCodPed);
    public void modificarPedido(Pedido pPedido);
    public Pedido buscarPorCodigo(String pCodPed);
    public ArrayList <Pedido> listarPedidos();
    public String generateCodigo();
}