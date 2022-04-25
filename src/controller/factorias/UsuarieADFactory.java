package controller.factorias;

import controller.ADUsuarie;
import controller.interfaces.Usuariable;

public class UsuarieADFactory {
    
    private static Usuariable u = new ADUsuarie();

    public static Usuariable getAccessUsuaries() {
        return u;
    }
}
