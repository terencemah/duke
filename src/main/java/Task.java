package main.java;

public class Task {
    protected boolean isDone;
    protected String name;

    public Task(String name) {
        this.name = name;
        isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718");
    }
    public String getName() {
        return name;
    }
    public void setDone() {
        isDone = true;
    }
}
