package telecomunicacionesTest.factoriaTest;

import org.junit.Test;
import telecomunicaciones.gestion.Clientes.Direccion;
import telecomunicaciones.gestion.Clientes.Empresa;
import telecomunicaciones.gestion.Clientes.Particular;
import telecomunicaciones.gestion.Clientes.factoria.Clientes.FactoriaClientes;
import static org.junit.Assert.assertEquals;


public class factoriaClientesTest {

    private FactoriaClientes factoriaClientes;

    @Test

    public void factoriaEmpresaTest(){


        factoriaClientes = new FactoriaClientes();
        Direccion direccion = new Direccion("Alicante", "Elche",47300);
        assertEquals(new Empresa("73402648B","Adri","adri@uji.es", direccion),factoriaClientes.getEmpresa("73402648B","Adri","adri@uji.es", direccion));
        assertEquals(new Empresa(),factoriaClientes.getEmpresa());
    }

    @Test

    public void factoriaParticularTest(){

        factoriaClientes = new FactoriaClientes();
        Direccion direccion = new Direccion("Alicante", "Elche",47300);
        assertEquals(new Particular("73402648B","Adri","Sorribas","adri@uji.es", direccion),factoriaClientes.getParticular("73402648B","Adri","Sorribas","adri@uji.es", direccion));
        assertEquals(new Particular(), factoriaClientes.getParticular());
    }
}
