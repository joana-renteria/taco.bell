package tests;

import org.junit.Test;
import static org.junit.Assert.*;
import users.*;
import controller.factorias.UsuarieADFactory;

public class TestsADUsuarie {
    // el código que se usa debe conservarse.
    private String pCodUsr =
        UsuarieADFactory
            .getAccessUsuaries()
                .crearCodigo("AU");

    /**Un método auxiliar para ahorrar toda la sentencia.
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

    //@Test
    public void testModificarUsuarie() {
        // se usa el mismo usuarie de antes para modificarlo.
        Usuarie pUsuarie = buscar(pCodUsr);
        System.out.println(pUsuarie);
        // se comprueba que se instancia del tipo correcto.
        assertTrue(pUsuarie instanceof Auxiliar);
        assertEquals(pCodUsr, pUsuarie.getCodUsr());
        // se modifica el objeto usuarie.
        pUsuarie.setNombre("Frank");
        pUsuarie.setNombre("Zappa");
        pUsuarie.setPasswd("59'Chevy");
        // se updatea en la base de datos.
        UsuarieADFactory
            .getAccessUsuaries()
                .modificarUsuarie(pUsuarie);
        // comprobar los cambios.    
        assertEquals(pUsuarie, buscar(pCodUsr));
        // si no da errores, de nuevo se observa que ha cambiado en la BD.
    }

    //@Test
    public void testBorrarUsuarie() {
        // se comprueba que se encuentra en la base de datos.
        assertEquals(buscar(pCodUsr).getCodUsr(), pCodUsr);
        // se procede a borrarlo.
        UsuarieADFactory
            .getAccessUsuaries()
                .borrarUsuarie(pCodUsr);
        // comprobar los cambios.
        assertNull(buscar(pCodUsr));
        // si no se encuentra es porque se ha borrado.
    }
    /**Sistema manual que muestra todos los códigos encontrados
     * en la base de datos.
     */
    //@Test
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
            System.out.println(buscar(pointer));

        System.out.print("\n");
    }
    /**Se comprueba que el numero de usuarios es
     * el correcto. (rows de usuarie)
     */
    public void numeroDeUsuaries() {
        System.out.println(
            UsuarieADFactory
                .getAccessUsuaries()
                    .numeroDeUsuaries());
    } 

    @Test
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
