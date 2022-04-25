package controller.factorias;

import controller.ADDescuento;
import controller.interfaces.Descontable;

public abstract class DescuentoADFactory {
    
    private static Descontable d = new ADDescuento();

    public static Descontable getAccessDescuento() {
        return d;
    }
}
