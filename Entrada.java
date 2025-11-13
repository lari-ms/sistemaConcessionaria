import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
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
    public int menu()
        throws InputMismatchException, IllegalArgumentException{

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
                "8) Histórico de Vendas do Vendedor\n" +
                "0) Sair\n";

        int op = this.lerInteiro(msg);

        if (op < 0 || op > 8) {
            throw new IllegalArgumentException("Opção inválida. Tente novamente.")
            //System.out.println("Opção inválida. Tente novamente: ");
            //op = this.lerInteiro(msg);
        } else if ()

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
        //s.listarClientes();

        String nome = this.lerLinha("Digite o nome do cliente: ");
        String cpf = this.lerLinha("Digite o cpf do cliente: ");
        int dia = this.lerInteiro("Digite o dia do nascimento do cliente: ");
        int mes = this.lerInteiro("Digite o mês do nascimento do cliente: ");
        int ano = this.lerInteiro("Digite o ano do nascimento do cliente: ");
        if ((dia <= 0 || dia > 31) || (mes <= 0 || mes > 12) || ano <= 0){
            System.out.println("Data invalida. Cliente nao adicionado.");
            return;
        }
        String email = this.lerLinha("Digite o email do cliente: ");

        if (s.localizarCliente(cpf) == null) {
            Cliente c = new Cliente(nome, cpf, dia, mes, ano, email);
            s.adicionar(c);
        }
        else {
            System.out.println("Erro: CPF duplicado. Cliente não adicionado.");
        }
    }

    public void cadVendedor (Sistema s) {
        //s.listarVendedores();

        String nome = this.lerLinha("Digite o nome do vendedor: ");
        String cpf = this.lerLinha("Digite o cpf do vendedor: ");
        int dia = this.lerInteiro("Digite o dia do nascimento do vendedor: ");
        int mes = this.lerInteiro("Digite o mês do nascimento do vendedor: ");
        int ano = this.lerInteiro("Digite o ano do nascimento do vendedor: ");
        if ((dia <= 0 || dia > 31) || (mes <= 0 || mes > 12) || ano <= 0){
            System.out.println("Data invalida. Vendedor nao adicionado.");
            return;
        }
        int salario = this.lerInteiro("Digite o salário mensal fixo do vendedor: ");
        if (salario < 0){
            System.out.println("Salario inválido. Vendedor nao adicionado.");
            return;
        }
        double comissao = this.lerDouble("Digite o percentual de comissão deste vendedor: ");
        if (comissao < 0){
            System.out.println("Valor inválido. Vendedor nao adicionado.");
            return;
        }

        if (s.localizarVendedor(cpf) == null) {
            Vendedor c = new Vendedor(nome, cpf, dia, mes, ano, salario, comissao);
            s.adicionar(c);
            //s.listarVendedores();
        }
        else {
            System.out.println("Erro: CPF duplicado. Vendedor não adicionado.");
        }
    }
    
    public void cadGerente (Sistema s) {
        //s.listarGerentes();

        String nome = this.lerLinha("Digite o nome do gerente: ");
        String cpf = this.lerLinha("Digite o cpf do gerente: ");
        int dia = this.lerInteiro("Digite o dia do nascimento do gerente: ");
        int mes = this.lerInteiro("Digite o mês do nascimento do gerente: ");
        int ano = this.lerInteiro("Digite o ano do nascimento do gerente: ");
        if ((dia <= 0 || dia > 31) || (mes <= 0 || mes > 12) || ano <= 0){
            System.out.println("Data invalida. Gerente nao adicionado.");
            return;
        }
        int salario = this.lerInteiro("Digite o salário mensal fixo do gerente: ");
        if (salario < 0){
            System.out.println("Salario inválido. Gerente nao adicionado.");
            return;
        }
        String senha = this.lerLinha("Digite a senha do gerente: ");

        if (s.localizarGerente(cpf) == null) {
            Gerente c = new Gerente(nome, cpf, dia, mes, ano, salario, senha); // CHECAAAARRRR
            s.adicionar(c); // CHECARRRRR
        }
        else {
            System.out.println("Erro: CPF duplicado. Vendedor não adicionado.");
        }
    }

    public void cadVeiculo (Sistema s) {
        //s.listarVeiculos();

        String marca = this.lerLinha("Digite a marca do veículo: ");
        String modelo = this.lerLinha("Digite o modelo do veículo: ");
        int anoFab = this.lerInteiro("Digite o ano de fabricação do veículo: ");
        int mesFab = this.lerInteiro("Digite o mês de fabricação do veículo: ");
        int anoMod = this.lerInteiro("Digite o ano do modelo do veículo: ");
        if (anoMod < 0){
            System.out.println("Ano inválido. Veículo nao adicionado");
            return;
        }
        int valor = this.lerInteiro("Digite o valor do veículo: ");
        if (valor < 0){
            System.out.println("Valor inválido. Veículo nao adicionado.");
            return;
        }
        int tipo = this.lerInteiro("Escolha o tipo do veículo: \n1) Elétrico \n2) Combustão \n3) Híbrido:");
        while (!(tipo == 0 || tipo == 1 || tipo == 2 || tipo == 3)) {
            System.out.println("Erro: Tipo inválido. Tente novamente (para sair, digite 0).");
            tipo = this.lerInteiro("Escolha o tipo do veículo: \n1) Elétrico \n2) Combustão \n3) Híbrido:");
        }
        if (tipo == 0) {
            menu();
        }
        if (tipo == 1) {
            int autonBateria = this.lerInteiro("Digite a autonomia da bateria (em km): ");
            if (autonBateria < 0){
                System.out.println("Valor inválido. Veículo nao cadastrado");
            }
            int capBateria = this.lerInteiro("Digite a capacidade da Bateria (em kwH): ");
            System.out.println("=> " + capBateria);
            if (capBateria < 0){
                System.out.println("Valor inválido. Veículo nao cadastrado");
            }
            Eletrico c = new Eletrico(marca, modelo, anoFab, mesFab, anoMod, valor, autonBateria, capBateria);
            s.adicionar(c);
        }
        if (tipo == 2) {
            int autonMotor = this.lerInteiro("Digite a autonomia do motor (em km): ");
            if (autonMotor < 0){
                System.out.println("Valor inválido. Veículo nao cadastrado");
            }
            int capMotor = this.lerInteiro("Digite a capacidade do motor (em L): ");
            if (capMotor < 0){
                System.out.println("Valor inválido. Veículo nao cadastrado");
            }
            Combustao c = new Combustao(marca, modelo, anoFab, mesFab, anoMod, valor, autonMotor, capMotor);
            s.adicionar(c);
        }
        if (tipo == 3) {
            int autonMotor = this.lerInteiro("Digite a autonomia do motor (em km): ");
            if (autonMotor < 0){
                System.out.println("Valor inválido. Veículo nao cadastrado");
            }
            int capMotor = this.lerInteiro("Digite a capacidade do motor (em L): ");
            if (capMotor < 0){
                System.out.println("Valor inválido. Veículo nao cadastrado");
            }
            int autonBateria = this.lerInteiro("Digite a autonomia da bateria (em km): ");
            if (autonBateria < 0){
                System.out.println("Valor inválido. Veículo nao cadastrado");
            }
            int capBateria = this.lerInteiro("Digite a capacidade da Bateria (em kwH): ");
            if (capBateria < 0){
                System.out.println("Valor inválido. Veículo nao cadastrado");
            }
            Hibrido c = new Hibrido(marca, modelo, anoFab, mesFab, anoMod, valor, autonMotor, capMotor, autonBateria, capBateria);
            s.adicionar(c);
        }
    }

    public void cadVenda (Sistema s) {
        s.listarVendedores();
        String cpfVendedor = this.lerLinha("\nDigite o CPF do vendedor: ");
        Vendedor v = s.localizarVendedor(cpfVendedor);
        if (v == null) {
            System.out.println("Erro: Vendedor não encontrado. Venda não cadastrada.");
            return;
        }

        System.out.println();
        s.listarVeiculos();
        int numVeiculo = this.lerInteiro("\nEscolha um veículo pelo número: ");
        if (numVeiculo <= 0 || numVeiculo > s.getVeiculos().size()){
            System.out.println("Erro: Número inválido. Não foi possível selecionar um veículo.");
            return; // aqui nao sei se deveria encerrar a funcao ou pedir pra digitar de novo
        }
        Veiculo veic = s.identificarVeiculo(numVeiculo);
        if (veic == null) {
            System.out.println("Erro: Veículo não encontrado. Venda não cadastrada.");
            return;
        }

        System.out.println();
        s.listarClientes();
        String cpfCliente = this.lerLinha("\nDigite o CPF do cliente: ");
        Cliente c = s.localizarCliente(cpfCliente);
        if (c == null) {
            System.out.println("Erro: Cliente não encontrado. Venda não cadastrada.");
            return;
        }

        double desconto = this.lerDouble("\nDigite o desconto (em R$): ");
        int diaVenda = this.lerInteiro("Digite o dia da venda: ");
        int mesVenda = this.lerInteiro("Digite o mês da venda: ");
        int anoVenda = this.lerInteiro("Digite o ano da venda: ");
        if ((diaVenda <= 0 || diaVenda > 31) || (mesVenda <= 0 || mesVenda > 12) || anoVenda <= 0){
            System.out.println("Data invalida. Venda nao adicionada.");
            return;
        }
        String chassi = this.lerLinha("Digite o chassi do veículo: ");
        
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

        while (!(tipo == 1 || tipo == 2 || tipo == 3)) {
            System.out.println("Erro: Tipo inválido. Tente novamente.");
            tipo = this.lerInteiro("Escolha o tipo de relatório:\n" +
                                   "1) Relatório Mensal\n" +
                                   "2) Relatório Anual\n" +
                                   "3) Relatório de Vendedor\n");
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
        int ano = this.lerInteiro("Digite o ano desejado: ");
        if ((mes <= 0 || mes > 12) || ano <= 0){
            System.out.println("Data invalida.");
            return;
        }
        System.out.println();
        s.relatorio(mes, ano);
    }

    public void relatorioAnual (Sistema s) {
        int ano = this.lerInteiro("Digite o ano desejado: ");
        if (ano <= 0){
            System.out.println("Ano invalido.");
            return;
        }
        System.out.println();
        s.relatorio(ano);
    }

    public void relatorioVendedor (Sistema s) {
        String cpf = this.lerLinha("Digite o CPF do vendedor: ");

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