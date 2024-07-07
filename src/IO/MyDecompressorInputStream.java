package IO;

import java.io.IOException;
import java.io.InputStream;

public class MyDecompressorInputStream extends InputStream {

    private InputStream in;
    /**
     * constructor
     * @param inputStream the inputStream field
     */
    public MyDecompressorInputStream(InputStream in) {
        this.in = in;
    }
    /**
     * A function that reads one byte from the inputStream (in)
     * @return 0 (int)
     */
    @Override
    public int read() throws IOException {
        return in.read();
    }
    /**
     * Gets Compressed array(compressed by the MyCompressor algorithm)
     * And Decompresses it adapted to the format
     * @return 0 (int)
     */
    @Override
    public int read(byte[] b) throws IOException {
        byte [] input = this.in.readAllBytes(); // read all the bytes from the Stream

        for(int i = 0; i < 12; i++) // read the first 12 bytes from the InPutStream [row, row, column, column, S(r), S(r), S(c), S(c), G(r), G(r), G(c), G(c), ..]
        {
            b[i] = input[i];
        }
        int counter = 12;

        for(int i = 12; i < input.length; i++)
        {
            if(i == input.length - 1)
            {
                break;
            }
            String s, fixedS;
            if(i != input.length - 2)
            {
                s = Integer.toBinaryString(input[i]);
                fixedS =  fixStr(s, 7);
            }
            else
            {
                int len = input[input.length - 1];
                s = Integer.toBinaryString(input[i]);
                fixedS =  fixStr(s, len);
            }

            for (int j = 0; j < fixedS.length(); j++)
            {
                b[counter] = (byte)(int)Integer.valueOf(String.valueOf(fixedS.charAt(j)), 2);
                counter++;
            }
        }

        return 0;
    }

    public static String fixStr(String s, int len) {
        int fix = len - s.length();
        for (int i = 0; i < fix; i++) {
            s = '0' + s;
        }
        return s;
    }
}
