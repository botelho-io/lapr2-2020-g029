package csvparser;

import java.io.IOException;

/**
 * Line Exception Class
 * <p>It represents an error that occurred when reading a certain file on a certain line.</p>
 * @author 1150462 and 1192232
*/

public class LineException extends IOException {
    String file; // File in which the error occurred.
    int line;    // Line of the file where the error occurred.

    /**
     * Creates a new LineException.
     * @param msg Error message.
     * @param file File in which the error occurred.
     * @param line Line of the file where the error occurred.
     */
    public LineException(String msg, String file, int line) {
        super(msg);
        this.file = file;
        this.line = line;
    }

    /**
     * Returns file in which the error occurred.
     * @return file in which the error occurred.
     */
    public String getFile() {
        return file;
    }

    /**
     * Returns line of the file where the error occurred.
     * @return line of the file where the error occurred.
     */
    public int getLine() {
        return line;
    }
}
