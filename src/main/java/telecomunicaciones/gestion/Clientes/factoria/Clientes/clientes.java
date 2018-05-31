package telecomunicaciones.gestion.Clientes.factoria.Clientes;

import telecomunicaciones.gestion.Clientes.Direccion;
import telecomunicaciones.gestion.Clientes.Empresa;
import telecomunicaciones.gestion.Clientes.Particular;

/**
 * Created by al361880 on 17/04/18.
 * Created by al361866 on 17/04/18.
 */
public interface clientes {

    //Interfaz factoriaClientes
    Particular getParticular();
    Empresa getEmpresa();
    Particular getParticular(String nif, String nombre, String apellidos, String email, Direccion dir);
    Empresa getEmpresa(String nif, String nombre, String email, Direccion dir);

}
