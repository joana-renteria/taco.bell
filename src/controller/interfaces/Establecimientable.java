package controller.interfaces;

import java.util.ArrayList;
import datos.Establecimiento;

public interface Establecimientable {
    public void crearEstablecimiento();
    public void borrarEstablecimiento();
    public void modificarEstablecimiento();
    public ArrayList <Establecimiento> listarEstablecimientos(); 
}
