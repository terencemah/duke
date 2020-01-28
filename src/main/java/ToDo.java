public class ToDo extends Task {
    public ToDo(String name) {
        super(name);
    }

    /**
     * Formats the task for display to the user.
     *
     * @return The output string to be displayed.
     */
    public String getTaskDisplay() {
        return "[T][" + getStatusIcon() + "] " + name;
    }

    /**
     * Formats the task as it is to be stored/saved
     * on the hard disk.
     *
     * @return The string the task is to be saved as.
     */
    public String getSaveDisplay() {
        return "T|" + (isDone ? "1" : "0") + "|" + name + "\n";
    }
}