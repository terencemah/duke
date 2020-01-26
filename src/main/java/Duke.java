import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Duke {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        ArrayList<String> list = new ArrayList<>(100);

        System.out.println("    Hello! I'm Duke");
        System.out.println("    What can I do for you?");
        String command = br.readLine();

        while (!command.equals("bye")) {
            switch (command) {
                case "list":
                    int listLength = list.size();
                    for (int i = 0; i < listLength; i++) {
                        System.out.println("    " + (i + 1) + ". " + list.get(i));
                    }
                    break;
                default:
                    list.add(command);
                    System.out.println("    added: " + command);
            }
            command = br.readLine();
        }

        System.out.println("    Bye. Hope to see you again soon!");
    }
}