package IO;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class SimpleCompressorOutputStream extends OutputStream {
    private OutputStream out;

    public SimpleCompressorOutputStream(OutputStream out) {
        this.out = out;
    }

    @Override
    public void write(int b) throws IOException {
        out.write((byte) b);
    }

    @Override
    public void write(byte[] b) throws IOException {
        List<Byte> compressedBytes = new ArrayList<>();
        int index = 0;

        // Copy the maze dimensions and start/end positions (first 24 bytes)
        for (int i = 0; i < 24; i++) {
            compressedBytes.add(b[i]);
        }

        // Compress the rest of the maze data
        byte currentByte = b[24];
        byte counter = 1;
        for (int i = 25; i < b.length; i++) {
            if (b[i] == currentByte) {
                counter++;
            } else {
                compressedBytes.add(counter);
                currentByte = b[i];
                counter = 1;
            }
        }
        // Add the last sequence
        compressedBytes.add(counter);

        // Convert List<Byte> to byte[]
        byte[] output = new byte[compressedBytes.size()];
        for (int i = 0; i < compressedBytes.size(); i++) {
            output[i] = compressedBytes.get(i);
        }

        out.write(output);
    }
}
