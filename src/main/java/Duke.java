import java.io.FileNotFoundException;
import java.io.IOException;

public class Duke {
    private Storage storage;
    private Ui ui;
    private TaskList tasks;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList();
        try {
            storage.load(tasks);
        } catch (FileNotFoundException e) {
            // do nothing
        }
    }

    public static void main(String[] args) throws IOException {
        new Duke("./data/duke.txt").run();
    }

    public void run() throws IOException {
        ui.printWelcome();
        while (ui.parseCommand(tasks)) {
            storage.save(tasks);
        }
        ui.printBye();
    }
}