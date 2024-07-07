package IO;

import java.io.IOException;
import java.io.InputStream;

public class SimpleDecompressorInputStream extends InputStream {

    private InputStream in;
    /**
     * constructor
     * @param inputStream the inputStream field
     */
    public SimpleDecompressorInputStream(InputStream in) {this.in = in;}
    /**
     * A function that reads one byte from the inputStream (in)
     * @return 0 (int)
     */
    @Override
    public int read() throws IOException {
        return in.read();
    }
    /**
     * Gets Compressed array(compressed by the simpleCompression algorithm)
     * And Decompresses it adapted to the format
     * @return 0 (int)
     */
    @Override
    public int read(byte[] b) throws IOException {
        byte[] input = this.in.readAllBytes();
        for (int i = 0; i < 12; i++) {
            b[i] = input[i];
        }
        int flag = 1;
        int thisIndex = 12;
        for (int i = 12; i < input.length; i++) {
            if (flag == 0)
                flag = 1;
            else
                flag = 0;
            for (int j = 0; j < input[i]; j++) {
                b[thisIndex] = (byte) flag;
                thisIndex++;
            }
        }
        return 0;
    }
}
