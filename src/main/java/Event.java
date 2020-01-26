public class Event extends Task {
    protected String time;

    public Event(String name, String time) {
        super(name);
        this.time = time;
    }

    public String getTaskDisplay() {
        return "[E][" + getStatusIcon() + "] " + name + " (at: " + time + ")";
    }
}