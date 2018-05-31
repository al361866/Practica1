package telecomunicaciones.gestion.Tarifas;

public enum OpcionesTarifa {

    TARDE("T"),
    NOCHE("N"),
    BASE("B");

    private final String value;

    OpcionesTarifa(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
