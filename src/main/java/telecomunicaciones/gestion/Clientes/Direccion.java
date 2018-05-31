package telecomunicaciones.gestion.Clientes;

import java.io.Serializable;

/**
 * Created by al361880 on 20/02/18.
 * Created by al361866 on 20/02/18.
 */
public class Direccion implements Serializable{

    //Atributos

    private String provincia;
    private String poblacion;
    private int codPostal;

    //Constructores

    public Direccion(){}

    public Direccion(String provincia, String poblacion, int codPostal) {
        this.provincia = provincia;
        this.poblacion = poblacion;
        this.codPostal = codPostal;
    }

    //Getters

    public String getProvincia() {
        return provincia;
    }
    public String getPoblacion() {
        return poblacion;
    }
    public int getCodPostal() {
        return codPostal;
    }

    //toString
    @Override
    public String toString() {
        return "Direccion: " + poblacion + " ," + provincia + "(" + codPostal + ")";
    }
}
