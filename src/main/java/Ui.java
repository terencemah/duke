import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Ui {
    private BufferedReader br;
    private Parser parser;

    public Ui() {
        br = new BufferedReader(new InputStreamReader(System.in));
        parser = new Parser();
    }

    /**
     * Scans user input and passes it to a Parser object
     * for processing, with the program's working task list.
     * Returns false to exit the program loop if user input is "bye",
     * and returns true otherwise.
     *
     * @param list The program's working task list, for updating.
     * @return false if user input is "bye", true otherwise
     * @throws IOException If an error occurs in parsing user input.
     */
    public boolean parseCommand(TaskList list) throws IOException {
        return parser.parse(br.readLine(), list);
    }

    /**
     * Prints the greeting message to the user on startup.
     */
    public void printWelcome() {
        System.out.println("    Hello! I'm Duke");
        System.out.println("    What can I do for you?");
    }

    /**
     * Prints a farewell message when the user chooses
     * to exit the program.
     */
    public void printBye() {
        System.out.println("    Bye. Hope to see you again soon!");
    }
}
