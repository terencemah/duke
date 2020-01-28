package main.java;

public class ToDo extends Task {
    public ToDo(String name) {
        super(name);
    }

    public String getTaskDisplay() {
        return "[T][" + getStatusIcon() + "] " + name;
    }
    public String getSaveDisplay() {
        return "T|" + (isDone ? "1" : "0") + "|" + name + "\n";
    }
}