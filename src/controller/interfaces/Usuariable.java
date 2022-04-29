package controller.interfaces;

import java.util.ArrayList;

import resources.Util;
import users.Usuarie;

public interface Usuariable {

    // se crean los distintos tipos de usuaries.
    /*
    public void crearAdministrador();
    public void crearAuxiliar();
    public void crearCliente();
    public void crearRepartidor();
    public void crearTrabajador();
    // los usuarios se borran a través de su código.
    public void borrarUsuario();
    public void modificarUsuario();
    */
    public ArrayList <Usuarie> listarUsuarios(); // util para administrador.
    
}
