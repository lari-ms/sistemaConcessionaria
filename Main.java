//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Entrada io = new Entrada();
        Sistema s = new Sistema();

        int op = io.menu();

        /*
         * Escolha uma opção:
1) Cadastrar Cliente
2) Cadastrar Vendedor
3) Cadastrar Gerente
4) Cadastrar Veículo
5) Cadastrar Venda
6) Relatório de Vendas Mensal
7) Relatório de Vendas Anual
8) Relatório de Vendas do Vendedor:
0) Sair
         */

        while (op != 0) {
            switch(op) {
                case 0: //Sair
                    break;
                case 1: //Cadastrar Cliente
                    io.cadCliente(s);
                    break;
                case 2: //Cadastrar Vendedor
                    io.cadVendedor(s);
                    break;
                case 3: //Cadastrar Gerente
                    io.cadGerente(s);
                    break;
                case 4: //Cadastrar Veículo
                    io.cadVeiculo(s);
                    break;
                case 5: //Cadastrar Venda
                    io.cadVenda(s);
                    break;
                case 6: //Relatorio mensal
                    io.relatorioMensal(s);
                    break;
                case 7: //Relatorio anual
                    io.relatorioAnual(s);
                    break;
                case 8: //Relatorio por vendedor
                    io.relatorioVendedor(s);
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");

            
            }
            System.out.println(" \n");
            op = io.menu();
            System.out.println();
        }
    }
}