import java.io.BufferedWriter;
import java.io.IOException;

public interface Registravel {
    public void salvar(BufferedWriter writer) throws IOException;
    public void carregar(String nomeArquivo, Sistema s) throws IOException {
    }
}