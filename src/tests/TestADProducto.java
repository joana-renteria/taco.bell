package tests;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Random;
import java.util.TreeMap;

import org.junit.Before;
import org.junit.runner.RunWith;

import datos.Producto;
import exceptions.GestorExcepciones;
import controller.factorias.ProductoADFactory;

@RunWith(OrderedRunner.class)
public class TestADProducto {
    // conservar el código del Producto creado.
    private final static String pCodPrd = 
        ProductoADFactory
            .getAccessProductos()
                .generateCodigo();
    // producto auxiliar.
    private Producto producto = null;
   /**Genera una lista con todos los descuentos.
     * Cada vez que se llama por primera vez en un 
     * método se genera de nuevo, por lo que 
     * siempre se mantiene actualizada.
     */
    private TreeMap <String, Producto> productos = 
        ProductoADFactory
            .getAccessProductos()
                .listarProductos();
    /**Método auxiliar que muestra la tabla. 
     * Se ejecuta antes de cada test individual.
     */
    @Before
    public void mostrarTablaCompleta() {
        productos.values().stream() 
            .forEach(p -> System.out.println(p));
        System.out.println("\n");
    }
    /**Un método auxiliar para ahorrar la sentencia.
     * Usando la factoría se busca el Producto por código.
     * @param pCodPrd
     */
    private Producto buscar(String pCodPrd) {
        return ProductoADFactory
            .getAccessProductos()
                .buscarProductoPorCodigo(pCodPrd);
    }
    /**Se comprueba la búsqueda de un producto 
     * en la base de datos. El código coincide.
     */
    /*@Test
    @Order (order = 0)*/
    public void testBuscarProducto() {
        // se calcula el número total de productos.
        int totalProductos = ProductoADFactory
            .getAccessProductos()
                .totalProductos(),
            cont = 0;
        // se genera un código aleatorio.
        String codPrdRandom = null;
        
        productos.keySet();

        for (int i = 1; i <= totalProductos; i++) {
            codPrdRandom = "PR000000";
            if (i < 10) 
                codPrdRandom += "0" + i;
            else
                codPrdRandom += i;
            
            cont = (buscar(codPrdRandom) != null) ? 
                cont + 1 :
                cont;
        }
        // se comprueba que se genera el producto correcto.
        
    }
    /**Se comprueba que el método equals
     * @see Producto equals()
     * hace la comparación de forma esperada.
     */
    @Test
    @Order (order = 1)
    public void testEqualsProducto() {
        // lista de ingredientes en distinto orden.
        String []
            pIngredientes1 = 
            {"Arroz", "quEsO", "naTa aGria", "veGGie"},
            pIngredientes2 = 
            {"quEsO", "aRRoZ", "veGGie", "NATa AgRIA"};
        // se generan dos productos con diferencias pero equivalentes.
        producto =
            new Producto(
            "PR00000002", 
            (float) 4.99, 
            "quEsaRitO veGGie", 
            pIngredientes1,
            "comIdA");

        Producto producto2 = 
            new Producto(
            "PR00000002", 
            (float) 4.99, 
            "qUeSARitO VeggiE", 
            pIngredientes2,
            "comIdA");
        // se observa que la comparación funciona.
        assertEquals(producto2, producto);
        assertTrue(producto2.equals(producto));
        // también los ingredientes se comparan correctamente.
    }
    /**Se añade un producto de prueba, y se comprueba que 
     * se ha grabado correctamente.
     */
    @Test
    @Order (order = 2)
    public void testAddProducto() {
        String [] pIngredientes = {"Carne", "Queso", "Arroz", "Tomate"};
        producto = 
            new Producto(
            pCodPrd,
            7, 
            "Quesarito y Taco",
            pIngredientes,
            "Comida");
        // añadir a la base de datos.
        ProductoADFactory
            .getAccessProductos()
                .grabarProducto(producto);
        System.out.println(pCodPrd);
        // comprobar los cambios.
        System.out.println(pCodPrd);
        assertEquals(buscar(pCodPrd), producto);
    }
    /**Se modifica el producto creado en el test
     * anterior (excepto el código).
     */
    @Test
    @Order (order = 3)
    public void testModificarProducto() {
        producto = buscar(pCodPrd);
        
        assertNotNull(producto);
        assertEquals(pCodPrd, producto.getCodPrd());

        producto.setNombre("Burrito de Queso");
        // TODO: en caso de fallo, un valor default en la clase?.
        String pTipo = "Diarrea explosiva";
        try {
            producto.setTipo(pTipo);
        } catch (GestorExcepciones ge) {
            producto.setTipoDefault();
        }

        producto.addIngrediente("Salsa extra-picante");

        ProductoADFactory
            .getAccessProductos()
                .modificarProducto(producto);
        // el producto modificado se encuentra en la tabla.
        assertEquals(producto, buscar(pCodPrd));
    }
    /**Se elimina el producto creado anteriormente
     * y se comprueba que ya no está almacenado.
     */
    @Test
    @Order (order = 4)
    public void testBorrarProducto() {
        producto = buscar(pCodPrd);

        assertNotNull(producto);
        assertEquals(pCodPrd, producto.getCodPrd());
        // conservar la cantidad de productos en total.
        int total = ProductoADFactory
            .getAccessProductos()
                .totalProductos();
        
        ProductoADFactory
                .getAccessProductos()
                    .borrarProducto(pCodPrd);
                    
        assertNull(buscar(pCodPrd));
        // ahora debe haber un producto menos.
        assertEquals(
            total - 1,
            ProductoADFactory
                .getAccessProductos()
                    .totalProductos());
    }
    /**Se comprueba que el TreeMap se genera 
     * correctamente mirando el tamaño, y comprobando
     * todos los elementos..
     */
    @Test
    @Order (order = 5)
    public void testListarProductos() {
        int total = ProductoADFactory
            .getAccessProductos()
                .totalProductos();
        // se comprueba que contiene x elementos.
        assertEquals(
            total,
            productos.size());
        /**Se comprueba que todas las 
         * claves del TreeMap están contenidas en los 
         * productos
         */
        assertEquals(
            total,
            productos.values().stream()
            .filter(p -> productos.keySet().contains(p.getCodPrd()))
            .count());
        /**Se comprueba que todos los productos se han
         * generado correctamente, uno a uno.
         */
        productos.keySet().stream()
            .forEach(k -> {
                assertTrue(productos.containsValue(buscar(k)));
                System.out.println(productos.containsKey(k));
            });
    }
}