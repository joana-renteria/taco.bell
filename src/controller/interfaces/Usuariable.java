package controller.interfaces;

import java.util.ArrayList;
import users.Usuarie;

public interface Usuariable {

    // se crean los distintos tipos de usuaries.
    
    public void crearUsuarie();
    // los usuarios se borran a través de su código.
    public void borrarUsuarie(String pCodUsr);
    public void modificarUsuarie(Usuarie pUsuarie);

    public ArrayList <Usuarie> listarUsuaries();
    public int numeroDeUsuaries();
}
