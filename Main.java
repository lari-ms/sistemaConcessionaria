//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Entrada io = new Entrada();
        Sistema s = new Sistema();

        int op = io.menu();

        while (op != 0) {
            switch(op) {
                case 1:
                    io.cadCliente(s); break;
                //case 2:
                    //io.cadVendedor(s); break;
                //case 3: ...
            }

            op = io.menu();
        }
    }
}