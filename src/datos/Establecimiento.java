package datos;

public class Establecimiento implements Comparable <Establecimiento>{
    private final String codEst;
    private String nombre;
    private String loc;

    public Establecimiento(String pCodEst) {
        codEst = pCodEst;
    }
    public Establecimiento(
    String pCodEst, 
    String pNombre, String pLoc) {
        codEst = pCodEst;
        nombre = pNombre;
        loc = pLoc;
    }
    
    // Getters.
    public String getCodEst() {
        return codEst;
    }
    public String getNombre() {
        return nombre;
    }
    public String getLoc() {
        return loc;
    }

    // Setters.
    public void setNombre(String pNombre) {
        nombre = pNombre;
    }
    public void setLoc(String pLoc) {
        loc = pLoc;
    }

    public int compareTo(Establecimiento pEstablecimiento) {
        return codEst.compareTo(pEstablecimiento.getCodEst());
    }

    @Override
    public String toString() {
        return "{" +
            " codEst='" + getCodEst() + "'" +
            ", nombre='" + getNombre() + "'" +
            ", loc='" + getLoc() + "'" +
            "}";
    }
    
}
