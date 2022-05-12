package tests;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Random;
import java.util.TreeMap;

import org.junit.Before;
import org.junit.runner.RunWith;

import datos.Establecimiento;
import controller.factorias.EstablecimientoADFactory;

@RunWith(OrderedRunner.class)
public class TestsADEstablecimiento {
    // conservar el código del Establecimiento creado.
    private final static String pCodEst = 
        EstablecimientoADFactory
            .getAccessEstablecimiento()
                .generateCodigo();
    // un objeto Establecimiento auxiliar.
    private Establecimiento establecimiento = null;
    /**Genera una lista con todos los establecimientos.
     * Cada vez que se llama por primera vez en un 
     * método se genera de nuevo, por lo que 
     * siempre se mantiene actualizada.
     */
    private TreeMap <String, Establecimiento> establecimientos = 
        EstablecimientoADFactory
            .getAccessEstablecimiento()
                .listarEstablecimientos();
    /**Método auxiliar que muestra la tabla.
     * Se ejecuta antes de cada test individual.
     */
    @Before
    public void mostrarTablaCompleta() {
        establecimientos.values().stream()
            .forEach(e -> System.out.println(e));
        System.out.print("\n");
    }
    /**Un método auxiliar para ahorrarnos la sentencia.
     * Usando la factoría se busca el Establecimiento por código.
     * @param pCodEst
     */
    public Establecimiento buscar(String pCodEst) {
        return EstablecimientoADFactory
            .getAccessEstablecimiento()
                .buscarEstablecimientoPorCodigo(pCodEst);
    }
    /**Se comprueba la búsqueda de un Establecimiento en 
     * la base de datos.
     */
    @Test
    @Order (order = 0)
    public void testBuscarEstablecimiento() {
        // calcular el número de establecimientos.
        int totalEstablecimientos = EstablecimientoADFactory
            .getAccessEstablecimiento()
                .totalEstablecimientos(),
            nEstablecimientoRandom = 
            new Random().nextInt(totalEstablecimientos) + 1;
            // se genera un código aleatorio.
            String codEstRandom = "ES000";

            if (nEstablecimientoRandom < 10)
                codEstRandom += "0" + nEstablecimientoRandom;
            else
                codEstRandom += nEstablecimientoRandom;
        // se comprueba el establecimiento.
        assertEquals(
            codEstRandom,
            buscar(codEstRandom).getCodEst());
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
     */
    @Test
    @Order (order = 2)
    public void testAddEstablecimiento() {
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
     */
    @Test
    @Order (order = 3)
    public void testModificarEstablecimiento() {
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
     */
    @Test
    @Order (order = 4)
    public void testBorrarEstablecimiento() {
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
     */
    @Test
    @Order (order = 5)
    public void testListarEstablecimientos() {
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
