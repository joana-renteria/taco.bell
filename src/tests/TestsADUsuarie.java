package tests;

import org.junit.Test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import org.junit.runner.RunWith;

import users.*;
import controller.factorias.UsuarieADFactory;
import exceptions.GestorExcepciones;

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
    private static String generateRandomUserCode() {
        List <String> l = new ArrayList <String> (usuaries.keySet());

        return l.get((int) (Math.random()*l.size()));
    }
    /**Se genera un codigo de usuarie no existente previamente.
     * Después se harán las pruebas de la base de datos con el.
     */
    private final static String pCodUsr = generateCodigo();
    private static String generateCodigo() {
        try {
            return UsuarieADFactory
            .getAccessUsuaries()
                .generateCodigo(type[(int) (Math.random()*3)]);
        } catch (GestorExcepciones ge) {
            System.out.println(ge.getFullMsg());
            return "CL00000";
        } 
    }
    
    /**Se genera un usuario de tipo aleatorio. 
     * Después se grabará en la base de datos.
    */
    public Usuarie instanceUsuarie() {
        String
            pPasswd = "octafish",
            pNombre = "Captain",
            pApellido = "Beefheart",
            pCodEst = "ES00001",
            pHorario = "Nocturno";

        float pSueldo = 1941;

        Usuarie u = null;
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
    private static TreeMap <String, Usuarie> usuaries = listarUsuaries();
    private static TreeMap <String, Usuarie> listarUsuaries() {
        try {
            return UsuarieADFactory
                .getAccessUsuaries()
                    .listarUsuaries();
        } catch (GestorExcepciones ge) {
            System.out.println(ge.getFullMsg());
            return new TreeMap <String, Usuarie> ();
        }
    }
    /**Un método especial para ahorrar toda la sentencia.
     * Usando la factoría, se busca en la base de datos.
     * @param pCodUsr
     */
    private static Usuarie buscar(String pCodUsr) {
        try {
            return UsuarieADFactory
                .getAccessUsuaries()
                    .buscarUsuarie(pCodUsr);
        } catch (GestorExcepciones ge) {
            System.out.println(ge.getFullMsg());
            return null;
        }
    }
    /**Se añade un usuario de prueba (Captain Beefheart.)
     * Después se busca y se muestra por pantalla.
     */
    @Test
    @Order (order = 0)
    public void testBuscarUsuarie() {
        // se genera un código aleatorio de un usuarie existente.
        String pCodRandom = generateRandomUserCode();
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
    /**Se usa un usuarie nuevo. Se instancia (de un tipo aleatorio)
     * y después se graba en la base de datos para despues comprobar 
     * que, efectivamente
     * @throws GestorExcepciones
     * 
     */
    @Test
    @Order (order = 2)
    public void testAddUsuarie() throws GestorExcepciones {
        usuarie = null;
        usuarie = instanceUsuarie();
        // añadir a la base de datos.
        UsuarieADFactory
            .getAccessUsuaries()
                .addUsuarie(usuarie);
        // comprobar los cambios.
        assertNotNull(buscar(pCodUsr));
        assertEquals(buscar(pCodUsr), usuarie);
    }
    /**Se usa el usuarie creado antes para modificarlo
     * y testar los cambios.
     * @throws GestorExcepciones
     */
    @Test
    @Order (order = 3)
    public void testModificarUsuarie() throws GestorExcepciones {
        usuarie = buscar(pCodUsr);
        assertNotNull(usuarie);
        usuarie.setNombre("Frank Vincent");
        usuarie.setApellido("Zappa");
        usuarie.setPasswd("59' Chevy");

        assertNotEquals(usuarie, buscar(pCodUsr));
    
        UsuarieADFactory
            .getAccessUsuaries()
                .modificarUsuarie(usuarie);

        assertEquals(buscar(pCodUsr), usuarie);
    }
    /**Se comprueba que el borrado de usuarie se hace 
     * de forma efectiva, usando el mismo objeto de antes.
     * @throws GestorExcepciones
     */
    @Test
    @Order (order = 4)
    public void testDeleteUsuarie() throws GestorExcepciones {
        usuarie = buscar(pCodUsr);
        assertNotNull(usuarie);
        
        UsuarieADFactory
            .getAccessUsuaries()
                .borrarUsuarie(pCodUsr);
        
        assertNull(buscar(pCodUsr));
    } 
    /**Se comprueba que la lista de usauries generada es correcta,
     * y que los códigos del KeySet corresponden a los de los 
     * objetos.
     * @throws GestorExcepciones
     */
    @Test
    public void testListarUsuaries() throws GestorExcepciones {
        int total = UsuarieADFactory
            .getAccessUsuaries()
                .totalUsuaries();
        // la cantidad total de elementos en la lista.
        assertEquals(
            total,
            usuaries.size()
        );
        /**Se comprueba que todas las claves
         * son las de los usuaries.
         */
        assertEquals(
            total,
            usuaries.values().stream()
            .filter(u -> usuaries.keySet().contains(u.getCodUsr()))
            .count());
    }
}
