package telecomunicacionesTest.decoradorTest;

import org.junit.Test;
import telecomunicaciones.gestion.Clientes.Cliente;
import telecomunicaciones.gestion.Clientes.Direccion;
import telecomunicaciones.gestion.Clientes.factoria.Clientes.FactoriaClientes;
import telecomunicaciones.gestion.Tarifas.factoria.Tarifa.FactoriaTarifaClientes;
import telecomunicaciones.gestion.Exception.FechaFueraRango;
import telecomunicaciones.swing.modelo.GestorFacturas;
import telecomunicaciones.swing.modelo.GestorLlamadas;
import telecomunicaciones.gestion.Tarifas.Tarifa;
import java.util.Date;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.fail;

public class decoradorTarifasTest {


    //Atributos
    private FactoriaClientes factoriaClientes;
    private FactoriaTarifaClientes factoriaTarifaClientes;

    @Test

    public void tarifaBaseTest(){

        factoriaTarifaClientes = new FactoriaTarifaClientes();

        Tarifa tarifa = factoriaTarifaClientes.getBase();
        assertEquals(5.0, tarifa.getPrecio(),0);

        tarifa = factoriaTarifaClientes.getBase(10);
        assertEquals(10.0,tarifa.getPrecio());


    }

    @Test

    public void tarifaNocheTest(){


        factoriaTarifaClientes = new FactoriaTarifaClientes();

        Tarifa tarifa = factoriaTarifaClientes.getNoche();
        assertEquals(5.0, tarifa.getPrecio(),0);

        tarifa = factoriaTarifaClientes.getNoche(tarifa,10);
        assertEquals(10.0,tarifa.getPrecio());
        assertEquals("Tarifa adicional(€/min): 10.0", tarifa.toString());


        tarifa = factoriaTarifaClientes.getNoche(tarifa,2);
        assertEquals(2.0, tarifa.getPrecio(),0);


        assertEquals("Tarifa adicional(€/min): 2.0", tarifa.toString());
    }

    @Test

    public void tarifaTardeTest(){


        factoriaTarifaClientes = new FactoriaTarifaClientes();

        Tarifa tarifa = factoriaTarifaClientes.getTarde();
        assertEquals(5.0, tarifa.getPrecio(),0);

        tarifa = factoriaTarifaClientes.getTarde(tarifa,20);
        assertEquals(20.0,tarifa.getPrecio());

        tarifa = factoriaTarifaClientes.getTarde(tarifa,2);
        assertEquals(2.0, tarifa.getPrecio(),0);

        assertEquals("Tarifa adicional(€/min): 2.0", tarifa.toString());
    }

    @Test
    public  void importeLlamadasSegunTardeTest(){
        Direccion direccion = new Direccion("Alcante", "Elche",47300);
        factoriaClientes = new FactoriaClientes();
        Cliente nuevo = factoriaClientes.getParticular("73402648B","Adri","Sorri","adri@uji.es",direccion);
        nuevo.elegirTarifa("T");
        GestorLlamadas mapaLlamadas = new GestorLlamadas();
        GestorFacturas mapaFacturas = new GestorFacturas();
        try {

            Date fecha = new Date(118,1,1);
            mapaLlamadas.altaLlamada(nuevo,"73402648B",681130613,fecha,14,1.0);//Tarde 2€

            fecha = new Date(119,1,1);
            mapaLlamadas.altaLlamada(nuevo,"73402648B",681130613,fecha,15,1.0);//Tarde 2€


            fecha = new Date(120,1,1);
            mapaLlamadas.altaLlamada(nuevo,"73402648B",681130613,fecha,5,1.0);//Base 5€

            fecha = new Date(118,2,1);
            mapaLlamadas.altaLlamada(nuevo,"73402648B",681130613,fecha,10,1.0);//Base 5€

            Date fechaEmision = new Date(120,1,2);
            Date fechaInicio = new Date(117,1,1);
            Date fechaFinal = new Date(120,1,2);
            mapaFacturas.emitirFactura(nuevo,1,fechaEmision,fechaInicio,fechaFinal);

            Double importe = mapaFacturas.getListaFacturasTotal().get("73402648B").get(1).getImporte();
            assertEquals(14.0, importe);


        }catch (FechaFueraRango e){
            fail("No debe entrar");
        }


    }

    @Test
    public  void importeLlamadasSegunNocheTest(){
        Direccion direccion = new Direccion("Alcante", "Elche",47300);
        factoriaClientes = new FactoriaClientes();
        Cliente nuevo = factoriaClientes.getParticular("73402648B","Adri","Sorri","adri@uji.es",direccion);
        nuevo.elegirTarifa("N");
        GestorLlamadas mapaLlamadas = new GestorLlamadas();
        GestorFacturas mapaFacturas = new GestorFacturas();
        try {

            Date fecha = new Date(118,1,1);
            mapaLlamadas.altaLlamada(nuevo,"73402648B",681130613,fecha,21,1.0);//Noche 4€

            fecha = new Date(119,1,1);
            mapaLlamadas.altaLlamada(nuevo,"73402648B",681130613,fecha,23,1.0);//Noche 4€

            fecha = new Date(120,1,1);
            mapaLlamadas.altaLlamada(nuevo,"73402648B",681130613,fecha,5,1.0);//Base 5€

            fecha = new Date(118,2,1);
            mapaLlamadas.altaLlamada(nuevo,"73402648B",681130613,fecha,10,1.0);//Base 5€

            Date fechaEmision = new Date(120,1,2);
            Date fechaInicio = new Date(117,1,1);
            Date fechaFinal = new Date(120,1,2);
            mapaFacturas.emitirFactura(nuevo,1,fechaEmision,fechaInicio,fechaFinal);

            Double importe = mapaFacturas.getListaFacturasTotal().get("73402648B").get(1).getImporte();
            assertEquals(18.0, importe);


        }catch (FechaFueraRango e){
            fail("No debe entrar");
        }


    }

    @Test
    public  void importeLlamadasSegunBaseTest(){
        Direccion direccion = new Direccion("Alcante", "Elche",47300);
        factoriaClientes = new FactoriaClientes();
        Cliente nuevo = factoriaClientes.getParticular("73402648B","Adri","Sorri","adri@uji.es",direccion);
        nuevo.elegirTarifa("B");
        GestorLlamadas mapaLlamadas = new GestorLlamadas();
        GestorFacturas mapaFacturas = new GestorFacturas();
        try {

            Date fecha = new Date(118,1,1);
            mapaLlamadas.altaLlamada(nuevo,"73402648B",681130613,fecha,21,1.0);//Base 5€

            fecha = new Date(119,1,1);
            mapaLlamadas.altaLlamada(nuevo,"73402648B",681130613,fecha,23,1.0);//Base 5€

            fecha = new Date(120,1,1);
            mapaLlamadas.altaLlamada(nuevo,"73402648B",681130613,fecha,16,1.0);//Base 5€

            fecha = new Date(118,2,1);
            mapaLlamadas.altaLlamada(nuevo,"73402648B",681130613,fecha,10,1.0);//Base 5€

            Date fechaEmision = new Date(120,1,2);
            Date fechaInicio = new Date(117,1,1);
            Date fechaFinal = new Date(120,1,2);
            mapaFacturas.emitirFactura(nuevo,1,fechaEmision,fechaInicio,fechaFinal);

            Double importe = mapaFacturas.getListaFacturasTotal().get("73402648B").get(1).getImporte();
            assertEquals(20.0, importe);


        }catch (FechaFueraRango e){
            fail("No debe entrar");
        }


    }
}
