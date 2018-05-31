package telecomunicaciones.gestion.Tarifas;

import telecomunicaciones.gestion.Llamadas.Llamada;

import java.io.Serializable;

/**
 * Created by al361880 on 20/02/18.
 * Created by al361866 on 20/02/18.
 */
public abstract class Tarifa implements Serializable{

    //Atributo
    private double precio = 5;

    //Constructores
    public Tarifa(){
    }

    public Tarifa(double precio) {
        this.precio = precio;
    }

    //Metodos

    public double calcularPrecio(Llamada llamada){

        return this.getPrecio()*llamada.getDuracion();
    }

    //Getters
    public double getPrecio() {
        return precio;
    }


    //Equals
    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (!(other instanceof Tarifa)) return false;
        Tarifa tarifa = (Tarifa) other;
        return this.precio == tarifa.getPrecio();
    }
    //toString
    @Override
    public String toString() {
        return " Tarifa(€/min): " + precio;
    }
}
