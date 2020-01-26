import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Duke {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        ArrayList<Task> list = new ArrayList<>(100);

        System.out.println("    Hello! I'm Duke");
        System.out.println("    What can I do for you?");
        String command = br.readLine();
        String[] input = command.split(" ", 2);

        while (!input[0].equals("bye")) {
            switch (input[0]) {
                case "list":
                    System.out.println("    Here are the tasks in your list:");
                    int listLength = list.size();
                    for (int i = 0; i < listLength; i++) {
                        System.out.println("    " + (i + 1) + "." + list.get(i).getTaskDisplay());
                    }
                    break;
                case "todo":
                    Task todo = new ToDo(input[1]);
                    list.add(todo);
                    System.out.println("    Got it. I've added this task:");
                    System.out.println("      " + todo.getTaskDisplay());
                    System.out.println("    Now you have " + list.size() + " task"
                            + ((list.size() == 1) ? "" : "s") + " in the list.");
                    break;
                case "deadline":
                    String[] details = input[1].split(" /by ", 2);
                    Task deadline = new Deadline(details[0], details[1]);
                    list.add(deadline);
                    System.out.println("    Got it. I've added this task:");
                    System.out.println("      " + deadline.getTaskDisplay());
                    System.out.println("    Now you have " + list.size() + " task"
                            + ((list.size() == 1) ? "" : "s") + " in the list.");
                    break;
                case "event":
                    details = input[1].split(" /at ", 2);
                    Task event = new Event(details[0], details[1]);
                    list.add(event);
                    System.out.println("    Got it. I've added this task:");
                    System.out.println("      " + event.getTaskDisplay());
                    System.out.println("    Now you have " + list.size() + " task"
                            + ((list.size() == 1) ? "" : "s") + " in the list.");
                    break;
                case "done":
                    int index = Integer.parseInt(input[1]) - 1;
                    list.get(index).markDone();
                    System.out.println("    Nice! I've marked this task as done:");
                    System.out.println("      " + list.get(index).getTaskDisplay());
                    break;
            }

            command = br.readLine();
            input = command.split(" ", 2);
        }

        System.out.println("    Bye. Hope to see you again soon!");
    }
}