import java.util.ArrayList;

public class Vendedor extends Funcionario{
    private double comissao;
    private ArrayList<Venda> vendidos;
    
    public Vendedor(String nome, String cpf, int dia, int mes, int ano, double salario, double comissao) {
        super(nome, cpf, dia, mes, ano, salario);
        this.comissao = comissao;
        this.vendidos = new ArrayList<Venda>(); // Lista de vendas
    }

    public double getComissao() {
        return this.comissao;
    }

    public void addVenda(Venda v) {
        vendidos.add(v);
    }

    public ArrayList<Venda> getVendas() {
        return vendidos;
    }

    public double comissaoTotal(int mes, int ano) {
        double comissaoTotal = 0.0;
        for (Venda v : vendidos) {
            if (v.getData().getMes() == mes && v.getData().getAno() == ano) {
                comissaoTotal += v.valor() * (this.comissao / 100);
            }
        }
        return comissaoTotal;
    }

    public double comissaoTotal(int ano) {
        double comissaoTotal = 0.0;
        for (Venda v : vendidos) {
            if (v.getData().getAno() == ano) {
                comissaoTotal += v.valor() * (this.comissao / 100);
            }
        }
        return comissaoTotal;   
    }

    public double getSalario (int mes, int ano) {
        return this.salario + this.comissaoTotal(mes, ano);
    }
}
