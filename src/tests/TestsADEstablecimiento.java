package tests;

import org.junit.Test;
import static org.junit.Assert.*;
 
import java.util.TreeMap;

import org.junit.Before;
import org.junit.runner.RunWith;

import datos.Establecimiento;
import exceptions.GestorExcepciones;
import controller.factorias.EstablecimientoADFactory;

@RunWith(OrderedRunner.class)
public class TestsADEstablecimiento {
    // conservar el código del Establecimiento creado.
    private final static String pCodEst = generateCodigo();
    private static String generateCodigo() {
         try {
            return EstablecimientoADFactory
                .getAccessEstablecimiento()
                    .generateCodigo();
        } catch (GestorExcepciones ge) {
            System.out.println(ge.getFullMsg());
            return "ES00000";
        }
    }
    // un objeto Establecimiento auxiliar.
    private Establecimiento establecimiento = null;
    /**Genera una lista con todos los establecimientos.
     * Cada vez que se llama por primera vez en un 
     * método se genera de nuevo, por lo que 
     * siempre se mantiene actualizada.
     */
    private TreeMap <String, Establecimiento> establecimientos = 
        listarEsablecimientos();
    private TreeMap <String, Establecimiento> listarEsablecimientos() {
        try {
            return EstablecimientoADFactory
            .getAccessEstablecimiento()
                .listarEstablecimientos();
        } catch (GestorExcepciones ge) {
            System.out.println(ge.getFullMsg());
            return new TreeMap <String, Establecimiento>  ();
        }
    }
    /**Un método auxiliar para ahorrarnos la sentencia.
     * Usando la factoría se busca el Establecimiento por código.
     * @param pCodEst
     */
    public Establecimiento buscar(String pCodEst) {
        try {
            return EstablecimientoADFactory
                .getAccessEstablecimiento()
                    .buscarEstablecimientoPorCodigo(pCodEst);
        } catch (GestorExcepciones ge) {
            System.out.println(ge.getFullMsg());
            return null;
        }
    }
    /**Se comprueba la búsqueda de un Establecimiento en 
     * la base de datos.
     * @throws GestorExcepciones
     */
    @Test
    @Order (order = 0)
    public void testBuscarEstablecimiento() throws GestorExcepciones {
        // se calcula el número total de productos.
        int maxEstablecimientos = Integer.parseInt(
            pCodEst.substring(2)),
            cont = 0;
        // se genera un código aleatorio.
        String codDscRandom = null;

        for (int i = 1; i <= maxEstablecimientos; i++) {
            codDscRandom = "ES000";
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
            EstablecimientoADFactory
                .getAccessEstablecimiento()
                    .totalEstablecimientos());
    }
    /**Se comprueba que el método equals
     * @see Establecimiento equals()
     * hace la comparación de la forma esperada.
     */
    @Test
    @Order (order = 1)
    public void testEqualsEstablecimiento() {
        // se generan dos establecimientos equivalentes.
        establecimiento = 
            new Establecimiento(pCodEst,
            "El de Indautxu",
            "obViAMente INDAutxu");
        Establecimiento establecimiento2 = 
            new Establecimiento(
            pCodEst,
            "el de INDAUTXU",
            "obviamente inDautXu");
        // se comprueba que la comparación funciona de la forma esperada.
        assertEquals(establecimiento2, establecimiento);
        assertTrue(establecimiento2.equals(establecimiento));
    }
    /**Se añade un descuento de prueba. 
     * Después se comprueba que se ha grabado.
     * @throws GestorExcepciones
     */
    @Test
    @Order (order = 2)
    public void testAddEstablecimiento() throws GestorExcepciones {
        // crear un establecimiento con datos.
        establecimiento = 
            new Establecimiento(
            pCodEst,
            "El de Indautxu", 
            "Obviamente Indautxu");
        // se añade a la base de datos.
        EstablecimientoADFactory
            .getAccessEstablecimiento()
                .grabarEstablecimiento(establecimiento);
        // comparar el objeto.
        assertEquals(buscar(pCodEst), establecimiento);
    }
    /**Se comprueba que se puede modificar un Establecimiento
     * por su código, y se cambia el creado antes (salvo su código)
     * @throws GestorExcepciones
     */
    @Test
    @Order (order = 3)
    public void testModificarEstablecimiento() throws GestorExcepciones {
        establecimiento = buscar(pCodEst);

        assertNotNull(establecimiento);
        assertEquals(pCodEst, establecimiento.getCodEst());

        establecimiento.setLoc("ya no es Indautxu.");
        establecimiento.setNombre("not indautxu");

        EstablecimientoADFactory
            .getAccessEstablecimiento()
                .modificarEstablecimiento(establecimiento);
        // el descuento una vez modificado se encuentra en la tabla.
        assertEquals(establecimiento, buscar(pCodEst));
    }
    /**Se vuelve a utilizar el modificado antes para poder 
     * verificar que se puede borrar de forma efectiva.
     * @throws GestorExcepciones
     */
    @Test
    @Order (order = 4)
    public void testBorrarEstablecimiento() throws GestorExcepciones {
        establecimiento = buscar(pCodEst);

        assertNotNull(establecimiento);
        assertEquals(establecimiento, buscar(pCodEst));
        // conservar la cantidad de establecimientos.
        int total = EstablecimientoADFactory
            .getAccessEstablecimiento()
                .totalEstablecimientos();

        EstablecimientoADFactory
            .getAccessEstablecimiento()
                .borrarEstablecimiento(pCodEst);

        assertNull(buscar(pCodEst));
        // ahora debe haber un descuento menos.
        assertEquals(
            total - 1,
            EstablecimientoADFactory
                .getAccessEstablecimiento()
                    .totalEstablecimientos());
    }
    /**Ser revisa que la lista generada
     * no tiene ningún error revisando 
     * los elementos que contiene.
     * @throws GestorExcepciones
     */
    @Test
    @Order (order = 5)
    public void testListarEstablecimientos() throws GestorExcepciones {
        int total = EstablecimientoADFactory
            .getAccessEstablecimiento()
                .totalEstablecimientos();
        // se comprueba el tamaño.
        assertEquals(
            total,
            establecimientos.size());
        
        /**Se comprueba que todas las claves
         * del TreeMap están contenidas en los objetos 
         * (Establecimiento) que contiene.
         */
        assertEquals(
            total,
            establecimientos.values().stream()
            .filter(e -> establecimientos.keySet().contains(e.getCodEst()))
            .count());
        /**Se comprueba que todos los establecientos se han
         * generado correctamente, uno a uno.*/
        establecimientos.keySet().stream()
            .forEach(k -> {
                assertTrue(establecimientos.containsValue(buscar(k)));
                System.out.println();
            });
    }
}
