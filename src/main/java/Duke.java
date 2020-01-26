import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Duke {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("    Hello! I'm Duke");
        System.out.println("    What can I do for you?");
        String command = br.readLine();

        while (!command.equals("bye")) {
            System.out.println("    " + command);
            command = br.readLine();
        }

        System.out.println("    Bye. Hope to see you again soon!");
    }
}