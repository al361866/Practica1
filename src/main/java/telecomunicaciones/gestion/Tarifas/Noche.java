package telecomunicaciones.gestion.Tarifas;

import telecomunicaciones.gestion.Llamadas.Llamada;

/**
 * Created by al361880 on 10/04/18.
 * Created by al361866 on 10/04/18.
 */

public class Noche extends Horario {

    //Desde 21:00 hasta 24:00(excluido)

    //Atributos
    private int precio = 4;

    //Constructores

    public Noche(){}

    public Noche(Tarifa tarifa, double precio){

        super(tarifa, precio);
    }

    //Métodos

    @Override
    public double calcularPrecio (Llamada llamada) {

        double precioBase = getTarifaBase().calcularPrecio(llamada);
        double costeAdicional;

        if (llamada.getHora() >= 21 && llamada.getHora() < 24) {
            //Noche
            costeAdicional = precio * llamada.getDuracion();

            if (costeAdicional < precioBase) {
                return costeAdicional;
            }
        }
        return precioBase;
    }

}
