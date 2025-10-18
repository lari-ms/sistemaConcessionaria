import java.util.ArrayList;

public class Sistema {
    private ArrayList<Cliente> clientes;
    private ArrayList<Gerente> gerentes;
    private ArrayList<Vendedor> vendedores;
    private ArrayList<Veiculo> veiculos;

    public Sistema() {
        this.clientes = new ArrayList<>();
        this.gerentes = new ArrayList<>();
        this.vendedores = new ArrayList<>();
        this.veiculos = new ArrayList<>();
    }

    public void redirect(Sistema s, Entrada io, int op) {
        switch(op) {
                case 1: // cadCliente
                    io.cadCliente(s);
                    break;
                case 2: // cadVendedor
                    io.cadVendedor(s);
                    break;
                case 3: // cadGerente
                    io.cadGerente(s);
                    break;
                case 4: // cadVeiculo
                    io.cadVeiculo(s);
                    break;
                case 5: // cadVenda
                    io.cadVenda(s);
                    break;
        }
    }

/**************************************************
    * CLIENTES - CLIENTES - CLIENTES - CLIENTES *
 ***************************************************/

    public ArrayList<Cliente> getClientes() {
        return clientes;
    }

    public void adicionar(Cliente cliente){
        this.clientes.add(cliente);
    }

    public void listarClientes() {
        System.out.println("Clientes cadastrados:");

        if (clientes.isEmpty()) {
            System.out.println("Nenhum cliente cadastrado");
        }
        else {
            for (Cliente c : this.clientes) {
                System.out.println(c);
            }
        }
    }

    public Cliente localizarCliente(String cpf) {
        for (Cliente c : this.clientes) {
            if (c.getCpf().equals(cpf)) {
                return c;
            }
        }
        return null;
    }

/**************************************************
    * GERENTES - GERENTES - GERENTES - GERENTES *
 ***************************************************/

    public ArrayList<Gerente> getGerentes() {
        return gerentes;
    }

    public void adicionar(Gerente gerente){
        this.gerentes.add(gerente);
    }

    public void listarGerentes() {
        System.out.println("Gerentes cadastrados:");

        if (gerentes.isEmpty()) {
            System.out.println("Nenhum gerente cadastrado");
        }
        else {
            for (Gerente g : this.gerentes) {
                System.out.println(g);
            }
        }
    }

    public Gerente localizarGerente(String cpf) {
        for (Gerente g : this.gerentes) {
            if (g.getCpf().equals(cpf)) {
                return g;
            }
        }
        return null;
    }

/**************************************************
 * VENDEDORES - VENDEDORES - VENDEDORES - VENDEDORES *
 ***************************************************/

    public ArrayList<Vendedor> getVendedores() {
        return vendedores;
    }

    public void adicionar(Vendedor vendedor){
        this.vendedores.add(vendedor);
    }

    public void listarVendedores() {
        System.out.println("Vendedores cadastrados:");

        if (vendedores.isEmpty()) {
            System.out.println("Nenhum vendedor cadastrado");
        }
        else {
            for (Vendedor v : this.vendedores) {
                System.out.println(v);
            }
        }
    }

    public Vendedor localizarVendedor(String cpf) {
        for (Vendedor v : this.vendedores) {
            if (v.getCpf().equals(cpf)) {
                return v;
            }
        }
        return null;
    }
    
/**************************************************
    * VEÍCULOS - VEÍCULOS - VEÍCULOS - VEÍCULOS *
 ***************************************************/

    public ArrayList<Veiculo> getVeiculos() {
        return veiculos;
    }

    public void adicionar(Veiculo veiculo){
        this.veiculos.add(veiculo);
    }

    public void listarVeiculos() {
        System.out.println("Veiculos cadastrados:");

        if (veiculos.isEmpty()) {
            System.out.println("Nenhum veiculo cadastrado");
        }
        else {
            for (Veiculo v : this.veiculos) {
                System.out.println(v);
            }
        }
    }

    public Veiculo identificarVeiculo(int indice) {
        if (indice >= 0 && indice < this.veiculos.size()) {
            return this.veiculos.get(indice);
        }
        return null;
    }

    public Veiculo localizarVeiculo(String marca, String modelo) {
        for (Veiculo v : this.veiculos) {
            if (v.marca.equals(marca) && v.modelo.equals(modelo)) {
                return v;
            }
        }
        return null;
    }

/**************************************************
    * VENDAS - VENDAS - VENDAS - VENDAS *
 ***************************************************/

    public void atribuirVendaVendedor(Venda venda, Vendedor vendedor) {
        vendedor.addVenda(venda);
    }

    /*public void listarVendas() {
        System.out.println("Vendas cadastradas:");

        for (Vendedor v : this.vendedores) {
            for (Venda venda : v.getVendas()) {
                System.out.println(venda);
            }
        }
    }*/
            
/**************************************************
* HISTÓRICOS - HISTÓRICOS - HISTÓRICOS - HISTÓRICOS *
 ***************************************************/

    public void relatorio (int mes, int ano) {//historicoMensal (int mes, int ano) {
        System.out.println("*** RELATÓRIO DE VENDAS MENSAL DE " + mes + "/" + ano + "***");
        double totalVendas = 0.0;
        for (Vendedor v : this.vendedores) {
            double comissaoVendedor = v.comissaoTotal(mes, ano);
            if (comissaoVendedor > 0) {
                System.out.println("Vendedor: " + v.getNome() + " (Salário neste mês: R$" + v.getSalario(mes, ano) + ")");
                for (Venda venda : v.getVendas()) {
                    if (venda.getData().getMes() == mes && venda.getData().getAno() == ano) {
                        System.out.println(venda);
                        System.out.println("***************************************");
                        totalVendas += venda.valor();
                    }
                }
            }
        }
        System.out.println("Total: R$" + String.format("%.2f", totalVendas));
    }
    
    public void relatorio (int ano) {//historicoAnual (int ano) {
        System.out.println("*** RELATÓRIO DE VENDAS ANUAL DE " + ano + " ***");
        double totalVendas = 0.0;
        for (Vendedor v : this.vendedores) {
            double comissaoVendedor = v.comissaoTotal(ano);
            if (comissaoVendedor > 0) {
                System.out.println("Vendedor: " + v.getNome() + " (Salário neste ano: R$" + (v.salario * 12 + comissaoVendedor) + ")");
                for (Venda venda : v.getVendas()) {
                    if (venda.getData().getAno() == ano) {
                        System.out.println(venda);
                        System.out.println("***************************************");
                        totalVendas += venda.valor();
                    }
                }
            }
        }
        System.out.println("Total: R$" + String.format("%.2f", totalVendas));
    }

    public void relatorio (Vendedor vendedor) {//historicoVendedor (Vendedor vendedor) {
        System.out.println("*** RELATÓRIO DE VENDAS DO VENDEDOR ***");
        System.out.println("Vendas do vendedor " + vendedor.getNome() + " :");
        double totalVendas = 0.0;
        for (Venda venda : vendedor.getVendas()) {
            System.out.println(venda);
            System.out.println("***************************************");
            totalVendas += venda.valor();
        }
        System.out.println("Total: R$" + String.format("%.2f", totalVendas));
    }
}
