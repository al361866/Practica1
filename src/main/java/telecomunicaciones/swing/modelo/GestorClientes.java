package telecomunicaciones.swing.modelo;


import telecomunicaciones.gestion.Clientes.Cliente;
import telecomunicaciones.gestion.Exception.DniAlreadyExists;
import telecomunicaciones.gestion.Exception.DniNotFound;
import java.io.Serializable;
import java.util.*;

public class GestorClientes implements Serializable{

    //Atributos
    private HashMap<String,Cliente> mapaClientes;

    //Constructor

    public GestorClientes(){
        this.mapaClientes = new HashMap<>();
    }

    //Metodos

    public void anyadirmapaClientes(String nif, Cliente cliente) throws DniAlreadyExists{

        if (mapaClientes.containsKey(nif)) throw new DniAlreadyExists();
        mapaClientes.put(nif, cliente);

    }

    public void borrarmapaClientes(String nif) throws DniNotFound{

        if (!mapaClientes.containsKey(nif)) throw new DniNotFound();
        mapaClientes.remove(nif);

    }

    public HashMap<String, Cliente> getmapaClientes() {
        return mapaClientes;
    }

    public Cliente recuperarDatos(String NIF) throws DniNotFound{

        if (!mapaClientes.containsKey(NIF)) throw new DniNotFound();

        return mapaClientes.get(NIF);
    }



}
