package controller.interfaces;

import java.util.ArrayList;
import datos.Establecimiento;

public interface Establecimientable {
    public void grabarEstablecimiento(Establecimiento pEstablecimiento);
    public void borrarEstablecimiento(String pCodEst);
    public void modificarEstablecimiento(Establecimiento pEstablecimiento);
    public Establecimiento buscarPorCodigo(String pCodEst);
    public ArrayList <Establecimiento> listarEstablecimientos(); 
    public String generateCodigo();
}
