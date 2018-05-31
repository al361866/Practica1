package telecomunicacionesTest.gestionFacturasTest;
import org.junit.Before;
import org.junit.Test;
import telecomunicaciones.gestion.Clientes.Cliente;
import telecomunicaciones.gestion.Clientes.Direccion;
import telecomunicaciones.gestion.Clientes.factoria.Clientes.FactoriaClientes;
import telecomunicaciones.gestion.Exception.FechaFueraRango;
import telecomunicaciones.gestion.Facturas.Factura;
import telecomunicaciones.swing.modelo.GestorFacturas;
import telecomunicaciones.gestion.Llamadas.Llamada;
import telecomunicaciones.gestion.Main.Main;
import telecomunicaciones.gestion.Tarifas.Tarifa;
import telecomunicaciones.gestion.Tarifas.factoria.Tarifa.FactoriaTarifaClientes;

import java.util.Date;
import java.util.HashSet;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class FacturasTest {

    private static GestorFacturas mapaFacturas;
    private FactoriaClientes factoriaClientes = new FactoriaClientes();
    private FactoriaTarifaClientes factoriaTarifaClientes = new FactoriaTarifaClientes();


    @Before
    public void rellenarFacturas(){
        try {
            mapaFacturas = new GestorFacturas();
            Direccion direccion = new Direccion("Valencia", "Segorbe",46005);
            Date fechaEmision = new Date(120,1,7);
            Date fechaInicioPeriodo = new Date(115,1,1);
            Date fechaFinPeriodo = new Date(119,1,1);
            Cliente nuevo = factoriaClientes.getEmpresa("73402647X","Belen","belen@uji.es",direccion);
            Tarifa tarifa = factoriaTarifaClientes.getBase(10.0);

            nuevo.setTarifa(tarifa);

            Date fechaLlamada = new Date(116,1,1);
            Llamada llamada = new Llamada(987656431,fechaLlamada,10,2);
            nuevo.getlistaLlamadasCliente().add(llamada);
            fechaLlamada = new Date(117,1,1);
            llamada = new Llamada(987656421,fechaLlamada,11,1);
            nuevo.getlistaLlamadasCliente().add(llamada);
            fechaLlamada = new Date(120,1,1);
            llamada = new Llamada(987656421,fechaLlamada,11,10);
            nuevo.getlistaLlamadasCliente().add(llamada);

            mapaFacturas.emitirFactura(nuevo,1,fechaEmision,fechaInicioPeriodo,fechaFinPeriodo);//2 llamadas

            fechaEmision = new Date(121,1,1);
            fechaInicioPeriodo = new Date(116,1,1);
            fechaFinPeriodo = new Date(116,2,1);
            mapaFacturas.emitirFactura(nuevo,2,fechaEmision,fechaInicioPeriodo,fechaFinPeriodo);//1 llamada

            fechaEmision = new Date(122,1,1);
            fechaInicioPeriodo = new Date(115,1,1);
            fechaFinPeriodo = new Date(120,1,1);
            mapaFacturas.emitirFactura(nuevo,3,fechaEmision,fechaInicioPeriodo,fechaFinPeriodo);//3 llamadas
        }catch (FechaFueraRango e){
            fail("No sede entrar");
        }
    }

    @Test
    public void emitirFactura(){
        try {
            assertEquals(3,mapaFacturas.getListaFacturasTotal().get("73402647X").size());

            Direccion direccion = new Direccion("Alicante", "Elche",47005);
            Cliente nuevo = factoriaClientes.getEmpresa("73402605L","Ferran","al361866@uji.es",direccion);
            Date fechaEmision = new Date(120,1,7);
            Date fechaInicioPeriodo = new Date(115,1,1);
            Date fechaFinPeriodo = new Date(119,1,1);
            mapaFacturas.emitirFactura(nuevo,7,fechaEmision,fechaInicioPeriodo,fechaFinPeriodo);//0 llamadas

            assertEquals(1,mapaFacturas.getListaFacturasTotal().get("73402605L").size());
        }catch (FechaFueraRango e){
            fail("No sede entrar");
        }

    }

    @Test

    public  void facturasImporteTest(){

        assertEquals(20.0,mapaFacturas.getListaFacturasTotal().get("73402647X").get(2).getImporte(),0);
        assertEquals(30.0,mapaFacturas.getListaFacturasTotal().get("73402647X").get(1).getImporte(),0);
        assertEquals(130.0,mapaFacturas.getListaFacturasTotal().get("73402647X").get(3).getImporte(),0);

    }

    @Test

    public void modficarTarifa(){

        try {
            Direccion direccion = new Direccion("Valencia", "Segorbe",46005);
            Date fechaEmision = new Date(120,1,7);
            Date fechaInicioPeriodo = new Date(115,1,1);
            Date fechaFinPeriodo = new Date(119,1,1);
            Cliente nuevo = factoriaClientes.getEmpresa("73402647H","Belen","belen@uji.es",direccion);
            Tarifa tarifa = factoriaTarifaClientes.getBase(10.0);

            nuevo.setTarifa(tarifa);

            Date fechaLlamada = new Date(116,1,1);
            Llamada llamada = new Llamada(987656431,fechaLlamada,10,2);
            nuevo.getlistaLlamadasCliente().add(llamada);
            mapaFacturas.emitirFactura(nuevo,4,fechaEmision,fechaInicioPeriodo,fechaFinPeriodo);//1 llamada
            assertEquals(20.0,mapaFacturas.getListaFacturasTotal().get("73402647H").get(4).getImporte(),0);
            assertEquals(fechaEmision,nuevo.getFactura(4).getFecha());
            tarifa = factoriaTarifaClientes.getBase(4);
            nuevo.setTarifa(tarifa);
            mapaFacturas.emitirFactura(nuevo,5,fechaEmision,fechaInicioPeriodo,fechaFinPeriodo);//1 llamadas
            assertEquals(8.0,mapaFacturas.getListaFacturasTotal().get("73402647H").get(5).getImporte(),0);
        }catch (FechaFueraRango e){
            fail("No sede entrar");
        }

    }

    @Test

    public void constructorTest(){
        Date fechaEmision = new Date(120,1,7);
        Date fechaInicioPeriodo = new Date(115,1,1);
        Date fechaFinPeriodo = new Date(119,1,1);
        Tarifa tarifaInicial = factoriaTarifaClientes.getBase();
        Factura factura = new Factura(7,tarifaInicial,fechaEmision,150,fechaInicioPeriodo,fechaFinPeriodo);

        assertEquals(fechaEmision,factura.getFecha());
        assertEquals(fechaInicioPeriodo,factura.getFechaInicial());
        assertEquals(fechaFinPeriodo,factura.getFechaFinal());
        assertEquals(tarifaInicial,factura.getTarifaInicial());
        assertEquals(7,factura.getCod());
        assertEquals(150,factura.getImporte(),0);

    }

    @Test

    public void excepcionPeriodoNoValidoFacturaTest(){
        try {

            Direccion direccion = new Direccion("Alicante", "Elche",47005);
            Cliente nuevo = factoriaClientes.getEmpresa("73402605L","Ferran","al361866@uji.es",direccion);
            Date fechaEmision = new Date(120,1,7);

            Date fechaInicioPeriodo = new Date(120,1,1);//Inicio mas grande que fin
            Date fechaFinPeriodo = new Date(119,1,1);
            mapaFacturas.emitirFactura(nuevo,7,fechaEmision,fechaInicioPeriodo,fechaFinPeriodo);//0 llamadas
            assertEquals(1,nuevo.getlistaFacturas().size());
            assertEquals(1,mapaFacturas.getListaFacturasTotal().get("73402605L").size());
            fail("No debe llegar");

        }catch (FechaFueraRango e){

        }
    }

    @Test

    public void toStringTest(){
        try {
            Direccion direccion = new Direccion("Alicante", "Elche",47005);
            Cliente nuevo =factoriaClientes.getEmpresa("73402605L","Ferran","al361866@uji.es,",direccion);
            Tarifa tarifa = factoriaTarifaClientes.getBase(2);
            nuevo.setTarifa(tarifa);
            Date fechaEmision = new Date(120,1,7);
            Date fechaInicioPeriodo = new Date(115,1,1);
            Date fechaFinPeriodo = new Date(119,1,1);
            mapaFacturas.emitirFactura(nuevo,7,fechaEmision,fechaInicioPeriodo,fechaFinPeriodo);



            assertEquals("Factura : " + 7+ " con una tarifa de " + tarifa +
                    " debe abonar un importe de " + 0.0 + "€. (Fecha de emision " + fechaEmision + " ) y periodo de facturacion desde "
                    + fechaInicioPeriodo + " hasta " + fechaFinPeriodo,mapaFacturas.getListaFacturasTotal().get("73402605L").get(7).toString());


        }catch (FechaFueraRango e){
            fail("No debe entrar");
        }
    }

    @Test

    public void muestreoGenericoFacturasTest(){
        try {
            Main metodoGenerico = new Main();

            HashSet<Factura> conjuntoFacturas = new HashSet<>();
            conjuntoFacturas.addAll(mapaFacturas.getListaFacturasTotal().get("73402647X").values());

            Date fechaInicio = new Date(120,1,1);
            Date fechaFinal = new Date(123,1,1);
            assertEquals(3,metodoGenerico.muestraPeriodo(conjuntoFacturas,fechaInicio,fechaFinal).size());
            fechaInicio = new Date(120,1,1);
            fechaFinal = new Date(121,12,30);
            assertEquals(2,metodoGenerico.muestraPeriodo(conjuntoFacturas,fechaInicio,fechaFinal).size());

        }catch (FechaFueraRango e){
            fail("No debe llegar");
        }
    }

}
