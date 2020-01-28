public class Event extends Task {
    protected String eventTime;

    public Event(String name, String eventTime) {
        super(name);
        this.eventTime = eventTime;
    }

    public String getTaskDisplay() {
        return "[E][" + getStatusIcon() + "] " + name + " (at: " + eventTime + ")";
    }
    public String getSaveDisplay() {
        return "E|" + (isDone ? "1" : "0") + "|" + name + "|" + eventTime + "\n";
    }
}