package IO;

import java.io.IOException;
import java.io.OutputStream;

public class SimpleCompressorOutputStream extends OutputStream {

    private OutputStream out;
    private byte lastByte = 0;
    private int counter;
    private boolean flag = false;
    /**
     * constructor
     * @param outputStream the outputStream field
     */
    public SimpleCompressorOutputStream(OutputStream out) {this.out = out;}
    /**
     * A function that used to write the compressed representation of the Maze (byte Array) to the outPutStream
     * it counts sequences of the same number it receives from the write(byte[] b) function
     * writes the counter to outputStream when a sequence of the same number ends
     * counter = 127 is a special case(max byte = 127)
     * @param b The number we send to the outputStream
     */
    @Override
    public void write(int b) throws IOException {
        if ((b == lastByte) && (!flag)) // check if b is '0', and lastByte was '0'
        {
            counter++;
            if (counter == 128)
            {
                this.out.write(127);
                this.out.write(0);
                counter = 1;
            }
        }
        else if ((b == lastByte) && (flag)) // check if b is '1', and lastByte was '1'
        {
            counter++;
            if(counter == 128) // check if b is shown 128 times in a row - if yes, insert 127 - 0, and keep counting from 1
            {
                this.out.write(127);
                this.out.write(0);
                counter = 1;
            }
        }
        else if((b != lastByte) && (flag)) // check if b is '0', and lastByte was '1'
        {
            this.out.write(counter);
            counter = 1;
            flag = false;
            lastByte = (byte)b;
        }
        else if((b != lastByte) && (!flag)) // check if b is '1', and lastByte was '0'
        {
            this.out.write(counter);
            counter = 1;
            flag = true;
            lastByte = (byte)b;
        }
    }
    /**
     * A function that gets a byte array and writes it to the outputStream
     * The first 12 elements will be sent as is
     * The others will be sent to the write(int b)
     * @param b The byte array we send to the outputStream
     */
    @Override
    public void write(byte[] b) throws IOException {
        for(int i = 0; i < 12; i++){ // write the first 12 bytes to the OutPutStream [row, row, column, column, S(r), S(r), S(c), S(c), G(r), G(r), G(c), G(c), ..]
            this.out.write(b[i]);
        }
        for(int i = 12 ; i < b.length; i++){ // write the rest bytes - the mazeArray values
            write(b[i]);
        }
        if(counter != 0)
            this.out.write(counter);
    }
}
