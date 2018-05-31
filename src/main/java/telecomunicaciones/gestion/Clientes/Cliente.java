package telecomunicaciones.gestion.Clientes;

import telecomunicaciones.gestion.Facturas.Factura;
import telecomunicaciones.gestion.Llamadas.Llamada;
import telecomunicaciones.gestion.Tarifas.*;
import telecomunicaciones.InterfazFechas;

import java.io.Serializable;
import java.util.*;

/**
 * Created by al361880 on 20/02/18.
 * Created by al361866 on 20/02/18.
 */

public abstract class Cliente implements InterfazFechas,Serializable{

    //Atributos
    private String NIF;
    private Tarifa tarifa;
    private String nombre;
    private Direccion direccion;
    private String email;
    private Date fechaAlta;
    private HashMap<Integer, Factura> mapaFacturas;
    private List<Llamada> listaLlamadasCliente;

    //Constructores
    public Cliente(){
    }

    public Cliente(String NIF,String nombre, Direccion direccion, String email) {
        this.NIF = NIF;
        this.tarifa = new TarifaBase();
        this.nombre = nombre;
        this.direccion = direccion;
        this.email = email;
        this.fechaAlta = new Date();
        mapaFacturas = new HashMap<>();
        listaLlamadasCliente = new LinkedList<>();
    }

    //Metodos
    
    //Getters
    public Direccion getDireccion() {
        return direccion;
    }
    public Date getFechaAlta() {
        return fechaAlta;
    }
    public String getNombre() {
        return nombre;
    }
    public String getNIF() {return NIF;}
    public Date getFecha(){return fechaAlta; }
    public Tarifa getTarifa(){return tarifa; }
    public String getEmail() {return email; }
    public Factura getFactura(int cod){return mapaFacturas.get(cod); }
    public HashMap<Integer, Factura> getlistaFacturas() { return mapaFacturas; }
    public List<Llamada> getlistaLlamadasCliente() {return listaLlamadasCliente; }

    //Setters
    
    public void setFecha(Date nueva) {
        fechaAlta = nueva;
    }
    public void setTarifa(Tarifa nueva){
        this.tarifa = nueva;
    }

    public void elegirTarifa(String tipo){

        Tarifa tarifaExtendida = this.tarifa;
        if (tipo.compareTo(OpcionesTarifa.TARDE.getValue()) == 0){
            tarifaExtendida = new Tarde(tarifa,2);
        }else if(tipo.compareTo(OpcionesTarifa.NOCHE.getValue()) == 0){
            tarifaExtendida = new Noche(tarifa,4);
        }else{
            tarifaExtendida = new TarifaBase(5);
        }

        tarifa = tarifaExtendida;
    }

    //Equals

    public boolean equals (Object other) {
        if (this == other) return true;
        if (!(other instanceof Cliente)) return false;
        Cliente cliente = (Cliente) other;
        return NIF == null && cliente.NIF == null || this.NIF.equals(cliente.NIF);
    }

    //toString
    @Override
    public String toString() {
        return "Cliente: " + nombre +" "+ direccion + " Email: " + email;
    }


}
