package controller.interfaces;

import java.util.TreeMap;

import datos.Pedido;
import exceptions.GestorExcepciones;

public interface Pedible {
    public void grabarPedido(Pedido pPedido)
                throws GestorExcepciones;
    public void borrarPedido(String pCodPed)
                throws GestorExcepciones;
    public void modificarPedido(Pedido pPedido)
                throws GestorExcepciones;
    public Pedido buscarPorCodigo(String pCodPed)
                throws GestorExcepciones;
    public TreeMap <String, Pedido> listarPedidos()
                throws GestorExcepciones;
    public String generateCodigo()
                throws GestorExcepciones;
    public int totalPedidos()
                throws GestorExcepciones;
}