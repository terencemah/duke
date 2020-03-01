import java.util.EmptyStackException;

public class Parser {
    private Ui ui;
    private ActionHistory history;

    public Parser() {
        ui = new Ui();
        history = new ActionHistory();
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
        String response;
        TaskList clonedList = new TaskList();
        ActionHistory.copyList(list, clonedList);
        String[] commands = input.split(" ", 2);

        switch (commands[0].toLowerCase()) {
        case "bye":
            response = ui.getExitMessage();
            break;
        case "list":
            response = executeListCommand(list);
            break;
        case "todo":
            response = executeTodoCommand(commands, list, clonedList);
            break;
        case "deadline":
            response = executeDeadlineCommand(commands, list, clonedList);
            break;
        case "event":
            response = executeEventCommand(commands, list, clonedList);
            break;
        case "done":
            response = executeDoneCommand(commands, list, clonedList);
            break;
        case "delete":
            response = executeDeleteCommand(commands, list, clonedList);
            break;
        case "find":
            response = executeFindCommand(commands, list);
            break;
        case "undo":
            response = executeUndoCommand(list);
            break;
        default:
            response = "    Oops! I'm sorry, but I don't know what that means :(\n";
            break;
        }
        return response;
    }

    private String executeListCommand(TaskList list) {
        String response = "    Here are the tasks in your list:\n";
        int listLength = list.size();
        for (int i = 0; i < listLength; i++) {
            response += "    " + (i + 1) + "." + list.get(i).getTaskDisplay() + "\n";
        }
        return response;
    }

    private String executeAddCommand(Task task, TaskList list) {
        list.add(task);
        return ui.getAddedMessage(task, list);
    }

    private String executeTodoCommand(String[] commands, TaskList original, TaskList clone) {
        String response;
        try {
            Task todo = new ToDo(commands[1]);
            response = executeAddCommand(todo, original);
            history.push(clone);
        } catch (IndexOutOfBoundsException e) {
            response = "    Oops! The description of a todo cannot be empty.\n";
        }
        return response;
    }

    private String executeDeadlineCommand(String[] commands, TaskList original, TaskList clone) {
        String response;
        try {
            String[] details = commands[1].split(" /by ", 2);
            Task deadline = new Deadline(details[0], details[1]);
            response = executeAddCommand(deadline, original);
            history.push(clone);
        } catch (IndexOutOfBoundsException e) {
            response = "    Oops! A deadline must contain both a task "
                    + "description and a deadline.\n";
        }
        return response;
    }

    private String executeEventCommand(String[] commands, TaskList original, TaskList clone) {
        String response;
        try {
            String[] details = commands[1].split(" /at ", 2);
            Task event = new Event(details[0], details[1]);
            response = executeAddCommand(event, original);
            history.push(clone);
        } catch (IndexOutOfBoundsException e) {
            response = "    Oops! An event must contain both an event "
                    + "description and event time.\n";
        }
        return response;
    }

    private String executeDoneCommand(String[] commands, TaskList original, TaskList clone) {
        String response;
        try {
            int index = Integer.parseInt(commands[1]) - 1;
            original.get(index).markDone();
            response = "    Nice! I've marked this task as done:\n      "
                    + original.get(index).getTaskDisplay() + "\n";
            history.push(clone);
        } catch (IndexOutOfBoundsException e) {
            response = ui.getIndexExceptionMessage();
        } catch (NumberFormatException e) {
            response = ui.getNumFormatExceptionMessage();
        }
        return response;
    }

    private String executeDeleteCommand(String[] commands, TaskList original, TaskList clone) {
        String response;
        try {
            int index = Integer.parseInt(commands[1]) - 1;
            Task toDelete = original.remove(index);
            response = "    Noted. I've removed this task:\n      " + toDelete.getTaskDisplay()
                    + "\n    Now you have " + original.size() + " task"
                    + ((original.size() == 1) ? "" : "s") + " in the list.\n";
            history.push(clone);
        } catch (IndexOutOfBoundsException e) {
            response = ui.getIndexExceptionMessage();
        } catch (NumberFormatException e) {
            response = ui.getNumFormatExceptionMessage();
        }
        return response;
    }

    private String executeFindCommand(String[] commands, TaskList list) {
        String response;
        try {
            response = "    Here are the matching tasks in your list:\n";
            int listSize = list.size();
            for (int i = 0; i < listSize; i++) {
                if (list.get(i).getName().contains(commands[1])) {
                    response += "    " + (i + 1) + "." + list.get(i).getTaskDisplay() + "\n";
                }
            }
        } catch (IndexOutOfBoundsException e) {
            response = "    Oops! A 'find' command must be followed by a search word.\n";
        }
        return response;
    }

    private String executeUndoCommand(TaskList list) {
        String response;
        try {
            ActionHistory.copyList(history.pop(), list);
            response = "    Undo successful!\n";
        } catch (EmptyStackException e) {
            response = "    Oops! Can't undo any further.\n";
        }
        return response;
    }
}
