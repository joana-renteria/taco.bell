package controller.interfaces;

import java.util.TreeMap;

import datos.Pedido;

public interface Pedible {
    public void grabarPedido(Pedido pPedido);
    public void borrarPedido(String pCodPed);
    public void modificarPedido(Pedido pPedido);
    public Pedido buscarPorCodigo(String pCodPed);
    public TreeMap <String, Pedido> listarPedidos();
    public String generateCodigo();
    public int totalPedidos();
}