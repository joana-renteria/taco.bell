package controller.interfaces;

import java.util.ArrayList;
import datos.Pedido;

public interface Pedible {
    public void crearPedido();
    public void borrarPedido(String pCodPed);
    public void modificarPedido(Pedido pPedido);
    public ArrayList <Pedido> listarPedidos();
}