package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.Test;

import controller.factorias.DescuentoADFactory;
import datos.Descuento;

public class TestsADDescuento {
    // Los tests se ejecutan a través de la factoría.
    
    @Test
    public void testListarDescuentos() {
        ArrayList <Descuento> descuentos =
            DescuentoADFactory
                .getAccessDescuento()
                    .listarDescuentos();
        // comprobar que la lista se ha creado debidamente.
        assertNotNull(descuentos);
        // printear la lista por pantalla.
        for (Descuento pointer : descuentos) 
            System.out.println(pointer.toString()); 
    }
    
    //@Test
    public void testCrearDescuento() {
        // crear un descuento con datos.
        String pCodDsc = 
            DescuentoADFactory
                .getAccessDescuento()
                    .generateCodigo();
        Descuento pDescuento = 
            new Descuento(
                pCodDsc,
                5,
                6,
                LocalDate.now(),
                LocalDate.now().plusDays(90));
        // grabado en la base de datos.
        DescuentoADFactory
            .getAccessDescuento()
                .grabarDescuento(pDescuento);
        // comprobar que contiene el valor.
        Descuento buscar = 
            DescuentoADFactory
                .getAccessDescuento() // busqueda de un código.
                .buscarPorCodigo(pCodDsc);
        // deberia ser el mismo objeto al compararlos.
        assertEquals(pDescuento.compareTo(buscar), 0);
    }
    
    public void testDeleteDescuento() {
        // se busca el descuento por código.
        String pCodDsc = "DE00000000";
        assertEquals(
            DescuentoADFactory
                .getAccessDescuento()
                    .buscarPorCodigo(pCodDsc).getCodDsc(), pCodDsc);
        // una vez confirmado que está, se borra.
        DescuentoADFactory
            .getAccessDescuento()
                .borrarDescuento(pCodDsc);
        // ahora no debería poder encontrar dicho descuento.
        assertNull(
            DescuentoADFactory
                .getAccessDescuento()
                    .buscarPorCodigo(pCodDsc));
    }
}