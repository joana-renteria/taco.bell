package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.Test;

import controller.factorias.MenuADFactory;
import controller.factorias.PedidoADFactory;
import datos.Pedido;

public class TestsADPedido {
    // Los tests se ejecutan a través de la factoría.
    @Test
    public void testListarPedido() {
        ArrayList <Pedido> pedidos =
            PedidoADFactory
                .getAccessPedido()
                    .listarPedidos();
        // comprobar que la lista se ha creado debidamente.
        assertNotNull(pedidos);

        for (Pedido pointer : pedidos) 
            System.out.println(pointer.toString()); 
    }
   
    @Test
    public void testCrearPedido() {
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
        Pedido buscar = 
            PedidoADFactory.getAccessPedido() // busqueda de un código.
                .buscarPorCodigo(pCodPed);
                // deberia ser el mismo objeto al compararlos.
        assertEquals(pPedido.compareTo(buscar), 0);
        PedidoADFactory
            .getAccessPedido()
                .borrarPedido(pCodPed);
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
