package controller.interfaces;

import java.util.ArrayList;
import datos.Producto;

public interface Productable {
    public void crearProducto(); 
    public void borrarProducto(String pCodPrd);
    public void modificarProducto(Producto pProducto);
    public Producto buscarProducto(String pCodPrd);
    public ArrayList <Producto> listarProducto();
    public ArrayList <Producto> listarProducto(String pTipo);
}