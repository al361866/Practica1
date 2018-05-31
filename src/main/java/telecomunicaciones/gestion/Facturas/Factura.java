package telecomunicaciones.gestion.Facturas;

import telecomunicaciones.gestion.Tarifas.Tarifa;
import telecomunicaciones.InterfazFechas;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by al361880 on 20/02/18.
 * Created by al361866 on 20/02/18.
 */
public class Factura implements InterfazFechas,Serializable{

    //Atributos

    private int cod;
    private double importe;
    private Tarifa tarifaInicial;
    private Date fechaEmision;
    private Date fechaInicial;
    private Date fechaFinal;


    //Constructores

    public Factura( int cod, Tarifa tarifaInicial, Date fechaEmision, double importe, Date fechaInicial, Date fechaFinal) {
        this.tarifaInicial = tarifaInicial;
        this.cod = cod;
        this.fechaEmision = fechaEmision;
        this.importe = importe;
        this.fechaInicial = fechaInicial;
        this.fechaFinal = fechaFinal;
    }

    //Getters

    public Date getFecha(){
        return fechaEmision;
    }
    public Date getFechaFinal() {return fechaFinal; }
    public Date getFechaInicial() {return fechaInicial; }
    public int getCod() {return cod; }
    public double getImporte() {return importe; }
    public Tarifa getTarifaInicial() {return tarifaInicial;}

    //toString
    public  String toString() {
        return "Factura : " + cod + " con una tarifa de " + tarifaInicial +
                " debe abonar un importe de " + importe + "€. (Fecha de emision " + fechaEmision + " ) y periodo de facturacion desde "
                + fechaInicial.toString() + " hasta " + fechaFinal.toString();
    }

}