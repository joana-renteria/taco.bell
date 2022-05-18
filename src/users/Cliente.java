package users;

import java.util.ArrayList;

import datos.Pedido;

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
    public Pedido consultaPedido(String pCodPedido) {
        // TODO ¿seguro que este método debe devolver un ArrayList <Pedido>s?
        return null;
    }
    public ArrayList <Pedido> getTodosLosPedidos() {
        // TODO
        /* Usa el codigo de usuarie para 
         * consultar todos los pedidos correspondientes al cliente*/ 
        return null;
    }


    @Override
    public boolean equals(Object obj) {        
        if (super.equals(obj) && obj instanceof Cliente) {
            Cliente pCliente = (Cliente) obj;
            return pCliente.getCorreoLogin().equalsIgnoreCase(correoLogin);
        }
        else
            return false;
    }

    @Override
    public String toString() {
        return super.toString() + " " + correoLogin;
    }
}
