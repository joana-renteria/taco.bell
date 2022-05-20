package controller;

import java.sql.SQLException;
import java.util.Optional;
import java.util.Set;
import java.util.TreeMap;

import controller.interfaces.Productable;
import datos.Producto;
import exceptions.GestorExcepciones;

public class ADProducto extends MasterConnection implements Productable {

    @Override
    public void grabarProducto(Producto pProducto) throws GestorExcepciones {
        try {
            openConnection();
            stmt = con.prepareStatement(insertar);
            stmt.setString(1, pProducto.getCodPrd());
            stmt.setFloat(2, pProducto.getPrecio());
            stmt.setString(3, pProducto.getNombre());

            for (int i = 0; i < 5; i++) 
                stmt.setString(i + 4, pProducto.getIngredientes()[i]);
                
            stmt.setString(9, pProducto.getTipo());
                stmt.executeUpdate();
        } catch (SQLException | GestorExcepciones e) {
            throw new GestorExcepciones(3);
        } finally {
            closeConnection();
        }
    }

    @Override
    public void borrarProducto(String pCodPrd) throws GestorExcepciones {
        try {
            openConnection();
            stmt = con.prepareStatement(borrar);
            stmt.setString(1, pCodPrd);
                stmt.executeUpdate();
        } catch (SQLException | GestorExcepciones e) {
            throw new GestorExcepciones(3);
        } finally {
            closeConnection();
        }
    }

    @Override
    public void modificarProducto(Producto pProducto) throws GestorExcepciones {
        try {
            openConnection();
            stmt = con.prepareStatement(modificar);
            stmt.setString(1, pProducto.getCodPrd());
            stmt.setFloat(2, pProducto.getPrecio());
            stmt.setString(3, pProducto.getNombre());
            stmt.setString(4, pProducto.getIngredientes()[0]);
            stmt.setString(5, pProducto.getIngredientes()[1]);
            stmt.setString(6, pProducto.getIngredientes()[2]);
            stmt.setString(7, pProducto.getIngredientes()[3]);
            stmt.setString(8, pProducto.getIngredientes()[4]);
            stmt.setString(9, pProducto.getTipo());
            stmt.setString(10, pProducto.getCodPrd());
            stmt.executeUpdate();
        } catch (SQLException | GestorExcepciones e) {
            throw new GestorExcepciones(3);
        } finally {
            closeConnection();
        }
    }

    @Override
    public Producto buscarProductoPorCodigo(String pCodPrd) throws GestorExcepciones {
        Producto pProducto = null;
        try {
            openConnection();
            stmt = con.prepareStatement(buscar);
            stmt.setString(1, pCodPrd);
                rs = stmt.executeQuery();
            rs.next();

            String [] pIngredientes = 
                new String [5];

            for (int i = 0; i < 5; i++) 
                pIngredientes[i] = rs.getString(i + 4);
    
            pProducto = 
                new Producto(
                rs.getString(1),
                rs.getFloat(2),
                rs.getString(3),
                pIngredientes,
                rs.getString(9));

        } catch (SQLException | GestorExcepciones e) {
            throw new GestorExcepciones(3);
        } finally {
            closeConnection();
        }
        return pProducto;
    }

    @Override
    public TreeMap <String, Producto> listarProductos() throws GestorExcepciones {
        Producto pProducto = null;
        String [] pIngredientes =
            new String [5];

        TreeMap <String, Producto> pProductos = 
            new TreeMap <String, Producto> ();
        try {
            openConnection();
            stmt = con.prepareStatement(listarTodo);
                rs = stmt.executeQuery();
            while (rs.next()) {

                for (int i = 0; i < 5; i++) 
                    pIngredientes[i] = rs.getString(i + 4);
    
                pProducto = 
                    new Producto(
                    rs.getString(1),
                    rs.getFloat(2),
                    rs.getString(3),
                    pIngredientes,
                    rs.getString(9));
                pProductos.put(pProducto.getCodPrd(), pProducto);
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
        Set <String> keys = listarProductos().keySet();

        Optional <String> lastKey = 
            keys.stream()
            .max((k1, k2) -> 
                k1.substring(2)
                .compareTo(k2.substring(2)));

        int k = 0;

        if (lastKey.isPresent())
            k = Integer.parseInt(lastKey.get().substring(2));

        String pCodDsc = "PR";
        String numDsc = String.valueOf(k + 1);
        for (int i = 0; i < 8 - numDsc.length(); i++)
            pCodDsc += "0";

        pCodDsc += numDsc;

        return pCodDsc;
    }

    @Override
    public int totalProductos() throws GestorExcepciones {
        return cantidadTotal("producto");
    }
    
    private final String insertar = 
        "INSERT INTO producto VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private final String borrar = 
        "DELETE FROM producto WHERE codPrd = ?";

    private final String modificar = 
        "UPDATE producto SET codPrd = ?, precio = ?, nombre = ?, " +
        "ingrediente1 = ?, ingrediente2 = ?, ingrediente3 = ?,  " + 
        "ingrediente4 = ?, ingrediente5 = ?, tipo = ? WHERE codPrd = ?";

    private final String listarTodo = "SELECT * FROM producto";
    private final String buscar = "SELECT * FROM producto WHERE codPrd = ?";
}
