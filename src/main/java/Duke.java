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
                    try {
                        Task todo = new ToDo(input[1]);
                        list.add(todo);
                        System.out.println("    Got it. I've added this task:");
                        System.out.println("      " + todo.getTaskDisplay());
                        System.out.println("    Now you have " + list.size() + " task"
                                + ((list.size() == 1) ? "" : "s") + " in the list.");
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("    Oops! The description of a todo cannot be empty.");
                    } finally {
                        break;
                    }
                case "deadline":
                    try {
                        String[] details = input[1].split(" /by ", 2);
                        Task deadline = new Deadline(details[0], details[1]);
                        list.add(deadline);
                        System.out.println("    Got it. I've added this task:");
                        System.out.println("      " + deadline.getTaskDisplay());
                        System.out.println("    Now you have " + list.size() + " task"
                                + ((list.size() == 1) ? "" : "s") + " in the list.");
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("    Oops! A deadline must contain both a task "
                                + "description and a deadline.");
                    } finally {
                        break;
                    }
                case "event":
                    try {
                        String[] details = input[1].split(" /at ", 2);
                        Task event = new Event(details[0], details[1]);
                        list.add(event);
                        System.out.println("    Got it. I've added this task:");
                        System.out.println("      " + event.getTaskDisplay());
                        System.out.println("    Now you have " + list.size() + " task"
                                + ((list.size() == 1) ? "" : "s") + " in the list.");
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("    Oops! An event must contain both an event "
                                + "description and event time.");
                    } finally {
                        break;
                    }
                case "done":
                    try {
                        int index = Integer.parseInt(input[1]) - 1;
                        list.get(index).markDone();
                        System.out.println("    Nice! I've marked this task as done:");
                        System.out.println("      " + list.get(index).getTaskDisplay());
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("    Oops! The task you referred to is not on the list.");
                        System.out.println("    Please refer to the list using the 'list' command.");
                    } catch (NumberFormatException e) {
                        System.out.println("    Oops! The 'done' command must be followed by "
                                + "a task number.");
                    } finally {
                        break;
                    }
                case "delete":
                    try {
                        int index = Integer.parseInt(input[1]) - 1;
                        Task toDelete = list.remove(index);
                        System.out.println("    Noted. I've removed this task:");
                        System.out.println("      " + toDelete.getTaskDisplay());
                        System.out.println("    Now you have " + list.size() + " task"
                                + ((list.size() == 1) ? "" : "s") + " in the list.");
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("    Oops! The task you referred to is not on the list.");
                        System.out.println("    Please refer to the list using the 'list' command.");
                    } catch (NumberFormatException e) {
                        System.out.println("    Oops! The 'delete' command must be followed by "
                                + "a task number.");
                    } finally {
                        break;
                    }
                default:
                    System.out.println("    Oops! I'm sorry, but I don't know what that means :(");
                    break;
            }
            command = br.readLine();
            input = command.split(" ", 2);
        }
        System.out.println("    Bye. Hope to see you again soon!");
    }
}