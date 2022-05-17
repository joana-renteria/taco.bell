package datos;

import java.util.ArrayList;
import java.util.List;

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
    /**Constructor con todo salvo 
     * los descuentos.
     */
    public Menu(
    String pCodMnu, String [] pCodPrds, 
    float pPrecio, String pNombre) {
        codMnu = pCodMnu;

        int bond = 
            (pCodPrds.length <= codPrds.length) ? 
                pCodPrds.length :
                codPrds.length;

        for (int i = 0; i < bond; i++) 
            if (pCodPrds != null) 
                codPrds[i] = pCodPrds[i];

        precio = pPrecio;
        nombre = upperAndLower(pNombre);
    }
    /**Constructor completo, Tiene todos los atributos
     * de la clase, incluso la lista.
     * @param pCodPrds codigos de los productos del menu.
     */
    public Menu(
    String pCodMnu, String pCodDsc, 
    String [] pCodPrds, float pPrecio, String pNombre) {
        this(pCodMnu, pCodPrds, pPrecio, pNombre);
        codDsc = pCodDsc;     
    }

    // Getters 
    public String getCodMnu() {
        return codMnu;
    }
    public String getCodDsc() {
        return codDsc;
    }
    public String [] getCodPrds() {
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
    /**Método especial para añadir la cantidad justa
     * de productos a la lista, evitando errores.*/
    public void addProducto(String pCodPrd) {
        boolean salir = false;
        for (int i = 0; i < codPrds.length && !salir; i++) 
            if (codPrds[i] == null) {
                codPrds[i] = pCodPrd;
                salir = true;
            }
    }
    /**Método para borrar un producto de la lista de productos,
     * pero solo en caso de que se encuentre.
     */
    public void deleteProducto(String pCodPrd) {
        boolean salir = false;
        for (int i = 0; i < codPrds.length && !salir; i++) 
            if (codPrds[i].equals(pCodPrd)) {
                codPrds[i] = null;
                salir = true;
            }
    }

    public float calcularPrecio() {
        float sum = 0;
        // TODO usar el controlador para acceder a los productos y sumar el precio.
        return sum;
    }

    // Métodos especiales.
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Menu) {
            Menu pMenu = (Menu) (obj);
            return pMenu.getCodMnu().equals(codMnu)
                && pMenu.getCodDsc().equals(codDsc)
                && compareProductos(pMenu.getCodPrds())
                && pMenu.getPrecio() == precio
                && pMenu.getNombre().equalsIgnoreCase(nombre);
        }
        else
            return false;
    }    

    private boolean compareProductos(String [] pCodPrds) {
        List <String>
            l1 = new ArrayList <String> (),
            l2 = new ArrayList <String> ();
        
        for (String c : codPrds)
            l1.add(c);

        for (String c : pCodPrds)
            l2.add(c);

        for (String c : l1) 
            if (!l2.contains(c))
                return false;

        return true;
    }

    /** Simplemente formatea el texto introducido
     * como parametro. Se reutiliza en más clases de datos.
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
        String codigosProductosTexto = "";

        for (String c : codPrds) 
            if (c != null)
                codigosProductosTexto += c + " ";

        return codMnu + " " + codDsc + " " + codigosProductosTexto
            + precio + " " + nombre;
    }

    @Override
    public int compareTo(Menu pMenu) {
        return pMenu.getCodMnu().compareTo(codMnu);
    }
}
