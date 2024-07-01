package IO;

import java.io.IOException;
import java.io.InputStream;

public class SimpleDecompressorInputStream extends InputStream {
    private InputStream in;

    public SimpleDecompressorInputStream(InputStream in) {
        this.in = in;
    }

    @Override
    public int read() throws IOException {
        return in.read();
    }

    @Override
    public int read(byte[] b) throws IOException {
        byte[] inputByteArray = in.readAllBytes();
        try {
            // Copy the first 24 bytes (maze dimensions and start/end positions)
            System.arraycopy(inputByteArray, 0, b, 0, 24);

            // Decompress the rest of the data
            int index = 24;
            boolean flag = true; // Indicates whether the next byte should be 0 or 1
            for (int i = 24; i < inputByteArray.length; i++) {
                byte count = inputByteArray[i];
                for (int j = 0; j < count; j++) {
                    b[index++] = flag ? (byte) 0 : (byte) 1;
                }
                flag = !flag; // Toggle flag for the next sequence
            }
        } catch (Exception e) {
            return -1; // Return -1 on exception
        }
        return 1; // Return 1 if successful
    }
}
/////maor////