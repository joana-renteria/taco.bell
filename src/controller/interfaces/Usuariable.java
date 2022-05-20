package controller.interfaces;
import java.util.TreeMap;

import exceptions.GestorExcepciones;
import users.Usuarie;

public interface Usuariable {
    public void addUsuarie(Usuarie pUsuarie)
                throws GestorExcepciones;
    public void borrarUsuarie(String pCodUsr)
                throws GestorExcepciones;
    public void modificarUsuarie(Usuarie pUsuarie)
                throws GestorExcepciones;
    public Usuarie buscarCliente(String pCorreo)
                throws GestorExcepciones;
    public Usuarie buscarUsuarie(String pCodUsr)
                throws GestorExcepciones;
    public TreeMap <String, Usuarie> listarUsuaries()
                throws GestorExcepciones;
    public String generateCodigo(String pCodUsr)
                throws GestorExcepciones;
    public int totalUsuaries()
                throws GestorExcepciones;
}
