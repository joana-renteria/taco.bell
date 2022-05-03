package controller;

import java.sql.SQLException;
import java.util.ArrayList;
import controller.interfaces.Productable;
import datos.Producto;
import resources.Util;

public class ADProducto extends MasterConnection implements Productable {

    private final String insertar = "INSERT INTO producto VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private final String borrar = "DELETE FROM producto WHERE codPrd = ?";
    private final String modificar = "UPDATE producto SET precio = ?, nombre = ?, ingrediente1 = ?, ingrediente2 = ?, ingrediente3 = ?, ingrediente4 = ?, ingrediente5 = ?, tipo = ? WHERE codPrd = ?";
    private final String buscar = "SELECT * FROM producto WHERE CodPrd = ?";
    private final String listar = "SELECT * FROM producto";
    private final String listarTipo = "SELECT * FROM producto WHERE tipo = ?";

    @Override
    public void crearProducto() {
        String pCodPrd = "PR";
        String numPrd = String.valueOf(listarProducto().size()+1);
        for (int i = 0; i < 8 - numPrd.length(); i++)
            pCodPrd += "0";

        pCodPrd += numPrd;

        // TODO Leer a traves de la ventana (factorías para aislar) los valores que introduce el usuarie.
        float pPrecio = Util.leerFloat();
        String pNombre = Util.introducirCadena();
        String[] pIngredientes = new String[5];
        for (int i = 0; i < 5; i++) {
            pIngredientes[i] = Util.introducirCadena();
        }
        String pTipo = Util.introducirCadena();

        openConnection();

        try {
            stmt = con.prepareStatement(insertar);
            stmt.setString(1, pCodPrd);
            stmt.setFloat(2, pPrecio);
            stmt.setString(3, pNombre);
            stmt.setString(4, pIngredientes[1]);
            stmt.setString(5, pIngredientes[2]);
            stmt.setString(6, pIngredientes[3]);
            stmt.setString(7, pIngredientes[4]);
            stmt.setString(8, pIngredientes[5]);
            stmt.setString(9, pTipo);
        } catch (SQLException e) {
            // TODO tratar excepción.
        }

        closeConnection();
    }

    @Override
    public void borrarProducto(String pCodPrd) {
        openConnection();
        
            try {
                stmt = con.prepareStatement(borrar);
                stmt.setString(1, pCodPrd);
                stmt.executeUpdate();
            } catch (SQLException e) {
                // TODO tratar excepción.
            }

        closeConnection();
    }

    @Override
    public void modificarProducto(Producto pProducto) {
        openConnection();

        try {
            stmt = con.prepareStatement(modificar);
            stmt.setFloat(1, pProducto.getPrecio());
            stmt.setString(2, pProducto.getNombre());
            for (int i = 0; i < 5; i++) {
                stmt.setString(3+i, pProducto.getIngredientes()[i]);
            }
            stmt.setString(8, pProducto.getTipo());
            stmt.executeUpdate();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        closeConnection();
    }

    @Override
    public Producto buscarProducto(String pCodPrd) {
        openConnection();
        String[] pIngredientes = new String[5];
        Producto pProducto = new Producto(pCodPrd);
        try {
            stmt = con.prepareStatement(buscar);
            stmt.setString(1, pCodPrd);
            rs = stmt.executeQuery();
            rs.next();
            for (int i = 0; i < 5; i++) {
                pIngredientes[i] = rs.getString(4+i);
            }
            pProducto = new Producto(
                rs.getString(1),
                rs.getFloat(2),
                rs.getString(3),
                pIngredientes,
                rs.getString(9)
            );
        } catch (SQLException e) {
            // TODO tratar la excepción.
        }

        closeConnection();
        return pProducto;
    }

    @Override
    public ArrayList<Producto> listarProducto() {
        ArrayList<Producto> pListaProducto = new ArrayList<Producto>();
        String[] pIngredientes = new String[5];
        openConnection();
        
        try {
            stmt = con.prepareStatement(listar);
            rs = stmt.executeQuery();
            for (int i = 0; i < 5; i++) {
                pIngredientes[i] = rs.getString(4+i);
            } 
            while(rs.next()) {
                pListaProducto.add(new Producto(
                    rs.getString(1),
                    rs.getFloat(2),
                    rs.getString(3),
                    pIngredientes,
                    rs.getString(9)
                ));
            }
        } catch (SQLException e) {
            // TODO tratar la excepción.
        }

        closeConnection();
        return pListaProducto;
    }

    @Override
    public ArrayList<Producto> listarProducto(String pTipo) {
        ArrayList<Producto> pListaProducto = new ArrayList<Producto>();
        String[] pIngredientes = new String[5];
        openConnection();
        
        try {
            stmt = con.prepareStatement(listarTipo);
            stmt.setString(1, pTipo);
            rs = stmt.executeQuery();
            for (int i = 0; i < 5; i++) {
                pIngredientes[i] = rs.getString(4+i);
            } 
            while(rs.next()) {
                pListaProducto.add(new Producto(
                    rs.getString(1),
                    rs.getFloat(2),
                    rs.getString(3),
                    pIngredientes,
                    rs.getString(9)
                ));
            }
        } catch (SQLException e) {
            // TODO tratar la excepción.
        }

        closeConnection();
        return pListaProducto;
    }
    
}
