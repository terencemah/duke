public class Parser {
    private Ui ui;

    public Parser() {
        this.ui = new Ui();
    }

    public String getWelcomeMessage() {
        return ui.getWelcomeMessage();
    }

    /**
     * Parses the user input and processes the commands accordingly.
     * Returns false to exit the program loop if the command is "bye",
     * and returns true otherwise.
     *
     * @param input Input string entered by user.
     * @param list Working task list of the program, for updating.
     * @return false if user input is "bye", and true otherwise.
     */
    public String parseCommand(String input, TaskList list) {
        String response = "";

        String[] commands = input.split(" ", 2);
        switch (commands[0]) {
        case "bye":
            response += ui.getExitMessage();
            break;
        case "list":
            response += "    Here are the tasks in your list:\n";
            int listLength = list.size();
            for (int i = 0; i < listLength; i++) {
                response += "    " + (i + 1) + "." + list.get(i).getTaskDisplay() + "\n";
            }
            break;
        case "todo":
            try {
                Task todo = new ToDo(commands[1]);
                list.add(todo);
                response += ui.getAddedMessage(todo, list);
            } catch (IndexOutOfBoundsException e) {
                response += "    Oops! The description of a todo cannot be empty.\n";
            } finally {
                break;
            }
        case "deadline":
            try {
                String[] details = commands[1].split(" /by ", 2);
                Task deadline = new Deadline(details[0], details[1]);
                list.add(deadline);
                response += ui.getAddedMessage(deadline, list);
            } catch (IndexOutOfBoundsException e) {
                response += "    Oops! A deadline must contain both a task "
                        + "description and a deadline.\n";
            } finally {
                break;
            }
        case "event":
            try {
                String[] details = commands[1].split(" /at ", 2);
                Task event = new Event(details[0], details[1]);
                list.add(event);
                response += ui.getAddedMessage(event, list);
            } catch (IndexOutOfBoundsException e) {
                response += "    Oops! An event must contain both an event "
                        + "description and event time.\n";
            } finally {
                break;
            }
        case "done":
            try {
                int index = Integer.parseInt(commands[1]) - 1;
                list.get(index).markDone();
                response += "    Nice! I've marked this task as done:\n      "
                        + list.get(index).getTaskDisplay() + "\n";
            } catch (IndexOutOfBoundsException e) {
                response += ui.getIndexExceptionMessage();
            } catch (NumberFormatException e) {
                response += ui.getNumFormatExceptionMessage();
            } finally {
                break;
            }
        case "delete":
            try {
                int index = Integer.parseInt(commands[1]) - 1;
                Task toDelete = list.remove(index);
                response += "    Noted. I've removed this task:\n";
                response += "      " + toDelete.getTaskDisplay() + "\n";
                response += "    Now you have " + list.size() + " task"
                        + ((list.size() == 1) ? "" : "s") + " in the list.\n";
            } catch (IndexOutOfBoundsException e) {
                response += ui.getIndexExceptionMessage();
            } catch (NumberFormatException e) {
                response += ui.getNumFormatExceptionMessage();
            } finally {
                break;
            }
        case "find":
            try {
                response += "    Here are the matching tasks in your list:\n";
                int listSize = list.size();
                for (int i = 0; i < listSize; i++) {
                    if (list.get(i).getName().contains(commands[1])) {
                        response += "    " + (i + 1) + "." + list.get(i).getTaskDisplay() + "\n";
                    }
                }
            } catch (IndexOutOfBoundsException e) {
                response += "    Oops! A 'find' command must be followed by a search word.\n";
            }
            break;
        default:
            response += "    Oops! I'm sorry, but I don't know what that means :(\n";
            break;
        }
        return response;
    }
}
