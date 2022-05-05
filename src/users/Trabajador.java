package users;

public abstract class Trabajador extends Usuarie {
    private String codEst;
    private String horario;
    private float sueldo;

    // Constructor.
    public Trabajador(String pCodUsr) {
        super(pCodUsr);
    }
    public Trabajador(
    String pCodUsr, String pPasswd, 
    String pNombre, String pApellido,
    String pCodEst, String pHorario, float pSueldo) {
        super(pCodUsr, pPasswd, pNombre, pApellido);
        codEst = pCodEst;
        horario = pHorario;
        sueldo = pSueldo;
    }

    // Getters.
    public String getCodEst() {
        return codEst;
    }
    public String getHorario() {
        return horario;
    }
    public float getSueldo() {
        return sueldo;
    }

    // Setters.
    public void setCodEst(String pCodEst) {
        codEst = pCodEst;
    }
    public void setHorario(String pHorario) {
        horario = pHorario;
    }
    public void setSueldo(float pSueldo) {
        sueldo = pSueldo;
    }

    @Override
    public boolean equals(Object obj) {
        Trabajador pTrabajador;

        if (super.equals(obj) && obj instanceof Trabajador ) {
            pTrabajador = (Trabajador) obj;
            return pTrabajador.getCodEst().equals(codEst)
                && pTrabajador.getHorario().equals(horario)
                && pTrabajador.getSueldo() == sueldo;
        }
        else
            return false;
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return super.toString() + " " + codEst + " " + horario + " " + sueldo;
    }
}
