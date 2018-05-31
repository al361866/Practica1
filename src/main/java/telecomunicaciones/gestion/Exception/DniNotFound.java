package telecomunicaciones.gestion.Exception;

public class DniNotFound extends  Exception {

    public DniNotFound(){
        super("El dni buscado no existe.");
    }

}