package controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import controller.interfaces.Usuariable;
import users.*;

public class ADUsuarie extends MasterConnection implements Usuariable {
    /**Para grabar un usuario se recurre a la clase
     * @GenerateUsers que genera un usuario del tipo
     * indicado por el usuario. Después se llama a 
     * grabarUsuarie(), que almacena el dato en la tabla 
     * usuarie. Después según el tipo de usuario creado 
     * se graba en las tablas correspondientes.
     */
    @Override
    public void addUsuarie(Usuarie pUsuarie) {
        // se graba en la tabla principal.
        grabarUsuarie(pUsuarie);
        // la variable choice almacena el tipo de usuarie a grabar en la BD.
        String type = pUsuarie.getClass().getName().substring(6);
        switch (type) {
            case "Administrador": grabarUsuarie(pUsuarie);
                break;
            case "Cliente": grabarCliente((Cliente) pUsuarie);
                break;
            case "Auxiliar": grabarAuxiliar((Auxiliar) pUsuarie);
                break;
            case "Repartidor": grabarRepartidor((Repartidor) pUsuarie);
                break;
            default: System.out.println("Errpr con el tipo."); 
                break; //TODO caso por defecto: cliente.
        }
    }
    /**Método que graba el usuarie en la tabla principal.
    */
    private void grabarUsuarie(Usuarie pUsuarie) {
        openConnection();

        try {
            stmt = con.prepareStatement(insertarUsuarie);
            stmt.setString(1, pUsuarie.getCodUsr());
            stmt.setString(2, pUsuarie.getPasswd());
            stmt.setString(3, pUsuarie.getNombre());
            stmt.setString(4, pUsuarie.getApellido());
            stmt.setString(5, pUsuarie.getClass().getName().substring(6));
                stmt.executeUpdate();
        } catch (SQLException sqle) {
            // se puede lanzar una excepción si executeUpdate() devuelve 2.
        }

        closeConnection();
    }
    /**Método que graba en la tabla cliente. */
    private void grabarCliente(Cliente pCliente) {
        grabarUsuarie(pCliente);
        openConnection();
        try {
            stmt = con.prepareStatement(insertarCliente);  
            stmt.setString(1, pCliente.getCodUsr());
            stmt.setString(2, pCliente.getCorreoLogin());
                stmt.executeUpdate();
        } catch (SQLException sqle) {
            //TODO: handle exception
        }
        closeConnection();
    }

    private void grabarTrabajador(Trabajador pTrabajador) {
        openConnection();
        try {
            stmt = con.prepareStatement(insertarTrabajador);
            stmt.setString(1, pTrabajador.getCodUsr());
            stmt.setString(2, pTrabajador.getCodEst());
            stmt.setString(3, pTrabajador.getHorario());
            stmt.setFloat(4, pTrabajador.getSueldo());
            stmt.setString(5, pTrabajador.getClass().getName().substring(6));
                stmt.execute();
            
        } catch (SQLException sqle) {
            System.out.println("Ha saltado una excepcion en grabar trabajador.");
        }
        closeConnection();
    }

    private void grabarAuxiliar(Auxiliar pAuxiliar) {
        grabarUsuarie(pAuxiliar);
        grabarTrabajador(pAuxiliar);
        openConnection();
        try {
            stmt = con.prepareStatement(insertarAuxiliar);
            stmt.setString(1, pAuxiliar.getCodUsr());
            stmt.setString(2, pAuxiliar.getPuesto());
                stmt.executeUpdate();
        } catch (SQLException sqle) {
            //TODO: handle exception
        }
        closeConnection();
    }

    private void grabarRepartidor(Repartidor pRepartidor) {
        grabarUsuarie(pRepartidor);
        grabarTrabajador(pRepartidor);
        openConnection();
        try {
            stmt = con.prepareStatement(insertarRepartidor);
            stmt.setString(1, pRepartidor.getCodUsr());
            stmt.setString(2, pRepartidor.getCodVehiculo());
            // ejecucion del comando.
            stmt.executeUpdate();
        } catch (SQLException sqle) {
            //TODO: handle exception
        } 
        closeConnection();
    }
    @Override
    public void borrarUsuarie(String pCodUsr) {
        openConnection();
        try {
            // borrado en cascada.
            stmt = con.prepareStatement(borrar);
                    stmt.setString(1, pCodUsr);
                    stmt.executeUpdate();
        } catch (SQLException e) {
            //TODO: handle exception
        }
        closeConnection();
    }

    @Override
    public void modificarUsuarie(Usuarie pUsuarie) {
        openConnection();

        try {
            String type = 
                pUsuarie.getClass().getName().substring(6);
            String pCodUsr = pUsuarie.getCodUsr();
            switch (type) {
                case "Cliente" :
                    stmt = con.prepareStatement(modificarCliente);
                    stmt.setString(1,pCodUsr);
                    stmt.setString(2, ((Cliente) pUsuarie).getCorreoLogin());
                        stmt.executeUpdate();
                    break;
                case "Auxiliar" : // modificar de auxiliar.
                    stmt = con.prepareStatement(modificarAuxiliar);
                    stmt.setString(1,pCodUsr);
                    stmt.setString(2, ((Auxiliar) pUsuarie).getPuesto());
                        stmt.executeUpdate();
                    // modificar de trabajador
                    stmt = con.prepareStatement(modificarTrabajador);
                    stmt.setString(1,pCodUsr);
                    stmt.setString(2, ((Auxiliar) pUsuarie).getCodEst());
                    stmt.setString(3, ((Auxiliar) pUsuarie).getHorario());
                    stmt.setFloat(4, ((Auxiliar) pUsuarie).getSueldo());
                    stmt.setString(5, type);
                        stmt.executeUpdate();
                    break;
                case "Repartidor" : // modificar de repartidor.
                    stmt = con.prepareStatement(modificarRepartidor);
                    stmt.setString(1,pCodUsr);
                    stmt.setString(2, ((Repartidor) pUsuarie).getCodVehiculo());
                        stmt.executeUpdate();
                    // modificar de trabajador.
                    stmt = con.prepareStatement(modificarTrabajador);
                    stmt.setString(1,pCodUsr);
                    stmt.setString(2, ((Repartidor) pUsuarie).getCodEst());
                    stmt.setString(3, ((Repartidor) pUsuarie).getHorario());
                    stmt.setFloat(4, ((Repartidor) pUsuarie).getSueldo());
                    stmt.setString(5, type);
                        stmt.executeUpdate();
                    break;
            }

            stmt = con.prepareStatement(modificar);
            stmt.setString(1,pCodUsr);
            stmt.setString(2, pUsuarie.getPasswd());
            stmt.setString(3, pUsuarie.getNombre());
            stmt.setString(4, pUsuarie.getApellido());
            stmt.setString(5, type);
                stmt.executeUpdate();

        } catch (SQLException sqle) {
            //TODO: handle exception
        }
        closeConnection();
    }
    
    @Override
    public Usuarie buscarUsuarie(String pCodUsr) {
        Usuarie pUsuarie = null;
        ResultSet rsTwo = null, rsThree = null;
        PreparedStatement stmtTwo = null, stmtThree = null;
        String type = pCodUsr.substring(0, 2);

        openConnection();

        try {
            // búsqueda en la tabla usuarie.
            stmt = con.prepareStatement(buscar);
            stmt.setString(1, pCodUsr);
                rs = stmt.executeQuery();
                rs.next();
            // búsqueda en la tabla individual.
                 
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
        } catch (SQLException sqle) {
            //TODO: handle exception
        } catch (NullPointerException npe) {
            System.out.println("Ha habido un error con los resultsets. Null pointer.");
        }
        closeConnection();
        return pUsuarie;
    }

    @Override
    public int numeroDeUsuaries() {
        openConnection();
        int pTotal = 0;
        try {
            stmt = con.prepareStatement(cantidadUsers);
                rs = stmt.executeQuery();
                rs.next();
            pTotal = rs.getInt(1);
        } catch (Exception e) {
            //TODO: handle exception
        }
        closeConnection();
        return pTotal;
    }

    /**Crea de manera automática el código. 
     * @param pCodUsr es el prefijo del código.
     */
    public String crearCodigo(String pCodUsr) {
        String numUsers = String.valueOf(numeroDeUsuaries()) + 1;
        for (int i = 0; i < 5 - numUsers.length(); i++) 
            pCodUsr += "0";
            
        pCodUsr += numUsers;
        return pCodUsr;
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
        "UPDATE FROM usuarie WHERE codUsr = ? SET usos = ?, cantidadDesc = ?, fechaInicio = ?, fechaFin = ?";
    private final String modificarCliente = 
        "UPDATE FROM cliente WHERE codUsr = ? SET correoLogin = ?";
    private final String modificarTrabajador = 
        "UPDATE FROM trabajador WHERE codUsr = ? SET codEst = ?, horario = ?, sueldo = ?, tipo = ?";
    private final String modificarAuxiliar = 
        "UPDATE FROM auxiliar WHERE codUsr = ? SET puesto = ?";
    private final String modificarRepartidor = 
        "UPDATE FROM repartidor WHERE codUsr = ? SET codVehiculo = ?";

    private final String buscar = 
        "SELECT * FROM usuarie WHERE codUsr = ?";
    private final String buscarRepartidor = 
        "SELECT codVehiculo FROM repartidor WHERE codUsr = ?";
    private final String buscarAuxiliar = 
        "SELECT puesto FROM auxiliar WHERE codUsr = ?";

    private final String cantidadUsers = 
        "SELECT COUNT(*) FROM usuarie";
}
