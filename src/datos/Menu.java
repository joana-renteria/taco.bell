package datos;

public class Menu implements Comparable <Menu> {

    private final String codMnu;
    private String codDsc;
    private String [] codPrds = new String [3];
    private float precio;
    private String nombre;
    
    // Constructors.
    public Menu(String pCodMnu) {
        codMnu = pCodMnu;
    }

    public Menu(
    String pCodMnu, 
    String pCodPrds[], float pPrecio, String pNombre) {
        codMnu = pCodMnu;
        codPrds = pCodPrds;
        precio = pPrecio;
        nombre = pNombre;
    }

    public Menu(
    String pCodMnu, String codDsc, 
    String pCodPrds[], float pPrecio, String pNombre) {
        codMnu = pCodMnu;
        codPrds = pCodPrds;
        precio = pPrecio;
        nombre = pNombre;
    }

    // Getters 
    public String getCodMnu() {
        return codMnu;
    }
    public String getCodDsc() {
        return codDsc;
    }
    public String[] getCodPrds() {
        return codPrds;
    }
    public float getPrecio() {
        return precio;
    }
    public String getNombre() {
        return nombre;
    }

    // Setters.
    public void setCodDsc(String pCodDsc) {
        codDsc = pCodDsc;
    }
    public void setCodPrds(String[] pCodPrds) {
        codPrds = pCodPrds;
    }
    public void setPrecio(float pPrecio) {
        precio = pPrecio;
    }   
    public void setNombre(String pNombre) {
        nombre = pNombre;
    }

    // Methods.

    public float calcularPrecio() {
        float sum = 0;
        // TODO usar el controlador para acceder a los productos y sumar el precio.
        return sum;
    }
    public int compareTo(Menu pMenu) {
        return codMnu.compareTo(pMenu.getCodMnu());
    }

    @Override
    public String toString() {
        return "{" +
            " codMnu='" + getCodMnu() + "'" +
            ", codDsc='" + getCodDsc() + "'" +
            ", codPrds='" + getCodPrds() + "'" +
            ", precio='" + getPrecio() + "'" +
            ", nombre='" + getNombre() + "'" +
            "}";
    }

}
