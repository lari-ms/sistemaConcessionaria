public class Venda {
    private Veiculo veiculo;
    private Cliente cliente;
    private double desconto;
    private Data d;
    private String chassi;

    public Venda(Veiculo veiculo, Cliente cliente, double desconto, Data d, String chassi) {
        this.veiculo = veiculo;
        this.cliente = cliente;
        this.desconto = desconto;
        this.d = d;
        this.chassi = chassi;
    }

    public Venda() { //precisa ter esse construtor tb?
        this.veiculo = null;
        this.cliente = null;
        this.desconto = 0.0;
        this.d = new Data(1, 1, 2000);
        this.chassi = "";
    }

    public Data getData() {
        return this.d;
    }

    public double valor() {
        return this.veiculo.valor - this.desconto;
    }

    public void setDesconto(double desconto, Gerente gerente, String senha) {
        if (gerente.validarAcesso(senha)) {
            this.desconto = desconto;
        }
    }
    
    public double getDesconto() {
        return this.desconto;
    }

    public String getChassi(){
        return this.chassi;
    }

    public Veiculo getVeiculo() { //nao tenho certeza se é necessario, mas acho q s ?
        return this.veiculo;
    }

    public String toString() {
        return "Veiculo: " + this.veiculo.toString() + "\n" +
               "Cliente: " + this.cliente.toString() + "\n" +
               "Valor da venda: R$" + this.valor() + "\n" +
               "Data: " + this.d.toString();
    }
}
