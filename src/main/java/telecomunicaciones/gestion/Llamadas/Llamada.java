package telecomunicaciones.gestion.Llamadas;

import telecomunicaciones.InterfazFechas;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by al361880 on 20/02/18.
 * Created by al361866 on 20/02/18.
 */
public class Llamada implements InterfazFechas,Serializable{

    //Atributos

    private int telefono;
    private int hora;
    private double duracion;
    private Date fecha;

    //Constructores

    public Llamada(int telefono, Date fecha, int hora, double duracion) {
        this.telefono = telefono;
        this.fecha = fecha;
        this.hora = hora;
        this.duracion = duracion;
    }

    //Getters
    public Date getFecha(){
        return fecha;
    }
    public double getDuracion(){
        return duracion;
    }
    public int getHora() {return hora; }
    public int getTelefono() {return telefono; }


    //toString
    @Override
    public String toString() {
        return "Llamada a " + telefono + " en fecha de " + fecha +
                " a la hora " + hora + " con una duracion de " + duracion +" minutos\n";
    }
}
