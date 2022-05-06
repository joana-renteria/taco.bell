package controller.factorias;

import controller.ADEstablecimiento;
import controller.interfaces.Establecimientable;

public abstract class EstablecimientoADFactory {
    
    private static Establecimientable e = new ADEstablecimiento();

    public static Establecimientable getAccessEstablecimiento() {
        return e;
    }
}
