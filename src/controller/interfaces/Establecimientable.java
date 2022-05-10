package controller.interfaces;

import java.util.TreeMap;

import datos.Establecimiento;

public interface Establecimientable {
    public void grabarEstablecimiento(Establecimiento pEstablecimiento);
    public void borrarEstablecimiento(String pCodEst);
    public void modificarEstablecimiento(Establecimiento pEstablecimiento);
    public Establecimiento buscarEstablecimientoPorCodigo(String pCodEst);
    public TreeMap <String, Establecimiento> listarEstablecimientos(); 
    public String generateCodigo();
    public int totalEstablecimientos();
}
