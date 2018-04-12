
/*
 * 5.12: DecoratorTester java and all required classes including EncryptingWriter and DecryptingReader.
 * Test your classes in the DecoratorTester.
 *
 * Supply decorator classes EncryptingWriter and DecryptingReader that encrypt and
 * decrypt the characters of the underlying reader or writer. Make sure that these
 * classes are again readers and writers so that you can apply additional decorations.
 * For the encryption, simply use the Caesar cipher, which shifts the alphabet by
 * three characters (i.e., A becomes D, B becomes E, and so on).
 */

import java.io.*;

public class DecoratorTester {

    /**
     * Demonstrates EncryptingWriter/DecryptingReader's abilities to enhance Readers/Writers
     */
    public static void main(String [] args) {
        String filename = "secrets.txt";

        try (
                // Regular Buffered Reader (for console input)
                InputStreamReader isr = new InputStreamReader(System.in);
                BufferedReader bisr = new BufferedReader(isr);

                // Decrpyting Buffered Reader (for encrypting console input)
                // NOT USEFUL FOR THIS TESTER - We use readLine for BufferedReader,
                // which is not affected/overridden by read()
                // DecryptingReader dr = new DecryptingReader(isr);
                // BufferedReader bdr = new BufferedReader(dr);

                //Regular Writer (for console printing)
                OutputStreamWriter osw = new OutputStreamWriter(System.out);  //Don't close this or System.out will close

                //Encrypting Writer (for encryption before console printing)
                EncryptingWriter eosw = new EncryptingWriter(osw);

                //Encrypted File reader/writer
                EncryptingWriter efw = new EncryptingWriter(new FileWriter(filename));
                FileReader fr = new FileReader(filename);
                FileReader fr2 = new FileReader(filename);
        ) {
            String input = "";

            System.out.println("-------------------------------------");
            System.out.println("EncryptingWriter testing");
            System.out.println("-------------------------------------");

            System.out.println("Enter text to encrypt (text will be read with regular Reader): ");
            input = bisr.readLine();
            System.out.println();

            efw.write(input);
            efw.close();
            System.out.println("Input saved to " + filename + " via FileWriter decorated with EncryptingWriter");
            System.out.println();

            System.out.println("Original input (printed via OutputStreamWriter):");
            osw.write(input);
            osw.flush();  //Important so that all text gets printed in order
            System.out.println();
            System.out.println();

            System.out.println("Encrypted input (printed via OutputStreamWriter decorated with EncryptingWriter:");
            eosw.write(input);
            eosw.flush();
            System.out.println();
            System.out.println();

            System.out.println("-------------------------------------");
            System.out.println("DecryptingReader testing");
            System.out.println("-------------------------------------");

            char[] fileContents = new char[input.length()];

            fr.read(fileContents, 0, input.length());
            System.out.println("Contents of " + filename + " read by regular FileReader:");
            System.out.println(String.valueOf(fileContents));
            System.out.println();
            fr.close();

            DecryptingReader dfr = new DecryptingReader(fr2);

            fileContents = new char[input.length()];
            //TRICKY!! MUST OVERWRITE APPROPRIATE OVERLOADED FUNCTIONS
            //STILL NOT WORKING OMG WHY IS CBUF BLANK CHARACTERS
            dfr.read(fileContents);
            System.out.println("Contents of " + filename + " read by FileReader decorated by DecryptingReader:");
            System.out.println(String.valueOf(fileContents));
            System.out.println();

        } catch (IOException e) {
            e.printStackTrace();
        }
        //Note: After this try block, System.out will be closed permanently and can't be used anymore.
    }
}
