package telecomunicaciones.gestion.Tarifas;

import telecomunicaciones.gestion.Llamadas.Llamada;

/**
 * Created by al361880 on 10/04/18.
 * Created by al361866 on 10/04/18.
 */
public class Tarde extends Horario {

    //Desde las 14:00 hasta las 21:00(excluido)

    //Atributos
    public int precio = 2;

    //Constructores
    public Tarde(){}

    public Tarde(Tarifa tarifa, double precio){

        super(tarifa, precio);

    }

    //Métodos
    @Override
    public double calcularPrecio (Llamada llamada) {

        double precioBase = getTarifaBase().calcularPrecio(llamada);
        double costeAdicional;

        if (llamada.getHora() >= 14 && llamada.getHora() < 21) {
            //Tarde
            costeAdicional = precio * llamada.getDuracion();
            if (costeAdicional < precioBase) {
                return costeAdicional;
            }
        }
        return precioBase;
    }

}
