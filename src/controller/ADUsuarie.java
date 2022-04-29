package controller;

import java.sql.PreparedStatement;
import java.util.ArrayList;

import controller.interfaces.Usuariable;
import resources.Util;
import users.*;

public class ADUsuarie extends MasterConnection implements Usuariable {

    private final String insertarAUsuarie = "INSERT INTO usuarie VALUES (?, ?, ?, ?, ?)";
    private final String borrar = "DELETE FROM usuarie WHERE codDsc = ?";
    private final String modificar = "UPDATE FROM usuarie WHERE codDsc = ? SET usos = ?, cantidadDesc = ?, fechaInicio = ?, fechaFin = ?";
    private final String buscar = "SELECT * FROM usuarie";

    public void anadirUsuarie() {
        // 1. Administrador, 2. Cliente, 3. Auxiliar, 4. Repartidor.
        int choice = Util.leerInt();
        Usuarie pUsuarie;
        switch (choice) {
            case 1: pUsuarie = crearAdministrador();
                break;
            case 2: pUsuarie = crearCliente();
                break;
            case 3: pUsuarie = crearAuxiliar();
                break;
            case 4: pUsuarie = crearRepartidor();
                break;
            default: pUsuarie = crearCliente();
                break; // caso por defecto: cliente.
        }
        grabarUsuarie(pUsuarie);
        String pType = 
            pUsuarie.getClass().getName().toLowerCase();
        switch (pType) {
            case "administrador": // grabado segun tipo.
                break;
            case "cliente":
                break;
            case "auxilar":
                break;
            case "repartidor" :
                break;
            default: 
                break;
        }

    }
    
    private void grabarUsuarie(Usuarie pUsuarie) {

    }
    
    public void borrarUsuario() {
        // TODO Auto-generated method stub
        
    }
    
    public void modificarUsuario() {
        // TODO Auto-generated method stub
        
    }
    
    public ArrayList <Usuarie> listarUsuarios() {
        // TODO Auto-generated method stub
        return null;
        
    }

    private String crearCodigo(String pCodUsr) {
        String numUsers = String.valueOf(listarUsuarios().size()) + 1;
        for (int i = 0; i < 5 - numUsers.length(); i++) 
            pCodUsr += "0";

        return pCodUsr += numUsers;
    }

    private Adminstrador crearAdministrador() {
        Adminstrador pAdminstrador = 
            new Adminstrador(crearCodigo("AD"));
        anadirDatosUsuarie(pAdminstrador);
        return pAdminstrador;
    }
    
    private Usuarie crearCliente() {
        Cliente pCliente = 
            new Cliente(crearCodigo("CL"));
        anadirDatosUsuarie(pCliente);
        String pCorreoLogin = Util.introducirCadena();
        pCliente.setCorreoLogin(pCorreoLogin);
        return pCliente;
    }

    private Auxiliar crearAuxiliar() {
        Auxiliar pAuxiliar = 
            new Auxiliar(crearCodigo("AU"));
        anadirDatosUsuarie(pAuxiliar);
        String pPuesto = Util.introducirCadena();
        pAuxiliar.setPuesto(pPuesto);
        return pAuxiliar;
    }
    
    private Repartidor crearRepartidor() {
        Repartidor pRepartidor = 
            new Repartidor(crearCodigo("RE"));
        anadirDatosTrabajador(pRepartidor);
        String pCodVehiculo = Util.introducirCadena();
        pRepartidor.setCodVehiculo(pCodVehiculo);
        return pRepartidor;
    }
    
    private void anadirDatosUsuarie(Usuarie pUsuarie) {
        String pPasswd = Util.introducirCadena();
        String pNombre = Util.introducirCadena();
        String pApellido = Util.introducirCadena();
        pUsuarie.setNombre(pNombre);
        pUsuarie.setApellido(pApellido);
        pUsuarie.setPasswd(pPasswd);
    }

    private void anadirDatosTrabajador(Trabajador pTrabajador) {
        anadirDatosUsuarie(pTrabajador);
        // se llama internamente al método que almacena datos de usuarie.
        String pCodEst = Util.introducirCadena();
        String pHorario = Util.introducirCadena();
        float pSueldo = Util.leerFloat();

        pTrabajador.setCodEst(pCodEst);
        pTrabajador.setHorario(pHorario);
        pTrabajador.setSueldo(pSueldo);
    }
}
