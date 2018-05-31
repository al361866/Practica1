package telecomunicacionesTest.gestionLlamadasTest;

import org.junit.Before;
import org.junit.Test;
import telecomunicaciones.gestion.Clientes.Cliente;
import telecomunicaciones.gestion.Clientes.Direccion;
import telecomunicaciones.gestion.Clientes.factoria.Clientes.FactoriaClientes;
import telecomunicaciones.gestion.Exception.FechaFueraRango;
import telecomunicaciones.swing.modelo.GestorLlamadas;
import telecomunicaciones.gestion.Llamadas.Llamada;
import telecomunicaciones.gestion.Main.Main;

import java.util.Date;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.fail;

public class LlamadasTest {

    private static GestorLlamadas mapaLlamadas;
    private FactoriaClientes factoriaClientes = new FactoriaClientes();

    @Before
    public void rellenarLlamadas(){

        mapaLlamadas = new GestorLlamadas();

        Direccion direccion = new Direccion("Castellon", "Morella",12300);
        Date fechaAlta = new Date(118,1,1);
        Cliente nuevo = factoriaClientes.getEmpresa("73402648B","Adrian","al361880@uji.es",direccion);
        nuevo.setFecha(fechaAlta);

        Date fechaRealizacion = new Date(118,1,1);
        mapaLlamadas.altaLlamada(nuevo,"73402648B",666666666,fechaRealizacion,10,1);
        fechaRealizacion = new Date(118,2,1);
        mapaLlamadas.altaLlamada(nuevo,"73402648B",666666667,fechaRealizacion,11,0.3);
        fechaRealizacion = new Date(118,2,7);
        mapaLlamadas.altaLlamada(nuevo,"73402648B",666666667,fechaRealizacion,1,2);
        fechaRealizacion = new Date(118,2,8);
        mapaLlamadas.altaLlamada(nuevo,"73402648B",666666677,fechaRealizacion,3,4);
    }

    @Test

    public  void tamanyoMapaLlamadasTest(){

        assertEquals(4,mapaLlamadas.getListaTotalLlamadas().get("73402648B").size());

    }

    @Test
    public void constructorLlamadasTest(){
        Date fechaRealizacion = new Date(118,1,1);
        Llamada llamada = new Llamada(666666666,fechaRealizacion,10,2.3);
        assertEquals(666666666,llamada.getTelefono());
        assertEquals(10,llamada.getHora());
        assertEquals(2.3,llamada.getDuracion());
        assertEquals(fechaRealizacion,llamada.getFecha());
    }

    @Test

    public  void altaLlamadaTest(){

        Direccion direccion = new Direccion("Valencia", "Segorbe",46005);
        Date fechaAlta = new Date(118,1,1);
        Cliente nuevo = factoriaClientes.getEmpresa("73402647X","Belen","belen@uji.es",direccion);
        nuevo.setFecha(fechaAlta);

        Date fechaRealizacion = new Date(119,1,1);
        mapaLlamadas.altaLlamada(nuevo,"73402647X",666666674,fechaRealizacion,14,3);
        assertEquals(1,mapaLlamadas.getListaTotalLlamadas().get("73402647X").size());
        fechaRealizacion = new Date(120,1,1);
        mapaLlamadas.altaLlamada(nuevo,"73402647X",666666677,fechaRealizacion,4,1);
        assertEquals(2,mapaLlamadas.getListaTotalLlamadas().get("73402647X").size());
        assertEquals(2,nuevo.getlistaLlamadasCliente().size());

        List<Llamada> listaLlamadas = mapaLlamadas.getListaTotalLlamadas().get("73402647X");

        for (Llamada llamada: listaLlamadas){
            if(llamada.getTelefono() == 666666677){
                assertEquals(666666677,llamada.getTelefono());
                assertEquals(fechaRealizacion, llamada.getFecha());
                assertEquals(1.0,llamada.getDuracion());
                assertEquals(4,llamada.getHora());
            }
        }

        assertEquals(2,mapaLlamadas.getListaTotalLlamadas().get("73402647X").size());
    }

    @Test

    public void toStringTest(){
        Date fechaRealizacion = new Date(119,1,1);

        Llamada llamada = new Llamada(654321987,fechaRealizacion,10,4);
        assertEquals("Llamada a " + 654321987 + " en fecha de " + fechaRealizacion +
                " a la hora " + 10 + " con una duracion de " + 4.0 +" minutos\n",llamada.toString());

    }

    @Test

    public void muestreoGenericoLlamadasTest(){

        try {
            Main metodoGenerico = new Main();
            List<Llamada> listaLlamadas = new LinkedList<>();
            listaLlamadas.addAll(mapaLlamadas.getListaTotalLlamadas().get("73402648B"));

            HashSet<Llamada> conjuntoLlamadas = new HashSet<>(listaLlamadas);

            Date fechaInicio = new Date(117,1,1);
            Date fechaFinal = new Date(121,1,1);
            assertEquals(4,metodoGenerico.muestraPeriodo(conjuntoLlamadas,fechaInicio,fechaFinal).size());
            fechaInicio = new Date(118,1,1);
            fechaFinal = new Date(118,2,7);
            assertEquals(3,metodoGenerico.muestraPeriodo(conjuntoLlamadas,fechaInicio,fechaFinal).size());
        }catch (FechaFueraRango e){
            fail("No debe entrar");
        }
    }

}
