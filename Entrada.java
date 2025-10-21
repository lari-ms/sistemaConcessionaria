import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Classe com as rotinas de entrada e saída do projeto
 * @author Hilario Seibel Junior, Larissa Santos e Lucas Silverio Gums
 */
public class Entrada {
    public Scanner input;

    /**
     * Construtor da classe Entrada
     * Se houver um arquivo input.txt na pasta em que o projeto está sendo executado,
     * define que o Scanner vai ler deste arquivo e não do teclado.
     * Se o arquivo não existir, define que o Scanner vai ler da entrada padrão (teclado)
     * NÃO ALTERE O CODIGO DESSA CLASSE!
     */
    public Entrada() {
        try {
            // Se houver um arquivo input.txt, o Scanner vai ler dele.
            this.input = new Scanner(new FileInputStream("input.txt"));
        } catch (FileNotFoundException e) {
            // Caso contrário, vai ler do teclado.
            this.input = new Scanner(System.in);
        }
    }

    /**
     * Faz a leitura de uma linha inteira
     * Ignora linhas começando com #, que vão indicar comentários no arquivo de entrada:
     * @param msg: Mensagem que será exibida ao usuário
     * @return Uma String contendo a linha que foi lida
     */
    private String lerLinha(String msg) {
        // Imprime uma mensagem ao usuário, lê uma e retorna esta linha
        System.out.print(msg);
        String linha = this.input.nextLine();

        // Ignora linhas começando com #, que vão indicar comentários no arquivo de entrada:
        while (linha.charAt(0) == '#') linha = this.input.nextLine();
        return linha;
    }

    /**
     * Faz a leitura de um número inteiro
     * @param msg: Mensagem que será exibida ao usuário
     * @return O número digitado pelo usuário convertido para int
     */
    private int lerInteiro(String msg) {
        // Imprime uma mensagem ao usuário, lê uma linha contendo um inteiro e retorna este inteiro
        String linha = this.lerLinha(msg);
        return Integer.parseInt(linha);
    }

    /**
     * Faz a leitura de um double
     * @param msg: Mensagem que será exibida ao usuário
     * @return O número digitado pelo usuário convertido para double
     */
    private double lerDouble(String msg) {
        // Imprime uma mensagem ao usuário, lê uma linha contendo um double e retorna este double
        String linha = this.lerLinha(msg);
        return Double.parseDouble(linha);
    }

    /**
     * Imprime o menu principal, lê a opção escolhida pelo usuário e retorna a opção selecionada.
     * @return Inteiro contendo a opção escolhida pelo usuário
     */
    public int menu() {
        // Imprime o menu principal, lê a opção escolhida pelo usuário e retorna a opção selecionada.

        String msg = "*********************\n" +
                "Escolha uma opção:\n" +
                "1) Cadastrar Cliente\n" +
                "2) Cadastrar Vendedor\n" +
                "3) Cadastrar Gerente\n" +
                "4) Cadastrar Veículo\n" +
                "5) Cadastrar Venda\n" +
                "6) Histórico de Vendas Mensal\n" +
                "7) Histórico de Vendas Anual\n" +
                "8) Histórico de Vendas do Vendedor:\n" +
                "0) Sair\n";

        int op = this.lerInteiro(msg);

        while (op < 0 || op > 11) {
            System.out.println("Opção inválida. Tente novamente: ");
            op = this.lerInteiro(msg);
        }

        return op;
    }

    /***************************************************/

    /**
     * Lê os dados de um novo Cliente e cadastra-o no sistema.
     * @param s: Um objeto da classe Sistema
     */

/**************************************************
* CADASTROS - CADASTROS - CADASTROS - CADASTROS *
***************************************************/

    public void cadCliente(Sistema s) {
        s.listarClientes();

        String nome = this.lerLinha("Digite o nome do cliente: ");
        System.out.println("=> " + nome);
        String cpf = this.lerLinha("Digite o cpf do cliente: ");
        System.out.println("=> " + cpf);
        int dia = this.lerInteiro("Digite o dia do nascimento do cliente: ");
        System.out.println("=> " + dia);
        int mes = this.lerInteiro("Digite o mês do nascimento do cliente: ");
        System.out.println("=> " + mes);
        int ano = this.lerInteiro("Digite o ano do nascimento do cliente: ");
        System.out.println("=> " + ano);
        String email = this.lerLinha("Digite o email do cliente: ");
        System.out.println("=> " + email);

        if (s.localizarCliente(cpf) == null) { // Garantindo que o não CPF esteja duplicado.
            Cliente c = new Cliente(nome, cpf, dia, mes, ano, email);
            s.adicionar(c);
        }
        else {
            System.out.println("Erro: CPF duplicado. Cliente não adicionado.");
        }
    }

    public void cadVendedor (Sistema s) {
        s.listarVendedores();

        String nome = this.lerLinha("Digite o nome do vendedor: ");
        System.out.println("=> " + nome);
        String cpf = this.lerLinha("Digite o cpf do vendedor: ");
        System.out.println("=> " + cpf);
        int dia = this.lerInteiro("Digite o dia do nascimento do vendedor: ");
        System.out.println("=> " + dia);
        int mes = this.lerInteiro("Digite o mês do nascimento do vendedor: ");
        System.out.println("=> " + mes);
        int ano = this.lerInteiro("Digite o ano do nascimento do vendedor: ");
        System.out.println("=> " + ano);
        int salario = this.lerInteiro("Digite o salário mensal fixo do vendedor: ");
        System.out.println("=> " + salario);
        double comissao = this.lerDouble("Digite o percentual de comissão deste vendedor: ");
        System.out.println("=> " + comissao);

        if (s.localizarVendedor(cpf) == null) { // Garantindo que o não CPF esteja duplicado.
            Vendedor c = new Vendedor(nome, cpf, dia, mes, ano, salario, comissao); // CHECAAAARRRR
            s.adicionar(c); // CHECARRRRR
            s.listarVendedores(); System.out.println("okok add :D");
        }
        else {
            System.out.println("Erro: CPF duplicado. Vendedor não adicionado.");
        }
    }
    
    public void cadGerente (Sistema s) {
        s.listarGerentes();

        String nome = this.lerLinha("Digite o nome do gerente: ");
        System.out.println("=> " + nome);
        String cpf = this.lerLinha("Digite o cpf do gerente: ");
        System.out.println("=> " + cpf);
        int dia = this.lerInteiro("Digite o dia do nascimento do gerente: ");
        System.out.println("=> " + dia);
        int mes = this.lerInteiro("Digite o mês do nascimento do gerente: ");
        System.out.println("=> " + mes);
        int ano = this.lerInteiro("Digite o ano do nascimento do gerente: ");
        System.out.println("=> " + ano);
        int salario = this.lerInteiro("Digite o salário mensal fixo do gerente: ");
        System.out.println("=> " + salario);
        String senha = this.lerLinha("Digite a senha do gerente: ");
        System.out.println("=> " + senha);

        if (s.localizarGerente(cpf) == null) {
            Gerente c = new Gerente(nome, cpf, dia, mes, ano, salario, senha); // CHECAAAARRRR
            s.adicionar(c); // CHECARRRRR
        }
        else {
            System.out.println("Erro: CPF duplicado. Vendedor não adicionado.");
        }
    }

    public void cadVeiculo (Sistema s) {
        s.listarVeiculos();

        String marca = this.lerLinha("Digite a marca do veículo: ");
        System.out.println("=> " + marca);
        String modelo = this.lerLinha("Digite o modelo do veículo: ");
        System.out.println("=> " + modelo);
        int anoFab = this.lerInteiro("Digite o ano de fabricação do veículo: ");
        System.out.println("=> " + anoFab);
        int mesFab = this.lerInteiro("Digite o mês de fabricação do veículo: ");
        System.out.println("=> " + mesFab);
        int anoMod = this.lerInteiro("Digite o ano do modelo do veículo: ");
        System.out.println("=> " + anoMod);
        int valor = this.lerInteiro("Digite o valor do veículo: ");
        System.out.println("=> " + valor);
        int tipo = this.lerInteiro("Escolha o tipo do veículo: \n1) Elétrico \n2) Combustão \n3) Híbrido:");
        System.out.println("=> " + tipo);
        while (!(tipo == 0 || tipo == 1 || tipo == 2 || tipo == 3)) {
            System.out.println("Erro: Tipo inválido. Tente novamente (para sair, digite 0).");
            tipo = this.lerInteiro("Escolha o tipo do veículo: \n1) Elétrico \n2) Combustão \n3) Híbrido:");
            System.out.println("=> " + tipo);
        }
        if (tipo == 0) {
            menu();
        }
        if (tipo == 1) {
            int autonBateria = this.lerInteiro("Digite a autonomia da bateria (em km): ");
            System.out.println("=> " + autonBateria);
            int capBateria = this.lerInteiro("Digite a capacidade da Bateria (em kwH): ");
            System.out.println("=> " + capBateria);
            Eletrico c = new Eletrico(marca, modelo, anoFab, mesFab, anoMod, valor, autonBateria, capBateria); // CHECAAAARRRR
            s.adicionar(c);
        }
        if (tipo == 2) {
            int autonMotor = this.lerInteiro("Digite a autonomia do motor (em km): ");
            System.out.println("=> " + autonMotor);
            int capMotor = this.lerInteiro("Digite a capacidade do motor (em L): ");
            System.out.println("=> " + capMotor);
            Combustao c = new Combustao(marca, modelo, anoFab, mesFab, anoMod, valor, autonMotor, capMotor); // CHECAAAARRRR
            s.adicionar(c);
        }
        if (tipo == 3) {
            int autonMotor = this.lerInteiro("Digite a autonomia do motor (em km): ");
            System.out.println("=> " + autonMotor);
            int capMotor = this.lerInteiro("Digite a capacidade do motor (em L): ");
            System.out.println("=> " + capMotor);
            int autonBateria = this.lerInteiro("Digite a autonomia da bateria (em km): ");
            System.out.println("=> " + autonBateria);
            int capBateria = this.lerInteiro("Digite a capacidade da Bateria (em kwH): ");
            System.out.println("=> " + capBateria);
            Hibrido c = new Hibrido(marca, modelo, anoFab, mesFab, anoMod, valor, autonMotor, capMotor, autonBateria, capBateria); // CHECAAAARRRR
            s.adicionar(c);
        }
    }

    public void cadVenda (Sistema s) {
        s.listarVendedores();
        String cpfVendedor = this.lerLinha("Digite o CPF do vendedor: ");
        System.out.println("=> " + cpfVendedor);
        Vendedor v = s.localizarVendedor(cpfVendedor);
        if (v == null) {
            System.out.println("Erro: Vendedor não encontrado. Venda não cadastrada.");
            return;
        }
        
        s.listarVeiculos();
        int numVeiculo = this.lerInteiro("Escolha um veículo pelo número: ");
        System.out.println("=> " + numVeiculo);
        if (numVeiculo <= 0 || numVeiculo > s.getVeiculos().size()){
            System.out.println("Erro: Número inválido. Não foi possível selecionar um veículo.");
            return; // aqui nao sei se deveria encerrar a funcao ou pedir pra digitar de novo
        }
        Veiculo veic = s.identificarVeiculo(numVeiculo);
        if (veic == null) {
            System.out.println("Erro: Veículo não encontrado. Venda não cadastrada.");
            return;
        }

        s.listarClientes();
        String cpfCliente = this.lerLinha("Digite o CPF do cliente: ");
        System.out.println("=> " + cpfCliente);
        Cliente c = s.localizarCliente(cpfCliente);
        if (c == null) {
            System.out.println("Erro: Cliente não encontrado. Venda não cadastrada.");
            return;
        }
        
                double desconto = this.lerDouble("Digite o desconto (em R$): ");
        System.out.println("=> " + desconto);
        int diaVenda = this.lerInteiro("Digite o dia da venda: ");
        System.out.println("=> " + diaVenda);
        int mesVenda = this.lerInteiro("Digite o mês da venda: ");
        System.out.println("=> " + mesVenda);
        int anoVenda = this.lerInteiro("Digite o ano da venda: ");
        System.out.println("=> " + anoVenda);
        String chassi = this.lerLinha("Digite o chassi do veículo: ");
        System.out.println("=> " + chassi);
        
        Venda venda = new Venda(veic, c, desconto, new Data(diaVenda, mesVenda, anoVenda), chassi);
        s.atribuirVendaVendedor(venda, v);
    }
    
/**************************************************
 * RELATÓRIOS - RELATÓRIOS - RELATÓRIOS - RELATÓRIOS
 ***************************************************/

    public void relatorio (Sistema s) {
        System.out.println("Escolha o tipo de relatório:\n" +
                           "1) Relatório Mensal\n" +
                           "2) Relatório Anual\n" +
                           "3) Relatório de Vendedor\n");
        int tipo = this.lerInteiro("Digite a opção desejada: ");
        System.out.println("=> " + tipo);

        while (!(tipo == 1 || tipo == 2 || tipo == 3)) {
            System.out.println("Erro: Tipo inválido. Tente novamente.");
            tipo = this.lerInteiro("Escolha o tipo de relatório:\n" +
                                   "1) Relatório Mensal\n" +
                                   "2) Relatório Anual\n" +
                                   "3) Relatório de Vendedor\n");
            System.out.println("=> " + tipo);
        }

        if (tipo == 1) {
            relatorioMensal(s);
        }
        else if (tipo == 2) {
            relatorioAnual(s);
        }
        else {
            relatorioVendedor(s);
        }
    }

    public void relatorioMensal (Sistema s) {
        int mes = this.lerInteiro("Digite o mês desejado: ");
        System.out.println("=> " + mes);
        int ano = this.lerInteiro("Digite o ano desejado: ");
        System.out.println("=> " + ano);
        System.out.println();
        s.relatorio(mes, ano);
    }

    public void relatorioAnual (Sistema s) {
        int ano = this.lerInteiro("Digite o ano desejado: ");
        System.out.println("=> " + ano);
        System.out.println();
        s.relatorio(ano);
    }

    public void relatorioVendedor (Sistema s) {
        String cpf = this.lerLinha("Digite o CPF do vendedor: ");
        System.out.println("=> " + cpf);

        Vendedor v = s.localizarVendedor(cpf);
        if (v == null) {
            System.out.println("Erro: Vendedor não encontrado.");
            return;
        }
        System.out.println();
        s.relatorio(v);
    }

    public void opInvalida() {
        System.out.println("Opção inválida. Tente novamente.");
        menu();
    }
}