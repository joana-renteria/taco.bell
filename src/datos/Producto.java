package datos;

public class Producto {
    private final String codPrd;
    private float precio;
    private String nombre;
    private String[] ingredientes;
    private String tipo;
    
    // Constructors.
    public Producto(String pCodPrd) {
        codPrd = pCodPrd;
    }
    public Producto(
    String pCodPrd, 
    float pPrecio, String pNombre, 
    String[] pIngredientes, String pTipo) {
        codPrd = pCodPrd;
        precio = pPrecio;
        nombre = pNombre;
        ingredientes = pIngredientes;
        tipo = pTipo;
    }

    // Getters.
    public String getCodPrd() {
        return codPrd;
    }
    public float getPrecio() {
        return precio;
    }
    public String getNombre() {
        return nombre;
    }
    public String[] getIngredientes() {
        return ingredientes;
    }
    public String getTipo() {
        return tipo;
    }

    // Setters.
    public void setPrecio(float pPrecio) {
        precio = pPrecio;
    }
    public void setNombre(String pNombre) {
        nombre = pNombre;
    }   
    public void setIngredientes(String[] pIngredientes) {
        ingredientes = pIngredientes;
    }

    // Methods.
    public float calcularPrecio() {
        float sum = 0;
        // TODO usar el controlador para acceder a los productos y sumar el precio.
        return sum;
    }
}
