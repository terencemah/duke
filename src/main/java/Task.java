public abstract class Task {
    protected boolean isDone;
    protected String name;

    public Task(String name) {
        this.name = name;
        isDone = false;
    }

    public abstract String getTaskDisplay();
    public abstract String getSaveDisplay();

    /**
     * Outputs a tick or cross symbol to indicate whether
     * or not a task has been completed.
     *
     * @return A tick if task is done, and a cross otherwise.
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718");
    }

    /**
     * Allows a task to be marked as done.
     */
    public void markDone() {
        isDone = true;
    }
    public String getName() {
        return name;
    }
}