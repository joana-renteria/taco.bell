package controller.interfaces;

import java.util.ArrayList;

import datos.Menu;

public interface Menuable {
    public void crearMenu();
    public void borrarMenu(Menu pMenu);
    public void modificarMenu(Menu pMenu);
    public Menu buscarMenu(String pCodMnu);
    public ArrayList <Menu> listarMenu();
}
