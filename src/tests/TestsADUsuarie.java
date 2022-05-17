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
        {"CL", "AU", "RE", "AD"};
    private final static String pCodUsr =
        UsuarieADFactory
            .getAccessUsuaries()
                .generateCodigo(type 
                [(int) (Math.random()*4)]
                .substring(0, 2));
    // un objeto usuarie auxiliar.
    private final static Usuarie usuarie = 
        instanceUsuarie();
    /**Genera una lista con todos los usuaries.
     * Cada vez que se llama por primera vez en un 
     * método se genera de nuevo. Siempre esta
     * actualizada.
     */
    private TreeMap <String, Usuarie> usuaries = 
        UsuarieADFactory
            .getAccessUsuaries()
                .listarUsuaries();
    
    @Before
    @Test
    public void mostrarTablaCompleta() {
        usuaries.values().stream()
            .forEach(u -> System.out.println());
        System.out.print("\n");
    }
    /**Un método especial para ahorrar toda la sentencia.
     * Usando la factoría, se busca en la base de datos.
     * @param pCodUsr
     */
    private Usuarie buscar(String pCodUsr) {
        return UsuarieADFactory
            .getAccessUsuaries()
                .buscarUsuarie(pCodUsr);
    }
    /**Se genera un usuario de tipo aleatorio. 
     * Después se grabará en la base de datos.
    */
    public static Usuarie instanceUsuarie() {
        String
            pPasswd = "octafish",
            pNombre = "Captain",
            pApellido = "Beefheart",
            pCodEst = "ES00001",
            pHorario = "Nocturno";
        float pSueldo = 1941;
        Usuarie u;
        // usuarie a grabar.
        switch (pCodUsr.substring(0, 2)) {
            case "CL" :
                u = 
               new Cliente(pCodUsr, pPasswd, pNombre, 
                pApellido, "fakemail@fake.wtf");
            break;

            case "AU" :
                u = 
                new Auxiliar(pCodUsr, pPasswd,
                pNombre, pApellido, pCodEst,
                pHorario, pSueldo, "TMR");
            break;

            case "RE" :
                u = 
                new Repartidor(pCodUsr,pPasswd, pNombre,
                pApellido, pCodEst, pHorario,
                pSueldo,"TMR");
            break;

            case "AD" :
                u = 
                new Adminstrador(pCodUsr, pPasswd, pNombre,
                pApellido);
            break;

            default :
                u = null;
            break;
        }
        return u;
    }
    /**Se añade un usuario de prueba (Captain Beefheart.)
     * Después se busca y se muestra por pantalla.
     */
    @Test
    @Order (order = 1)
    public void testAddUsuarie() {
        System.out.println(usuarie);
        // añadir a la base de datos.
        UsuarieADFactory
            .getAccessUsuaries()
                .addUsuarie(usuarie);
        // comprobar los cambios.
        assertNotNull(buscar(pCodUsr));
        assertEquals(usuarie, buscar(pCodUsr));
       // si no da errores, coincide cuando se busca por código.
    }
    /**Se usa el usuarie creado antes para modificarlo
     * y testar los cambios.
     */
    //@Test
    public void testModificarUsuarie() {
        Usuarie usuarie = buscar(pCodUsr);
        // se comprueba que se instancia del tipo correcto.
        assertTrue(usuarie instanceof Auxiliar);
        assertEquals(pCodUsr, usuarie.getCodUsr());
        // se modifica el objeto usuarie.
        usuarie.setNombre("Frank");
        usuarie.setApellido("Zappa");
        usuarie.setPasswd("59'Chevy");
        ((Auxiliar) usuarie).setSueldo(1940);
        ((Auxiliar) usuarie).setPuesto("WOIFTM");
        // se updatea en la base de datos.
        UsuarieADFactory
            .getAccessUsuaries()
                .modificarUsuarie(usuarie);
        // comprobar los cambios.    
        assertEquals(usuarie, buscar(pCodUsr));
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
   
    
}
