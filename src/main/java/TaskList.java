import java.util.ArrayList;

public class TaskList extends ArrayList<Task> {
    public TaskList() {
        super(100);
    }
    public TaskList(TaskList list) {
        super(list);
    }

}
