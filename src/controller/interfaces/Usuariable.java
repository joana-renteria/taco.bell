package controller.interfaces;
import java.util.TreeMap;

import users.Usuarie;

public interface Usuariable {

    // se crean los distintos tipos de usuaries.
    
    public void addUsuarie(Usuarie pUsuarie);
    // los usuarios se borran a través de su código.
    public void borrarUsuarie(String pCodUsr);
    public void modificarUsuarie(Usuarie pUsuarie);
    public Usuarie buscarCliente(String pCorreo);
    public Usuarie buscarUsuarie(String pCodUsr);
    public TreeMap <String, Usuarie> listarUsuaries();
    public String generateCodigo(String pCodUsr);
    public int totalUsuaries();
}
