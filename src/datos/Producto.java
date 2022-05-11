package datos;

import exceptions.GestorExcepciones;

public class Producto implements Comparable <Producto> {
    private final String codPrd;
    private float precio;
    private String nombre;
    private String tipo;
    
    // Constructors.
    public Producto(String pCodPrd) {
        codPrd = pCodPrd;
    }
    public Producto(
    String pCodPrd, 
    float pPrecio,
    String pNombre, String pTipo) {
        codPrd = pCodPrd;
        precio = pPrecio;
        nombre = pNombre;
        tipo   = pTipo;
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

    public void setTipo(String pTipo) throws GestorExcepciones {
    	if(pTipo.equals("Comida")
    			|| pTipo.equals("Aperitivo")
    			|| pTipo.equals("Bebida"))
    		tipo = pTipo;
    	else
    		throw new GestorExcepciones(021);
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
