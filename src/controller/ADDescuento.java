package controller;

import java.sql.SQLException;
import java.util.Optional;
import java.util.Set;
import java.util.TreeMap;

import controller.interfaces.Descontable;
import datos.Descuento;
import exceptions.GestorExcepciones;

public class ADDescuento extends MasterConnection implements Descontable {
    /**Este método recibe como parámetro un objeto de tipo
     * descuento, y crea una sentencia SQL con los valores 
     * correspondientes a dicho objeto para después grabarlo.
     * @param pDescuento objeto de tipo descuento a grabar en la 
     * tabla.
     */
    @Override
    public void grabarDescuento(Descuento pDescuento) throws GestorExcepciones {
        try {
            openConnection();   
            stmt = con.prepareStatement(insertar);
            stmt.setString(1, pDescuento.getCodDsc());
            stmt.setFloat(2, pDescuento.getUsos());
            stmt.setFloat(3, pDescuento.getCantidadDsc());
            stmt.setObject(4, pDescuento.getFechaInicio());
            stmt.setObject(5, pDescuento.getFechaFin());
                stmt.executeUpdate();
        } catch (SQLException | GestorExcepciones e) {
            throw new GestorExcepciones(3);
        } finally {
            closeConnection();
        }
    }
    /**Este método recibe como parámetro un String y lo introduce
     * en una sentencia SQL que lo borra de la tabla.
     * @param pCodDsc descuento a borrar de la base de datos.
     */
    @Override
    public void borrarDescuento(String pCodDsc) throws GestorExcepciones {
        try {
            openConnection();
            stmt = con.prepareStatement(borrar);
            stmt.setString(1, pCodDsc);
                stmt.executeUpdate();
        } catch (SQLException | GestorExcepciones e) {
            throw new GestorExcepciones(3);
        } finally {
            closeConnection();
        }
    }
    /**Este método recibe como parámetro un Descuento, y prepara
     * una sentencia SQL para modificarlo de la base de datos.
     * TODO: en caso de no estar, se lanza una excepción.
     * @param pDescuento descuento que se modifica en las tablas.
     */
    @Override
    public void modificarDescuento(Descuento pDescuento) throws GestorExcepciones {
        try {
            openConnection();
            stmt = con.prepareStatement(modificar);
            stmt.setString(5, pDescuento.getCodDsc());
            stmt.setFloat(1, pDescuento.getUsos());
            stmt.setFloat(2, pDescuento.getCantidadDsc());
            stmt.setObject(3, pDescuento.getFechaInicio());
            stmt.setObject(4, pDescuento.getFechaFin());
                stmt.executeUpdate();
        } catch (GestorExcepciones | SQLException sqle) {
            throw new GestorExcepciones(3);
        } finally {
            closeConnection();
        }
    }
    /**Este método recibe como parámetro un codigo de descuento, 
     * y prepara una sentencia SQL que busca en la base de datos 
     * el descuento con dicho código.
     * @param pCodDsc codigo del descuento a buscar.
     * @return el descuento con el código correspondiente.
     * En caso de no encontrarlo, devuelve null.
    */
    @Override
    public Descuento buscarDescuentoPorCodigo(String pCodDsc) throws GestorExcepciones {
        Descuento pDescuento = null;
        try {
            openConnection();
            stmt = con.prepareStatement(buscar);
            stmt.setString(1, pCodDsc);
            rs = stmt.executeQuery();
            rs.next();
            pDescuento = 
                new Descuento(
                rs.getString(1),
                rs.getInt(2),
                rs.getFloat(3),
                rs.getDate(4).toLocalDate(),
                rs.getDate(5).toLocalDate());
        } catch (GestorExcepciones | SQLException sqle) {
            throw new GestorExcepciones(3);
        } finally {
            closeConnection();
        }
        return pDescuento;
    }
    /**Este método devuelve un TreeMap con todos los descuentos.
     * Se utiliza como clave el codigo de descuento. Esta lista
     * facilita la búsqueda de Descuentos a la hora de listarlos.
     * @return TreeMap con todos los descuentos de la base de datos.
     */
    @Override
    public TreeMap <String, Descuento> listarDescuentos() throws GestorExcepciones {
        Descuento pDescuento = null;
        TreeMap <String, Descuento> pListaDescuentos = 
            new TreeMap<String, Descuento> ();
        try {
            openConnection();
            stmt = con.prepareStatement(listarTodo);
            rs = stmt.executeQuery();
            while (rs.next()) {
                pDescuento = 
                    new Descuento(
                    rs.getString(1),
                    rs.getInt(2),
                    rs.getFloat(3),
                    rs.getDate(4).toLocalDate(),
                    rs.getDate(5).toLocalDate());
                pListaDescuentos.put(
                    pDescuento.getCodDsc(),
                    pDescuento);
            }

        } catch (GestorExcepciones | SQLException sqle) {
            throw new GestorExcepciones(3);
        } finally {
            closeConnection();
        }
        return pListaDescuentos;
    }
    /**Este método genera el código correspondiente al siguiente
     * descuento en la base de datos.
     * @return String correspondiente al siguiente código.
    */
    @Override
    public String generateCodigo() throws GestorExcepciones {
        Set <String> keys = listarDescuentos().keySet();

        Optional <String> lastKey = 
            keys.stream()
            .max((k1, k2) -> 
                k1.substring(2)
                .compareTo(k2.substring(2)));

        int k = 0;

        if (lastKey.isPresent())
            k = Integer.parseInt(lastKey.get().substring(2));

        String pCodDsc = "DE";
        String numDsc = String.valueOf(k + 1);
        for (int i = 0; i < 8 - numDsc.length(); i++)
            pCodDsc += "0";

        pCodDsc += numDsc;
        
        return pCodDsc;
    }
    /**@return un entero con la cantidad total de descuentos 
     * en la tabla de descuento. Usa cantidad total, implementado en 
     * la superclase. Se le envía como parámetro el nombre de la tabla.
     * @see MasterConnection
     */
    @Override
    public int totalDescuentos() throws GestorExcepciones {
        return cantidadTotal("descuento");
    }

    // sentencias sql preparadas.
    private final String insertar = 
        "INSERT INTO descuento VALUES (?, ?, ?, ?, ?)";
    private final String borrar = 
        "DELETE FROM descuento WHERE codDsc = ?";
    private final String modificar = 
        "UPDATE descuento SET usos = ?, cantidadDesc = ?, " + 
        "fechaInicio = ?, fechaFin = ? WHERE codDsc = ?";
    private final String listarTodo = 
        "SELECT * FROM descuento";
    private final String buscar = 
        "SELECT * FROM descuento WHERE codDsc = ?";

}
