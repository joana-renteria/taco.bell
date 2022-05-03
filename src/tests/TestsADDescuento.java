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

        for (Descuento pointer : descuentos) 
            System.out.println(pointer.toString()); 
    }
    
    @Test
    public void testCrearDescuento() {
        // crear un descuento con datos.
        String pCodDsc = DescuentoADFactory.getAccessDescuento().generateCodigo();
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
            DescuentoADFactory.getAccessDescuento() // busqueda de un código.
                .buscarPorCodigo(pCodDsc);
        // deberia ser el mismo objeto al compararlos.
        assertEquals(pDescuento.compareTo(buscar), 0);
    }

    @Test
    public void testDeleteDescuento() {
        String pCodDsc = "DE00000010";
        Descuento pDescuento =  
            DescuentoADFactory
                .getAccessDescuento()
                    .buscarPorCodigo(pCodDsc);
        DescuentoADFactory
            .getAccessDescuento()
                .borrarDescuento(pCodDsc);

        assertNull(DescuentoADFactory.getAccessDescuento().buscarPorCodigo(pCodDsc));
    }
}