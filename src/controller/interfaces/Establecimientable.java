package controller.interfaces;

import java.util.ArrayList;
import datos.Establecimiento;

public interface Establecimientable {
    public void crearEstablecimiento();
    public void borrarEstablecimiento(String pCodEst);
    public void modificarEstablecimiento(Establecimiento pEstablecimiento);
    public ArrayList <Establecimiento> listarEstablecimientos(); 
}
