package telecomunicaciones.swing.modelo;

import telecomunicaciones.gestion.Clientes.Cliente;
import telecomunicaciones.gestion.Facturas.Factura;
import telecomunicaciones.gestion.Llamadas.Llamada;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

// todo Podéis segregar este interface en dos, lo que necesita controlador y lo que necesita vista.
public interface InterfazModeloControlador {

    //Cargadores fichero
    void cargarDatosFichero();
    void grabarDatosFichero();

    //Clientes
    void anyadirNuevoCliente(String NIF, Cliente cliente);
    void borrarCliente(String nif);
    void buscaCliente(String nombre);
    Cliente getCliente(String NIF);
    HashMap<String,Cliente> getGestorClientes();
    void cambiarTarifa(String NIF,String tipo);


    //Llamadas
    void altaLlamada(Cliente cliente, String NIF, int telefono, Date fecha, int hora, double duracion);
    void buscaLlamada(String nombre);
    HashMap<String,List<Llamada>> getGestorLlamada();
    void buscaLlamadaPeriodo(Llamada llamada);


    //Facturas
    void emitirNuevaFactura(Cliente cliente, int cod, Date fechaEmision, Date fechaInicial, Date fechaFinal);
    void buscaFactura(String NIF, int cod);
    HashMap<String,HashMap<Integer,Factura>> getGestorFacturas();

    //ListadoPeriodo

    HashSet<Cliente>  muestraPeriodoClientes(HashSet<Cliente> conjuntoClientes, Date fechaInicio, Date fechaFinal);
    HashSet<Llamada>  muestraPeriodoLlamadas(HashSet<Llamada> conjuntoLlamadas, Date fechaInicio, Date fechaFinal);
    HashSet<Factura>  muestraPeriodoFacturas(HashSet<Factura> conjuntoFacturas, Date fechaInicio, Date fechaFinal);


}
