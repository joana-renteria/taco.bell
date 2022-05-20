package controller.interfaces;

import java.util.TreeMap;

import datos.Producto;
import exceptions.GestorExcepciones;

public interface Productable {
    public void grabarProducto(Producto pProducto)
                throws GestorExcepciones;
    public void borrarProducto(String pCodPrd)
                throws GestorExcepciones;
    public void modificarProducto(Producto pProducto)
                throws GestorExcepciones;
    public Producto buscarProductoPorCodigo(String pCodPrd)
                throws GestorExcepciones;
    public TreeMap <String, Producto> listarProductos()
                throws GestorExcepciones;
    public String generateCodigo()
                throws GestorExcepciones;
    public int totalProductos()
                throws GestorExcepciones;
}