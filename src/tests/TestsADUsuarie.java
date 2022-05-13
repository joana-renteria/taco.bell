package tests;

import org.junit.Test;
import static org.junit.Assert.*;
 
import java.util.Random;
import java.util.TreeMap;

import org.junit.Before;
import org.junit.runner.RunWith;

import users.*;
import controller.factorias.UsuarieADFactory;

@RunWith(OrderedRunner.class)
public class TestsADUsuarie {
    // conservar el código del Usuarie creado.
    private final static String [] type = 
        {"", "", ""};
    // un objeto usuarie auxiliar.
    private Usuarie pUsuarie = null;
    /**Genera una lista con todos los usuaries.
     * Cada vez que se llama por primera vez en un 
     * método se genera de nuevo. Siempre esta
     * actualizada.
     */
    private String pCodUsr =
        UsuarieADFactory
            .getAccessUsuaries()
                .crearCodigo(type [0]
                    .substring(0, 2)
                        .toUpperCase());
    /**Un método especial para ahorrar toda la sentencia.
     * Usando la factoría, se busca en la base de datos.
     * @param pCodUsr
     */
    private Usuarie buscar(String pCodUsr) {
        return UsuarieADFactory
            .getAccessUsuaries()
                .buscarUsuarie(pCodUsr);
    }
    /**Se añade un usuario de prueba (Captain Beefheart.)
     * Después se busca y se muestra por pantalla.
     */
    //@Test
    //@Before
    public void testAddUsuarie() {    
        // usuarie a grabar.
        Usuarie pUsuarie = 
            new Auxiliar(
                pCodUsr,
                "octafish",
                "Captain",
                "Beefheart",
                "ES00001", // codEst DEBE SER REAL.
                "Midnight",
                1941,
                "TMR");
        // añadir a la base de datos.
        UsuarieADFactory
            .getAccessUsuaries()
                .addUsuarie(pUsuarie);
        // comprobar los cambios.
        assertEquals(pUsuarie, buscar(pCodUsr));
       // si no da errores, coincide cuando se busca por código.
    }
    /**Se usa el usuarie creado antes para modificarlo
     * y testar los cambios.
     */
    //@Test
    public void testModificarUsuarie() {
        Usuarie pUsuarie = buscar(pCodUsr);
        // se comprueba que se instancia del tipo correcto.
        assertTrue(pUsuarie instanceof Auxiliar);
        assertEquals(pCodUsr, pUsuarie.getCodUsr());
        // se modifica el objeto usuarie.
        pUsuarie.setNombre("Frank");
        pUsuarie.setApellido("Zappa");
        pUsuarie.setPasswd("59'Chevy");
        ((Auxiliar) pUsuarie).setSueldo(1940);
        ((Auxiliar) pUsuarie).setPuesto("WOIFTM");
        // se updatea en la base de datos.
        UsuarieADFactory
            .getAccessUsuaries()
                .modificarUsuarie(pUsuarie);
        // comprobar los cambios.    
        assertEquals(pUsuarie, buscar(pCodUsr));
        // si no da errores, de nuevo se observa que ha cambiado en la BD.
    }
    /**Se comprueba que el borrado de usuarie se hace 
     * de forma efectiva, usando el mismo objeto de antes.
     */
    //@Test
    public void testBorrarUsuarie() {
        // se comprueba que se encuentra en la base de datos.
        assertEquals(buscar(pCodUsr).getCodUsr(), pCodUsr);
        // se procede a borrarlo.
        UsuarieADFactory
            .getAccessUsuaries()
                .borrarUsuarie(pCodUsr);
        // no debería poderse encontrar.
        assertNull(buscar(pCodUsr));
    }
    /**Sistema manual que muestra todos los códigos encontrados
     * en la base de datos.
     */
    // @Test
    public void testListarUsuaries() {
        String [] codes = 
            UsuarieADFactory
                .getAccessUsuaries()
                    .codigosUsuaries();
        // se comprueba que todos los códigos están.
        assertEquals(codes[0], buscar(codes[0]).getCodUsr());
        assertEquals(codes[1], buscar(codes[1]).getCodUsr());
        assertEquals(codes[2], buscar(codes[2]).getCodUsr());
        assertEquals(codes[3], buscar(codes[3]).getCodUsr());
        // para asegurarlo, se muestran por pantalla.
        for (String pointer : codes) 
            if (!pointer.equals(pCodUsr))
                System.out.println(buscar(pointer));
            else
                System.out.println("* " + buscar(pointer)); // sumarle un string.

        System.out.print("\n");
        
    }
    /**Se comprueban todos los tests de 
     * forma secuencial.
     */
    //@Test
    public void testTodo() {
        testListarUsuaries();
            testAddUsuarie();
        testListarUsuaries();
            testModificarUsuarie();
        testListarUsuaries();
            testBorrarUsuarie();
        testListarUsuaries();
    }
}
