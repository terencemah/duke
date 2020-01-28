public class Deadline extends Task {
    protected String deadline;

    public Deadline(String name, String deadline) {
        super(name);
        this.deadline = deadline;
    }

    public String getTaskDisplay() {
        return "[D][" + getStatusIcon() + "] " + name + " (by: " + deadline + ")";
    }
    public String getSaveDisplay() {
        return "D|" + (isDone ? "1" : "0") + "|" + name + "|" + deadline + "\n";
    }
}