package controller.interfaces;

import java.util.TreeMap;

import datos.Producto;

public interface Productable {
    public void grabarProducto(Producto pProducto); 
    public void borrarProducto(String pCodPrd);
    public void modificarProducto(Producto pProducto);
    public Producto buscarProducto(String pCodPrd);
    public TreeMap <String, Producto> listarProductos();
    public String generateCodigo();
    public int totalProductos();
}