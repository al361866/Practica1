package telecomunicaciones.gestion.Main;

/**
 * Created by al361880 on 20/02/18.
 * Created by al361866 on 20/02/18.
 */

public enum OpcionesMenu {

    DAR_ALTA_CLIENTE("Dar de alta a un cliente nuevo."),
    DAR_BAJA_CLIENTE("Dar de baja a un cliente existente."),
    CAMBIAR_TARIFA_CLIENTE("Modificar tarifa actual."),
    RECUPERAR_DATOS_CLIENTE("Recuperar datos del cliente."),
    RECUPERAR_LISTADO_CLIENTES("Obtener numero total de clientes."),
    DAR_ALTA_LLAMADA("Dar de alta una nueva llamada."),
    LISTAR_LLAMADAS("Listar el numero de llamadas de un cliente."),
    EMITIR_FACTURA("Emitir una nueva factura."),
    RECUPERAR_DATOS_FACTURA("Recuperar datos factura."),
    RECUPERAR_FACTURAS("Recuperar el numero de facturas del cliente."),
    MOSTRAR_CLIENTES_ALTA_PERIODO("Mostrar un listado de clientes que fueron dados de alta entre dos fechas."),
    MOSTRAR_LLAMADAS_CLIENTE_PERIODO("Mostrar un listado de llamadas de un cliente que fueron realizadas entre dos fechas."),
    MOSTRAR_FACTURAS_CLIENTE_PERIODO("Mostrar un listado de facturas de un cliente emitidas entre dos fechas."),
    SALIR("Para salir del menu y finalizar la ejecucion.");


    private String descripcion;

    OpcionesMenu(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public static OpcionesMenu getOpcion(int posicion) {
        return values()[posicion];
    }

    public static String getMenu() {
        StringBuilder sb = new StringBuilder();
        for(OpcionesMenu opcion: OpcionesMenu.values()) {
            sb.append(opcion.ordinal());
            sb.append(".- ");
            sb.append(opcion.getDescripcion());
            sb.append("\n");
        }
        return sb.toString();
    }
}
