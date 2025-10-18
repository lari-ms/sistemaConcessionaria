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

    public int getAutonomia() {
        return (int) (autonomiaComb + autonomiaBat); // !!! Não sei se ele quer aproximado
    }

    public String toString() { // BYD Song Pro 2025/2026 - Autonomia: 1110.0km (Híbrido)
        return super.toString() + "(Híbrido)"; //super.marca + super.modelo + super.anoMod + "/" + super.anoFab + " - Autonomia: " + (this.autonomiaComb + this.autonomiaBat) + ".0km (Híbrido)"; 
    }
}