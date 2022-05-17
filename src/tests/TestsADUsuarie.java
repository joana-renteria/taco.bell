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
    // lista con todos los prefijos de los códigos posibles.
    private final static String [] type = 
        {"CL", "AU", "RE", "AD"};
    /**Método que genera códigos de usuaries (existentes o no)
     * @param is sirve para indicar si existe o no.
     * @return codigo de un Usuarie que se encuentra en la base 
     * de datos o no.
    */
    private static String generateRandomUserCode(boolean is) {
        // calcular el número total de usuaries.
        int totalUsuaries = UsuarieADFactory
            .getAccessUsuaries()
                .totalUsuaries(),
            nUsuarieRandom = 
            new Random().nextInt(totalUsuaries) + 1;
            // se genera un número de usuarie aleatorio..
            String codUsuarieRandom = "00000";

            if (nUsuarieRandom < 10)
                codUsuarieRandom += "0" + nUsuarieRandom;
            else
                codUsuarieRandom += nUsuarieRandom;
        // en caso de uno que no exista, se devuelve con un prefijo cualquiera.
        if (!is)
            return type [(int) (Math.random()*4)] + codUsuarieRandom;

        while ((buscar(codUsuarieRandom) == null) == is) 
            codUsuarieRandom = 
                type [(int) (Math.random()*4)] + 
                codUsuarieRandom.substring(2);

        return codUsuarieRandom;
    }
    /**Se genera un codigo de usuarie no existente previamente.
     * Después se harán las pruebas de la base de datos con el.
     */
    private final static String pCodUsr =
        generateRandomUserCode(false);
    
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
    // un objeto usuarie auxiliar.
    private static Usuarie usuarie = null;
    /**Genera una lista con todos los usuaries.
     * Cada vez que se llama por primera vez en un 
     * método se genera de nuevo. Siempre esta
     * actualizada.
     */
    private TreeMap <String, Usuarie> usuaries = 
        UsuarieADFactory
            .getAccessUsuaries()
                .listarUsuaries();
    /**Método auxiliar que muestra la tabla.
     * Se ejecuta antes de cada tests individual.
     */
    @Before
    @Test
    public void mostrarTablaCompleta() {
        usuaries.values().stream()
            .forEach(u -> System.out.println(u));
        System.out.print("\n");
    }
    /**Un método especial para ahorrar toda la sentencia.
     * Usando la factoría, se busca en la base de datos.
     * @param pCodUsr
     */
    private static Usuarie buscar(String pCodUsr) {
        return UsuarieADFactory
            .getAccessUsuaries()
                .buscarUsuarie(pCodUsr);
    }
    /**Se añade un usuario de prueba (Captain Beefheart.)
     * Después se busca y se muestra por pantalla.
     */
    @Test
    @Order (order = 0)
    public void testBuscarUsuarie() {
        // se genera un código aleatorio de un usuarie existente.
        String pCodRandom = generateRandomUserCode(true);
        usuarie = buscar(pCodRandom);
        // se comprueba que se ha devuelto un usuarie.
        assertNotNull(usuarie);
        // se comprueba que el código del usuarie coincide.
        assertEquals(pCodRandom, usuarie.getCodUsr());
        // se comprueba que la clase del objeto es la correspondiente. 
        assertEquals(
            pCodRandom.substring(0, 2),

            usuarie.getClass().getName()
            .substring(6, 8).toUpperCase());
    }
    @Test
    @Order (order = 1)
    public void testAddUsuarie() {
        assertNull(usuarie);
        usuarie = instanceUsuarie();
        System.out.println(usuarie);
        // añadir a la base de datos.
        UsuarieADFactory
            .getAccessUsuaries()
                .addUsuarie(usuarie);
        // comprobar los cambios.
        assertNotNull(buscar(pCodUsr));

        assertEquals(usuarie, buscar(pCodUsr));
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
