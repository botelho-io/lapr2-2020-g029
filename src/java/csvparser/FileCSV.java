package csvparser;

import lapr.utils.SimpleMatrix;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

/**
 * FileCSV Reader
 * <p>Classe que representa um ficheiro CSV (simples sem caracteres de escape).</p>
 * @author 1150462 and 1192223
*/

public class FileCSV extends SimpleMatrix<String> {

    /**
     * Abre um ficheiro, lendo-o para memória.
     * @param path Caminho do ficheiro a ler.
     * @param lineSeparator Caractere de separação de linhas.
     * @param columnSeparator Caractere de separação de colunas.
     * @param skipHeader Se verdadeiro ignora a primeira linha do ficheiro, assumindo que esta contém apenas cabeçalhos.
     * @param width Especifica uma largura para o ficheiro (número de colunas), se for 0 será automaticamente inferido pela primeira linha.
     * @throws IOException Caso o ficheiro não possa ser aberto ou erros semelhantes.
     * @throws LineException Caso encontre um erro relacionado com a formatação do ficheiro.
     */
    public FileCSV(String path, String lineSeparator, String columnSeparator, boolean skipHeader, int width)
            throws IOException, LineException
    {
        super(width);
        // Read file
        StringBuilder contentBuilder = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String sCurrentLine;
            while ((sCurrentLine = br.readLine()) != null) {
                contentBuilder.append(sCurrentLine).append("\n");
            }
        }
        // Read matrix
        String[] lines = contentBuilder.toString().split(lineSeparator);
        for(int l = skipHeader?1:0; l < lines.length; l++){
            try {
                String[] arr = lines[l].split(columnSeparator);
                for (int i = 0; i < arr.length; i++)
                    arr[i] = arr[i].trim();
                this.addLine(Arrays.asList(arr), "");
            } catch (IllegalArgumentException e) {
                throw new LineException(e.getMessage(), path, l);
            }
        }
    }
}
