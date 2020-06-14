package csvparser;

import lapr.utils.SimpleMatrix;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

/**
 * FileCSV Reader
 * <p>Class that represents a CSV file (simple without escape characters).</p>
 * @author 1150462 and 1192232
*/

public class FileCSV extends SimpleMatrix<String> {

    /**
     *
     * Open a file, reading it into memory.
     * @param path the path of the file to read.
     * @param lineSeparator the line separator character.
     * @param columnSeparator the column separation character.
     * @param skipHeader if true it ignores the first line of the file, assuming it contains only headers.
     * @param width specifies a width for the file (number of columns), if it is 0 it will be automatically inferred by the first line.
     * @throws IOException in case of the file cannot be opened or similar errors.
     * @throws LineException in case of you encounter an error related to the formatting of the file.
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
