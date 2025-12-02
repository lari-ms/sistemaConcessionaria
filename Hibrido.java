public class Hibrido extends Veiculo {
    private double autonomiaComb;
    private double capacidadeComb;
    private double autonomiaBat;
    private double capacidadeBat;

    public Hibrido(String marca, String modelo, int anoFab, int mesFab, int anoMod, double valor, double autonComb, double capComb, double autonBat, double capBat) {
        super(marca, modelo, anoFab, mesFab, anoMod, valor);
        this.autonomiaComb = autonComb;
        this.capacidadeComb = capComb;
        this.autonomiaBat = autonBat;
        this.capacidadeBat = capBat;
    }

    public double getAutonomiaComb() {
        return autonomiaComb;
    }

    public double getCapacidadeComb() {
        return capacidadeComb;
    }

    public double getAutonomiaBat() {
        return autonomiaBat;
    }

    public double getCapacidadeBat() {
        return capacidadeBat;
    }

    public int getAutonomia() {
        return (int) (autonomiaComb + autonomiaBat); // !!! Não sei se ele quer aproximado
    }

    public String toString() {
        return super.toString() + " (Híbrido)";
    }
}