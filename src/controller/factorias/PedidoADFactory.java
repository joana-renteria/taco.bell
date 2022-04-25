package controller.factorias;

import controller.ADPedido;
import controller.interfaces.Pedible;

public abstract class PedidoADFactory {

    private static Pedible p = new ADPedido();

    public static Pedible getAccessPedidos() {
        return p;
    }
}
