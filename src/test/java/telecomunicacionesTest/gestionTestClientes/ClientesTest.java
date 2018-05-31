package telecomunicacionesTest.gestionTestClientes;

import org.junit.Before;
import org.junit.Test;
import telecomunicaciones.gestion.Clientes.*;
import telecomunicaciones.gestion.Clientes.factoria.Clientes.FactoriaClientes;
import telecomunicaciones.gestion.Exception.DniAlreadyExists;
import telecomunicaciones.gestion.Exception.DniNotFound;
import telecomunicaciones.gestion.Exception.FechaFueraRango;
import telecomunicaciones.gestion.Main.Main;
import telecomunicaciones.swing.modelo.GestorClientes;

import java.util.Date;
import java.util.HashSet;
import static junit.framework.TestCase.fail;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;


public class ClientesTest {

    private static GestorClientes mapaClientes;
    private FactoriaClientes factoriaClientes= new FactoriaClientes();

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
            nuevo = factoriaClientes.getEmpresa("73402640X","Belen","belen@gmail.es",direccion);
            fechaAlta = new Date(120,6,1);
            nuevo.setFecha(fechaAlta);
            mapaClientes.anyadirmapaClientes("73402640X",nuevo);
        }catch (DniAlreadyExists e){
            fail("No sede entrar");
        }

    }

    @Test
    public void tamanyoMapaClientesTest(){

        try {
            assertThat(mapaClientes,notNullValue());
            assertEquals(2,mapaClientes.getmapaClientes().size());

            Direccion direccion = new Direccion("Alcante", "Elche",47300);
            Cliente nuevo = factoriaClientes.getEmpresa("73408596L","Ferran","al361866@uji.es",direccion);
            mapaClientes.anyadirmapaClientes("73408596L",nuevo);
            assertEquals(3,mapaClientes.getmapaClientes().size());

            mapaClientes.borrarmapaClientes("73408596L");
            assertEquals(2,mapaClientes.getmapaClientes().size());

            mapaClientes.borrarmapaClientes("73402648B");
            assertEquals(1,mapaClientes.getmapaClientes().size());

            mapaClientes.borrarmapaClientes("73402640X");
            assertEquals(0,mapaClientes.getmapaClientes().size());

        }catch (DniNotFound e){
            fail("No sede entrar");
        }catch (DniAlreadyExists e){
            fail("No sede entrar");
        }
    }

    @Test
    public  void borrarClienteTest(){

        try {

            mapaClientes.borrarmapaClientes("73402648B");
            assertEquals(1,mapaClientes.getmapaClientes().size());

            mapaClientes.borrarmapaClientes("73402640X");
            assertEquals(0,mapaClientes.getmapaClientes().size());
        }catch (DniNotFound e){
            fail("No sede entrar");
        }

    }

    @Test

    public  void anyadirClienteTest(){
        try {
            Direccion direccion = new Direccion("Alicante", "Elche",47300);
            Cliente nuevo = factoriaClientes.getEmpresa("73408596L","Ferran","al361866@uji.es",direccion);
            mapaClientes.anyadirmapaClientes("73408596L",nuevo);

            assertEquals(3,mapaClientes.getmapaClientes().size());

            assertEquals(nuevo,mapaClientes.recuperarDatos("73408596L"));

        }catch (DniAlreadyExists e){
            fail("No debe entrar");

        }catch (DniNotFound e){
            fail("No debe entrar");
        }
    }

    @Test

    public void constructorClientesTest(){
        try {

            Direccion direccion = new Direccion("Castellon", "Morella",12300);
            Date fechaAlta = new Date(118,1,1);
            Cliente nuevo = factoriaClientes.getEmpresa("73402648P","Adrian","al361880@uji.es",direccion);
            nuevo.setFecha(fechaAlta);
            mapaClientes.anyadirmapaClientes("73402648P",nuevo);

            assertEquals(nuevo,mapaClientes.getmapaClientes().get("73402648P"));

            assertEquals("Adrian",nuevo.getNombre());
            assertEquals("al361880@uji.es",nuevo.getEmail());
            assertEquals("73402648P",nuevo.getNIF());
            assertEquals(direccion,nuevo.getDireccion());
        }catch (DniAlreadyExists e){
            fail("No debe entrar");
        }

    }

    @Test

    public void nombreClienteTest(){

        assertEquals("Adrian",mapaClientes.getmapaClientes().get("73402648B").getNombre());

    }

    @Test

    public void direccionCliente(){
        assertEquals("Castellon",mapaClientes.getmapaClientes().get("73402648B").getDireccion().getProvincia());
        assertEquals("Morella",mapaClientes.getmapaClientes().get("73402648B").getDireccion().getPoblacion());
        assertEquals(12300,mapaClientes.getmapaClientes().get("73402648B").getDireccion().getCodPostal());
    }

    @Test

    public void fechaAltaTest(){

        Date fecha = new Date(118,1,1);
        assertEquals(fecha,mapaClientes.getmapaClientes().get("73402648B").getFechaAlta());

        fecha = new Date(120,6,1);
        assertEquals(fecha,mapaClientes.getmapaClientes().get("73402640X").getFechaAlta());

    }

    @Test

    public void constructorParticular(){

        try {
            Direccion direccion = new Direccion("Castellon", "Vinaros",123470);
            Date fechaAlta = new Date(118,1,1);
            Particular nuevo = factoriaClientes.getParticular("77809896A","Sergio","Sorribas","sergio@uji.es",direccion);
            nuevo.setFecha(fechaAlta);
            mapaClientes.anyadirmapaClientes("77809896A",nuevo);

            assertEquals("Sorribas",nuevo.getApellidos());
            assertEquals(nuevo,mapaClientes.getmapaClientes().get("77809896A"));
            assertEquals("Sergio",nuevo.getNombre());
            assertEquals("sergio@uji.es",nuevo.getEmail());
            assertEquals(direccion,nuevo.getDireccion());
            assertEquals("77809896A",nuevo.getNIF());
        }catch (DniAlreadyExists e){
            fail("No sede entrar");
        }
    }

    @Test

    public void constructorEmpesa(){

        try {
            Direccion direccion = new Direccion("Alicante", "Cocentaina",123480);
            Date fechaAlta = new Date(120,7,1);
            Empresa nuevo = factoriaClientes.getEmpresa("20405260K","Luis","luis@uji.es",direccion);
            nuevo.setFecha(fechaAlta);
            mapaClientes.anyadirmapaClientes("20405260K",nuevo);

            assertEquals(nuevo,mapaClientes.getmapaClientes().get("20405260K"));
            assertEquals(fechaAlta,mapaClientes.getmapaClientes().get("20405260K").getFechaAlta());
        }catch (DniAlreadyExists e){
            fail("No sede entrar");
        }
    }

    @Test

    public void dniNoEncontradoBorrarExcepcionTest(){

        try {
            mapaClientes.borrarmapaClientes("78945612");//Dni no existe
            fail("No debe llegar");
        }catch (DniNotFound e){

        }
    }

    @Test
    public void dniNoEncontradoRecuperarDatosTest(){
        try {
            mapaClientes.recuperarDatos("71112233");//Dni no existe
            fail("No debe llegar");
        }catch (DniNotFound e){

        }
    }

    @Test

    public void anyadirDniExistenteExcepcionTest(){

        try {
            Cliente cliente = factoriaClientes.getEmpresa();
            mapaClientes.anyadirmapaClientes("73402648B",cliente);//El dni ya existe
            fail("No debe llegar");

        }catch (DniAlreadyExists e){

        }
    }

    @Test

    public void toStringTest(){

        Direccion direccion = new Direccion("Castellon", "Vinaros",123470);
        Particular nuevo = factoriaClientes.getParticular("77809896A","Sergio","Sorribas","sergio@uji.es",direccion);

        assertEquals("Cliente: " + "Sergio" +" "+ direccion + " Email: " + "sergio@uji.es",nuevo.toString());

    }

    @Test

    public void muestreoGenericoClientesTest(){
        try {
            Main metodoGenerico = new Main();
            HashSet<Cliente> conjuntoClientes = new HashSet<>();
            conjuntoClientes.addAll(mapaClientes.getmapaClientes().values());


            Date fechaInicio = new Date(117,1,1);
            Date fechaFinal = new Date(121,1,1);
            assertEquals(2,metodoGenerico.muestraPeriodo(conjuntoClientes,fechaInicio,fechaFinal).size());

            fechaInicio = new Date(117,1,1);
            fechaFinal = new Date(119,1,1);
            assertEquals(1,metodoGenerico.muestraPeriodo(conjuntoClientes,fechaInicio,fechaFinal).size());
        }catch (FechaFueraRango e){
            fail("No debe entrar");
        }
    }

}
