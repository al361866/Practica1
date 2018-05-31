package telecomunicaciones.gestion.Tarifas;

/**
 * Created by al361880 on 10/04/18.
 * Created by al361866 on 10/04/18.
 */
public abstract class Horario extends Tarifa {


    //Atributos
    private Tarifa tarifaBase;

    //Constructores

    Horario(){}
    Horario(Tarifa tarifa, double precio){

        super(precio);
        this.tarifaBase = tarifa;


    }

    //Metodos


    //Getters

    Tarifa getTarifaBase(){
        return this.tarifaBase;
    }

    //toString

    @Override
    public String toString(){
        return "Tarifa adicional(€/min): "+ getPrecio();
    }
}
