package controller.interfaces;

import java.util.TreeMap;

import datos.Descuento;
import exceptions.GestorExcepciones;

public interface Descontable {
    public void grabarDescuento(Descuento pDescuento)
                throws GestorExcepciones;
    public void borrarDescuento(String pCodDsc)
                throws GestorExcepciones;
    public void modificarDescuento(Descuento pDescuento)
                throws GestorExcepciones;
    public Descuento buscarDescuentoPorCodigo(String pCodDsc)
                throws GestorExcepciones;
    public TreeMap <String, Descuento> listarDescuentos()
                throws GestorExcepciones;
    public String generateCodigo()
                throws GestorExcepciones;
    public int totalDescuentos()
                throws GestorExcepciones;
}
