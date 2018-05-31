package telecomunicacionesTest.factoriaTest;

import org.junit.Test;
import telecomunicaciones.gestion.Tarifas.factoria.Tarifa.FactoriaTarifaClientes;
import telecomunicaciones.gestion.Tarifas.Noche;
import telecomunicaciones.gestion.Tarifas.Tarde;
import telecomunicaciones.gestion.Tarifas.TarifaBase;
import static org.junit.Assert.assertEquals;


public class factoriaTarifasTest {

    private FactoriaTarifaClientes factoriaTarifaClientes;

    @Test
    public void factoriaNocheTest(){
        factoriaTarifaClientes = new FactoriaTarifaClientes();
        assertEquals(new Noche(), factoriaTarifaClientes.getNoche());
        assertEquals(new Noche(new TarifaBase(),4),factoriaTarifaClientes.getNoche(new TarifaBase(),4));
    }

    @Test
    public void factoriaTardeTest(){
        factoriaTarifaClientes = new FactoriaTarifaClientes();

        assertEquals(new Tarde(), factoriaTarifaClientes.getTarde());
        assertEquals(new Tarde(new TarifaBase(),4),factoriaTarifaClientes.getTarde(new TarifaBase(),4));

    }

    @Test
    public void factoriaBaseTest(){
        factoriaTarifaClientes = new FactoriaTarifaClientes();
        assertEquals(new TarifaBase(), factoriaTarifaClientes.getBase());
        assertEquals(new TarifaBase(8),factoriaTarifaClientes.getBase(8));

    }

}
