package datos;

public class Menu {

    private final String codMnu;
    private String [] codPrds = new String [5];
    private float precio;
    
    // Constructors.
    public Menu(String pCodMnu) {
        codMnu = pCodMnu;
    }


    public Menu(
    String pCodMnu, float pPrecio) {
        codMnu = pCodMnu;
        precio = pPrecio;
    }


    public Menu(
    String pCodMnu, 
    String [] pCodPrds, float pPrecio) {
        codMnu = pCodMnu;
        codPrds = pCodPrds;
        precio = pPrecio;
    }

    // Getters 
    public String getCodMnu() {
        return codMnu;
    }
    public String[] getCodPrds() {
        return codPrds;
    }
    public float getPrecio() {
        return precio;
    }

    // Setters.
    public void setCodPrds(String[] pCodPrds) {
        codPrds = pCodPrds;
    }
    public void setPrecio(float pPrecio) {
        precio = pPrecio;
    }   

    // Methods.

    public float calcularPrecio() {
        float sum = 0;
        // TODO usar el controlador para acceder a los productos y sumar el precio.
        return sum;
    }

}
