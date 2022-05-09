package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;

import org.junit.Test;

import controller.factorias.DescuentoADFactory;
import controller.factorias.MenuADFactory;
import datos.Menu;

public class TestsADMenu {
    // Los tests se ejecutan a través de la factoría.
    @Test
    public void testListarMenu() {
        ArrayList <Menu> menus =
            MenuADFactory
                .getAccessMenu()
                    .listarMenus();
        // comprobar que la lista se ha creado debidamente.
        assertNotNull(menus);

        for (Menu pointer : menus) 
            System.out.println(pointer.toString()); 
    }
   
    @Test
    public void testCrearMenu() {
        // crear un establecimiento con datos.
        String[] ing =  {"PR00000001","PR00000002","PR00000003"};
        String pCodMnu = MenuADFactory.getAccessMenu().generateCodigo();
        Menu pMenu = 
            new Menu(
                pCodMnu,
                DescuentoADFactory.getAccessDescuento().buscarDescuentoPorCodigo("DE00000001").getCodDsc(),
                ing,
                (float)2.99,
                "Menu auxiliar");
                System.out.println(pMenu.toString());
        // grabado en la base de datos.
        MenuADFactory
            .getAccessMenu()
                .grabarMenu(pMenu);
        // comprobar que contiene el valor.
        Menu buscar = 
            MenuADFactory
                .getAccessMenu() // busqueda de un código.
                    .buscarMenu(pCodMnu);
                // deberia ser el mismo objeto al compararlos.
        assertEquals(pMenu.compareTo(buscar), 0);
        MenuADFactory
            .getAccessMenu()
                .borrarMenu(pMenu);
    }
    @Test
    public void testDeleteMenu() {
        // crear un establecimiento con datos.
        String[] ing =  {"PR00000001","PR00000002","PR00000003"};
        String pCodMnu = MenuADFactory.getAccessMenu().generateCodigo();
        Menu pMenu = 
            new Menu(
                pCodMnu,
                DescuentoADFactory.getAccessDescuento().buscarDescuentoPorCodigo("DE00000001").getCodDsc(),
                ing,
                (float)2.99,
                "Menu auxiliar");
                System.out.println(pMenu.toString());
        // grabado en la base de datos.
        MenuADFactory
            .getAccessMenu()
                .grabarMenu(pMenu);
        // comprobar que contiene el valor.
        MenuADFactory
            .getAccessMenu()
                .borrarMenu(pMenu);

        assertNull(MenuADFactory
            .getAccessMenu()
                .buscarMenu(pCodMnu));
    }
}
