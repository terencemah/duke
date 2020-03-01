import java.io.FileNotFoundException;

public class Duke {
    private Parser parser;
    private Storage storage;
    private TaskList tasks;

    private final String SAVE_DIR = "./data/duke.txt";

    /**
     * Initialises the Duke program on startup
     * by loading the previously saved task list
     * onto the working list, if there exists one.
     * Otherwise, the working list starts empty.
     */
    public Duke() {
        parser = new Parser();
        storage = new Storage(SAVE_DIR);
        tasks = new TaskList();
        try {
            storage.load(tasks);
        } catch (FileNotFoundException e) {
            // do nothing
        }
    }

    public String getWelcomeMessage() {
        return parser.getWelcomeMessage();
    }

    public String getResponse(String command) {
        String response = parser.parseCommand(command, tasks);
        storage.save(tasks);
        return response;
    }
}