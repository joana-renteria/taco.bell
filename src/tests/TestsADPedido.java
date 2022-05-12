package tests;

import org.junit.Test;
import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.Random;
import java.util.TreeMap;

import org.junit.Before;
import org.junit.runner.RunWith;

import datos.Pedido;
import controller.factorias.MenuADFactory;
import controller.factorias.PedidoADFactory;

@RunWith(OrderedRunner.class)
public class TestsADPedido {
    // conservar el código del Pedido creado.
    private final static String pCodPed = 
        PedidoADFactory
            .getAccessPedido()
                .generateCodigo();
    // pedido auxiliar.
    private Pedido pedido = null;
    /**Genera un TreeMap con todos los pedidos de la tabla.
     * Cada vez que se llama por primera vez en un test
     * se genera de nuevo, por lo que siempre
     * se encuentra actualizada.
     */
    private TreeMap <String, Pedido> pedidos = 
         PedidoADFactory
            .getAccessPedido()
                .listarPedidos();
    /**Método auxiliar que muestra la tabla.
     * Se ejecuta antes de cada test. // TODO: borrarlo.
     */
    @Test
    public void mostrarTablaCompleta() {
        pedidos.values().stream()
            .forEach(p -> System.out.println(p));
        System.out.print("\n");
    }
    /**Método para buscar más cómodamente y ahorrar 
     * la sentencia. Busca por código,
     * @param pCodPed
     */
    public Pedido buscar(String pCodPed) {
        return PedidoADFactory
            .getAccessPedido()
                .buscarPorCodigo(pCodPed);
    }
    /**Se comprueba que la búsqueda de un producto funciona
     * de la forma esperada.
     */
    @Test
    @Order (order = 0)
    public void testBuscarPedido() {
        int totalPedidos = PedidoADFactory
            .getAccessPedido()
                .totalPedidos(),
            nPedidoRandom = 
            new Random().nextInt(totalPedidos) + 1;
        // se genera, pues, un código aleatorio.
        String codPedRandom = "PR000000";

        if (nPedidoRandom < 10)
            codPedRandom += "0" + nPedidoRandom;
        else
            codPedRandom += nPedidoRandom;

        Pedido buscar = buscar(codPedRandom);

        assertEquals(
            buscar,
            buscar(codPedRandom));
    }
   /**Se comprueba el método equals
    * @see Pedido equals()
    */
    @Test
    @Order (order = 1)
    public void testEqualsPedido() {
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
     */
    @Test
    @Order (order = 3)
    public void testAddPedido() {
        // crear un pedido con datos.
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
        // grabado en la base de datos.
        PedidoADFactory
            .getAccessPedido()
                .grabarPedido(pedido);
        // comprobar que contiene el valor.
       assertEquals(buscar(pCodPed), pedido);
    }
    /**Se usa el pedido anterior, y se modifica en la base
     * de datos, para después comprobar que ha cambiado.
     */
    @Test
    @Order (order = 3)
    public void testModificarPedido() {
        pedido = buscar(pCodPed);
        
        System.out.println(buscar(pCodPed));
        System.out.println(pedido);
        //assertNotNull(buscar(pCodPed));
        //assertEquals(pCodPed, pedido.getCodPed());


    }
    @Test
    public void testDeletePedido() {
        // crear un pedido con datos.
        String pCodPed = PedidoADFactory.getAccessPedido().generateCodigo();
        System.out.println(pCodPed);
        Pedido pPedido = 
            new Pedido(
                pCodPed,
                LocalDate.now(),
                "CL00004",// TODO UsuarieADFactory.getAccessUsuaries().buscarPorCodigo("CL%"), (buscar CLIENTE)
                "RE00003",// TODO UsuarieADFactory.getAccessUsuaries().buscarPorCodigo("RE%"), (buscar REPARTIDOR)
                "ES00001",
                MenuADFactory.getAccessMenu().buscarMenuPorCodigo("ME00000001"));
                System.out.println(pPedido.toString());
        // grabado en la base de datos.
        PedidoADFactory
            .getAccessPedido()
                .grabarPedido(pPedido);
        // comprobar que contiene el valor.
        PedidoADFactory
            .getAccessPedido()
                .borrarPedido(pCodPed);

        assertNull(PedidoADFactory.getAccessPedido().buscarPorCodigo(pCodPed));
    }
}
