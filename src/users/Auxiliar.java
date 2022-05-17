package users;

import java.sql.ResultSet;

public class Auxiliar extends Trabajador {
    private String puesto;

    // Constructors.
    public Auxiliar(String pCodUsr) {
        super(pCodUsr);
    }
    public Auxiliar(
    String pCodUsr, String pPasswd, 
    String pNombre, String pApellido,
    String pCodEst, String pHorario, 
    float pSueldo, String pPuesto) {
        super(pCodUsr, pPasswd, pNombre, 
        pApellido, pCodEst, pHorario, pSueldo);
        puesto = pPuesto;
    }

    // Getter.
    public String getPuesto() {
        return puesto;
    }

    // Setter.
    public void setPuesto(String pPuesto) {
        puesto = pPuesto;
    }

    // Methods.
    public ResultSet findPedidos() {
        return null;
        // TODO
    }
    public ResultSet findPedido(String pCodPed) {
        return null;
        // TODO.
    }
    public void actualizarPedido() {
        // TODO
    }

    @Override
    public boolean equals(Object obj) {
        if (super.equals(obj) && obj instanceof Auxiliar) {
            Auxiliar pAuxiliar;
            pAuxiliar = (Auxiliar) obj;
            return pAuxiliar.getPuesto().equalsIgnoreCase(puesto);
        }
        else
            return false;
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return super.toString() + " " + puesto;
    }
}
