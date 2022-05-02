package controller.interfaces;

import java.util.ArrayList;
import datos.Descuento;

public interface Descontable {
    public void crearDescuento();
    public void borrarDescuento(String pCodDsc);
    public void modificarDescuento(Descuento pDescuento);
    public ArrayList <Descuento> listarDescuentos();
}
