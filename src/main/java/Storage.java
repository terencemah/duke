import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public void load(TaskList list) throws FileNotFoundException {
        File f = new File(filePath);
        Scanner sc = new Scanner(f);

        while (sc.hasNextLine()) {
            String nextLine = sc.nextLine();
            StringTokenizer st = new StringTokenizer(nextLine, "|");
            String taskType = st.nextToken();
            String taskDone = st.nextToken();

            Task newTask;
            switch (taskType) {
                case "D":
                    newTask = new Deadline(st.nextToken(), st.nextToken());
                    break;
                case "E":
                    newTask = new Event(st.nextToken(), st.nextToken());
                    break;
                default:
                    newTask = new ToDo(st.nextToken());
                    break;
            }
            if (taskDone.equals("1")) {
                newTask.markDone();
            }
            list.add(newTask);
        }
    }
    public void save(TaskList list) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        String textToWrite = "";
        int listLength = list.size();
        for (int i = 0; i < listLength; i++) {
            textToWrite = textToWrite + list.get(i).getSaveDisplay();
        }
        fw.write(textToWrite);
        fw.close();
    }
}
