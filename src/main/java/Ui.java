public class Ui {
    /**
     * Returns the greeting message display on startup.
     */
    public String getWelcomeMessage() {
        return "    Hello! I'm Duke!\n" + "    What can I do for you today?\n";
    }

    /**
     * Returns the farewell message when user exits the application.
     */
    public String getExitMessage() {
        return "    Bye. Hope to see you again soon!\n";
    }

    public String getAddedMessage(Task task, TaskList list) {
        String output = "";
        output += "    Got it. I've added this task:\n";
        output += "      " + task.getTaskDisplay() + "\n";
        output += "    Now you have " + list.size() + " task"
                + ((list.size() == 1) ? "" : "s") + " in the list.\n";
        return output;
    }

    public String getIndexExceptionMessage() {
        return "    Oops! The task you referred to is not on the list.\n"
                + "    Please refer to the list using the 'list' command.\n";
    }

    public String getNumFormatExceptionMessage() {
        return "    Oops! This command must be followed by a task number.\n";
    }
}
