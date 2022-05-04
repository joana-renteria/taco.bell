package controller.interfaces;
import users.Usuarie;

public interface Usuariable {

    // se crean los distintos tipos de usuaries.
    
    public void addUsuarie(Usuarie pUsuarie);
    // los usuarios se borran a través de su código.
    public void borrarUsuarie(String pCodUsr);
    public void modificarUsuarie(Usuarie pUsuarie);
    public Usuarie buscarUsuarie(String pCodUsr);
    public String [] codigosUsuaries();
    public int numeroDeUsuaries();
    public String crearCodigo(String pCodUsr);
}
