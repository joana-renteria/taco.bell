package controller.interfaces;

import java.time.LocalDate;
import java.util.ArrayList;
import datos.Pedido;

public interface Pedible {
    public void crearPedido();
    public void borrarPedido();
    public void modificarPedido();
    public Pedido buscarPedido();
    public ArrayList <Pedido> listarPedidos();
    public ArrayList <Pedido> listarPedidos(LocalDate pFecha);
}