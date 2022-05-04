package controller;

import java.sql.SQLException;
import java.util.ArrayList;

import controller.interfaces.Menuable;
import datos.Menu;

public class ADMenu extends MasterConnection implements Menuable {

    @Override
    public void grabarMenu(Menu pMenu) {
        openConnection();

        try {
            stmt = con.prepareStatement(insertar);
            stmt.setString(1, pMenu.getCodMnu());
            stmt.setString(2, pMenu.getCodDsc());
            stmt.setFloat(3, pMenu.getPrecio());
            stmt.setString(4, pMenu.getNombre());
            // ejecución del comando.
            stmt.executeUpdate();

            // meter datos en menu_producto
            for (int i = 0; i < 3; i++) {
                stmt = con.prepareStatement(insertarNM);
                stmt.setString(1, pMenu.getCodMnu());
                stmt.setString(2, pMenu.getCodPrds()[i]);
                // ejecución del comando.
                stmt.executeUpdate();
            }

        } catch (SQLException e) {
            // TODO tratar excepción.
        }
        closeConnection();
    }

    @Override
    public void borrarMenu(Menu pMenu) {
        openConnection();

        try {
            stmt = con.prepareStatement(borrar);
            stmt.setString(1, pMenu.getCodMnu());

            // ejecución del comando.
            stmt.executeUpdate();

            for (int i = 0; i < 3; i++) {
                stmt = con.prepareStatement(borrarNM);
                stmt.setString(1, pMenu.getCodMnu());
                stmt.setString(2, pMenu.getCodPrds()[i]);
            }

        } catch (SQLException e) {
            // TODO tratar excepción.
        }

        closeConnection();
    }

    @Override
    public void modificarMenu(Menu pMenu) {
        openConnection();

        try {
            stmt = con.prepareStatement(modificar);
            stmt.setString(1, pMenu.getCodDsc());
            stmt.setFloat(2, pMenu.getPrecio());
            stmt.setString(3, pMenu.getNombre());
            stmt.setString(4, pMenu.getCodMnu());
            stmt.executeUpdate();

            Menu pMenuAux = buscarMenu(pMenu.getCodMnu());

            for (int i = 0; i < 3; i++) {
                stmt = con.prepareStatement(modificarNM);
                stmt.setString(1, pMenu.getCodPrds()[i]);
                stmt.setString(2, pMenu.getCodMnu());
                stmt.setString(3, pMenuAux.getCodMnu());
                stmt.setString(4, pMenuAux.getCodPrds()[i]);
                stmt.executeUpdate();
            }    
        } catch (SQLException e) {
            
        }

        closeConnection();
    }

    @Override
    public Menu buscarMenu(String pCodMnu) {
        openConnection();
        Menu mnuAux = null;
        String[] codPrdsAux = new String[3];
        // TODO agregar productos al array
        try {
            stmt = con.prepareStatement(buscar);
            stmt.setString(1, pCodMnu);
            rs = stmt.executeQuery();
            rs.next();
            mnuAux = new Menu(
                rs.getString(1),
                rs.getString(2),
                codPrdsAux,
                rs.getFloat(3),
                rs.getString(4));

        } catch (SQLException e) {

        }
        closeConnection();
        return mnuAux;
    }

    @Override
    public ArrayList<Menu> listarMenus() {
        ArrayList<Menu> pListaMenu = new ArrayList<Menu>();
        String[] codPrdsAux = new String[3];
        openConnection();
        // TODO agregar productos al array
        try {
            stmt = con.prepareStatement(listar);
            rs = stmt.executeQuery();
            while(rs.next())
                pListaMenu.add(new Menu(
                    rs.getString(1),
                    rs.getString(2),
                    codPrdsAux,
                    rs.getFloat(3),
                    rs.getString(4)
                ));
        } catch (SQLException e) {

        }

        closeConnection();
        return pListaMenu;
    }

	@Override
	public String generateCodigo() {
		String pCodMnu = "ME";
        String numMnu = String.valueOf(listarMenus().size()+1);
        for (int i = 0; i < 8 - numMnu.length(); i++)
            pCodMnu += "0";

        pCodMnu += numMnu;
		return pCodMnu;
	}
    
    private final String insertar = "INSERT INTO menu VALUES (?, ?, ?, ?)";
    private final String insertarNM = "INSERT INTO menu_producto VALUES (?, ?)";
    private final String borrar = "DELETE FROM menu WHERE codMnu = ?";
    private final String borrarNM = "DELETE FROM menu WHERE codMnu = ?";
    private final String modificar = "UPDATE menu SET codDsc = ?, precio = ?, nombre = ? WHERE codMnu = ?";
    private final String modificarNM = "UPDATE MENU_PRODUCTO SET CodPrd = ?, CodMnu = ? WHERE CodMnu = ? && CodPrd = ?";
    private final String buscar = "SELECT * FROM menu WHERE CodMnu = ?";
    private final String listar = "SELECT * FROM menu";
}
