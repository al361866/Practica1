package telecomunicaciones.gestion.Clientes;

import telecomunicaciones.gestion.Tarifas.Tarifa;
import java.io.Serializable;

public class Empresa extends Cliente implements Serializable{

    //Constructores

    public Empresa(){}
    public Empresa(String NIF, String nombre,String email, Direccion dir) {
        super(NIF,nombre,dir,email);
    }

    //Métodos

    public void cambiarTarifa(Tarifa nuevaTarifa){

        this.setTarifa(nuevaTarifa);
    }

}
