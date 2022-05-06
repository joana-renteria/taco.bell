package tests;

import org.junit.Test;
import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.HashMap;

import datos.Descuento;
import controller.factorias.DescuentoADFactory;

public class TestsADDescuento {
    // conservar el código del Descuento creado.
    private String pCodDsc = 
        DescuentoADFactory
            .getAccessDescuento()
                .generateCodigo();
    /**Un método auxiliar para ahorrar la sentencia.
     * Usando la factoría se busca el Descuento por código.
     * @param pCodDsc
     */
    private Descuento buscar(String pCodDsc) {
        return DescuentoADFactory
                .getAccessDescuento() // busqueda de un código.
                    .buscarPorCodigo(pCodDsc);
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
    /**Mostrar por pantalla todos los descuentos de 
     * la base de datos.
     */
    public void testListarDescuentos() {
        HashMap <String,Descuento> descuentos =
            DescuentoADFactory
                .getAccessDescuento()
                    .listarDescuentos();
        // comprobar que la lista se ha creado debidamente.
        assertNotNull(descuentos);
        // printear la lista por pantalla.
        for (String pointer : descuentos.keySet()) 
            if (!pointer.equals(pCodDsc))
                System.out.println(buscar(pointer));
            else
                System.out.println("* " + buscar(pointer)); 
        System.out.print("\n");
    }

    @Test
    public void testTodo() {
        testListarDescuentos();
            testAddDescuento();
        testListarDescuentos();
            testModificarDescuento();
        testListarDescuentos();
            testBorrarDescuento();
        testListarDescuentos();
    }
}