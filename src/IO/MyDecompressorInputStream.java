package IO;

import java.io.IOException;
import java.io.InputStream;

public class MyDecompressorInputStream extends InputStream {
    private InputStream in;

    public MyDecompressorInputStream(InputStream in) {
        this.in = in;
    }

    @Override
    public int read() throws IOException {
        return in.read();
    }

    @Override
    public int read(byte[] b) throws IOException {
        byte[] compressedData = in.readAllBytes();
        int index = 0;
        int outputIndex = 0;

        // Copy the first 24 bytes directly
        while (index < 24) {
            b[outputIndex++] = compressedData[index++];
        }

        // Decompress the rest of the data
        int bitIndex = 0;
        byte currentByte = 0;
        while (index < compressedData.length) {
            byte compressedByte = compressedData[index++];
            for (int i = 0; i < 8; i++) {
                currentByte |= ((compressedByte >> (7 - i)) & 0x01) << (7 - bitIndex);
                bitIndex++;
                if (bitIndex == 8) {
                    b[outputIndex++] = currentByte;
                    currentByte = 0;
                    bitIndex = 0;
                }
            }
        }

        return 0;
    }
}
