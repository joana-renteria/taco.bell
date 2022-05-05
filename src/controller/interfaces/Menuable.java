package controller.interfaces;

import java.util.ArrayList;

import datos.Menu;

public interface Menuable {
    public void grabarMenu(Menu pMenu);
    public void borrarMenu(Menu pMnu);
    public void modificarMenu(Menu pMenu);
    public Menu buscarMenu(String pCodMnu);
    public ArrayList <Menu> listarMenus();
    public String generateCodigo();
}
