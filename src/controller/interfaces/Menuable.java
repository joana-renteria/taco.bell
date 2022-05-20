package controller.interfaces;

import java.util.TreeMap;

import datos.Menu;
import exceptions.GestorExcepciones;

public interface Menuable {
    public void grabarMenu(Menu pMenu)
                throws GestorExcepciones;
    public void borrarMenu(Menu pMnu)
                throws GestorExcepciones;
    public void modificarMenu(Menu pMenu)
                throws GestorExcepciones;
    public Menu buscarMenuPorCodigo(String pCodMnu)
                throws GestorExcepciones;
    public String [] getCodigosProductos(Menu pMenu)
                throws GestorExcepciones;
    public TreeMap <String, Menu> listarMenus()
                throws GestorExcepciones;
    public String generateCodigo()
                throws GestorExcepciones;
    public int totalMenus()
                throws GestorExcepciones;
}
