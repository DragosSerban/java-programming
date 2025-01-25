import java.io.*;
import java.util.*;

/*
    clasa cu ajutorul careia vom citi datele aplicatiei din fisierele csv
 */

public final class SaveFileContent {
    private static SaveFileContent instance;
    private Streamers streamers;
    private Streams streams;
    private Users users;

    private SaveFileContent() {
    }

    public static SaveFileContent createInstance() {
        if (instance == null)
            instance = new SaveFileContent();
        return instance;
    }

    // se citeste o lista de Stringuri (liniile csv-ului)
    public List<String> readFromFile(String name) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(name));
        List<String> content = new ArrayList<>();
        String line;
        while ((line = br.readLine()) != null) {
            content.add(line);
        }
        br.close();
        return content;
    }

    // se salveaza streamerii, streamurile si userii
    public Streamers saveStreamers(String file) throws IOException {
        List<String> l = readFromFile(file);
        this.streamers = new Streamers(l);
        return this.streamers;
    }

    public Streams saveStreams(String file) throws IOException {
        List<String> l = readFromFile(file);
        this.streams = new Streams(l, streamers);
        return this.streams;
    }

    public Users saveUsers(String file) throws IOException {
        List<String> l = readFromFile(file);
        this.users = new Users(l, streams);
        return this.users;
    }
}