import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.*;

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
        indice -=1;
        if (indice >= 0 && indice < this.veiculos.size()) {
            return this.veiculos.get(indice);
        }
        return null;
    }

    // public Veiculo localizarVeiculo(String marca, String modelo) {
    //     for (Veiculo v : this.veiculos) {
    //         if (v.marca.equals(marca) && v.modelo.equals(modelo)) {
    //             return v;
    //         }
    //     }
    //     return null;
    // }

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
        System.out.println("RELATÓRIO DE VENDAS MENSAL DE " + mes + "/" + ano +":");
        double totalVendas = 0.0;
        for (Vendedor v : this.vendedores) {
            double comissaoVendedor = v.comissaoTotal(mes, ano);
            if (comissaoVendedor > 0) {
                for (Venda venda : v.getVendas()) {
                    if (venda.getData().getMes() == mes && venda.getData().getAno() == ano) {
                        System.out.println("Vendedor: " + v.getNome() + " (Salário neste mês: R$" + v.getSalario(mes, ano) + ")");
                        System.out.println(venda);
                        System.out.println("***************************************");
                        totalVendas += venda.valor();
                    }
                }
            }
        }
        System.out.println("Total: R$" + totalVendas);
    }
    
    public void relatorio (int ano) {//historicoAnual (int ano) {
        System.out.println("RELATÓRIO DE VENDAS ANUAL DE " + ano +":");
        double totalVendas = 0.0;
        for (Vendedor v : this.vendedores) {
            double comissaoVendedor = v.comissaoTotal(ano);
            if (comissaoVendedor > 0) {
                for (Venda venda : v.getVendas()) {
                    System.out.println("Vendedor: " + v.getNome());// + " (Salário neste ano: R$" + (v.salario * 12 + comissaoVendedor) + ")");
                    if (venda.getData().getAno() == ano) {
                        System.out.println(venda);
                        System.out.println("***************************************");
                        totalVendas += venda.valor();
                    }
                }
            }
        }
        System.out.println("Total: R$" + totalVendas);
    }

    public void relatorio (Vendedor vendedor) {//historicoVendedor (Vendedor vendedor) {
        System.out.println("RELATÓRIO DE VENDAS DO VENDEDOR:");
        System.out.println("Vendas do vendedor " + vendedor.getNome() + " :");
        double totalVendas = 0.0;
        for (Venda venda : vendedor.getVendas()) {
            System.out.println(venda);
            System.out.println("***************************************");
            totalVendas += venda.valor();
        }
        System.out.println("Total: R$" + totalVendas);
    }

    public List<Venda> todasVendas() {
    List<Venda> lista = new ArrayList<>();
    for (Vendedor v : this.vendedores) {
        for (Venda venda : v.getVendas()) {
            // Garanta que venda tenha referencia ao vendedor se necessário:
            try {
                venda.getClass().getMethod("setVendedor", Vendedor.class).invoke(venda, v);
            } catch (Exception e) {
                // se Venda não tem setVendedor, OK — usaremos o vendedor no comparator via laço externo
            }
            lista.add(venda);
        }
    }
    return lista;
}

/**
 * Relatório anual seguindo os 4 critérios:
 * 1) nome do vendedor (alfabética)
 * 2) valor da venda (maior -> menor)
 * 3) data (mais recente -> mais antiga)
 * 4) cpf do comprador
 *
 * Observação: se Venda não contém referência direta ao vendedor, construimos pares temporários.
 */
public void relatorioAnualOrdenado(int ano) {
    System.out.println("RELATÓRIO DE VENDAS ANUAL DE " + ano + ":");
    // construir lista de pares (Vendedor, Venda)
    List<Map.Entry<Vendedor, Venda>> pares = new ArrayList<>();
    for (Vendedor v : this.vendedores) {
        for (Venda venda : v.getVendas()) {
            if (venda.getData().getAno() == ano) {
                pares.add(new AbstractMap.SimpleEntry<>(v, venda));
            }
        }
    }

    Comparator<Map.Entry<Vendedor, Venda>> comp = Comparator
        .comparing((Map.Entry<Vendedor, Venda> e) -> e.getKey().getNome(), String.CASE_INSENSITIVE_ORDER)
        .thenComparing(e -> e.getValue().valor(), Comparator.reverseOrder())
        .thenComparing(e -> {
            Data d = e.getValue().getData();
            // traduz data para inteiro YYYYMMDD para comparação simples
            return d.getAno() * 10000 + d.getMes() * 100 + d.getDia();
        }, Comparator.reverseOrder())
        .thenComparing(e -> e.getValue().getCliente().getCpf());

    Collections.sort(pares, comp);

    double total = 0.0;
    for (Map.Entry<Vendedor, Venda> entry : pares) {
        Vendedor v = entry.getKey();
        Venda vend = entry.getValue();
        System.out.println("Vendedor: " + v.getNome());
        System.out.println(vend);
        System.out.println("***************************************");
        total += vend.valor();
    }
    System.out.println("Total: R$" + total);
}
}
