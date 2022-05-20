package tests;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.TreeMap;
import java.time.LocalDate;

import org.junit.Before;

import org.junit.runner.RunWith;

import datos.Descuento;
import exceptions.GestorExcepciones;
import controller.factorias.DescuentoADFactory;

@RunWith(OrderedRunner.class)
public class TestsADDescuento {
    // conservar el código del Descuento creado.
    private final static String pCodDsc = generateCodigo();
    // se evita la excepcion con un método auxiliar.
    private static String generateCodigo() {
        try {
            return DescuentoADFactory
            .getAccessDescuento()
                .generateCodigo();
        } catch (GestorExcepciones ge) {
            System.out.println(ge.getFullMsg());
            return "DE00000000";
        }
    }
    // un Descuento auxiliar.
    private Descuento descuento = null;
    /**Genera una lista con todos los descuentos.
     * Cada vez que se llama por primera vez en un 
     * método se genera de nuevo, por lo que 
     * siempre se mantiene actualizada.
     */
    private TreeMap <String, Descuento> descuentos = listarDescuentos();
    private TreeMap <String, Descuento> listarDescuentos() {
        try {
            return DescuentoADFactory
                .getAccessDescuento()
                    .listarDescuentos();
        } catch (GestorExcepciones ge) {
            System.out.println(ge.getFullMsg());
            return new TreeMap <String, Descuento> ();
        }
    }
    /**Un método auxiliar para ahorrar la sentencia.
     * Usando la factoría se busca el Descuento por código.
     * @param pCodDsc código del descuento a buscar.
     */
    private Descuento buscar(String pCodDsc) {
        try {
            return DescuentoADFactory
                    .getAccessDescuento() // busqueda de un código.
                        .buscarDescuentoPorCodigo(pCodDsc);
        } catch (GestorExcepciones ge) {
            System.out.println(ge.getFullMsg());
            return null;
        }
    }
    /**Se comprueba la búsqueda de un descuento
     * en la base de datos. El código debe coincidir.
     * @throws GestorExcepciones
     */
    @Test
    @Order (order = 0)
    public void testBuscarDescuento() throws GestorExcepciones {
        // se calcula el número total de productos.
        int maxDescuentos = Integer.parseInt(
            pCodDsc.substring(2)),
            cont = 0;
        // se genera un código aleatorio.
        String codDscRandom = null;

        for (int i = 1; i <= maxDescuentos; i++) {
            codDscRandom = "DE000000";
            if (i < 10) 
                codDscRandom += "0" + i;
            else
                codDscRandom += i;
            
            cont = (buscar(codDscRandom) != null) ? 
                cont + 1 :
                cont;
        }
        // se comprueba que se genera el producto correcto.
        assertEquals(
            cont,
            DescuentoADFactory
                .getAccessDescuento()
                    .totalDescuentos());
    }
    /**Se comprueba que el método equals
     * @see Descuento equals() 
     * hace la comparacion de forma esperada.
     */
    @Test
    @Order (order = 1)
    public void testEqualsDescuento() {
        // se generan dos descuentos con diferencias pero equivalentes.
        descuento = 
            new Descuento(
            "DE00000001",
            3,
            (float) 2,
            LocalDate.of(2022, 4, 8),
            LocalDate.of(2022, 5, 27));

        Descuento descuento2 = 
            new Descuento(
            "DE00000001",
            3,
            (float) 2,
            LocalDate.of(2022, 4, 8),
            LocalDate.of(2022, 5, 27));
        // se comprueba que la comparación funciona de la forma esperada.
        assertEquals(descuento2, descuento);
        assertTrue(descuento2.equals(descuento));
    }
    /**Se añade un usuarie de prueba.
     * Después se comprueba que se ha grabado.
     * @throws GestorExcepciones
     */
    @Test
    @Order (order = 2)
    public void testAddDescuento() throws GestorExcepciones {
        // crear un descuento.
        descuento = 
            new Descuento(
            pCodDsc,
            5,
            6,
            LocalDate.now(),
            LocalDate.now().plusDays(90));
        // añadir a la base de datos.
        DescuentoADFactory
            .getAccessDescuento()
                .grabarDescuento(descuento);
        // comparar el objeto.
        assertEquals(buscar(pCodDsc), descuento);
    }
    /**Se comprueba que se puede modificar un Descuento 
     * por su código, y se cambia el creado antes (salvo código).
     * @throws GestorExcepciones
     */
    @Test
    @Order (order = 3)
    public void testModificarDescuento() throws GestorExcepciones {
        descuento = buscar(pCodDsc);

        assertNotNull(descuento);
        assertEquals(pCodDsc, descuento.getCodDsc());
        
        descuento.setUsos(94);
        descuento.setCantidadDsc(0);
        descuento.setFechaFin(
            descuento.getFechaFin().plusDays(2000));
        
        DescuentoADFactory
            .getAccessDescuento()
                .modificarDescuento(descuento);
        // el descuento modificado se encuentra en la tabla.
        assertEquals(descuento, buscar(pCodDsc));
    }
    /**De nuevo usando el creado antes se comprueba que 
     * se borra de forma efectiva de la base de datos.
     * @throws GestorExcepciones
     */
    @Test
    @Order (order = 4)
    public void testBorrarDescuento() throws GestorExcepciones {
        descuento = buscar(pCodDsc);
        
        assertNotNull(descuento);
        assertEquals(descuento, buscar(pCodDsc));
        // conservar la cantidad de productos en total.
        int total = DescuentoADFactory
            .getAccessDescuento()
                .totalDescuentos();

        DescuentoADFactory
            .getAccessDescuento()
                .borrarDescuento(pCodDsc);
        
        assertNull(buscar(pCodDsc));
        // ahora debe haber un descuento menos.
        assertEquals(
            total - 1,
            DescuentoADFactory
                .getAccessDescuento()
                    .totalDescuentos());
    }
    /**Se comprueba la generación del TreeMap
     * mirando el tamaño y comprobando todos los
     * elementos del mismo.
     * @throws GestorExcepciones
     */
    @Test
    @Order (order = 5)
    public void testListarDescuentos() throws GestorExcepciones {
        int total = DescuentoADFactory
            .getAccessDescuento()
                .totalDescuentos();
        // se comprueba el tamaño.
        assertEquals(
            total,
            descuentos.size());
        
        /**Se comprueba que efectivamente, todas las 
         * claves del TreeMap están contenidas en los
         * descuentos.
         */
        assertEquals(
            total,
            descuentos.values().stream()
            .filter(d -> descuentos.keySet().contains(d.getCodDsc()))
            .count());
        /**Se comprueba que todos los descuentos se han 
         * generado correctamente, uno a uno.
        */
        descuentos.keySet().stream()
            .forEach(k -> {
                assertTrue(descuentos.containsValue(buscar(k)));
                System.out.println(descuentos.containsKey(k));
            });
    }

}