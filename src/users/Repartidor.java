package users;

public class Repartidor extends Trabajador {
    private String codVehiculo;

    // Constructor.
    public Repartidor(String pCodUsr) {
        super(pCodUsr);
    }
    public Repartidor(
    String pCodUsr, String pPasswd, 
    String pNombre, String pApellido,
    String pCodEst, String pHorario, float pSueldo, String pCodVehiculo) {
        super(pCodUsr, pPasswd, pNombre, pApellido, pCodEst, pHorario, pSueldo);
        codVehiculo = pCodVehiculo;
    }

    // Getter.
    public String getCodVehiculo() {
        return codVehiculo;
    }

    // Setter.
    public void setCodVehiculo(String pCodVehiculo) {
        codVehiculo = pCodVehiculo;
    }
    
}
