public class Eletrico extends Veiculo {
    private double autonomiaBat;
    private double capacidadeBat;

    public Eletrico(String marca, String modelo, int anoFab, int mesFab, int anoMod, double valor, double autonBat, double capBat) {
        super(marca, modelo, anoFab, mesFab, anoMod, valor);
        this.autonomiaBat = autonBat;
        this.capacidadeBat = capBat;
    }

    public int getAutonomia() {
        return (int) autonomiaBat;
    }

    public String toString() {
        return super.toString() + " (Elétrico)";
    }
}