package tests;

import org.junit.Test;

import static org.junit.Assert.*;
 
import java.util.Random;
import java.util.TreeMap;

import org.junit.Before;
import org.junit.runner.RunWith;

import users.*;
import controller.ADUsuarie;
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
            String codUsuarie = "000";
        /**Si se desea un código no existente, se devuelve el número
         * correspondiente al siguiente usuarie con un código 
         * aleatorio.
         */
        if (!is) {
            if (totalUsuaries + 1 < 10)
                codUsuarie += "0" + (totalUsuaries + 1);
            else
                codUsuarie += (totalUsuaries + 1);
            
            return type [(int) (Math.random()*4)] + codUsuarie;
        }
        /**Si se desea un codigo existente, se comprueba que este 
         * se genera un numero existente y después se busca 
         * el prefijo correspondiente, comprobando que existe.
         */
        else
            if (nUsuarieRandom < 10)
                codUsuarie += "0" + nUsuarieRandom;
            else
                codUsuarie += nUsuarieRandom;

        boolean salir = false;
        for (int i = 0; i < type.length && !salir; i++) 
            if (buscar(type[i] + codUsuarie) != null) {
                codUsuarie = type[i] + codUsuarie;
                salir = true;  
            }    

        return codUsuarie;
    }
    /**Se genera un codigo de usuarie no existente previamente.
     * Después se harán las pruebas de la base de datos con el.
     */
    private final static String pCodUsr = generateRandomUserCode(false);
        //generateRandomUserCode(false);
    
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

            default :
                System.out.println("Linea 116.");
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
    private static TreeMap <String, Usuarie> usuaries = 
        UsuarieADFactory
            .getAccessUsuaries()
                .listarUsuaries();
    /**Método auxiliar que muestra la tabla.
     * Se ejecuta antes de cada tests individual.
     * La tabla se muestra ordenada segun el número
     * de usuarie, se comparan los códigos.
     */
    @Before
    public void mostrarTablaCompleta() {
        usuaries.values().stream()
            .sorted((u1, u2) -> 
                u1.getCodUsr().substring(2).compareTo(
                u2.getCodUsr().substring(2)))
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
    /**Se usa un usuarie nuevo. Se instancia (de un tipo aleatorio)
     * y después se graba en la base de datos para despues comprobar 
     * que, efectivamente
     * 
     */
    @Test
    @Order (order = 1)
    public void testAddUsuarie() {
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
     */
    @Test
    @Order (order = 2)
    public void testModificarUsuarie() {
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
     */
    @Test
    public void testBorrarUsuarie() {
        usuarie = buscar(pCodUsr);
        assertNotNull(usuarie);
        
        UsuarieADFactory
            .getAccessUsuaries()
                .borrarUsuarie(pCodUsr);
        
        assertNull(buscar(pCodUsr));
    } 
}
