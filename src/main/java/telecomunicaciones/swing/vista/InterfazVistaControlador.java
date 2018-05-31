package telecomunicaciones.swing.vista;

// todo Podéis segregar este interfaz en dos, el que necesita modelo y el que necesita controlador.
public interface InterfazVistaControlador {

    //Pestanya clientes
    String getNombre();
    String getApellidos();
    String getNIF();
    String getPoblacion();
    String getProvincia();
    String getCodPostal();
    String getEmail();
    boolean getjrbNoche();
    boolean getjrbTarde();
    boolean getjrbBasica();


    //Pestanya llamadas
    String getHora();
    String getDia();
    String getMes();
    String getAnyo();
    String getDuracion();
    String getNIFLlamada();
    String getTelefonoLlamada();


    //Pestanya facturacion
    String getNIFFactura();
    int getCodFactura();
    int getDiaInicioPeriodo();
    int getMesInicioPeriodo();
    int getAnyoInicioPeriodo();
    int getDiaFinalPeriodo();
    int getMesFinalPeriodo();
    int getAnyoFinalPeriodo();

    //Pestanya Listado Periodo
    String getNIFListadoPeriodo();
    boolean getCliente();
    boolean getLlamada();
    boolean getFactura();
    int getDiaInicioPeriodoListar();
    int getMesInicioPeriodoListar();
    int getAnyoInicioPeriodoListar();
    int getDiaFinalPeriodoListar();
    int getMesFinalPeriodoListar();
    int getAnyoFinalPeriodoListar();

}
