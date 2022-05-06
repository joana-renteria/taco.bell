package datos;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Producto {
    private final String codPrd;
    private float precio;
    private String nombre;
    private String [] ingredientes = new String [5];
    private String tipo;
    
    // Constructors.
    public Producto(String pCodPrd, float pPrecio, String pNombre) {
        codPrd = pCodPrd;
        precio = pPrecio;
        nombre = pNombre;
    }
    /**Constructor con todos los datos salvo ingredientes.
     * Se pueden añadir después.
     */
    public Producto(
    String pCodPrd, float pPrecio,
    String pNombre, String pTipo) {
        codPrd = pCodPrd;
        precio = pPrecio;
        nombre = pNombre;
        tipo = pTipo;
    }
    /**Consctructor completo. Incluye todos los datos
     * de la clase, incluyendo la lista.
     * @param pIngredientes
     */
    public Producto(
    String pCodPrd, float pPrecio, String pNombre,
    String [] pIngredientes, String pTipo) {
        this(pCodPrd, pPrecio, pNombre, pTipo);

        int bond = (pIngredientes.length <= ingredientes.length) ? pIngredientes.length : 5;
        for (int i = 0; i < bond; i++) 
            ingredientes[i] = pIngredientes[i];
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
    public String [] getIngredientes() {
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
    /**Método especial para añadir la cantidad justa
     * de ingredientes a la lista.*/
    public void addIngrediente(String pIngrediente) {
        for (int i = 0; i < ingredientes.length; i++) 
            if (ingredientes[i] == null) {
                ingredientes[i] = pIngrediente;
                break;
            }
    }
    /**Método para borrar un ingrediente en caso de
     * que esté. 
     * @param pIngrediente ignore case.
     */
    public void deleteIngrediente(String pIngrediente) {
        for (int i = 0; i < ingredientes.length; i++) 
            if (ingredientes[i].equalsIgnoreCase(pIngrediente)) {
                ingredientes[i] = null;
                break;
            }
    }

    public void setTipo(String pTipo) {
        tipo = pTipo;
    }

    // Métodos especiales.
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Producto) {
            Producto pProducto = (Producto) obj;
            return pProducto.getCodPrd().equals(codPrd)
                && pProducto.getPrecio() == precio
                && pProducto.getNombre().equalsIgnoreCase(nombre)
                && true//compareIngredientes(pProducto.getIngredientes())
                && pProducto.getTipo().equalsIgnoreCase(tipo);
        }
        else
            return false;
    }

    /**Método interno para comparar ambas listas de ingredientes.
     * El tamaño de la lista será 5, pues es el máximo permitido
     * por el constructor.
     * @param pIngredientes
     */
    public boolean compareIngredientes(String [] pIngredientes) {
        List <String> 
            ingList1 = Arrays.asList(ingredientes),
            ingList2 = Arrays.asList(pIngredientes);
            
        
        ingList1.stream()
            .sorted((i1, i2) -> i1.compareToIgnoreCase(i2));
        ingList2.stream()
            .sorted((i1, i2) -> i1.compareToIgnoreCase(i2));

        ingList1.stream()
            .forEach(i -> i.toLowerCase());
        ingList2.stream()
            .forEach(i -> i.toLowerCase());

        for (String i : ingList1)
            if (!ingList2.contains(i))
                return false;
        
        
        return ingList1.equals(ingList2);
    }



    @Override
    public String toString() {
        String ingredientesTexto = "";

        for (String s : ingredientes) 
            if (s != null)
                ingredientesTexto += s + " ";

        return codPrd + " " + precio + " " + nombre + " " 
            + ingredientesTexto + " " + tipo;
    }
   
}
