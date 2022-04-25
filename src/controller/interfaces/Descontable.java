package controller.interfaces;

import java.util.ArrayList;
import datos.Descuento;

public interface Descontable {
    public void crearDescuento();
    public void borrarDescuento();
    public void modificarDescuento();
    public ArrayList <Descuento> listarDescuentos();
}
