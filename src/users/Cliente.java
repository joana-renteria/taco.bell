package users;

import java.sql.ResultSet;

public class Cliente extends Usuarie {
    private String correoLogin;

    // Constructors.
    public Cliente(
    String pCodUsr) {
        super(pCodUsr);
    }

    public Cliente(
    String pCodUsr, String pPasswd, 
    String pNombre, String pApellido, String pCorreoLogin) {
        super(pCodUsr, pPasswd, pNombre, pApellido);
        correoLogin = pCorreoLogin;
    }

    // Getter.
    public String getCorreoLogin() {
        return correoLogin;
    }

    // Setters.
    public void setCorreoLogin(String pCorreoLogin) {
        correoLogin = pCorreoLogin;
    }

    
    // Methods.
    public void realizarPedido() {

    }
    public void darseDeBaja() {

    }
    public ResultSet consultaPedido(String pCodPedido) {
        // TODO ¿seguro que este método debe devolver un ResultSets?
        return null;
    }
    public ResultSet findPedidos() {
        // TODO
        /* Usa el codigo de usuarie para 
         * consultar todos los pedidos correspondientes al cliente*/ 
        return null;
    }
}
