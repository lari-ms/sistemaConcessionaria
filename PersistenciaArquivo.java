import java.io.*;
import java.util.*;

/**
 * PersistenciaArquivo
 * Formatos de arquivo (cada campo separado por '|'):
 *
 * clientes.txt:
 * CPF|NOME|DIA|MES|ANO|EMAIL
 *
 * vendedores.txt:
 * CPF|NOME|DIA|MES|ANO|SALARIO|COMISSAO
 *
 * gerentes.txt:
 * CPF|NOME|DIA|MES|ANO|SALARIO|SENHA
 *
 * veiculos.txt:
 * TIPO|MARCA|MODELO|ANOFAB|MESFAB|ANOMOD|VALOR|CAMPO1|CAMPO2...
 *  - TIPO = ELETRICO | COMBUSTAO | HIBRIDO
 *  - ELETRICO: CAMPO1=autonomiaBat, CAMPO2=capacidadeBat
 *  - COMBUSTAO: CAMPO1=autonomiaComb, CAMPO2=capacidadeComb
 *  - HIBRIDO: CAMPO1=autonomiaComb, CAMPO2=capComb, CAMPO3=autonBat, CAMPO4=capBat
 *
 * vendas.txt:
 * CHASSI|CPF_VENDEDOR|CPF_CLIENTE|VEICULO_INDEX|DIA|MES|ANO|DESCONTO
 *  - VEICULO_INDEX é o índice (1-based) do veículo na ordem salvo em veiculos.txt
 *
 * Observação: ao carregar vendas, garante-se que veiculos/clientes/vendedores já foram carregados.
 */
public class PersistenciaArquivo {

    public static void carregarTodos(Sistema s) {
        carregarClientes(s, "clientes.txt");
        carregarVendedores(s, "vendedores.txt");
        carregarGerentes(s, "gerentes.txt");
        carregarVeiculos(s, "veiculos.txt");
        carregarVendas(s, "vendas.txt");
    }

    public static void salvarTodos(Sistema s) {
        salvarClientes(s, "clientes.txt");
        salvarVendedores(s, "vendedores.txt");
        salvarGerentes(s, "gerentes.txt");
        salvarVeiculos(s, "veiculos.txt");
        salvarVendas(s, "vendas.txt");
    }

    /* --------------------- CLIENTES --------------------- */
    private static void carregarClientes(Sistema s, String arquivo) {
        File f = new File(arquivo);
        if (!f.exists()) return;
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty() || line.startsWith("#")) continue;
                String[] p = line.split("\\|");
                // p[0]=cpf, p[1]=nome, p[2]=dia, p[3]=mes, p[4]=ano, p[5]=email
                try {
                    String cpf = p[0];
                    String nome = p[1];
                    int dia = Integer.parseInt(p[2]);
                    int mes = Integer.parseInt(p[3]);
                    int ano = Integer.parseInt(p[4]);
                    String email = p.length > 5 ? p[5] : "";
                    if (s.localizarCliente(cpf) == null) {
                        Cliente c = new Cliente(nome, cpf, dia, mes, ano, email);
                        s.adicionar(c);
                    }
                } catch (Exception ex) {
                    System.err.println("Linha clientes.txt inválida: " + line + " -> " + ex.getMessage());
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao carregar clientes: " + e.getMessage());
        }
    }

    private static void salvarClientes(Sistema s, String arquivo) {
        try (PrintWriter out = new PrintWriter(new FileWriter(arquivo))) {
            for (Cliente c : s.getClientes()) {
                out.printf("%s|%s|%d|%d|%d|%s%n",
                        c.getCpf(), c.getNome(),
                        c.getNasc().getDia(), c.getNasc().getMes(), c.getNasc().getAno(),
                        c.getEmail() != null ? c.getEmail() : "");
            }
        } catch (IOException e) {
            System.err.println("Erro ao salvar clientes: " + e.getMessage());
        }
    }

    /* --------------------- VENDEDORES --------------------- */
    private static void carregarVendedores(Sistema s, String arquivo) {
        File f = new File(arquivo);
        if (!f.exists()) return;
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty() || line.startsWith("#")) continue;
                String[] p = line.split("\\|");
                // CPF|NOME|DIA|MES|ANO|SALARIO|COMISSAO
                try {
                    String cpf = p[0];
                    String nome = p[1];
                    int dia = Integer.parseInt(p[2]);
                    int mes = Integer.parseInt(p[3]);
                    int ano = Integer.parseInt(p[4]);
                    double salario = Double.parseDouble(p[5]);
                    double comissao = Double.parseDouble(p[6]);
                    if (s.localizarVendedor(cpf) == null) {
                        Vendedor v = new Vendedor(nome, cpf, dia, mes, ano, salario, comissao);
                        s.adicionar(v);
                    }
                } catch (Exception ex) {
                    System.err.println("Linha vendedores.txt inválida: " + line + " -> " + ex.getMessage());
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao carregar vendedores: " + e.getMessage());
        }
    }

    private static void salvarVendedores(Sistema s, String arquivo) {
        try (PrintWriter out = new PrintWriter(new FileWriter(arquivo))) {
            for (Vendedor v : s.getVendedores()) {
                out.printf("%s|%s|%d|%d|%d|%f|%f%n",
                        v.getCpf(), v.getNome(),
                        v.getNasc().getDia(), v.getNasc().getMes(), v.getNasc().getAno(),
                        v.getSalario(), v.getComissao()); // acessa campo comissao (se for private, use getter)
            }
        } catch (IOException e) {
            System.err.println("Erro ao salvar vendedores: " + e.getMessage());
        }
    }

    /* --------------------- GERENTES --------------------- */
    private static void carregarGerentes(Sistema s, String arquivo) {
        File f = new File(arquivo);
        if (!f.exists()) return;
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty() || line.startsWith("#")) continue;
                String[] p = line.split("\\|");
                // CPF|NOME|DIA|MES|ANO|SALARIO|SENHA
                try {
                    String cpf = p[0];
                    String nome = p[1];
                    int dia = Integer.parseInt(p[2]);
                    int mes = Integer.parseInt(p[3]);
                    int ano = Integer.parseInt(p[4]);
                    double salario = Double.parseDouble(p[5]);
                    String senha = p.length > 6 ? p[6] : "";
                    if (s.localizarGerente(cpf) == null) {
                        Gerente g = new Gerente(nome, cpf, dia, mes, ano, (int)salario, senha);
                        s.adicionar(g);
                    }
                } catch (Exception ex) {
                    System.err.println("Linha gerentes.txt inválida: " + line + " -> " + ex.getMessage());
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao carregar gerentes: " + e.getMessage());
        }
    }

    private static void salvarGerentes(Sistema s, String arquivo) {
        try (PrintWriter out = new PrintWriter(new FileWriter(arquivo))) {
            for (Gerente g : s.getGerentes()) {
                out.printf("%s|%s|%d|%d|%d|%f|%s%n",
                        g.getCpf(), g.getNome(),
                        g.getNasc().getDia(), g.getNasc().getMes(), g.getNasc().getAno(),
                        g.getSalario(), g.getSenha()); // se senha for private, adicione getter
            }
        } catch (IOException e) {
            System.err.println("Erro ao salvar gerentes: " + e.getMessage());
        }
    }

    /* --------------------- VEICULOS --------------------- */
    private static void carregarVeiculos(Sistema s, String arquivo) {
        File f = new File(arquivo);
        if (!f.exists()) return;
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty() || line.startsWith("#")) continue;
                String[] p = line.split("\\|");
                try {
                    String tipo = p[0];
                    String marca = p[1];
                    String modelo = p[2];
                    int anoFab = Integer.parseInt(p[3]);
                    int mesFab = Integer.parseInt(p[4]);
                    int anoMod = Integer.parseInt(p[5]);
                    double valor = Double.parseDouble(p[6]);
                    Veiculo v = null;
                    switch (tipo.toUpperCase()) {
                        case "ELETRICO":
                            double autonBat = Double.parseDouble(p[7]);
                            double capBat = Double.parseDouble(p[8]);
                            v = new Eletrico(marca, modelo, anoFab, mesFab, anoMod, valor, autonBat, capBat);
                            break;
                        case "COMBUSTAO":
                            double autonComb = Double.parseDouble(p[7]);
                            double capComb = Double.parseDouble(p[8]);
                            v = new Combustao(marca, modelo, anoFab, mesFab, anoMod, valor, autonComb, capComb);
                            break;
                        case "HIBRIDO":
                            double autonCombH = Double.parseDouble(p[7]);
                            double capCombH = Double.parseDouble(p[8]);
                            double autonBatH = Double.parseDouble(p[9]);
                            double capBatH = Double.parseDouble(p[10]);
                            v = new Hibrido(marca, modelo, anoFab, mesFab, anoMod, valor,
                                    autonCombH, capCombH, autonBatH, capBatH);
                            break;
                        default:
                            System.err.println("Tipo de veículo desconhecido: " + tipo);
                    }
                    if (v != null) s.adicionar(v);
                } catch (Exception ex) {
                    System.err.println("Linha veiculos.txt inválida: " + line + " -> " + ex.getMessage());
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao carregar veículos: " + e.getMessage());
        }
    }

    private static void salvarVeiculos(Sistema s, String arquivo) {
        try (PrintWriter out = new PrintWriter(new FileWriter(arquivo))) {
            for (Veiculo v : s.getVeiculos()) {
                if (v instanceof Eletrico) {
                    Eletrico e = (Eletrico) v;
                    out.printf("ELETRICO|%s|%s|%d|%d|%d|%f|%f|%f%n",
                            e.getMarca(), e.getModelo(), e.anoFab, e.mesFab, e.anoMod, e.valor,
                            e.getAutonomia(), e.getCapacidade()); // se campos privados, use getters
                } else if (v instanceof Combustao) {
                    Combustao c = (Combustao) v;
                    out.printf("COMBUSTAO|%s|%s|%d|%d|%d|%f|%f|%f%n",
                            c.getMarca(), c.getModelo(), c.anoFab, c.mesFab, c.anoMod, c.valor,
                            c.getAutonomia(), c.getCapacidade());
                } else if (v instanceof Hibrido) {
                    Hibrido h = (Hibrido) v;
                    out.printf("HIBRIDO|%s|%s|%d|%d|%d|%f|%f|%f|%f|%f%n",
                            h.getMarca(), h.getModelo(), h.anoFab, h.mesFab, h.anoMod, h.valor,
                            h.getAutonomiaComb(), h.getCapacidadeComb(), h.getAutonomiaBat(), h.getCapacidadeBat());
                } else {
                    // fallback genérico (não esperado)
                    out.printf("DESCONHECIDO|%s|%s|%d|%d|%d|%f%n",
                            v.getMarca(), v.getModelo(), v.anoFab, v.mesFab, v.anoMod, v.valor);
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao salvar veículos: " + e.getMessage());
        }
    }

    /* --------------------- VENDAS --------------------- */
    private static void carregarVendas(Sistema s, String arquivo) {
        File f = new File(arquivo);
        if (!f.exists()) return;
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty() || line.startsWith("#")) continue;
                String[] p = line.split("\\|");
                // CHASSI|CPF_VENDEDOR|CPF_CLIENTE|VEICULO_INDEX|DIA|MES|ANO|DESCONTO
                try {
                    String chassi = p[0];
                    String cpfVendedor = p[1];
                    String cpfCliente = p[2];
                    int veicIndex = Integer.parseInt(p[3]); // 1-based
                    int dia = Integer.parseInt(p[4]);
                    int mes = Integer.parseInt(p[5]);
                    int ano = Integer.parseInt(p[6]);
                    double desconto = Double.parseDouble(p[7]);

                    Vendedor v = s.localizarVendedor(cpfVendedor);
                    Cliente c = s.localizarCliente(cpfCliente);
                    Veiculo veic = null;
                    if (veicIndex > 0 && veicIndex <= s.getVeiculos().size()) {
                        veic = s.getVeiculos().get(veicIndex - 1);
                    }
                    if (v == null || c == null || veic == null) {
                        System.err.println("Venda ignorada (referência inválida): " + line);
                        continue;
                    }
                    Venda venda = new Venda(veic, c, desconto, new Data(dia, mes, ano), chassi);
                    s.atribuirVendaVendedor(venda, v);
                } catch (Exception ex) {
                    System.err.println("Linha vendas.txt inválida: " + line + " -> " + ex.getMessage());
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao carregar vendas: " + e.getMessage());
        }
    }

    private static void salvarVendas(Sistema s, String arquivo) {
        try (PrintWriter out = new PrintWriter(new FileWriter(arquivo))) {
            // Para referenciar veículos, salvamos o índice (1-based) do veículo no momento do salvamento
            for (Vendedor v : s.getVendedores()) {
                for (Venda venda : v.getVendas()) {
                    Veiculo veic = venda.getVeiculo();
                    int veicIndex = s.getVeiculos().indexOf(veic) + 1; // se não encontrado retorna 0
                    out.printf("%s|%s|%s|%d|%d|%d|%d|%f%n",
                            venda.getChassi(),
                            v.getCpf(),
                            venda.getCliente().getCpf(), // se cliente for private, use getter
                            veicIndex,
                            venda.getData().getDia(),
                            venda.getData().getMes(),
                            venda.getData().getAno(),
                            venda.getDesconto());
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao salvar vendas: " + e.getMessage());
        }
    }
}
