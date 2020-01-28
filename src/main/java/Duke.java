import java.io.FileNotFoundException;
import java.io.IOException;

public class Duke {
    private Storage storage;
    private Ui ui;
    private TaskList tasks;

    /**
     * Initialises the Duke program on startup
     * by loading the previously saved task list
     * onto the working list, if there exists one.
     * Otherwise, the working list starts empty.
     *
     * @param filePath Relative file path to the file the task list was previously saved in.
     */
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

    /**
     * Runs the Duke program.
     *
     * @throws IOException If error is encountered in parsing user input.
     */
    public void run() throws IOException {
        ui.printWelcome();
        while (ui.parseCommand(tasks)) {
            storage.save(tasks);
        }
        ui.printBye();
    }
}