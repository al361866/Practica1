package telecomunicaciones.gestion.Exception;

public class DniAlreadyExists extends  Exception {
    public DniAlreadyExists(){super("El dni introducido ya existe en la base de datos");}
}
