package tests;

import org.junit.Test;
import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.Random;
import java.util.TreeMap;

import org.junit.Before;
import org.junit.runner.RunWith;

import datos.Descuento;
import controller.factorias.DescuentoADFactory;

@RunWith(OrderedRunner.class)
public class TestsADDescuento {
    // conservar el código del Descuento creado.
    private final static String pCodDsc = 
        DescuentoADFactory
            .getAccessDescuento()
                .generateCodigo();
    // un Descuento auxiliar.
    private Descuento descuento = null;
    /**GEnera una lista con todos los productos.
     * Cada vez que se llama se genera de nuevo.
     */
    private TreeMap <String, Descuento> descuentos = 
        DescuentoADFactory
            .getAccessDescuento()
                .listarDescuentos();

    /**Método auxiliar que muestra la tabla. 
    * Se ejecuta antes de cada test individual.
    */
    @Before
    public void mostrarTablaCompleta() {
        descuentos.values().stream()
            .forEach(d -> System.out.println(d));
        System.out.println("\n");
    }
    /**Un método auxiliar para ahorrar la sentencia.
     * Usando la factoría se busca el Descuento por código.
     * @param pCodDsc
     */
    private Descuento buscar(String pCodDsc) {
        return DescuentoADFactory
                .getAccessDescuento() // busqueda de un código.
                    .buscarDescuentoPorCodigo(pCodDsc);
    }
    /**Se comprueba la búsqueda de un descuento
     * en la base de datos. El código debe coincidir.
     */
    @Test
    @Order (order = 0)
    public void testBuscarDescuento() {
        int totalDescuentos = DescuentoADFactory
            .getAccessDescuento()
                .totalDescuentos(),
            nDescuentoRandom = 
            new Random().nextInt(totalDescuentos) + 1;
        // se genera un código aleatorio.
        String codDscRandom = "DE000000";

        if (nDescuentoRandom < 10) 
            codDscRandom += "0" + nDescuentoRandom;
        else
            codDscRandom += nDescuentoRandom;
        // se comprueba que se genera el descuento con el codigo.
        assertEquals(
            codDscRandom,
            buscar(codDscRandom).getCodDsc());
    }
    /**Se comprueba que el método equals
     * @see Descuento equals() 
     * hace la comparacion de forma esperada.
     */
    public void testEqualsDescuento() {
        descuento = 
            new Descuento(
                "DE00000001",
                3,
                (float) 2,
                LocalDate.of(2022, 4, 8),
                LocalDate.of(2022, 5, 27));
    }
    /**Se añade un usuarie de prueba.
     * Después se comprueba que se ha grabado.
     */
    public void testAddDescuento() {
        // crear un descuento.
        Descuento pDescuento = 
            new Descuento(
                pCodDsc,
                5,
                6,
                LocalDate.now(),
                LocalDate.now().plusDays(90));
        // añadir a la base de datos.
        DescuentoADFactory
            .getAccessDescuento()
                .grabarDescuento(pDescuento);

        // comprobar los cambios.
        assertEquals(buscar(pCodDsc), pDescuento);
    }
    /**Se comprueba que se puede modificar un Descuento 
     * por su código usando el creado antes.
     */
    // @Test
    public void testModificarDescuento() {
        Descuento pDescuento = buscar(pCodDsc);
        assertEquals(pDescuento.getCodDsc(), pCodDsc);
        // se hace algún cambio en el descuento.
        pDescuento.setUsos(94);
        pDescuento.setCantidadDsc(0);
        pDescuento.setFechaFin(
            pDescuento.getFechaFin()
                .plusDays(2000));
        // se updatea en la base de datos.
        DescuentoADFactory
            .getAccessDescuento()
                .modificarDescuento(pDescuento);
        // comprobar lso cambios.
        assertEquals(pDescuento, buscar(pCodDsc));
    }
    /**De nuevo usando el creado antes se comprueba que 
     * se borra de forma efectiva de la base de datos.
     */
    // @Test
    public void testBorrarDescuento() {
        // se busca el descuento por código.
        assertEquals(buscar(pCodDsc).getCodDsc(), pCodDsc);
        // una vez confirmado que está, se borra.
        DescuentoADFactory
            .getAccessDescuento()
                .borrarDescuento(pCodDsc);
        // ahora no debería poder encontrar dicho descuento.
        assertNull(buscar(pCodDsc));
    }
}