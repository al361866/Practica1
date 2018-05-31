package telecomunicaciones.swing.modelo;

import telecomunicaciones.gestion.Clientes.Cliente;
import telecomunicaciones.gestion.Exception.FechaFueraRango;
import telecomunicaciones.gestion.Facturas.Factura;
import telecomunicaciones.gestion.Llamadas.Llamada;
import telecomunicaciones.gestion.Tarifas.Tarifa;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Created by al361880 on 27/03/18.
 * Created by al361866 on 20/02/18.
 */

public class GestorFacturas implements Serializable{

    //Atributos

    private HashMap<String,HashMap<Integer,Factura>> mapaFacturasTotal;

    //Constructor

    public GestorFacturas(){
        mapaFacturasTotal = new HashMap<>();
    }

    //Metodos

    public void emitirFactura(Cliente cliente, int cod, Date fechaEmision, Date fechaInicial, Date fechaFinal) throws FechaFueraRango{

        if(fechaInicial.compareTo(fechaFinal)>0) throw new FechaFueraRango();

        double importe = 0;

        Iterator<Llamada> iter = cliente.getlistaLlamadasCliente().listIterator();

        Tarifa tarifa = cliente.getTarifa();
        while (iter.hasNext()){
            Llamada nueva = iter.next();
            if(nueva.getFecha().compareTo(fechaInicial)>=0 && nueva.getFecha().compareTo(fechaFinal) <= 0){

                importe += tarifa.calcularPrecio(nueva);

            }
        }

        Factura nueva = new Factura(cod,cliente.getTarifa(),fechaEmision,importe,fechaInicial,fechaFinal);
        cliente.getlistaFacturas().put(cod,nueva);
        mapaFacturasTotal.put(cliente.getNIF(),cliente.getlistaFacturas());

    }

    //Getters
    public HashMap<String, HashMap<Integer, Factura>> getListaFacturasTotal() {
        return mapaFacturasTotal;
    }
}
