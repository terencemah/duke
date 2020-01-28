public class Parser {
    /**
     * Parses the user input and processes the commands accordingly.
     * Returns false to exit the program loop if the command is "bye",
     * and returns true otherwise.
     *
     * @param input Input string entered by user.
     * @param list Working task list of the program, for updating.
     * @return false if user input is "bye", and true otherwise.
     */
    public boolean parse(String input, TaskList list) {
        String[] commands = input.split(" ", 2);
        switch (commands[0]) {
        case "bye":
            return false;
        case "list":
            System.out.println("    Here are the tasks in your list:");
            int listLength = list.size();
            for (int i = 0; i < listLength; i++) {
                System.out.println("    " + (i + 1) + "." + list.get(i).getTaskDisplay());
            }
            break;
        case "todo":
            try {
                Task todo = new ToDo(commands[1]);
                list.add(todo);
                printAdded(todo, list);
            } catch (IndexOutOfBoundsException e) {
                System.out.println("    Oops! The description of a todo cannot be empty.");
            } finally {
                break;
            }
        case "deadline":
            try {
                String[] details = commands[1].split(" /by ", 2);
                Task deadline = new Deadline(details[0], details[1]);
                list.add(deadline);
                printAdded(deadline, list);
            } catch (IndexOutOfBoundsException e) {
                System.out.println("    Oops! A deadline must contain both a task "
                        + "description and a deadline.");
            } finally {
                break;
            }
        case "event":
            try {
                String[] details = commands[1].split(" /at ", 2);
                Task event = new Event(details[0], details[1]);
                list.add(event);
                printAdded(event, list);
            } catch (IndexOutOfBoundsException e) {
                System.out.println("    Oops! An event must contain both an event "
                        + "description and event time.");
            } finally {
                break;
            }
        case "done":
            try {
                int index = Integer.parseInt(commands[1]) - 1;
                list.get(index).markDone();
                System.out.println("    Nice! I've marked this task as done:");
                System.out.println("      " + list.get(index).getTaskDisplay());
            } catch (IndexOutOfBoundsException e) {
                printIndexException();
            } catch (NumberFormatException e) {
                printNumFormatException();
            } finally {
                break;
            }
        case "delete":
            try {
                int index = Integer.parseInt(commands[1]) - 1;
                Task toDelete = list.remove(index);
                System.out.println("    Noted. I've removed this task:");
                System.out.println("      " + toDelete.getTaskDisplay());
                System.out.println("    Now you have " + list.size() + " task"
                        + ((list.size() == 1) ? "" : "s") + " in the list.");
            } catch (IndexOutOfBoundsException e) {
                printIndexException();
            } catch (NumberFormatException e) {
                printNumFormatException();
            } finally {
                break;
            }
        case "find":
            try {
                System.out.println("    Here are the matching tasks in your list:");
                int listSize = list.size();
                for (int i = 0; i < listSize; i++) {
                    if (list.get(i).getName().contains(commands[1])) {
                        System.out.println("    " + (i + 1) + "." + list.get(i).getTaskDisplay());
                    }
                }
            } catch (IndexOutOfBoundsException e) {
                System.out.println("    Oops! A 'find' command must be followed by a search word.");
            }
            break;
        default:
            System.out.println("    Oops! I'm sorry, but I don't know what that means :(");
            break;
        }
        return true;
    }

    /**
     * Prints the message for whenever the user adds a task.
     * The message tells the user that the task has been added
     * and displays the task just added.
     *
     * @param task Task that was added.
     * @param list Working task list, for printing reference.
     */
    private void printAdded(Task task, TaskList list) {
        System.out.println("    Got it. I've added this task:");
        System.out.println("      " + task.getTaskDisplay());
        System.out.println("    Now you have " + list.size() + " task"
                + ((list.size() == 1) ? "" : "s") + " in the list.");
    }

    /**
     * Prints a message to notify user of an invalid command.
     */
    private void printIndexException() {
        System.out.println("    Oops! The task you referred to is not on the list.");
        System.out.println("    Please refer to the list using the 'list' command.");
    }

    /**
     * Prints a message to notify user of an invalid command.
     */
    private void printNumFormatException() {
        System.out.println("    Oops! The 'done' command must be followed by "
                + "a task number.");
    }
}
