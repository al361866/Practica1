package telecomunicaciones.gestion.Clientes.factoria.Clientes;

import telecomunicaciones.gestion.Clientes.Direccion;
import telecomunicaciones.gestion.Clientes.Empresa;
import telecomunicaciones.gestion.Clientes.Particular;

/**
 * Created by al361880 on 17/04/18.
 * Created by al361866 on 17/04/18.
 */
public class FactoriaClientes implements clientes {

    @Override

    public Particular getParticular(){
        return new Particular();
    }

    @Override

    public Empresa getEmpresa(){

        return new Empresa();
    }

    @Override
    public Particular getParticular(String nif, String nombre, String apellidos, String email, Direccion dir) {
        return new Particular(nif,nombre,apellidos,email,dir);
    }

    @Override

    public Empresa getEmpresa(String nif, String nombre, String email, Direccion dir){

        return  new Empresa(nif,nombre,email,dir);

    }
}
