package controller.interfaces;

import java.util.TreeMap;

import datos.Menu;

public interface Menuable {
    public void grabarMenu(Menu pMenu);
    public void borrarMenu(Menu pMnu);
    public void modificarMenu(Menu pMenu);
    public Menu buscarMenuPorCodigo(String pCodMnu);
    public String [] getCodigosProductos(Menu pMenu);
    public TreeMap <String, Menu> listarMenus();
    public String generateCodigo();
    public int totalMenus();
}
