package telecomunicaciones.swing.modelo;

import telecomunicaciones.gestion.Clientes.Cliente;
import telecomunicaciones.gestion.Exception.DniAlreadyExists;
import telecomunicaciones.gestion.Exception.DniNotFound;
import telecomunicaciones.gestion.Exception.FechaFueraRango;
import telecomunicaciones.gestion.Facturas.Factura;
import telecomunicaciones.gestion.Llamadas.Llamada;
import telecomunicaciones.gestion.Main.Main;
import telecomunicaciones.swing.vista.InterfazVistaControlador;
import telecomunicaciones.swing.vista.InterfazVistaModelo;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;


public class ModeloControlador implements InterfazModeloControlador, InterfazModeloVista {

    private GestorClientes gestorClientes;
    private GestorLlamadas gestorLlamadas;
    private GestorFacturas gestorFacturas;
    private InterfazVistaControlador vistaControlador;
    private InterfazVistaModelo vistaModelo;
    private String descripcionCliente;
    private String descripcionLlamada;
    private String descripcionFactura;
    private Main main;

    public ModeloControlador(){
        gestorClientes = new GestorClientes();
        gestorLlamadas = new GestorLlamadas();
        gestorFacturas = new GestorFacturas();
        main = new Main();
    }

    public void setVistaControlador(InterfazVistaControlador vista) {
        this.vistaControlador = vista;
    }
    public void setVistaModelo(InterfazVistaModelo vista) {
        this.vistaModelo = vista;
    }

    //Cargadores fichero
    @Override
    public void cargarDatosFichero(){

        try{
            FileInputStream fis = new FileInputStream("listasapp.bin");
            ObjectInputStream ois = new ObjectInputStream(fis);
            gestorClientes = (GestorClientes)ois.readObject();
            gestorFacturas = (GestorFacturas) ois.readObject();
            gestorLlamadas = (GestorLlamadas) ois.readObject();
            ois.close();
        }catch (IOException | ClassNotFoundException e){
            e.printStackTrace();
        }
        vistaModelo.actualizaLlamada();
        vistaModelo.actualizaFactura();
        vistaModelo.actualizaCliente();
    }

    @Override
    public void grabarDatosFichero(){
        main.grabarDatosFichero(gestorClientes,gestorLlamadas,gestorFacturas);

    }

    //Pestanya clientes

    @Override
    public void anyadirNuevoCliente(String NIF, Cliente cliente){
        try {
            gestorClientes.anyadirmapaClientes(NIF,cliente);
            vistaModelo.actualizaCliente();

        }catch (DniAlreadyExists e){
            e.printStackTrace();
        }

    }

    @Override
    public int getnClientes(){
        return gestorClientes.getmapaClientes().size();
    }

    @Override
    public HashMap<String, Cliente> getGestorClientes(){
        return gestorClientes.getmapaClientes();
    }


    @Override
    public void buscaCliente(String NIF){
        try {
            descripcionCliente = gestorClientes.recuperarDatos(NIF).toString();

            vistaModelo.nuevoResultadoCliente();
        }catch (DniNotFound e) {
            e.printStackTrace();
        }
    }

    @Override
    public  String getCliente(){
        return descripcionCliente;
    }

    @Override
    public Cliente getCliente(String NIF){
        return gestorClientes.getmapaClientes().get(NIF);
    }


    @Override
    public void borrarCliente(String NIF){

        try {
            gestorClientes.borrarmapaClientes(NIF);
            vistaModelo.actualizaCliente();
        }catch (DniNotFound e){
            e.printStackTrace();
        }
    }
    @Override
    public void cambiarTarifa(String NIF,String tipo){

        gestorClientes.getmapaClientes().get(NIF).elegirTarifa(tipo);
        vistaModelo.actualizaCliente();

    }

    //Pestanya llamadas

    @Override
    public void altaLlamada(Cliente cliente, String NIF, int telefono, Date fecha, int hora, double duracion){
        gestorLlamadas.altaLlamada(cliente,NIF,telefono,fecha,hora,duracion);
        vistaModelo.actualizaLlamada();
    }

    @Override
    public int getnLlamadas(){
        return gestorLlamadas.getListaTotalLlamadas().size();
    }

    @Override
    public void buscaLlamada(String nombre){

        List<Llamada> listaLlamadas = gestorLlamadas.getListaTotalLlamadas().get(nombre);

        for(Llamada llamada: listaLlamadas){
            descripcionLlamada = llamada.toString();
            vistaModelo.nuevoResultadoLlamada();
        }
    }

    @Override
    public  String getLlamada(){
        return descripcionLlamada;
    }
    @Override
    public void buscaLlamadaPeriodo(Llamada llamada){
        descripcionLlamada = llamada.toString();
        vistaModelo.nuevoResultadoLlamada();
    }
    @Override

    public HashMap<String,List<Llamada>> getGestorLlamada(){
        return gestorLlamadas.getListaTotalLlamadas();
    }

    //Pestanya facturas

    @Override
    public void  emitirNuevaFactura(Cliente cliente, int cod, Date fechaEmision, Date fechaInicial, Date fechaFinal){

        try{
            gestorFacturas.emitirFactura(cliente,cod,fechaEmision,fechaInicial,fechaFinal);
            vistaModelo.actualizaFactura();
        }catch (FechaFueraRango e){
            e.printStackTrace();
        }
    }

    @Override
    public HashMap<String,HashMap<Integer,Factura>>getGestorFacturas(){
        return gestorFacturas.getListaFacturasTotal();
    }
    @Override

    public int getnFacturas(){
        return gestorFacturas.getListaFacturasTotal().size();
    }

    @Override
    public void buscaFactura(String NIF, int cod){
        descripcionFactura = gestorFacturas.getListaFacturasTotal().get(NIF).get(cod).toString();
        vistaModelo.nuevoResultadoFactura();
    }

    @Override
    public String getFactura(){
        return descripcionFactura;
    }


    //Pestanya listado periodos

    @Override

    public HashSet<Cliente>  muestraPeriodoClientes(HashSet<Cliente> conjuntoClientes, Date fechaInicio, Date fechaFinal){
        HashSet<Cliente> conjunto = new HashSet<>();
        try{

            conjunto = main.muestraPeriodo(conjuntoClientes,fechaInicio,fechaFinal);

        }catch (FechaFueraRango e){
            e.printStackTrace();
        }
        return conjunto;
    }

    @Override
    public HashSet<Llamada>  muestraPeriodoLlamadas(HashSet<Llamada> conjuntoLlamadas, Date fechaInicio, Date fechaFinal){

        HashSet<Llamada>  conjunto = new HashSet<>();
        try{

            conjunto = main.muestraPeriodo(conjuntoLlamadas,fechaInicio,fechaFinal);

        }catch (FechaFueraRango e){
            e.printStackTrace();
        }
        return conjunto;
    }

    @Override

    public HashSet<Factura>  muestraPeriodoFacturas(HashSet<Factura> conjuntoFacturas, Date fechaInicio, Date fechaFinal){

        HashSet<Factura> conjuto = new HashSet<>();
        try {
            conjuto = main.muestraPeriodo(conjuntoFacturas,fechaInicio,fechaFinal);
        }catch (FechaFueraRango e){
            e.printStackTrace();
        }
        return conjuto;
    }

}
