/*
 * mvc: Write MVCTester.java. When the program starts, the initial screen displays a
 * button labeled "add", a blank text area, and a text field.
 *
 * A user places a line in the text field and clicks on the add button. Then, the
 * text area displays the line.
 *
 * Each time the user enters a new line in a text field and clicks on the add button,
 * the text area is updated displaying previously entered lines and the new line.
 *
 * To get a credit, the following requirements have to be satisfied.
 * 1. The program follows the MVC model.
 * 2. Listeners are implemented in an anonymous class.
 * 3. Model is a separate class from the client (test) program.
 * 4. Indication of which part of your program serves as model, controller or view.
 *
 * Think of the responsibilities of model, view and controller.
 * I will see if the responsibilities are appropriately placed.
 */

/**
  Run a test of the mail system using two telephones to access the system
*/
public class MVCTester
{
   /**
     Creates two phones and connects them to the system.
   */
   public static void main(String[] args)
   {
      MailSystem system = new MailSystem(MAILBOX_COUNT);

      ChatBoxView p1 = new ChatBoxView();
      p1.run();
   }

   private static int MAILBOX_COUNT = 20;
}
