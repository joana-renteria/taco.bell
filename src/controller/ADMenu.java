package controller;

import java.util.ArrayList;

import controller.interfaces.Menuable;
import datos.Menu;

public class ADMenu extends MasterConnection implements Menuable {

    private final String insertar = "INSERT INTO menu VALUES (?, ?, ?, ?, ?)";
    private final String borrar = "DELETE FROM menu WHERE codMnu = ?";
    private final String modificar = "UPDATE FROM menu WHERE codMnu = ? SET usos = ?, cantidadDesc = ?, fechaInicio = ?, fechaFin = ?";
    private final String buscar = "SELECT * FROM menu";

    @Override
    public void crearMenu() {
        String pCodMnu = "ME";
        String numMen = String.valueOf(listarMenu().size());
        for (int i = 0; i < 8 - numMen.length(); i++)
            pCodMnu += "0";

            pCodMnu += numMen;

    }

    @Override
    public void borrarMenu() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void modificarMenu() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public Menu buscarMenu() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ArrayList<Menu> listarMenu() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Menu buscarMenu(String pCodMnu) {
        // TODO Auto-generated method stub
        return null;
    }
    
}
