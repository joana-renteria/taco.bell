package users;

public class Adminstrador extends Usuarie {

    // Constructors.
    public Adminstrador(String pCodUsr) {
        super(pCodUsr);
    }
    public Adminstrador(
    String pCodUsr, String pPasswd, 
    String pNombre, String pApellido) {
        super(pCodUsr, pPasswd, pNombre, pApellido);
    }

    // Methods.
    public void eliminarPedido() {

    }
    public void modificarPedido() {

    }
    public void eliminarUsuarie() {

    }
    public void modificarUsuarie() {

    }
    public void anadirUsuarie() {
        
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
