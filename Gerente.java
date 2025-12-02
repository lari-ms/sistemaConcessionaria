public class Gerente extends Funcionario {
    private String senha;

    public Gerente(String nome, String cpf, int dia, int mes, int ano, int salario, String senha) {
        super(nome, cpf, dia, mes, ano, salario);
        this.senha = senha;
    }

    public boolean validarAcesso (String senha) {
        return (this.senha.equals(senha));
    }

    //n sei se faz mt sentido deixar o filewriter dentro do metodo e criar um novo toda vez q chamar a funcao, mas vou deixar assim por enquanto
    public void salvar(String filename){
        FileWriter fw = new FileWriter(filename, true);
        fw.write(this.nome+","+this.cpf+","+this.nasc+","+this.salario+","+this.senha + "\n");
        fw.close();

    }
}
