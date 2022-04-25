package controller.interfaces;

import java.util.ArrayList;

import datos.Menu;

public interface Menuable {
    public void crearMenu();
    public void borrarMenu();
    public void modificarMenu();
    public Menu buscarMenu();
    public ArrayList <Menu> listarMenu();
}
