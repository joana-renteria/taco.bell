package controller;

import controller.factorias.UsuarieADFactory;
import resources.Util;
import users.*;

public abstract class GenerateUsers {
    
    public static Adminstrador crearAdministrador() {
        Adminstrador pAdminstrador = 
            new Adminstrador(crearCodigo("AD"));
        anadirDatosUsuarie(pAdminstrador);
        return pAdminstrador;
    }
    
    
    public static Usuarie crearCliente() {
        Cliente pCliente = 
            new Cliente(crearCodigo("CL"));
        anadirDatosUsuarie(pCliente);
        String pCorreoLogin = Util.introducirCadena();
        pCliente.setCorreoLogin(pCorreoLogin);
        return pCliente;
    }


    public static Auxiliar crearAuxiliar() {
        Auxiliar pAuxiliar = 
            new Auxiliar(crearCodigo("AU"));
        anadirDatosUsuarie(pAuxiliar);
        String pPuesto = Util.introducirCadena();
        pAuxiliar.setPuesto(pPuesto);
        return pAuxiliar;
    }
    

    public static Repartidor crearRepartidor() {
        Repartidor pRepartidor = 
            new Repartidor(crearCodigo("RE"));
        anadirDatosTrabajador(pRepartidor);
        String pCodVehiculo = Util.introducirCadena();
        pRepartidor.setCodVehiculo(pCodVehiculo);
        return pRepartidor;
    }
    /**Crea de manera automática el código. 
     * El prefijo del mismo se envía como parámetro.
     */
    private static String crearCodigo(String pCodUsr) {
        String numUsers = 
            String.valueOf(
                UsuarieADFactory.getAccessUsuaries()
                    .numeroDeUsuaries()) + 1;
        for (int i = 0; i < 5 - numUsers.length(); i++) 
            pCodUsr += "0";

        return pCodUsr += numUsers;
    }
    /**Crea, instancia y añade los datos comunes 
     * a todos los objetos de clases que implementen 
     * @Usuarie
     */
    private static void anadirDatosUsuarie(Usuarie pUsuarie) {
        String pPasswd = Util.introducirCadena();
        String pNombre = Util.introducirCadena();
        String pApellido = Util.introducirCadena();

        pUsuarie.setNombre(pNombre);
        pUsuarie.setApellido(pApellido);
        pUsuarie.setPasswd(pPasswd);
    }
    /**Crea objetos de tipo trabajador de la misma forma
     * que anadirDatosUsuarie.
     */
    private static void anadirDatosTrabajador(Trabajador pTrabajador) {
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