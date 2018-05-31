package telecomunicacionesTest.gestionTarifaTest;

import org.junit.Before;
import org.junit.Test;
import telecomunicaciones.gestion.Clientes.*;
import telecomunicaciones.gestion.Clientes.factoria.Clientes.FactoriaClientes;
import telecomunicaciones.gestion.Exception.DniAlreadyExists;
import telecomunicaciones.gestion.Tarifas.Tarifa;
import telecomunicaciones.gestion.Tarifas.factoria.Tarifa.FactoriaTarifaClientes;
import telecomunicaciones.swing.modelo.GestorClientes;

import java.util.Date;
import static junit.framework.TestCase.assertEquals;

public class TarifasTest {

    private static GestorClientes mapaClientes;
    private FactoriaClientes factoriaClientes = new FactoriaClientes();
    private FactoriaTarifaClientes factoriaTarifaClientes = new FactoriaTarifaClientes();


    @Before

    public void rellenarClientes(){
        try {
            mapaClientes = new GestorClientes();

            Direccion direccion = new Direccion("Castellon", "Morella",12300);
            Date fechaAlta = new Date(118,1,1);
            Cliente nuevo = factoriaClientes.getEmpresa("73402648B","Adrian","al361880@uji.es",direccion);
            nuevo.setFecha(fechaAlta);
            mapaClientes.anyadirmapaClientes("73402648B",nuevo);

            direccion = new Direccion("Valencia", "El Puig",46005);
            nuevo = factoriaClientes.getParticular("73402640X","Belen","Sorribas","belen@gmail.es",direccion);
            fechaAlta = new Date(120,6,1);
            nuevo.setFecha(fechaAlta);
            mapaClientes.anyadirmapaClientes("73402640X",nuevo);

        }catch (DniAlreadyExists e){

        }

    }

    @Test

    public void setTarifaParticular(){

        try {
            assertEquals(5.0,mapaClientes.getmapaClientes().get("73402640X").getTarifa().getPrecio());

            Tarifa nueva = factoriaTarifaClientes.getBase(10.9);
            mapaClientes.getmapaClientes().get("73402640X").setTarifa(nueva);
            assertEquals(nueva,mapaClientes.getmapaClientes().get("73402640X").getTarifa());
            assertEquals(10.9,mapaClientes.getmapaClientes().get("73402640X").getTarifa().getPrecio());

            Direccion direccion = new Direccion("Valencia", "Segorbe",45025);
            Date fechaAlta = new Date(119,2,1);
            Particular nuevo = factoriaClientes.getParticular("73402641X","Belen","Sorribas","Belen@uji.es",direccion);
            nuevo.setFecha(fechaAlta);
            mapaClientes.anyadirmapaClientes("73402641X",nuevo);
            Tarifa tarifa = factoriaTarifaClientes.getBase(4.7);
            nuevo.cambiarTarifa(tarifa);
            assertEquals(tarifa,nuevo.getTarifa());
        }catch (DniAlreadyExists e){

        }


    }

    @Test

    public void setTarifaEmpresa(){

        try {

            assertEquals(5.0,mapaClientes.getmapaClientes().get("73402648B").getTarifa().getPrecio());

            Tarifa nueva = factoriaTarifaClientes.getBase(7.0);
            mapaClientes.getmapaClientes().get("73402648B").setTarifa(nueva);
            assertEquals(nueva,mapaClientes.getmapaClientes().get("73402648B").getTarifa());
            assertEquals(7.0,mapaClientes.getmapaClientes().get("73402648B").getTarifa().getPrecio());


            Direccion direccion = new Direccion("Castellon", "Morella",12300);
            Date fechaAlta = new Date(118,1,1);
            Empresa nuevo = factoriaClientes.getEmpresa("73402647B","Adrian","al361880@uji.es",direccion);
            nuevo.setFecha(fechaAlta);
            mapaClientes.anyadirmapaClientes("73402647B",nuevo);
            Tarifa tarifa = factoriaTarifaClientes.getBase(4);
            nuevo.cambiarTarifa(tarifa);
            assertEquals(tarifa,nuevo.getTarifa());
        }catch (DniAlreadyExists e){

        }
    }

    @Test

    public void getTarifaParticular(){

        assertEquals(5.0,mapaClientes.getmapaClientes().get("73402640X").getTarifa().getPrecio());
        Tarifa tarifa = factoriaTarifaClientes.getBase(47.5);
        assertEquals(47.5,tarifa.getPrecio());
    }

    @Test
    public void getTarifaEmpresa(){
        assertEquals(5.0,mapaClientes.getmapaClientes().get("73402648B").getTarifa().getPrecio());
        Tarifa tarifa = factoriaTarifaClientes.getBase(4.0);
        assertEquals(4.0,tarifa.getPrecio());
    }

    @Test

    public void constructorTarifasTest(){
        Tarifa tarifa = factoriaTarifaClientes.getBase();
        assertEquals(5.0,tarifa.getPrecio());

        Tarifa tarifa2 = factoriaTarifaClientes.getBase();
        assertEquals(tarifa.getPrecio(),tarifa2.getPrecio());

        tarifa = factoriaTarifaClientes.getBase(4.9);

        assertEquals(4.9,tarifa.getPrecio());
    }

    @Test

    public void toStringTest(){
        Tarifa tarifa = factoriaTarifaClientes.getBase(4.5);
        assertEquals(" Tarifa(€/min): " + 4.5,tarifa.toString());
    }
}
