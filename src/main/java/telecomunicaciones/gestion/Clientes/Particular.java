package telecomunicaciones.gestion.Clientes;

import telecomunicaciones.gestion.Tarifas.Tarifa;

import java.io.Serializable;

public class Particular extends Cliente implements Serializable{

    //Atributos

    private String apellidos;

    //Constructor
    public Particular(){}

    public Particular(String NIF, String nombre,String apellidos, String email, Direccion dir) {

        super(NIF,nombre,dir,email);
        this.apellidos = apellidos;

    }

    //Métodos

    //Setters
    public void cambiarTarifa(Tarifa nuevaTarifa){
        this.setTarifa(nuevaTarifa);
    }

    //Getters
    public String getApellidos() {
        return apellidos;
    }
}
