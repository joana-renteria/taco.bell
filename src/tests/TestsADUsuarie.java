package tests;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.SynchronousQueue;

import org.junit.Test;

import controller.factorias.UsuarieADFactory;
import users.Repartidor;
import users.Usuarie;

public class TestsADUsuarie {
    
    @Test
    public void testListarUsuaries() {
        String [] codes = 
            {"AD00001", "AU00002", "CL00004", "RE00003"}; 
        // se comprueba que todos los códigos están.
        assertEquals(codes[0], testBuscar(codes[0]).getCodUsr());
        assertEquals(codes[1], testBuscar(codes[1]).getCodUsr());
        assertEquals(codes[2], testBuscar(codes[2]).getCodUsr());
        assertEquals(codes[3], testBuscar(codes[3]).getCodUsr());
        // para asegurarlo, se muestran por pantalla.
        for (String pCodUsr : codes) 
            System.out.println(testBuscar(pCodUsr).toString());
    }
    /**Simplemente sirve para ahorrarse escribir de nuevo
     * el acceso a la factoría.
     */
    private Usuarie testBuscar(String pCodUsr) {
        return UsuarieADFactory
            .getAccessUsuaries()
                .buscarUsuarie(pCodUsr);
    }

    @Test
    public void testNumeroDeUsuaries() {
        System.out.println(
            UsuarieADFactory
                .getAccessUsuaries()
                    .numeroDeUsuaries());
    }

    @Test
    public void testAddUsuarie() {
        String pCodUsr = 
            UsuarieADFactory
                .getAccessUsuaries()
                    .crearCodigo("RE");
                    
        Repartidor pRepartidor = 
            new Repartidor(
                pCodUsr,
                "octafish",
                "Captain",
                "Beefheart",
                "ES00001", // codEst DEBE SER REAL.
                "Medianoche",
                1941,
                "TMR");
        
        UsuarieADFactory
            .getAccessUsuaries()
                .addUsuarie(pRepartidor);
        
        System.out.println(testBuscar(pCodUsr));

        // FALLO DETECTADO: NO GRABA EN LAS DEMÁS TABLAS.
    }
}
