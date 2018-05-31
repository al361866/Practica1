package telecomunicaciones.gestion.Exception;

public class FechaFueraRango extends Exception {
    public FechaFueraRango(){
        super("Debes introducir un periodo de fecha correcto");
    }

}
