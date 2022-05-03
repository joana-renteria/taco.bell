package controller.interfaces;

import java.util.ArrayList;
import datos.Descuento;

public interface Descontable {
    public void grabarDescuento(Descuento pDescuento);
    public void borrarDescuento(String pCodDsc);
    public void modificarDescuento(Descuento pDescuento);
    public Descuento buscarPorCodigo(String pCodDsc);
    public ArrayList <Descuento> listarDescuentos();
    public String generateCodigo();
}
