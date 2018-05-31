package telecomunicaciones.gestion.Tarifas.factoria.Tarifa;

import telecomunicaciones.gestion.Tarifas.Noche;
import telecomunicaciones.gestion.Tarifas.Tarde;
import telecomunicaciones.gestion.Tarifas.Tarifa;
import telecomunicaciones.gestion.Tarifas.TarifaBase;

/**
 * Created by al361880 on 17/04/18.
 * Created by al361866 on 17/04/18.
 */
public class FactoriaTarifaClientes implements TarifaClientes {


    @Override

    public Noche getNoche(){
        return new Noche();
    }

    @Override

    public Noche getNoche(Tarifa tarifa, double precio){

        return new Noche(tarifa,precio);
    }

    @Override

    public Tarde getTarde(){
        return new Tarde();
    }

    @Override

    public Tarde getTarde(Tarifa tarifa, double precio){
        return new Tarde(tarifa,precio);
    }

    @Override

    public TarifaBase getBase(){
        return new TarifaBase();
    }

    @Override

    public TarifaBase getBase(double precio){
        return new TarifaBase(precio);
    }
}
