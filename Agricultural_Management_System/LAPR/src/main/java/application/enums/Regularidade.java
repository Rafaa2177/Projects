package application.enums;

public enum Regularidade {
    TODOS_DIAS("T"),
    IMPARES("I"),
    PARES("P"),
    CADA_TRES("3");

    private final String codigo;
    Regularidade(String codigo){
        this.codigo=codigo;
    }

    public String getCodigo() {
        return codigo;
    }
}
