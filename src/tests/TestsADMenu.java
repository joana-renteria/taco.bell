package tests;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Random;
import java.util.TreeMap;

import org.junit.Before;
import org.junit.runner.RunWith;

import datos.Menu;
import controller.factorias.MenuADFactory;

@RunWith(OrderedRunner.class)
public class TestsADMenu {
    // conservar el código del Menu creado.
    private final static String pCodMnu = 
        MenuADFactory
            .getAccessMenu()
                .generateCodigo();
    // producto auxiliar.
    private Menu menu = null;
    /**Genera un TreeMao con todos los menus.
     * Cada vez que se llama por primera vez en un test
     * se genera de nuevo, por lo que siempre
     * se encuentra actualizada.
     */
    private TreeMap <String, Menu> menus = 
        MenuADFactory
            .getAccessMenu()
                .listarMenus();
    /**Método auxiliar que muestra ala tabla.
     * Se ejecuta antes de cada test individual.
     */
    @Before
    public void mostrarTablaCompleta() {
        menus.values().stream()
            .forEach(m -> System.out.println(m));
        System.out.print("\n");
    }
    /**Método auxiliar para ahorrar la sentencia de búsqueda.
     * Se usa la factoría, y se busca el Menu por código.
     * @param pCodMnu
     */
    private Menu buscar(String pCodMnu) {
        return MenuADFactory
            .getAccessMenu()
                .buscarMenuPorCodigo(pCodMnu);
    }
    /**Se comprueba que la búsqueda de un producto
     * en la base de datos funciona bien. Se mira el código.
     */
    
    public void testBuscarMenu() {
        int totalMenus = MenuADFactory
            .getAccessMenu()
                .totalMenus(),
            nMenuRandom = 
            new Random().nextInt(totalMenus) + 1;
        // se genera, pues, un código aleatorio.
        String codMnuRandom = "PR000000";

        if (nMenuRandom < 10)
            codMnuRandom += "0" + nMenuRandom;
        else
            codMnuRandom += nMenuRandom;
        // se comprueba que el producto generado es el correcto.
        assertEquals(
            "ME00000001",
            buscar("ME00000001").getCodMnu());
        System.out.println(buscar("ME00000001"));
    }
    /**Se comprueba el método equals
     * @see Producto equals()
     * hace la comparación esperada.
     */
    //@Test
    //@Order (order = 1)
    public void testEqualsMenu() {
        // lista de productos en distinto orden.
        String [] 
            pCodPrds1 = 
            {"PR00000003", "PR00000004", "PR00000007"},
            pCodPrds2 = 
            {"PR00000004", "PR00000003", "PR00000007"};
        // se generan dos menus distintos pero equivalentes.
        menu = 
            new Menu(
            pCodMnu,
            "DE00000001",
            pCodPrds1,
            (float) 8.99,
            "Menú Quesarito y Nachos");
        Menu menu2 = 
            new Menu(
            pCodMnu,
            "DE00000001",
            pCodPrds2,
            (float) 8.99,
            "menú quesarito y Nachos");
            // se observa que la comparación funciona.
            System.out.println(menu + "\n" + menu2);
            assertEquals(menu2, menu);
            assertTrue(menu2.equals(menu));
    }

    @Test
    @Order (order = 2)
    public void testAddMenu() {
        // crear un establecimiento con datos.
        String[] pCodPrds = 
        {"PR00000004", "PR00000003", "PR00000007"};
        menu = 
            new Menu(
            pCodMnu,
            "DE00000002",
            pCodPrds,
            (float) 186161,
            "Comida Mexicana Estereotipica");
        // añadir a la base de datos.
        MenuADFactory
            .getAccessMenu()
                .grabarMenu(menu);
        // comprobar los cambios en la tabla.
        assertEquals(buscar(pCodMnu), menu);
    }
    /**Se modifica el menu del test anterior, salvo su 
     * codigo, y se verifica que ha cambiado.
     */
    @Test
    @Order (order = 3)
    public void testModificarMenu() {
        menu = buscar(pCodMnu);

        assertNotNull(buscar(pCodMnu));
        assertEquals(pCodMnu, menu.getCodMnu());

        menu.setNombre("Comida picante india.");
        menu.setCodDsc("DE00000001");
        menu.setPrecio(5);

        MenuADFactory
            .getAccessMenu()
                .modificarMenu(menu);
        // el menu ya modificado se encuentra en la tabla.
        assertEquals(menu, buscar(pCodMnu));
    }
    /**Se elimina, y se observa que ya no 
     * está en la tabla.
     */
    @Test
    @Order (order = 4)
    public void testDeleteMenu() {
        menu = buscar(pCodMnu);

        assertNotNull(menu);
        assertEquals(pCodMnu, menu.getCodMnu());
        // conservar la cantidad de menus total.
        int total = MenuADFactory
            .getAccessMenu()
                .totalMenus();

        MenuADFactory
            .getAccessMenu()
                .borrarMenu(menu);
        
        assertNull(buscar(pCodMnu));
        // ahora debe haber un menu menos.
        assertEquals(
            total - 1,
            MenuADFactory
                .getAccessMenu()
                    .totalMenus());
    }
    /**Se comprueba el TreeMap, y se observa que 
     * se genera correctamente mirando el tamaño
     * y sus elementos.
     */
    @Test
    @Order (order = 5)
    public void testListarMenus() {
        int total = MenuADFactory
            .getAccessMenu()
                .totalMenus();
        // ¿es la cantidad de elementos correcta?
        assertEquals(
            total,
            menus.size());
        /**Se comprueba que todas las claves del 
         * TreeMap son los códigos de los menus.
         */
        assertEquals(
            total, 
            menus.values().stream()
            .filter(m -> menus.keySet().contains(m.getCodMnu()))
            .count());
    }

    @Test
    public void testMenuCompareCodigos() {
        String [] pProductos = // diferencias de case y de orden.
        {"aRrOz", "quEsO", "veGGie", "naTa aGria"};

        menu =
            new Menu(
            "PR00000002", 
            " ",
            pProductos,
            (float) 5, 
            "quEsaRitO veGGie");
        // se busca el producto real en la base de datos.
        Menu menu2 = 
            new Menu(
            "PR00000002", 
            " ",
            pProductos,
            (float) 5, 
            "quEsaRitO veGGie");      
            
        assertEquals(menu, menu2);
    }
}
