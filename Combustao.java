public class Combustao extends Veiculo {
    private double autonomiaComb; // em km
    private double capacidadeComb; // em litros

    public Combustao(String marca, String modelo, int anoFab, int mesFab, int anoMod, double valor, double autonComb, double capComb) {
        super(marca, modelo, anoFab, mesFab, anoMod, valor);
        this.autonomiaComb = autonComb;
        this.capacidadeComb = capComb;
    }

    public int getAutonomia() {
        return (int) autonomiaComb; 
    }

    public String toString() { //Honda HRV 2015/2016 - Autonomia: 400.0km
        return super.toString() + "Combustão";//super.marca + super.modelo + super.anoMod + "/" + super.anoFab + " - Autonomia: " + this.autonomiaComb + ".0km (Combustão)";
    }
}
