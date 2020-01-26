public class ToDo extends Task {
    public ToDo(String name) {
        super(name);
    }

    public String getTaskDisplay() {
        return "[T][" + getStatusIcon() + "] " + name;
    }
}