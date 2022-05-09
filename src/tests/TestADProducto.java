package tests;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Random;
import java.util.TreeMap;

import org.junit.Before;
import org.junit.runner.RunWith;

import datos.Producto;
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
    /**Genera una lista con los productos.
     * Cada vez que se llama se genera de nuevo, 
     * por lo que siempre está actualizada.*/
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
    /**Un método auxiliar para poder buscar
     * de forma más cómoda en la base de datos
     * a través del código, y ahorrar la sentencia.
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
    @Test
    @Order (order = 0)
    public void testBuscarProducto() {
        // se calcula el número total de productos.
        int totalProductos = ProductoADFactory
            .getAccessProductos()
                .totalProductos(),
            nProductoRandom = 
                new Random().nextInt(totalProductos) + 1;
        // se genera un código aleatorio.
        String codPrdRandom = "PR000000" ;

        if (nProductoRandom < 10) 
            codPrdRandom += "0" + nProductoRandom;
        else
            codPrdRandom += nProductoRandom;
        // se comprueba que se genera el producto correcto.
        assertEquals(
            codPrdRandom,
            buscar(codPrdRandom).getCodPrd());
    }
    /**Se comprueba que el método equals
     * @see Producto equals()
     * hace la comparación de forma esperada.
     */
    @Test
    @Order (order = 1)
    public void testEqualsProducto() {
        // se genera un producto con diferencias pero equivalente.
        String [] pIngredientes = // diferencias de case y de orden.
            {"Arroz", "quEsO", "naTa aGria", "veGGie"};

        Producto 
            producto1 =
            new Producto(
                "PR00000002", 
                (float) 4.99, 
                "quEsaRitO veGGie", 
                pIngredientes,
                "comIdA"),
        // se busca el producto real en la base de datos.
            producto2 = 
            buscar("PR00000002");

        assertNotNull(producto2);
        // se observa que la comparación funciona.
        assertEquals(producto1, producto2);
        assertTrue(producto2.equals(producto1));
        System.out.println(producto1 + "\n" + producto2 + "\n\n");
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
                795/100, 
                "Quesarito",
                pIngredientes,
                "Comida");

        ProductoADFactory
            .getAccessProductos()
                .grabarProducto(producto);
        // se observa que el producto está en la tabla.
        assertEquals(buscar(pCodPrd), producto);
    }
    /**Se modifica el producto creado en el test
     * anterior (excepto el código).
     */
    @Test
    @Order (order = 3)
    public void testModificarProducto() {                    
        producto = 
            buscar(pCodPrd);
        
        assertNotNull(producto);
        assertEquals(pCodPrd, producto.getCodPrd());

        producto.setNombre("Burrito de Queso");
        producto.setTipo("Diarrea explosiva");
        producto.addIngrediente("Salsa extra-picante");

        ProductoADFactory
            .getAccessProductos()
                .modificarProducto(producto);
        // el producto se encuentra en la tabla.
        assertEquals(
            producto,
            buscar(pCodPrd));
    }
    /**Se elimina el producto creado anteriormente
     * y se comprueba que ya no está almacenado.
     */
    @Test
    @Order (order = 4)
    public void testDeleteProducto() {
        producto = 
            buscar(pCodPrd);

        assertNotNull(producto);
        assertEquals(
            pCodPrd,
            producto.getCodPrd());
        // almacenar la cantidad de productos en total.
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
     * correctamente mirando el tamaño.
     */
    @Test
    @Order (order = 5)
    public void testListarProductos() {
        // se comprueba que contiene x elementos.
        assertEquals(
            productos.size(),
            ProductoADFactory
                .getAccessProductos()
                    .totalProductos());
    }

    @Test
    public void testProductoCompareIngredientes() {
        String [] pIngredientes = // diferencias de case y de orden.
            {"aRrOz", "quEsO", "veGGie", "naTa aGria"};

        Producto producto1 =
            new Producto(
                "PR00000002", 
                (float) 4.99, 
                "quEsaRitO veGGie", 
                pIngredientes,
                "comIdA"),
        // se busca el producto real en la base de datos.
        producto2 = new Producto(
            "PR00000002", 
            (float) 4.99, 
            "QUESarITo VeggiE", 
            pIngredientes,
            "COmiDa");           

        assertNotNull(producto2);
        assertEquals(producto1, producto2);
        assertEquals(producto2, buscar("PR00000002"));
    }
}