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

    public boolean parseCommand(TaskList list) throws IOException {
        return parser.parse(br.readLine(), list);
    }
    public void printWelcome() {
        System.out.println("    Hello! I'm Duke");
        System.out.println("    What can I do for you?");
    }
    public void printBye() {
        System.out.println("    Bye. Hope to see you again soon!");
    }
}
