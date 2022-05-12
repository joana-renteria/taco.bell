package datos;

import GestorExcepciones

import java.util.ArrayList;
import java.util.List;


public class Producto implements Comparable <Producto> {

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
        nombre = upperAndLower(pNombre);
        tipo = upperAndLower(pTipo);
    }
    /**Consctructor completo. Tiene todos los datos
     * de la clase, incluyendo la lista.
     * @param pIngredientes ingredientes del producto.
     */
    public Producto(
    String pCodPrd, float pPrecio, String pNombre,
    String [] pIngredientes, String pTipo) {
        this(pCodPrd, pPrecio, pNombre, pTipo);

        int bond = 
            (pIngredientes.length <= ingredientes.length) ? 
                pIngredientes.length : 
                ingredientes.length;

        for (int i = 0; i < bond; i++) 
            if (pIngredientes[i] != null)
                ingredientes[i] = upperAndLower(pIngredientes[i]);
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
        nombre = upperAndLower(pNombre);
    }   
    /**Método especial para añadir la cantidad justa
     * de ingredientes a la lista.*/
    public void addIngrediente(String pIngrediente) {
        pIngrediente = upperAndLower(pIngrediente);
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

        public void setTipo(String pTipo) throws GestorExcepciones {
          pTipo = upperAndLower(pTipo);
    	if(pTipo.equals("Comida")
    			|| pTipo.equals("Aperitivo")
    			|| pTipo.equals("Bebida"))
    		tipo = pTipo;
    	else
    		throw new GestorExcepciones(021);
    }

    // Métodos especiales.
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Producto) {
            Producto pProducto = (Producto) obj;
            return pProducto.getCodPrd().equals(codPrd)
                && pProducto.getPrecio() == precio
                && pProducto.getNombre().equalsIgnoreCase(nombre)
                && compareIngredientes(pProducto.getIngredientes())
                && pProducto.getTipo().equalsIgnoreCase(tipo);
        }
        else
            return false;
    }
    
    public boolean compareIngredientes(String [] pIngredientes) {
        List <String> 
            l1 = new ArrayList <String> (),
            l2 = new ArrayList <String> ();
        
        for (String s : ingredientes) 
            if (s !=  null)
                l1.add(s.toLowerCase());

        for (String s : pIngredientes) 
            if (s !=  null)
                l2.add(s.toLowerCase());

        for (String s : l1) 
            if (!l2.contains(s))
                return false;

        return true;
    }

    /** Simplemente formatea el texto introducido
     * como parametro. Se reutiliza.
     */
    private String upperAndLower(String pString) {
        pString = pString.substring(0, 1).toUpperCase() +  
            pString.substring(1).toLowerCase();

        if (pString.contains(" "))
            for (int i = 0; i < pString.length(); i++) 
                if (pString.charAt(i) == ' '
                && i != pString.length() - 2)
                    pString = 
                        pString.substring(0, i + 1) + 
                        Character.toUpperCase(pString.charAt(i + 1)) +
                        pString.substring(i + 2, pString.length());
        
        return pString;
    }


    @Override
    public String toString() {
        String ingredientesTexto = "";

        for (String s : ingredientes) 
            if (s != null)
                ingredientesTexto += upperAndLower(s) + " ";


        return codPrd + " " + precio + " " + upperAndLower(nombre) + " " 
            + ingredientesTexto + upperAndLower(tipo);
    }


    }
    @Override
    public int compareTo(Producto pProducto) {// TODO Auto-generated method stub
        return pProducto.getCodPrd().compareTo(codPrd);
    }
}
