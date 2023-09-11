

public class Inecuacion {
    private double coeficienteA;
    private double coeficienteB;
    private String sentido;
    private double valorObjetivo;

    public Inecuacion(double coeficienteA, double coeficienteB, String sentido, double valorObjetivo) {
        this.coeficienteA = coeficienteA;
        this.coeficienteB = coeficienteB;
        this.sentido = sentido;
        this.valorObjetivo = valorObjetivo;
    }

    public double getCoeficienteA() {
        return coeficienteA;
    }

    public double getCoeficienteB() {
        return coeficienteB;
    }

    public String getSentido() {
        return sentido;
    }

    public double getValorObjetivo() {
        return valorObjetivo;
    }
}
