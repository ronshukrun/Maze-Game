package IO;

import java.io.IOException;
import java.io.OutputStream;

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
