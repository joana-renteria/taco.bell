package datos;

import java.time.LocalDate;

public class Descuento implements Comparable <Descuento> {

    private final String codDsc;
    private float usos;
    private float cantidadDsc;
    private LocalDate fechaInicio, fechaFin;

    /**Constructor solo con el 
     * @param pCodDsc codigo del descuento.
     */
    public Descuento(String pCodDsc) {
        codDsc = pCodDsc;
    }

    public Descuento(
    String pCodDsc, float pUsos, float pCantidadDsc,
    LocalDate pInicio, LocalDate pFin) {
        codDsc = pCodDsc;
        usos = pUsos;
        cantidadDsc = pCantidadDsc;
        fechaInicio = pInicio;
        fechaFin = pFin;
    }

    // Getters.
    public String getCodDsc() {
        return codDsc;
    }
    public float getUsos() {
        return usos;
    }
    public float getCantidadDsc() {
        return cantidadDsc;
    }
    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    // Setters.
    public void setUsos(float pUsos) {
        usos = pUsos;
    }
    public void setCantidadDsc(float pCantidadDsc) {
        cantidadDsc = pCantidadDsc;
    }
    public void setFechaInicio(LocalDate pInicio) {
        fechaInicio = pInicio;
    }
    public void setFechaFin(LocalDate pFin) {
        fechaFin = pFin;
    }

    @Override
    public int compareTo(Descuento pDescuento) {
        return codDsc.compareTo(pDescuento.getCodDsc());
    }

    @Override
    public String toString() {
        return codDsc + " " + usos + " " + cantidadDsc + " " +
        fechaInicio.toString() + " " + fechaFin.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Descuento) {
            Descuento pDescuento = (Descuento) obj;
            return pDescuento.getCodDsc().equals(codDsc)
            && pDescuento.getUsos() == usos
            && pDescuento.getCantidadDsc() == cantidadDsc
            && pDescuento.getFechaInicio().equals(fechaInicio)
            && pDescuento.getFechaFin().equals(fechaFin);
        }
        else
            return false;
    }   
}
