public abstract class Task {
    protected boolean isDone;
    protected String name;

    public Task(String name) {
        this.name = name;
        isDone = false;
    }

    public abstract String getTaskDisplay();
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718");
    }
    public void markDone() {
        isDone = true;
    }
}