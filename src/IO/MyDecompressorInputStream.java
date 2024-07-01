//package IO;
//
//import java.io.IOException;
//import java.io.InputStream;
//import java.nio.ByteBuffer;
//import java.util.Arrays;
//
//public class MyDecompressorInputStream extends InputStream {
//    private InputStream in;
//
//    public MyDecompressorInputStream(InputStream in) {
//        this.in = in;
//    }
//
//    @Override
//    public int read() throws IOException {
//        return in.read();
//    }
//
//    @Override
//    public int read(byte[] b) throws IOException {
//        try {
//            byte[] compressedData = in.readAllBytes();
//            int index = 0;
//            int outputIndex = 0;
//
//            // Copy the first 24 bytes directly
//            while (index < 24) {
//                b[outputIndex++] = compressedData[index++];
//            }
//
//            // Decompress the rest of the data
//            byte[] bytesToDecompress = ByteBuffer.wrap(Arrays.copyOfRange(compressedData, 24, compressedData.length)).array();
//            StringBuilder result = new StringBuilder();
//            for (byte val : bytesToDecompress) {
//                for (int j = 0; j < 8; j++) {
//                    result.append((val >> (7 - j)) & 0x01);
//                }
//            }
//            String binaryString = result.toString();
//            for (int i = 24; i < b.length; i++) {
//                int temp = Character.getNumericValue(binaryString.charAt(i - 24));
//                b[i] = (byte) temp;
//            }
//
//            return b.length;
//
//        } catch (Exception e) {
//            return -1;
//        }
//    }
//}
package IO;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Arrays;

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
        if (b == null) {
            throw new IllegalArgumentException("Output byte array is null");
        }

        byte[] compressedData = in.readAllBytes();
        if (compressedData.length < 24) {
            throw new IOException("Compressed data is too short");
        }

        int index = 0;
        int outputIndex = 0;

        // Copy the first 24 bytes directly
        while (index < 24) {
            b[outputIndex++] = compressedData[index++];
        }

        // Decompress the rest of the data
        int bitIndex = 0;
        for (int i = 24; i < compressedData.length; i++) {
            byte val = compressedData[i];
            for (int j = 0; j < 8; j++) {
                if (outputIndex < b.length) {
                    b[outputIndex++] = (byte) ((val >> (7 - j)) & 0x01);
                }
            }
        }

        if (outputIndex < b.length) {
            throw new IOException("Decompressed data is shorter than expected");
        }

        return b.length;
    }
}
