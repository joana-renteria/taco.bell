package controller.factorias;

import controller.ADProducto;
import controller.interfaces.Productable;

public abstract class ProductoADFactory {
    
    private static Productable p = new ADProducto();

    public static Productable getAccessProductos() {
        return p;
    }
}
