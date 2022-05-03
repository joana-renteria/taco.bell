package controller.interfaces;

import java.util.ArrayList;
import datos.Producto;

public interface Productable {
    public void crearProducto(); 
    public void borrarProducto();
    public void modificarProducto();
    public Producto buscarProducto(String pCodPrd);
    public ArrayList <Producto> listarProducto();
    public ArrayList <Producto> listarProducto(String pTipo);
}