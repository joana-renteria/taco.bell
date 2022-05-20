package controller.interfaces;

import java.util.TreeMap;

import datos.Establecimiento;
import exceptions.GestorExcepciones;

public interface Establecimientable {
    public void grabarEstablecimiento(Establecimiento pEstablecimiento)
                throws GestorExcepciones;
    public void borrarEstablecimiento(String pCodEst)
                throws GestorExcepciones;
    public void modificarEstablecimiento(Establecimiento pEstablecimiento)
                throws GestorExcepciones;
    public Establecimiento buscarEstablecimientoPorCodigo(String pCodEst)
                throws GestorExcepciones;
    public TreeMap <String, Establecimiento> listarEstablecimientos()
                throws GestorExcepciones;
    public String generateCodigo()
                throws GestorExcepciones;
    public int totalEstablecimientos()
                throws GestorExcepciones;
}
