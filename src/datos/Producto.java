package datos;

public class Producto implements Comparable <Producto> {
    private final String codPrd;
    private float precio;
    private String nombre;
    
    // Constructors.
    public Producto(String pCodPrd) {
        codPrd = pCodPrd;
    }
    public Producto(
    String pCodPrd, 
    float pPrecio, String pNombre) {
        codPrd = pCodPrd;
        precio = pPrecio;
        nombre = pNombre;
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

    // Setters.
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
    @Override
    public int compareTo(Producto o) {
        // TODO Auto-generated method stub
        return 0;
    }
}
