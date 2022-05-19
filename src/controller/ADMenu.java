package controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.TreeMap;

import controller.interfaces.Menuable;
import datos.Menu;
import exceptions.GestorExcepciones;

public class ADMenu extends MasterConnection implements Menuable {

    @Override
    public void grabarMenu(Menu pMenu) throws GestorExcepciones {
        try {
            openConnection();
            stmt = con.prepareStatement(insertar);
            stmt.setString(1, pMenu.getCodMnu());
            stmt.setString(2, pMenu.getCodDsc());
            stmt.setFloat(3, pMenu.getPrecio());
            stmt.setString(4, pMenu.getNombre());
                stmt.executeUpdate();

            // meter datos en menu_producto
            for (int i = 0; i < 3; i++) {
                stmt = con.prepareStatement(insertarNM);
                stmt.setString(1, pMenu.getCodMnu());
                stmt.setString(2, pMenu.getCodPrds()[i]);
                    stmt.executeUpdate();
            }
        } catch (SQLException | GestorExcepciones e) {
            throw new GestorExcepciones(3);
        } finally {
            closeConnection();
        }
    }

    @Override
    public void borrarMenu(Menu pMenu) throws GestorExcepciones {
        try {
            openConnection();
            stmt = con.prepareStatement(borrar);
            stmt.setString(1, pMenu.getCodMnu());
                stmt.executeUpdate();

            for (int i = 0; i < 3; i++) {
                stmt = con.prepareStatement(borrarNM);
                stmt.setString(1, pMenu.getCodMnu());
                stmt.setString(2, pMenu.getCodPrds()[i]);
            }
        } catch (SQLException | GestorExcepciones e) {
            throw new GestorExcepciones(3);
        } finally {
            closeConnection();
        }
    }

    @Override
    public void modificarMenu(Menu pMenu) throws GestorExcepciones {
        try {
            openConnection();
            stmt = con.prepareStatement(modificar);
            stmt.setString(1, pMenu.getCodDsc());
            stmt.setFloat(2, pMenu.getPrecio());
            stmt.setString(3, pMenu.getNombre());
            stmt.setString(4, pMenu.getCodMnu());
                stmt.executeUpdate();

            Menu pMenuAux = 
                buscarMenuPorCodigo(pMenu.getCodMnu());

            for (int i = 0; i < 3; i++) {
                stmt = con.prepareStatement(modificarNM);
                stmt.setString(1, pMenu.getCodPrds()[i]);
                stmt.setString(2, pMenu.getCodMnu());
                stmt.setString(3, pMenuAux.getCodMnu());
                stmt.setString(4, pMenuAux.getCodPrds()[i]);
                    stmt.executeUpdate();
            }    
        } catch (SQLException | GestorExcepciones e) {
            throw new GestorExcepciones(3);
        } finally {
            closeConnection();
        }
    }

    @Override
    public Menu buscarMenuPorCodigo(String pCodMnu) throws GestorExcepciones {
        Menu pMenu = null;
        String [] pCodPrds = getCodigosProductos(pCodMnu);
        try {
            openConnection();
            stmt = con.prepareStatement(buscar);
            stmt.setString(1, pCodMnu);
                rs = stmt.executeQuery();
            rs.next();

            pMenu = 
                new Menu(
                rs.getString(1),
                rs.getString(2),
                pCodPrds,
                rs.getFloat(3),
                rs.getString(4));
        } catch (SQLException | GestorExcepciones e) {
            throw new GestorExcepciones(3);
        } finally {
            closeConnection();
        }
        return pMenu;
    }

    @Override
    public String [] getCodigosProductos(Menu pMenu) throws GestorExcepciones {
        return getCodigosProductos(pMenu.getCodMnu());
    }

    private String [] getCodigosProductos(String pCodMnu) throws GestorExcepciones {
        String [] codigosProductos = 
            new String [3];

        ResultSet rs2 = null;
        /**Se crea un nuevo resultset para no sobreescribir el valor
         * del original. Dado que se va llamar a este método desde otro
         * que accede a datos, el valor contenido en el ResultSet común
         * a toda la clase podría ser sobreescrito, y para evitar esto
         * se utiliza otro.
         */
        try {
            openConnection();
            stmt = con.prepareStatement(buscarCodigosProductos);
            stmt.setString(1, pCodMnu);
                rs2 = stmt.executeQuery();

            for (int i = 0; i < codigosProductos.length && rs2.next(); i++) 
                codigosProductos[i] = rs2.getString(1);

        } catch (SQLException | GestorExcepciones e) {
            throw new GestorExcepciones(3);
        } finally {
            closeConnection();
        }
        return codigosProductos;
    }

    @Override
    public TreeMap <String, Menu> listarMenus() throws GestorExcepciones {
        Menu pMenu = null;
        
        String [] pCodPrds =
            new String [5];

        TreeMap <String, Menu> pProductos = 
            new TreeMap <String, Menu> ();
        try {
            openConnection();
            stmt = con.prepareStatement(listarTodo);
                rs = stmt.executeQuery();
            while (rs.next()) {

                pCodPrds = 
                    getCodigosProductos(rs.getString(1));
    
                    pMenu = 
                        new Menu(
                        rs.getString(1),
                        rs.getString(2),
                        pCodPrds,
                        rs.getFloat(3),
                        rs.getString(4));

                pProductos.put(pMenu.getCodMnu(), pMenu);
            }
        } catch (SQLException | GestorExcepciones e) {
            throw new GestorExcepciones(3);
        } finally {
            closeConnection();
        }
        return pProductos;
    }

	@Override
	public String generateCodigo() throws GestorExcepciones {
		String pCodMnu = "ME";
        String numMnu = String.valueOf(totalMenus() + 1);
        for (int i = 0; i < 8 - numMnu.length(); i++)
            pCodMnu += "0";

        pCodMnu += numMnu;

		return pCodMnu;
	}

    @Override
    public int totalMenus() throws GestorExcepciones {
        return cantidadTotal("menu");
    }
    
    private final String insertar = "INSERT INTO menu VALUES (?, ?, ?, ?)";
    private final String insertarNM = "INSERT INTO menu_producto VALUES (?, ?)";
    private final String borrar = "DELETE FROM menu WHERE codMnu = ?";
    private final String borrarNM = "DELETE FROM menu WHERE codMnu = ?";
    private final String modificar = "UPDATE menu SET codDsc = ?, precio = ?, nombre = ? WHERE codMnu = ?";
    private final String modificarNM = "UPDATE MENU_PRODUCTO SET CodPrd = ?, CodMnu = ? WHERE CodMnu = ? && CodPrd = ?";
    private final String buscarCodigosProductos = "SELECT codPrd FROM menu_producto WHERE codMnu = ?";
    private final String buscar = "SELECT * FROM menu WHERE CodMnu = ?";
    private final String listarTodo = "SELECT * FROM menu";

}
