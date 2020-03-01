import java.util.Stack;

public class ActionHistory extends Stack<TaskList> {
    public static void copyList(TaskList original, TaskList copy) {
        int copyLength = copy.size();
        for (int i = 0; i < copyLength; i++) {
            copy.remove(0);
        }
        int originalLength = original.size();
        for (int i = 0; i < originalLength; i++) {
            copy.add(Task.duplicate(original.get(i)));
            if (original.get(i).isDone) {
                copy.get(i).markDone();
            }
        }
    }
}
