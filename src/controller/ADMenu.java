package controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.Set;
import java.util.TreeMap;

import controller.interfaces.Menuable;
import datos.Menu;
import exceptions.GestorExcepciones;

public class ADMenu extends MasterConnection implements Menuable {

    @Override
    /**
     * guarda un menu en la base de datos
     * @param pMenu el menu con sus datos
     * @throws GestorExcepciones
     */
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

    /**
     * borra un menu en la base de datos
     * @param pMenu el menu ha ser borrado
     * @throws GestorExcepciones
     */
    @Override
    public void borrarMenu(Menu pMenu) throws GestorExcepciones {
        try {
            openConnection();
            stmt = con.prepareStatement(borrar);
            stmt.setString(1, pMenu.getCodMnu());
                stmt.executeUpdate();

        } catch (SQLException | GestorExcepciones e) {
            e.printStackTrace();
            throw new GestorExcepciones(3);
        } finally {
            closeConnection();
        }
    }

    /**
     * modifica un menu en la base de datos
     * @param pMenu el menu con sus datos actualizados
     * @throws GestorExcepciones
     */
    @Override
    public void modificarMenu(Menu pMenu) throws GestorExcepciones {
        Menu pMenuAux = 
                buscarMenuPorCodigo(pMenu.getCodMnu());
        try {
            openConnection();
            stmt = con.prepareStatement(modificar);
            stmt.setString(1, pMenu.getCodDsc());
            stmt.setFloat(2, pMenu.getPrecio());
            stmt.setString(3, pMenu.getNombre());
            stmt.setString(4, pMenu.getCodMnu());
                stmt.executeUpdate();

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

    /**
     * devuelve un menu segun su codigo
     * @param pCodMenu el codigo del menu
     * @throws GestorExcepciones
     */
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

    /**
     * Devuelve los codigos de los productos de un menu
     * @param pMenu el objeto menu
     * @return String[] los distintos codigos de productos
     * @throws GestorExcepciones
     */
    @Override
    public String [] getCodigosProductos(Menu pMenu) throws GestorExcepciones {
        return getCodigosProductos(pMenu.getCodMnu());
    }

    /**
     * Devuelve los codigos de los productos de un menu
     * @param pCodMnu el codigo del menu
     * @return String[] los distintos codigos de productos
     * @throws GestorExcepciones
     */
    private String [] getCodigosProductos(String pCodMnu) throws GestorExcepciones {
        String [] codigosProductos = 
            new String [3];

        ResultSet rs2 = null;
        /*Se crea un nuevo resultset para no sobreescribir el valor
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
        } catch(NullPointerException ne) {
            throw new GestorExcepciones(404);
        } finally {
            closeConnection();

        }
        return codigosProductos;
    }

    @Override
    /**
     * Lista todos los menus
     * @return TreeMap<String,Menu> el String es la clave primaria
     * @throws GestorExcepciones
     */
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
    /**
     * Genera codigo con su prefijo respectivo
     * @return String
     * @throws GestorExcepciones
     */
	public String generateCodigo() throws GestorExcepciones {
        Set <String> keys = listarMenus().keySet();

        Optional <String> lastKey = 
            keys.stream()
            .max((k1, k2) -> 
                k1.substring(2)
                .compareTo(k2.substring(2)));

        int k = 0;

        if (lastKey.isPresent())
            k = Integer.parseInt(lastKey.get().substring(2));
            
		String pCodMnu = "ME";
        String numMnu = String.valueOf(k + 1);
        for (int i = 0; i < 8 - numMnu.length(); i++)
            pCodMnu += "0";

        pCodMnu += numMnu;

		return pCodMnu;
	}

    @Override
    /**
     * Devuelve la cantidad total de menus
     * @return int
     * @throws GestorExcepciones
     */
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
