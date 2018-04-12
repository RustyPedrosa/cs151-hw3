import java.io.IOException;
import java.io.Reader;

/**
 * Overrides read functions of a Writer.
 * Characters will be decrypted by Caeser cipher and passed on.
 */
public class DecryptingReader extends Reader {
    private Reader reader;
    final private int shift = 3;

    /**
     * Creates a decrypting reader from a regular reader
     * @param reader
     */
    public DecryptingReader(Reader reader){
        this.reader = reader;
    }

    /**
     * Reads characters and decrypts them, into a portion of an array.  This
     * method will block until some input is available, an I/O error occurs,
     * or the end of the stream is reached.
     *
     * @param cbuf Destination buffer
     * @param off  Offset at which to start storing characters
     * @param len  Maximum number of characters to read
     * @return The number of characters read, or -1 if the end of the
     * stream has been reached
     * @throws IOException               If an I/O error occurs
     * @throws IndexOutOfBoundsException If {@code off} is negative, or {@code len} is negative,
     *                                   or {@code len} is greater than {@code cbuf.length - off}
     */
    @Override
    public int read(char[] cbuf, int off, int len) throws IOException {
        //Decrypt each character in buffer
        for(int i = off; i < (len + off); i++)
        {
            //Only if the character is alphabetic
            if (Character.isAlphabetic(cbuf[i])){
                char decrypted = (char)(cbuf[i] - shift);
                //If we shifted past A for uppercase or a for lowercase...
                if (decrypted < (Character.isUpperCase(cbuf[i]) ? 'Z' : 'z'))
                    decrypted += 26;  //Loop back within the alphabet
                cbuf[i] = decrypted;
            }
        }

        return this.reader.read(cbuf, off, len);
    }

    @Override
    public int read(char[] cbuf) throws IOException {
        //Decrypt each character in buffer
        for(int i = 0; i < cbuf.length; i++)
        {
            //Only if the character is alphabetic
            if (Character.isAlphabetic(cbuf[i])){
                char decrypted = (char)(cbuf[i] - shift);
                //If we shifted past A for uppercase or a for lowercase...
                if (decrypted < (Character.isUpperCase(cbuf[i]) ? 'Z' : 'z'))
                    decrypted += 26;  //Loop back within the alphabet
                cbuf[i] = decrypted;
            }
        }

        return this.reader.read(cbuf);
    }

    /**
     * Closes the stream and releases any system resources associated with
     * it.  Once the stream has been closed, further read(), ready(),
     * mark(), reset(), or skip() invocations will throw an IOException.
     * Closing a previously closed stream has no effect.
     *
     * @throws IOException If an I/O error occurs
     */
    @Override
    public void close() throws IOException {
        reader.close();
    }
}
