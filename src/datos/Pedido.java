package datos;

import java.time.LocalDate;

public class Pedido implements Comparable <Pedido> {

    private final String codPed; 
    private LocalDate fechaPed; 
    private String codCle; // código cliente
    private String codRep; // código repartidor
    private String codEst; // código establecimiento
    private Menu menu; 
    
    // Constructors.
    public Pedido(String pCodPed) {
        codPed = pCodPed;
    }

    public Pedido(
    String pCodPed, LocalDate pFechaPed, 
    String pCodCle, String pCodRep, String pcodEst, Menu pMenu) {
        codPed = pCodPed;
        fechaPed = pFechaPed;
        codCle = pCodCle;
        codRep = pCodRep;
        codEst = pcodEst;
        menu = pMenu;
    }

    // Getters.
    public String getCodPed() {
        return codPed;
    }
    public LocalDate getFechaPed() {
        return fechaPed;
    }
    public String getCodCle() {
        return codCle;
    }
    public String getCodRep() {
        return codRep;
    }
    public String getCodEst() {
        return codEst;
    }
    public Menu getMenu() {
        return menu;
    }

    // Setters.
    public void setFechaPed(LocalDate pFechaPed) {
        fechaPed = pFechaPed;
    }
    public void setCodCle(String pCodCle) {
        codCle = pCodCle;
    }
    public void setCodRep(String pCodRep) {
        codRep = pCodRep;
    }
    public void setCodEst(String pCodEst) {
        codEst = pCodEst;
    }
    public void setMenu(Menu pMenu) {
        menu = pMenu;
    }   

    // Methods
    public float calcularPrecio() {
        float sum = 0;
        // TODO
        return sum;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Pedido) {
            Pedido pPedido = (Pedido) obj;
            return pPedido.getCodPed().equals(codPed)
                && pPedido.getFechaPed().equals(fechaPed)
                && pPedido.getCodCle().equals(codCle)
                && pPedido.getCodRep().equals(codRep)
                && pPedido.getCodEst().equals(codEst);
        }
        return false;
    }

    @Override
    public int compareTo(Pedido pPedido) {
        return codPed.compareTo(pPedido.getCodPed());
    }


    @Override
    public String toString() {
        return "{" +
            " codPed='" + getCodPed() + "'" +
            ", fechaPed='" + getFechaPed() + "'" +
            ", codCle='" + getCodCle() + "'" +
            ", codRep='" + getCodRep() + "'" +
            ", codEst='" + getCodEst() + "'" +
            ", menu='" + getMenu() + "'" +
            "}";
    }
    
    
}