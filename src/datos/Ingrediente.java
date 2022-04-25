package datos;

public class Ingrediente {

    private final String codIng;
    private float precio;

    public Ingrediente(String pCodIng) {
        codIng = pCodIng;
    }
    public Ingrediente(
    String pCodIng, float pPrecio) {
        codIng = pCodIng;
        precio = pPrecio;
    }

    // Getters.
    public String getCodIng() {
        return codIng;
    }
    public float getPrecio() {
        return precio;
    }

    // Setters
    public void setPrecio(float pPrecio) {
        precio = pPrecio;
    } 
}
