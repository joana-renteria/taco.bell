package tests;

import org.junit.Test;
import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.TreeMap;

import org.junit.runner.RunWith;

import datos.Pedido;
import exceptions.GestorExcepciones;
import controller.factorias.MenuADFactory;
import controller.factorias.PedidoADFactory;

@RunWith(OrderedRunner.class)
public class TestsADPedido {
    // conservar el código del Pedido creado.
    private final static String pCodPed = generateCodigo();
    private static String generateCodigo() {
        try {
            return PedidoADFactory
                .getAccessPedido()
                    .generateCodigo();
        } catch (GestorExcepciones ge) {
            System.out.println(ge.getFullMsg());
            return "PE00000000";
        }
    }
    // pedido auxiliar.
    private Pedido pedido = null;
    /**Genera un TreeMap con todos los pedidos de la tabla.
     * Cada vez que se llama por primera vez en un test
     * se genera de nuevo, por lo que siempre
     * se encuentra actualizada.
     */
    private TreeMap <String, Pedido> pedidos = listarPedidos();
         
    private TreeMap <String, Pedido> listarPedidos() {
        try {
            return PedidoADFactory
            .getAccessPedido()
                .listarPedidos();
        } catch (GestorExcepciones ge) {
            System.out.println(ge.getFullMsg());
            return new TreeMap <String, Pedido> ();
        }
    }
    /**Método para buscar más cómodamente y ahorrar 
     * la sentencia. Busca por código,
     * @param pCodPed
     */
    public Pedido buscar(String pCodPed) {
        try {
            return PedidoADFactory
                .getAccessPedido()
                    .buscarPorCodigo(pCodPed);
        } catch (GestorExcepciones ge) {
            System.out.println(ge.getFullMsg());
            return null;
        }
    }
    /**Se comprueba que la búsqueda de un producto elegido
     * de forma aleatoria funciona de la forma esperada.
     * @throws GestorExcepciones
     */
    @Test
    @Order (order = 0)
    public void testBuscarPedido() throws GestorExcepciones {
        // se calcula el número total de productos.
        int maxPedidos = Integer.parseInt(
            pCodPed.substring(2)),
            cont = 0;
        // se genera un código aleatorio.
        String codPedRandom = null;

        for (int i = 1; i <= maxPedidos; i++) {
            codPedRandom = "PE000000";
            if (i < 10) 
                codPedRandom += "0" + i;
            else
                codPedRandom += i;
            
            cont = (buscar(codPedRandom) != null) ? 
                cont + 1 :
                cont;
        }
        // se comprueba que se genera el producto correcto.
        assertEquals(
            cont,
            PedidoADFactory
                .getAccessPedido()
                    .totalPedidos());
    }
   /**Se comprueba el método equals
 * @throws GestorExcepciones
    * @see Pedido equals()
    */
    @Test
    @Order (order = 1)
    public void testEqualsPedido() throws GestorExcepciones {
        /**Los códigos del cliente, el establecimiento y 
         * el repartidor deben existir en la base de datos.
         */
        pedido =
            new Pedido(
            pCodPed,
            LocalDate.now(),
            "CL00004",
            "RE00003",
            "ES00001",
            MenuADFactory
                .getAccessMenu()
                    .buscarMenuPorCodigo("ME00000001"));
        Pedido pedido2 = 
            new Pedido(
            pCodPed,
            LocalDate.now(),
            "CL00004",
            "RE00003",
            "ES00001",
            MenuADFactory
                .getAccessMenu()
                    .buscarMenuPorCodigo("ME00000001"));
            // se observa que la comparación devuelve true.
        assertEquals(pedido, pedido2);
        assertTrue(pedido.equals(pedido2));
    }
    /**Se testa el grabado en la base de datos.
     * @throws GestorExcepciones
     */
    @Test
    @Order (order = 2)
    public void testAddPedido() throws GestorExcepciones {
        // crear un pedido con datos.
        pedido = 
            new Pedido(
            pCodPed,
            LocalDate.now(),
            "CL00004",
            "RE00007",
            "ES00001",
            MenuADFactory
                .getAccessMenu()
                    .buscarMenuPorCodigo("ME00000001"));
        // grabado en la base de datos.
        PedidoADFactory
            .getAccessPedido()
                .grabarPedido(pedido);
        // comprobar que contiene el valor.
       assertEquals(buscar(pCodPed), pedido);
    }
    /**Se usa el pedido anterior, y se modifica en la base
     * de datos, para después comprobar que ha cambiado.
     * @throws GestorExcepciones
     */
    @Test
    @Order (order = 3)
    public void testModificarPedido() throws GestorExcepciones {
        pedido = buscar(pCodPed);
        
        assertNotNull(pedido);
        assertEquals(pedido.getCodPed(), pCodPed);

        pedido.setFechaPed(
            LocalDate.of(1969, 8, 15));

        PedidoADFactory
            .getAccessPedido()
                .modificarPedido(pedido);
        // el pedido ha cambiado en la tabla.
        assertEquals(pedido, buscar(pCodPed));
    }
    @Test
    @Order (order = 4)
    public void testDeletePedido() throws GestorExcepciones {
        pedido = buscar(pCodPed);
        
        assertNotNull(pedido);
        assertEquals(pedido, buscar(pCodPed));
        // conservar la cantidad total de pedidos.
        int total = PedidoADFactory
            .getAccessPedido()
                .totalPedidos();

        PedidoADFactory
            .getAccessPedido()
                .borrarPedido(pCodPed);

        assertNull(buscar(pCodPed));
        // ahora debe haber un pedido menos.
        assertEquals(
            total - 1,
            PedidoADFactory
                .getAccessPedido()
                    .totalPedidos());
    }
    /**Se comprueba que se genera el TreeMap de forma correcta,
     * y que se incluyen todos los elementos, con el par de 
     * objeto y código.
     * @throws GestorExcepciones
     */
    @Test
    @Order (order = 5)
    public void testListarPedidos() throws GestorExcepciones {
        int total = PedidoADFactory
            .getAccessPedido()
                .totalPedidos();
        // la cantidad total de elementos.
        assertEquals(
            total,
            pedidos.size());
        /**Se comprueba que todas las claves son 
         * las de los menús.
         */
        assertEquals(
            total,
            pedidos.values().stream()
            .filter(p -> pedidos.keySet().contains(p.getCodPed()))
            .count());
    }
}
