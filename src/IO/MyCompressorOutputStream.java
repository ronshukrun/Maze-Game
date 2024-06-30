package IO;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MyCompressorOutputStream extends OutputStream {

    private OutputStream out;

    public MyCompressorOutputStream(OutputStream out) {
        this.out = out;
    }

    @Override
    public void write(int b) throws IOException {
        out.write(b);
    }

    @Override
    public void write(byte[] bytes) throws IOException {
        List<Byte> byteArrayList = new ArrayList<>();

        // Add first 24 bytes as they are
        for (int i = 0; i < 24; i++) {
            byteArrayList.add(bytes[i]);
        }

        int bitCounter = 0;
        byte currentByte = 0;

        for (int i = 24; i < bytes.length; i++) {
            currentByte |= (bytes[i] & 0x01) << (7 - bitCounter);
            bitCounter++;

            if (bitCounter == 8) {
                byteArrayList.add(currentByte);
                currentByte = 0;
                bitCounter = 0;
            }
        }

        // If there are remaining bits
        if (bitCounter > 0) {
            byteArrayList.add(currentByte);
        }

        byte[] compressedBytes = new byte[byteArrayList.size()];
        for (int i = 0; i < compressedBytes.length; i++) {
            compressedBytes[i] = byteArrayList.get(i);
        }

        out.write(compressedBytes);
    }
}
