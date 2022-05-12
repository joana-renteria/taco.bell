package controller.interfaces;

import java.util.TreeMap;

import datos.Producto;

public interface Productable {
    public void grabarProducto(Producto pProducto); 
    public void borrarProducto(String pCodPrd);
    public void modificarProducto(Producto pProducto);
    public Producto buscarProductoPorCodigo(String pCodPrd);
    public TreeMap <String, Producto> listarProductos();
    public String generateCodigo();
    public int totalProductos();
}