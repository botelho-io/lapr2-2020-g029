package csvparser;

import java.io.IOException;

/**
 * Line Exception Class
 * <p>Representa um erro que ocureu ao ler um certo ficheiro numa certa linha.</p>
 * @author 1150462 and 1192223
*/

public class LineException extends IOException {
    String file; // Ficheiro em que o erro ocorreu.
    int line;    // Linha do ficheiro onde ocorreu o erro.

    /**
     * Cria uma nova LineException.
     * @param msg  Mensagem de erro.
     * @param file Ficheiro em que o erro ocorreu.
     * @param line Linha do ficheiro onde ocorreu o erro.
     */
    public LineException(String msg, String file, int line) {
        super(msg);
        this.file = file;
        this.line = line;
    }

    /**
     * Returns ficheiro em que o erro ocorreu.
     * @return icheiro em que o erro ocorreu.
     */
    public String getFile() {
        return file;
    }

    /**
     * Returns linha do ficheiro onde ocorreu o erro.
     * @return inha do ficheiro onde ocorreu o erro.
     */
    public int getLine() {
        return line;
    }
}
