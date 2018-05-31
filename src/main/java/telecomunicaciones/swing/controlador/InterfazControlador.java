package telecomunicaciones.swing.controlador;

public interface InterfazControlador {

    //Fichero

    void cargarDatos();
    void grabarDatos();

    //Clientes
    void nuevaEntradaCliente();
    void borraEntradaCliente();
    void buscaEntradaCliente();
    void buscaListadoClientes();
    void cambiaTarifaCliente();

    //Llamadas
    void nuevaEntradaLlamada();
    void buscaEntradaLlamada();

    //Facturas
    void nuevaEntradaFactura();
    void buscaEntradaFactura();
    void buscaListadoFacturas();

    //ListadoPeriodo
    void buscaListado();
}
