import java.io.IOException;
import java.util.List;

/*
    clasa ce va crea obiecte ce vor reprezenta interfata aplicatiei; utilizeaza Facade design pattern
    pt a face utilizarea functionalitatilor programului mai usor de folosit si intuitive pt utilizator
 */
public class AppInterface {
    private String streamersFile;
    private String streamsFile;
    private String usersFile;
    private String commandFile;
    private Streamers streamers;
    private Streams streams;
    private Users users;
    private List<String> commands;
    private Data data;

    public AppInterface(String streamersFile, String streamsFile, String usersFile, String commandFile) {
        this.streamersFile = streamersFile;
        this.streamsFile = streamsFile;
        this.usersFile = usersFile;
        this.commandFile = commandFile;
    }

    public void readAppData() {
        // se va folosi un obiect pt citirea datelor din fisierele de input
        SaveFileContent saveFileContent = SaveFileContent.createInstance();

        // se vor citi informatiile aplicatiei
        try {
            streamers = saveFileContent.saveStreamers("src/main/resources/" + streamersFile);
            streams = saveFileContent.saveStreams("src/main/resources/" + streamsFile);
            users = saveFileContent.saveUsers("src/main/resources/" + usersFile);
            commands = saveFileContent.readFromFile("src/main/resources/" + commandFile);
        } catch (IOException e) {
        }
    }

    public void saveAppData() {
        // va fi creat un obiect ce va contine toate datele aplicatiei (obiectul va fi creat cu Singleton)
        if(streamers != null && streams != null && users != null && commands != null)
            data = Data.createInstance(streamers, streams, users);
    }

    public void runCommands() {
        // se ruleaza fiecare comanda in parte
        if(data == null)
            return;

        for (String command : commands)
            data.runCommand(command);
    }

    public void deleteAppData() {
        // se sterg datele aplicatiei
        Data.deleteInstance();
    }
}
