package controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.Set;
import java.util.TreeMap;

import controller.interfaces.Usuariable;
import exceptions.GestorExcepciones;
import users.*;

public class ADUsuarie extends MasterConnection implements Usuariable {

    /**Para grabar un usuario se recurre a la clase
     * @GenerateUsers que genera un usuario del tipo
     * indicado por el usuario. Después se llama a 
     * grabarUsuarie(), que almacena el dato en la tabla 
     * usuarie. Después según el tipo de usuario creado 
     * se graba en las tablas correspondientes.
     * @param pUsuarie usuarie a añadirse
     */
    @Override
    public void addUsuarie(Usuarie pUsuarie) throws GestorExcepciones {
        // la variable choice almacena el tipo de usuarie a grabar en la BD.
        String type = pUsuarie.getClass().getName().substring(6);
        switch (type) {
            
            case "Adminstrador": grabarUsuarie(pUsuarie);
                break;
            case "Cliente": grabarCliente((Cliente) pUsuarie);
                break;
            case "Auxiliar": grabarAuxiliar((Auxiliar) pUsuarie);
                break;
            case "Repartidor": grabarRepartidor((Repartidor) pUsuarie);
                break;
            default: 
                throw new GestorExcepciones(23);
        }
    }

    /**Método que graba le usuarie en la tabla principal.
     * @param pUsuarie
    */
    private void grabarUsuarie(Usuarie pUsuarie) throws GestorExcepciones {
        try {
            openConnection();
            stmt = con.prepareStatement(insertarUsuarie);
            stmt.setString(1, pUsuarie.getCodUsr());
            stmt.setString(2, pUsuarie.getPasswd());
            stmt.setString(3, pUsuarie.getNombre());
            stmt.setString(4, pUsuarie.getApellido());
            stmt.setString(5, pUsuarie.getClass().getName().substring(6));
            stmt.executeUpdate();
        } catch (SQLException | GestorExcepciones e) {
            throw new GestorExcepciones(3);
        } finally {
            closeConnection();
        }
    }

    /**Método que graba en la tabla cliente. 
     * @param pCliente
    */
    private void grabarCliente(Cliente pCliente) throws GestorExcepciones {
        try {
            grabarUsuarie(pCliente);
            openConnection();
            stmt = con.prepareStatement(insertarCliente);  
            stmt.setString(1, pCliente.getCodUsr());
            stmt.setString(2, pCliente.getCorreoLogin());
                stmt.executeUpdate();
        } catch (SQLException | GestorExcepciones e) {
            throw new GestorExcepciones(3);
        } finally {
            closeConnection();
        }
    }

    /**Método que graba en la tabla trabajador. 
     * @param pTrabajador
    */
    private void grabarTrabajador(Trabajador pTrabajador) throws GestorExcepciones {
        try {
            openConnection();
            stmt = con.prepareStatement(insertarTrabajador);
            stmt.setString(1, pTrabajador.getCodUsr());
            stmt.setString(2, pTrabajador.getCodEst());
            stmt.setString(3, pTrabajador.getHorario());
            stmt.setFloat(4, pTrabajador.getSueldo());
            stmt.setString(5, pTrabajador.getClass().getName().substring(6));
            stmt.executeUpdate();
            
        } catch (SQLException | GestorExcepciones e) {
            throw new GestorExcepciones(3);
        } finally {
            closeConnection();
        }
    }
    
    /**Método que graba en la tabla auxiliar. 
     * @param pAuxiliar
    */
    private void grabarAuxiliar(Auxiliar pAuxiliar) throws GestorExcepciones {
        try {
            grabarUsuarie(pAuxiliar);
            grabarTrabajador(pAuxiliar);
            openConnection();
            stmt = con.prepareStatement(insertarAuxiliar);
            stmt.setString(1, pAuxiliar.getCodUsr());
            stmt.setString(2, pAuxiliar.getPuesto());
            stmt.executeUpdate();
        } catch (SQLException | GestorExcepciones e) {
            throw new GestorExcepciones(3);
        } finally {
            closeConnection();
        }
    }

    /**Método que graba en la tabla Repartidor. 
     * @param pRepartidor
    */
    private void grabarRepartidor(Repartidor pRepartidor) throws GestorExcepciones {
        try {
            grabarUsuarie(pRepartidor);
            grabarTrabajador(pRepartidor);
            openConnection();
            stmt = con.prepareStatement(insertarRepartidor);
            stmt.setString(1, pRepartidor.getCodUsr());
            stmt.setString(2, pRepartidor.getCodVehiculo());
            // ejecucion del comando.
            stmt.executeUpdate();
        } catch (SQLException | GestorExcepciones e) {
            throw new GestorExcepciones(3);
        } finally {
            closeConnection();
        }
    }

    /**Método que borra en la tabla Usuarie. 
     * @param pCodUsr
    */
    @Override
    public void borrarUsuarie(String pCodUsr) throws GestorExcepciones {
        try {
            openConnection();
            // borrado en cascada.
            stmt = con.prepareStatement(borrar);
                    stmt.setString(1, pCodUsr);
                    stmt.executeUpdate();
        } catch (SQLException | GestorExcepciones e) {
            throw new GestorExcepciones(3);
        } finally {
            closeConnection();
        }
    }

    /**Método que modifica en la tabla Usuarie. 
     * @param pUsuarie
    */
    @Override
    public void modificarUsuarie(Usuarie pUsuarie) throws GestorExcepciones {
        try {
            openConnection();
            String type = 
                pUsuarie.getClass().getName().substring(6);
            String pCodUsr = pUsuarie.getCodUsr();
            switch (type) {
                case "Cliente" :
                    stmt = con.prepareStatement(modificarCliente);
                    stmt.setString(2,pCodUsr);
                    stmt.setString(1, ((Cliente) pUsuarie).getCorreoLogin());
                        stmt.executeUpdate();
                    break;
                case "Auxiliar" : // modificar de auxiliar.
                    stmt = con.prepareStatement(modificarAuxiliar);
                    stmt.setString(2,pCodUsr);
                    stmt.setString(1, ((Auxiliar) pUsuarie).getPuesto());
                        stmt.executeUpdate();
                    // modificar de trabajador
                    stmt = con.prepareStatement(modificarTrabajador);
                    stmt.setString(5,pCodUsr);
                    stmt.setString(1, ((Auxiliar) pUsuarie).getCodEst());
                    stmt.setString(2, ((Auxiliar) pUsuarie).getHorario());
                    stmt.setFloat(3, ((Auxiliar) pUsuarie).getSueldo());
                    stmt.setString(4, type);
                        stmt.executeUpdate();
                    break;
                case "Repartidor" : // modificar de repartidor.
                    stmt = con.prepareStatement(modificarRepartidor);
                    stmt.setString(2,pCodUsr);
                    stmt.setString(1, ((Repartidor) pUsuarie).getCodVehiculo());
                        stmt.executeUpdate();
                    // modificar de trabajador.
                    stmt = con.prepareStatement(modificarTrabajador);
                    stmt.setString(5,pCodUsr);
                    stmt.setString(1, ((Repartidor) pUsuarie).getCodEst());
                    stmt.setString(2, ((Repartidor) pUsuarie).getHorario());
                    stmt.setFloat(3, ((Repartidor) pUsuarie).getSueldo());
                    stmt.setString(4, type);
                        stmt.executeUpdate();
                    break;
            }

            stmt = con.prepareStatement(modificar);
            stmt.setString(5,pCodUsr);
            stmt.setString(1, pUsuarie.getPasswd());
            stmt.setString(2, pUsuarie.getNombre());
            stmt.setString(3, pUsuarie.getApellido());
            stmt.setString(4, type);
            stmt.executeUpdate();
    
        } catch (SQLException | GestorExcepciones e) {
            throw new GestorExcepciones(3);
        } finally {
            closeConnection();
        }
    }
    
    /**Método para buscar en la tabla Usuarie por correo
     * Solo funciona con clientes
     * @param pCorreo correo electronico
     * @return Usuarie si lo encuentra, sino null
    */
    public Usuarie buscarCliente(String pCorreo) throws GestorExcepciones {
        Usuarie pUsuarie = null;
        try {
            openConnection();
            stmt = con.prepareStatement(buscarCliente);
            stmt.setString(1, pCorreo);
            rs = stmt.executeQuery();
            rs.next();
            pUsuarie = new Cliente(rs.getString(1));
            ((Cliente) pUsuarie).setCorreoLogin(rs.getString(2));
            stmt = con.prepareStatement(buscar);
            stmt.setString(1, pUsuarie.getCodUsr());
            rs = stmt.executeQuery();
            rs.next();
            pUsuarie.setPasswd(rs.getString(2));
            pUsuarie.setNombre(rs.getString(3));
            pUsuarie.setApellido(rs.getString(4));
        } catch (SQLException | GestorExcepciones e) {
            throw new GestorExcepciones(3);
        } finally {
            closeConnection();
        }
        return pUsuarie;
    }

    /**Método para buscar en la tabla Usuarie por codigo
     * distingue entre distintos tipos de usuaries segun su codigo
     * @param pCodUsr codigo de usuarie
     * @return Usuarie si lo encuentra, sino null
    */
    @Override
    public Usuarie buscarUsuarie(String pCodUsr) throws GestorExcepciones {
        Usuarie pUsuarie = null;
        ResultSet rsTwo = null, rsThree = null;
        PreparedStatement stmtTwo = null, stmtThree = null;
        String type = pCodUsr.substring(0, 2);

        try {
            openConnection();
            // búsqueda en la tabla usuarie.
            stmt = con.prepareStatement(buscar);
            stmt.setString(1, pCodUsr);
                rs = stmt.executeQuery();
                
            // búsqueda en la tabla individual.
            if (rs.next())
            switch (type) {
                case "AD" : pUsuarie = 
                    new Adminstrador(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4));
                    break;

                case "CL" : 
                    stmtTwo = con.prepareStatement(
                        buscar.replace("usuarie", "cliente"));
                    stmtTwo.setString(1, pCodUsr);
                    rsTwo = stmtTwo.executeQuery();
                    rsTwo.next();
                    pUsuarie = 
                    new Cliente(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                            rsTwo.getString(2));
                    break;

                case "AU" : // se busca en la tabla de trabajador los datos adicionales.  
                    stmtTwo = con.prepareStatement(buscarAuxiliar);
                    stmtTwo.setString(1, pCodUsr);
                        rsTwo = stmtTwo.executeQuery();
                        rsTwo.next();
                    stmtThree = con.prepareStatement(
                        buscar.replace("usuarie", "trabajador"));
                    stmtThree.setString(1, pCodUsr);
                        rsThree = stmtThree.executeQuery();
                        rsThree.next();              
                    pUsuarie = 
                        new Auxiliar(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                            rsThree.getString(2),
                            rsThree.getString(3),
                            rsThree.getFloat(4),
                                rsTwo.getString(1));
                    break;

                case "RE" : 
                    stmtTwo = con.prepareStatement(buscarRepartidor);
                    stmtTwo.setString(1, pCodUsr);
                        rsTwo = stmtTwo.executeQuery();
                        rsTwo.next();
                    stmtThree = con.prepareStatement(
                        buscar.replace("usuarie", "trabajador"));
                    stmtThree.setString(1, pCodUsr);
                        rsThree = stmtThree.executeQuery();
                        rsThree.next();
                    pUsuarie = 
                        new Repartidor(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                            rsThree.getString(2),
                            rsThree.getString(3),
                            rsThree.getFloat(4),
                                rsTwo.getString(1));
                    break;
            }  
            else
                return null;
        } catch (SQLException | GestorExcepciones e) {
            throw new GestorExcepciones(3);
        } finally {
            closeConnection();
        }
        return pUsuarie;
    }

    /**Método para listar de la tabla Usuarie
     * @return TreeMap de usuaries
    */
    @Override
    public TreeMap <String, Usuarie> listarUsuaries() throws GestorExcepciones {
        Usuarie pUsuarie = null;
        TreeMap <String, Usuarie> pListaUsuaries = 
            new TreeMap <String, Usuarie> ();

        ResultSet rs2;
        try {
            openConnection();
            stmt = con.prepareStatement(listarCodigos);
                rs2 = stmt.executeQuery();
            while (rs2.next()) {
                pUsuarie = buscarUsuarie(rs2.getString(1));
                pListaUsuaries.put(
                    rs2.getString(1),
                    pUsuarie);
            }
        } catch (SQLException | GestorExcepciones e) {
            throw new GestorExcepciones(3);
        } finally {
            closeConnection();
        }
        return pListaUsuaries;
    }

    /**Crea de manera automática el código. 
     * @param pCodUsr es el prefijo del código.
     */
    @Override
    public String generateCodigo(String pCodUsr) throws GestorExcepciones {
        Set <String> keys = listarUsuaries().keySet();

        Optional <String> lastKey = 
            keys.stream()
            .max((k1, k2) -> 
                k1.substring(2)
                .compareTo(k2.substring(2)));

        int k = 0;

        if (lastKey.isPresent())
            k = Integer.parseInt(lastKey.get().substring(2));

        String numUsers = String.valueOf(k + 1);
        for (int i = 0; i < 5 - numUsers.length(); i++) 
            pCodUsr += "0";
            
        pCodUsr += numUsers;
        return pCodUsr;
    }

    @Override
    public int totalUsuaries() throws GestorExcepciones {
        return cantidadTotal("usuarie");
    }

    private final String insertarUsuarie = 
        "INSERT INTO usuarie VALUES (?, ?, ?, ?, ?)";
    private final String insertarCliente =
        "INSERT INTO cliente VALUES (?, ?)";
    private final String insertarTrabajador = 
        "INSERT INTO trabajador VALUES (?, ?, ?, ?, ?)";
    private final String insertarAuxiliar =
        "INSERT INTO auxiliar VALUES (?, ?)";
    private final String insertarRepartidor = 
        "INSERT INTO repartidor VALUES (?, ?)";

    private final String borrar = 
        "DELETE FROM usuarie WHERE codUsr = ?";

    private final String modificar = 
        "UPDATE usuarie SET passwd = ?, nombre = ?, apellido = ?, tipo = ? WHERE codUsr = ?";
    private final String modificarCliente = 
        "UPDATE cliente SET correoLogin = ? WHERE codUsr = ?";
    private final String modificarTrabajador = 
        "UPDATE trabajador SET codEst = ?, horario = ?, sueldo = ?, tipo = ? WHERE codUsr = ?";
    private final String modificarAuxiliar = 
        "UPDATE auxiliar SET puesto = ? WHERE codUsr = ?";
    private final String modificarRepartidor = 
        "UPDATE repartidor SET codVehiculo = ? WHERE codUsr = ?";

    private final String buscar = 
        "SELECT * FROM usuarie WHERE codUsr = ?";
    private final String buscarCliente = 
        "SELECT * FROM cliente WHERE correoLogin = ?";
    private final String buscarRepartidor = 
        "SELECT codVehiculo FROM repartidor WHERE codUsr = ?";
    private final String buscarAuxiliar = 
        "SELECT puesto FROM auxiliar WHERE codUsr = ?";

    private final String listarCodigos = 
        "SELECT codUsr from usuarie";
}
