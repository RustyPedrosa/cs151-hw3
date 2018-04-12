import java.io.IOException;
import java.io.Writer;

/**
 * Overrides write(char[] cbuf, int off, int len) of a Writer.
 * Characters will be encrypted by Caeser cipher and passed on.
 */
public class EncryptingWriter extends Writer {
    private Writer writer;
    final private int shift = 3;

    /**
     * Creates an encrypting writer from a regular writer
     * @param writer
     */
    public EncryptingWriter(Writer writer){
        this.writer = writer;
    }

    /**
     * Writes a portion of an array of characters, encrypted.
     *
     * @param cbuf Array of characters
     * @param off  Offset from which to start writing characters
     * @param len  Number of characters to write
     * @throws IndexOutOfBoundsException Implementations should throw this exception
     *                                   if {@code off} is negative, or {@code len} is negative,
     *                                   or {@code off + len} is negative or greater than the length
     *                                   of the given array
     * @throws IOException               If an I/O error occurs
     */
    @Override
    public void write(char[] cbuf, int off, int len) throws IOException {
        //Encrypt each character in buffer
        for(int i = off; i < (len + off); i++)
        {
            //Only if the character is alphabetic
            if (Character.isAlphabetic(cbuf[i])){
                char encrypted = (char)(cbuf[i] + shift);
                //If we shifted past Z for uppercase or z for lowercase...
                if (encrypted > (Character.isUpperCase(cbuf[i]) ? 'Z' : 'z'))
                    encrypted -= 26;  //Loop back within the alphabet
                cbuf[i] = encrypted;
            }
        }

        this.writer.write(cbuf, off, len);
    }

    /**
     * Flushes the stream.  If the stream has saved any characters from the
     * various write() methods in a buffer, write them immediately to their
     * intended destination.  Then, if that destination is another character or
     * byte stream, flush it.  Thus one flush() invocation will flush all the
     * buffers in a chain of Writers and OutputStreams.
     * <p>
     * <p> If the intended destination of this stream is an abstraction provided
     * by the underlying operating system, for example a file, then flushing the
     * stream guarantees only that bytes previously written to the stream are
     * passed to the operating system for writing; it does not guarantee that
     * they are actually written to a physical device such as a disk drive.
     *
     * @throws IOException If an I/O error occurs
     */
    @Override
    public void flush() throws IOException {
        writer.flush();
    }

    /**
     * Closes the stream, flushing it first. Once the stream has been closed,
     * further write() or flush() invocations will cause an IOException to be
     * thrown. Closing a previously closed stream has no effect.
     *
     * @throws IOException If an I/O error occurs
     */
    @Override
    public void close() throws IOException {
        writer.flush();// writer.close(); // DO NOT LET THIS CLOSE IF USING SYSTEM.OUT
    }
}
