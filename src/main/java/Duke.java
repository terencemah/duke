package main.java;

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
                        System.out.println("    " + (i + 1) + ".[" + list.get(i).getStatusIcon()
                                + "] " + list.get(i).getName());
                    }
                    break;
                case "done":
                    int index = Integer.parseInt(input[1]) - 1;
                    list.get(index).setDone();
                    System.out.println("    Nice! I've marked this task as done:");
                    System.out.println("      [" + list.get(index).getStatusIcon()
                            + "] " + list.get(index).getName());
                    break;
                default:
                    list.add(new Task(command));
                    System.out.println("    added: " + command);
            }

            command = br.readLine();
            input = command.split(" ", 2);
        }

        System.out.println("    Bye. Hope to see you again soon!");
    }
}