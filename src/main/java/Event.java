public class Event extends Task {
    protected String time;

    public Event(String name, String time) {
        super(name);
        this.time = time;
    }

    public String getTaskDisplay() {
        return "[E][" + getStatusIcon() + "] " + name + " (at: " + time + ")";
    }
    public String getSaveDisplay() {
        return "E|" + (isDone ? "1" : "0") + "|" + name + "|" + time + "\n";
    }
}