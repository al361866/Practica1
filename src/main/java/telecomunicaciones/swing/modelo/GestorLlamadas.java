package telecomunicaciones.swing.modelo;

import telecomunicaciones.gestion.Clientes.Cliente;
import telecomunicaciones.gestion.Llamadas.Llamada;


import java.io.Serializable;
import java.util.*;

/**
 * Created by al361880 on 27/03/18.
 * Created by al361866 on 20/02/18.
 */

public class GestorLlamadas implements Serializable{

    //Atributo

    private HashMap<String,List<Llamada>> mapaTotalLlamadas;

    //Constructor

    public GestorLlamadas(){
        this.mapaTotalLlamadas = new HashMap<>();
    }

    //Metodos

    public void altaLlamada(Cliente cliente, String NIF, int telefono, Date fecha, int hora, double duracion){

        Llamada alta = new Llamada(telefono, fecha, hora, duracion);
        cliente.getlistaLlamadasCliente().add(alta);
        mapaTotalLlamadas.put(NIF,cliente.getlistaLlamadasCliente());
    }


    //Getters

    public HashMap<String, List<Llamada>> getListaTotalLlamadas() {
        return mapaTotalLlamadas;
    }

}