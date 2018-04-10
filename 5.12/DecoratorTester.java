
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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Scanner;

public class DecoratorTester {

    public static void main(String [] args) {
        String input = "";

        System.out.print("Enter text: ");
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        try {
            input = in.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("System.out.print: " + input);

        System.out.println("Regular writer: ");
        OutputStreamWriter osw = new OutputStreamWriter(System.out);
        try {
            osw.write(input, 0, input.length());
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("");

        System.out.println("Encrypted writer:");

        EncryptingWriter ew = new EncryptingWriter(new OutputStreamWriter(System.out));
        try {
            ew.write(input);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
