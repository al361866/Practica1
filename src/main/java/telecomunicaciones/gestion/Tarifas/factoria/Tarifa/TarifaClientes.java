package telecomunicaciones.gestion.Tarifas.factoria.Tarifa;

import telecomunicaciones.gestion.Tarifas.Noche;
import telecomunicaciones.gestion.Tarifas.Tarde;
import telecomunicaciones.gestion.Tarifas.Tarifa;
import telecomunicaciones.gestion.Tarifas.TarifaBase;

/**
 * Created by al361880 on 17/04/18.
 *  Created by al361866 on 17/04/18.
 */
public interface TarifaClientes {

    Noche getNoche();
    Noche getNoche(Tarifa tarifa, double precio);
    Tarde getTarde();
    Tarde getTarde(Tarifa tarifa, double precio);
    TarifaBase getBase();
    TarifaBase getBase(double precio);
}
