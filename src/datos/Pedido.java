package datos;

import java.time.LocalDate;

public class Pedido {

    private final String codPed; 
    private LocalDate fechaPed; 
    private String codCle; // código cliente
    private String codRep; // código repartidor
    private Menu menu; 
    
    // Constructors.
    public Pedido(String pCodPed) {
        codPed = pCodPed;
    }

    public Pedido(
    String pCodPed, LocalDate pFechaPed, 
    String pCodCle, String pCodRep, Menu pMenu) {
        codPed = pCodPed;
        fechaPed = pFechaPed;
        codCle = pCodCle;
        codRep = pCodRep;
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
    public void setMenu(Menu pMenu) {
        menu = pMenu;
    }   

    // Methods
    public float calcularPrecio() {
        float sum = 0;
        // TODO
        return sum;
    }
}