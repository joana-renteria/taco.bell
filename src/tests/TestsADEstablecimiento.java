package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;

import org.junit.Test;

import controller.factorias.EstablecimientoADFactory;
import datos.Establecimiento;

public class TestsADEstablecimiento {
    // Los tests se ejecutan a través de la factoría.
    @Test
    public void testListarEstablecimiento() {
        ArrayList <Establecimiento> establecimientos =
            EstablecimientoADFactory
                .getAccessEstablecimiento()
                    .listarEstablecimientos();
        // comprobar que la lista se ha creado debidamente.
        assertNotNull(establecimientos);

        for (Establecimiento pointer : establecimientos) 
            System.out.println(pointer.toString()); 
    }
   
    @Test
    public void testCrearEstablecimiento() {
        // crear un establecimiento con datos.
        String pCodEst = EstablecimientoADFactory.getAccessEstablecimiento().generateCodigo();
        Establecimiento pEstablecimiento = 
            new Establecimiento(
                pCodEst,
                "Urquijo Bell",
                "Alameda Urquijo");
                System.out.println(pEstablecimiento.toString());
        // grabado en la base de datos.
        EstablecimientoADFactory
            .getAccessEstablecimiento()
                .grabarEstablecimiento(pEstablecimiento);
        // comprobar que contiene el valor.
        Establecimiento buscar = 
            EstablecimientoADFactory.getAccessEstablecimiento() // busqueda de un código.
                .buscarPorCodigo(pCodEst);
                // deberia ser el mismo objeto al compararlos.
        assertEquals(pEstablecimiento.compareTo(buscar), 0);
        EstablecimientoADFactory
            .getAccessEstablecimiento()
                .borrarEstablecimiento(pCodEst);
    }
    @Test
    public void testDeleteEstablecimiento() {
        // crear un establecimiento con datos.
        String pCodEst = EstablecimientoADFactory.getAccessEstablecimiento().generateCodigo();
        Establecimiento pEstablecimiento = 
            new Establecimiento(
                pCodEst,
                "Urquijo Bell",
                "Alameda Urquijo");
                System.out.println(pEstablecimiento.toString());
        // grabado en la base de datos.
        EstablecimientoADFactory
            .getAccessEstablecimiento()
                .grabarEstablecimiento(pEstablecimiento);
        // comprobar que contiene el valor.
        EstablecimientoADFactory
            .getAccessEstablecimiento()
                .borrarEstablecimiento(pCodEst);

        assertNull(EstablecimientoADFactory.getAccessEstablecimiento().buscarPorCodigo(pCodEst));
    }
}
