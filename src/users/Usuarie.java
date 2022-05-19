package users;

public abstract class Usuarie implements Comparable <Usuarie> {
    private final String codUsr;
    private String passwd;
    private String nombre;
    private String apellido;

    // Constructors.
    public Usuarie(String pCodUsr) {
        codUsr = pCodUsr;
    }
    public Usuarie(
    String pCodUsr, String pPasswd, 
    String pNombre, String pApellido) {
        codUsr = pCodUsr;
        passwd = pPasswd;
        nombre = pNombre;
        apellido = pApellido;
    }

    // Getters.
    public String getCodUsr() {
        return codUsr;
    } 
    public String getPasswd() {
        return passwd;
    }
    public String getNombre() {
        return nombre;
    }
    public String getApellido() {
        return apellido;
    }

    // Setters.
    public void setPasswd(String pPasswd) {
        passwd = pPasswd;
    }
    public void setNombre(String pNombre) {
        nombre = pNombre;
    }
    public void setApellido(String pApellido) {
        apellido = pApellido;
    }
    

    // Methods.
    public void login() {

    }

    public void register() {
        
    }

    @Override
    public boolean equals(Object obj) {        
        if (super.equals(obj) && obj instanceof Usuarie) {
            Usuarie pUsuarie = (Usuarie) obj;
            return pUsuarie.getCodUsr().equals(codUsr) 
                && pUsuarie.getPasswd().equals(passwd)
                && pUsuarie.getNombre().equalsIgnoreCase(nombre) 
                && pUsuarie.getApellido().equalsIgnoreCase(apellido);
        }
        else 
            return false;
    }

    @Override
    public int compareTo(Usuarie pUsuarie) {
        //return codUsr.compareTo(pUsuarie.getCodUsr());

        return codUsr.substring(2).compareTo(pUsuarie.getCodUsr().substring(2));
    }
    
    
    @Override
    public String toString() {
        return codUsr + " " + passwd + " " + nombre + " " + apellido;
    }
}
