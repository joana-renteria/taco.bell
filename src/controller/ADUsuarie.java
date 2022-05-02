package controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import controller.interfaces.Usuariable;
import resources.Util;
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
    public void crearUsuarie() {
        // 1. Administrador, 2. Cliente, 3. Auxiliar, 4. Repartidor.
        int choice = Util.leerInt(); // stores the type of user to be registered.
        Usuarie pUsuarie;
        // la variable choice almacena el tipo de usuarie a grabar en la BD.
        switch (choice) {
            case 1: pUsuarie = GenerateUsers.crearAdministrador();
                break;
            case 2: pUsuarie = GenerateUsers.crearCliente();
                break;
            case 3: pUsuarie = GenerateUsers.crearAuxiliar();
                break;
            case 4: pUsuarie = GenerateUsers.crearRepartidor();
                break;
            default: pUsuarie = GenerateUsers.crearCliente();
                break; // caso por defecto: cliente.
        }
        // se graba en la tabla principal.
        grabarUsuarie(pUsuarie);
        // se envía el objeto.
        switch (choice) {
            case 2: grabarCliente((Cliente) pUsuarie);
                break;
            case 3: grabarAuxiliar((Auxiliar) pUsuarie);
                break;
            case 4 : grabarRepartidor((Repartidor) pUsuarie);
                break;
            default: grabarCliente((Cliente) pUsuarie);
                break; // al igual que antes, se graba en la tabla cliente.
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
            stmt.setString(5, pUsuarie.getClass().getName());
            // ejecución.
            stmt.executeUpdate();
        } catch (SQLException sqle) {
            // se puede lanzar una excepción si executeUpdate() devuelve 2.
        }

        closeConnection();
    }
    /**Método que graba en la tabla cliente. */
    private void grabarCliente(Cliente pCliente) {
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
            stmt.setString(5, pTrabajador.getClass().getName());
            stmt.executeUpdate();
        } catch (SQLException sqle) {
            // TODO: handle exception
        }
        closeConnection();
    }

    private void grabarAuxiliar(Auxiliar pAuxiliar) {
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
        String type = pCodUsr.substring(0, 2);

        openConnection();

        try {
            switch (type) {
                case "AU": 
                    // auxiliar.
                    stmt = con.prepareStatement(borrar.replace("usuarie", "auxiliar"));
                    stmt.setString(1, pCodUsr);
                        stmt.executeUpdate();
                    // trabajador.
                    stmt = con.prepareStatement(borrar.replace("usuarie", "trabajador"));
                    stmt.setString(1, pCodUsr);
                        stmt.executeUpdate();
                    break;
    
                case "CL": 
                    // cliente.
                    stmt = con.prepareStatement(borrar.replace("usuarie", "cliente"));
                    break;
    
                case "RE": 
                    // repartidor.
                    stmt = con.prepareStatement(borrar.replace("usuarie", "repartidor"));
                        stmt.executeUpdate();
                    // trabajador.
                    stmt = con.prepareStatement(borrar.replace("usuarie", "trabajador"));
                        stmt.executeUpdate();
                    break;
                    
                default :
                    break;
            }

            stmt = con.prepareStatement(borrar);
                    stmt.setString(1, pCodUsr);
                    stmt.executeUpdate();
        } catch (Exception e) {
            //TODO: handle exception
        }
        closeConnection();
    }

    @Override
    public void modificarUsuarie(Usuarie pUsuarie) {
        openConnection();

        try {
            String type = 
                pUsuarie.getClass().getName().toLowerCase();

            switch (type) {
                case "cliente" :
                    stmt = con.prepareStatement(modificarCliente);
                    stmt.setString(1, ((Cliente) pUsuarie).getCorreoLogin());
                    // ejecución del comando.
                    stmt.executeUpdate();
                    break;
                case "auxiliar" : // modificar de auxiliar.
                    stmt = con.prepareStatement(modificarAuxiliar);
                    stmt.setString(1, ((Auxiliar) pUsuarie).getPuesto());
                    stmt.executeUpdate();
                    // modificar de trabajador
                    stmt = con.prepareStatement(modificarTrabajador);
                    stmt.setString(1, ((Auxiliar) pUsuarie).getCodUsr());
                    stmt.setString(2, ((Auxiliar) pUsuarie).getCodEst());
                    stmt.setString(3, ((Auxiliar) pUsuarie).getHorario());
                    stmt.setFloat(4, ((Auxiliar) pUsuarie).getSueldo());
                    stmt.setString(5, ((Auxiliar) pUsuarie).getClass().getName());
                    break;
                case "repartidor" : // modificar de repartidor.
                    stmt = con.prepareStatement(modificarRepartidor);
                    stmt.setString(1, ((Repartidor) pUsuarie).getCodVehiculo());
                    stmt.executeUpdate();
                    // borraro de repartidor.
                    stmt.executeUpdate();
                    stmt.setString(1, ((Repartidor) pUsuarie).getCodUsr());
                    stmt.setString(2, ((Repartidor) pUsuarie).getCodEst());
                    stmt.setString(3, ((Repartidor) pUsuarie).getHorario());
                    stmt.setFloat(4, ((Repartidor) pUsuarie).getSueldo());
                    stmt.setString(5, ((Repartidor) pUsuarie).getClass().getName());
                    break;
            }

            stmt = con.prepareStatement(modificar);
            stmt.setString(1, pUsuarie.getCodUsr());
            stmt.setString(2, pUsuarie.getPasswd());
            stmt.setString(3, pUsuarie.getNombre());
            stmt.setString(4, pUsuarie.getApellido());
            stmt.setString(5, pUsuarie.getClass().getName());
            // ejecucion.
            stmt.executeUpdate();

        } catch (Exception e) {
            //TODO: handle exception
        }

        closeConnection();
    }
    
    @Override
    public ArrayList <Usuarie> listarUsuaries() {
        ArrayList <Usuarie> all =
            new ArrayList <Usuarie> ();
        String auxCodUsr;
        Usuarie pUsuarie = null;
        try {
            stmt = con.prepareStatement(buscar);
            rs = stmt.executeQuery();
            PreparedStatement stmtTwo, stmtThree;
            ResultSet rsTwo, rsThree;
            while (rs.next()) {
                auxCodUsr = rs.getString(1)
                    .substring(0, 2);

                stmtTwo = con.prepareStatement(buscar.replace("usuarie", rs.getString(5)));
                stmtTwo.setString(1, rs.getString(1));
                rsTwo = stmtTwo.executeQuery();
                
                switch (auxCodUsr) {
                    case "AD" : pUsuarie = 
                        new Adminstrador(
                            rs.getString(1),
                            rs.getString(2),
                            rs.getString(3),
                            rs.getString(4));
                        break;

                    case "CL" : pUsuarie = 
                        new Cliente(
                            rs.getString(1),
                            rs.getString(2),
                            rs.getString(3),
                            rs.getString(4),
                            rsTwo.getString(2));
                        break;

                    case "AU" : 
                    stmtThree = con.prepareStatement(buscar.replace("usuarie", "trabajador"));
                    stmtThree.setString(1, rs.getString(1));
                    rsThree = stmtThree.executeQuery();
                    
                    pUsuarie = 
                        new Auxiliar(
                            rs.getString(1),
                            rs.getString(2),
                            rs.getString(3),
                            rs.getString(4),
                                rsThree.getString(2),
                                rsThree.getString(3),
                                rsThree.getFloat(4),
                            rsTwo.getString(2));
                        break;

                    case "RE" : 
                        stmtThree = con.prepareStatement(buscar.replace("usuarie", "trabajador"));
                        stmtThree.setString(1, rs.getString(1));
                        rsThree = stmtThree.executeQuery();

                        pUsuarie = 
                            new Repartidor(
                                rs.getString(1),
                                rs.getString(2),
                                rs.getString(3),
                                rs.getString(4),
                                rsThree.getString(2),
                                rsThree.getString(3),
                                rsThree.getFloat(4),
                            rsTwo.getString(2));
                        break;
                }   
                all.add(pUsuarie);
            }
        } catch (Exception e) {
            //TODO: handle exception
        }
        return all;
    }

    @Override
    public int numeroDeUsuaries() {
        return listarUsuaries().size();
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
        "UPDATE FROM cliente WHERE codUsr = ? SET codUsr = ?, correoLogin = ?";
    private final String modificarTrabajador = 
        "UPDATE FROM trabajador WHERE codUsr = ? SET codUsr = ?, codEst = ?, horario = ?, sueldo = ?, tipo = ?";
    private final String modificarAuxiliar = 
        "UPDATE FROM auxiliar WHERE codUsr = ? SET puesto = ?";
    private final String modificarRepartidor = 
        "UPDATE FROM repartidor WHERE codUsr = ? SET codVehiculo = ?";

    private final String buscar = 
        "SELECT * FROM usuarie WHERE codUsr = ?";
}
