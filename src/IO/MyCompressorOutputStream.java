package IO;

import java.io.IOException;
import java.io.OutputStream;

public class MyCompressorOutputStream extends OutputStream {

    private OutputStream out;
    /**
     * constructor
     * @param outputStream the outputStream field
     */
    public MyCompressorOutputStream(OutputStream out) {
        this.out = out;
    }

    /**
     * A function that write one byte to the outputStream (out)
     */
    @Override
    public void write(int b) throws IOException {
        out.write(b);
    }
    /**
     * A function that used to write the compressed representation of the Maze (byte Array) to the outPutStream
     * in this algorithm we are inserting to the array (from index 12 until the end) all the indexes that '1' was shown in them
     * in this method we can understand weather we have '1' or '0' for every index in the real mazeArr
     * @param b The number we send to the outputStream
     */
    @Override
    public void write(byte[] b) throws IOException {
        for (int i = 0; i < 12; i++) {
            this.out.write(b[i]);
        }
        int counter = 0;
        String s = "";
        int lastIter = 7;
        for (int i = 12; i < b.length; i++) {
            s += b[i];
            counter++;
            if ((counter == 7) || (i == b.length - 1)) {
                if (i == b.length - 1) {
                    lastIter = counter;
                }
                byte binaryByte = (byte) (int) Integer.valueOf(s, 2);
                this.out.write(binaryByte);
                counter = 0;
                s = "";
            }
        }
        this.out.write(lastIter);
    }
}
