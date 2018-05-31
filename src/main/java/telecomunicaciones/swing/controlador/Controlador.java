package telecomunicaciones.swing.controlador;

import telecomunicaciones.gestion.Clientes.Cliente;
import telecomunicaciones.gestion.Clientes.Direccion;
import telecomunicaciones.gestion.Clientes.Empresa;
import telecomunicaciones.gestion.Clientes.Particular;
import telecomunicaciones.gestion.Facturas.Factura;
import telecomunicaciones.gestion.Llamadas.Llamada;
import telecomunicaciones.gestion.Tarifas.OpcionesTarifa;
import telecomunicaciones.swing.modelo.InterfazModeloControlador;
import telecomunicaciones.swing.vista.InterfazVistaControlador;
import java.util.*;
import static java.lang.Integer.parseInt;

public class Controlador implements InterfazControlador {
    private InterfazVistaControlador vista;
    private InterfazModeloControlador modeloControlador;

    public Controlador() {
    }

    public void setVista(InterfazVistaControlador vista) {
        this.vista = vista;
    }
    public void setModeloControlador(InterfazModeloControlador modelo) {
        this.modeloControlador = modelo;
    }

    //Cargadores fichero
    @Override
    public void cargarDatos() {
        modeloControlador.cargarDatosFichero();
    }

    @Override
    public void grabarDatos() {
        modeloControlador.grabarDatosFichero();
    }

    //Pestanya clientes

    @Override
    public void nuevaEntradaCliente() {

        String NIF = vista.getNIF();
        String nombre = vista.getNombre();
        String apellidos = vista.getApellidos();
        String poblacion = vista.getPoblacion();
        String provincia = vista.getProvincia();
        String codpostal = vista.getCodPostal();
        String email = vista.getEmail();
        String tipoTarifa = "B";

        if (vista.getjrbTarde()) {
            tipoTarifa = OpcionesTarifa.TARDE.getValue();

        } else if (vista.getjrbNoche()) {

            tipoTarifa = OpcionesTarifa.NOCHE.getValue();

        } else if(vista.getjrbBasica()) {
            tipoTarifa = OpcionesTarifa.BASE.getValue();
        }

        Direccion dir = new Direccion(provincia, poblacion, parseInt(codpostal));
        if (apellidos.equals("")) {
            Empresa empresa = new Empresa(NIF, nombre, email, dir);
            empresa.elegirTarifa(tipoTarifa);
            modeloControlador.anyadirNuevoCliente(NIF, empresa);
        } else {
            Particular particular = new Particular(NIF, nombre, apellidos, email, dir);
            particular.elegirTarifa(tipoTarifa);
            modeloControlador.anyadirNuevoCliente(NIF, particular);
        }
    }

    @Override
    public void buscaEntradaCliente() {

        String NIF = vista.getNIF();
        modeloControlador.buscaCliente(NIF);
    }

    @Override
    public void buscaListadoClientes() {

        HashMap<String, Cliente> mapaClientes = modeloControlador.getGestorClientes();

        for (String NIF : mapaClientes.keySet()) {
            modeloControlador.buscaCliente(NIF);
        }
    }

    @Override
    public void borraEntradaCliente() {
        String NIF = vista.getNIF();
        modeloControlador.borrarCliente(NIF);
    }

    @Override
    public void cambiaTarifaCliente() {
        String NIF = vista.getNIF();
        String tipoTarifa;

        if (vista.getjrbTarde()) {
            tipoTarifa = OpcionesTarifa.TARDE.getValue();
        } else if (vista.getjrbNoche()) {
            tipoTarifa = OpcionesTarifa.NOCHE.getValue();
        } else {
            tipoTarifa = OpcionesTarifa.BASE.getValue();
        }
        modeloControlador.cambiarTarifa(NIF, tipoTarifa);
    }

    //Pestanya llamadas

    @Override
    public void nuevaEntradaLlamada() {
        String NIFLlamada = vista.getNIFLlamada();
        int telefono = parseInt(vista.getTelefonoLlamada());
        int hora = parseInt(vista.getHora());
        int dia = parseInt(vista.getDia());
        int mes = parseInt(vista.getMes());
        int anyo = parseInt(vista.getAnyo());
        double duracion = parseInt(vista.getDuracion());
        Date fecha = new Date(anyo, mes, dia);
        Cliente cliente = modeloControlador.getCliente(NIFLlamada);

        modeloControlador.altaLlamada(cliente, NIFLlamada, telefono, fecha, hora, duracion);
    }

    @Override
    public void buscaEntradaLlamada() {

        String NIF = vista.getNIFLlamada();
        modeloControlador.buscaLlamada(NIF);
    }

    //Pestanya facturacion

    @Override
    public void nuevaEntradaFactura() {
        String NIFFactura = vista.getNIFFactura();
        int codigo = vista.getCodFactura();
        int diaInicio = vista.getDiaInicioPeriodo();
        int mesInicio = vista.getMesInicioPeriodo();
        int anyoInicio = vista.getAnyoInicioPeriodo();
        int diaFinal = vista.getDiaFinalPeriodo();
        int mesFinal = vista.getMesFinalPeriodo();
        int anyoFinal = vista.getAnyoFinalPeriodo();

        Date fechaEmision = new Date();
        fechaEmision.toLocaleString();

        Date fechaInicioPeriodo = new Date(anyoInicio, mesInicio, diaInicio);
        Date fechaFinalPeriodo = new Date(anyoFinal, mesFinal, diaFinal);
        Cliente cliente = modeloControlador.getCliente(NIFFactura);

        modeloControlador.emitirNuevaFactura(cliente, codigo, fechaEmision, fechaInicioPeriodo, fechaFinalPeriodo);

    }

    @Override
    public void buscaEntradaFactura(){

        String NIF = vista.getNIFFactura();
        int codigo = vista.getCodFactura();
        modeloControlador.buscaFactura(NIF,codigo);
    }


    @Override
    public void buscaListadoFacturas() {

        String NIF = vista.getNIFFactura();
        HashMap<Integer, Factura> mapaFacturasCliente = modeloControlador.getGestorFacturas().get(NIF);

        for (Integer cod : mapaFacturasCliente.keySet()) {
            modeloControlador.buscaFactura(NIF,cod);
        }
    }
    //Listado periodo

    @Override
    public void buscaListado() {

        String NIF = vista.getNIFListadoPeriodo();
        int diaInicio = vista.getDiaInicioPeriodoListar();
        int mesInicio = vista.getMesInicioPeriodoListar();
        int anyoInicio = vista.getAnyoInicioPeriodoListar();
        int diaFinal = vista.getDiaFinalPeriodoListar();
        int mesFinal = vista.getMesFinalPeriodoListar();
        int anyoFinal = vista.getAnyoFinalPeriodoListar();
        Date fechaInicioPeriodo = new Date(anyoInicio, mesInicio, diaInicio);
        Date fechaFinalPeriodo = new Date(anyoFinal, mesFinal, diaFinal);

        if (vista.getCliente()) {

            HashSet<Cliente> conjuntoCli = new HashSet<>(modeloControlador.getGestorClientes().values());
            HashSet<Cliente> conjuntoResultado = modeloControlador.muestraPeriodoClientes(conjuntoCli,fechaInicioPeriodo,fechaFinalPeriodo);

            for (Cliente aRetorno : conjuntoResultado) {
                modeloControlador.buscaCliente(aRetorno.getNIF());
            }

        }else if(vista.getLlamada()) {

            List<Llamada> value = modeloControlador.getGestorLlamada().get(NIF);
            HashSet<Llamada> conjuntoLlamadas = new HashSet<>(value);
            HashSet<Llamada> conjuntoResultado = modeloControlador.muestraPeriodoLlamadas(conjuntoLlamadas,fechaInicioPeriodo,fechaFinalPeriodo);
            for(Llamada aRetorno : conjuntoResultado){

                modeloControlador.buscaLlamadaPeriodo(aRetorno);
            }
        }else if(vista.getFactura()){

            HashMap<Integer,Factura> mapaFacturasTotal = modeloControlador.getGestorFacturas().get(NIF);
            HashSet<Factura> conjuntoFacturas = new HashSet<>(mapaFacturasTotal.values());
            HashSet<Factura> conjuntoResultado =modeloControlador.muestraPeriodoFacturas(conjuntoFacturas,fechaInicioPeriodo,fechaFinalPeriodo);
            for (Factura aRetorno : conjuntoResultado) {
                modeloControlador.buscaFactura(NIF,aRetorno.getCod());
            }
         }
    }

}