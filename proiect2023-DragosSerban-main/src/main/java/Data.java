/*
    obiectul de tip Data va contine datele aplicatiei
    folosim Singleton design pattern pt a crea un singur obiect de acest tip;
    aplicatia noastra ruleaza pe un singur tip de date de forma (Streamers, Streams, Users)
 */
public final class Data {
    private static Data instance;
    // obiectele cu datele streamerilor, ale streamurilor si ale userilor
    private Streamers streamers;
    private Streams streams;
    private Users users;

    private Data() {
    }

    private Data(Streamers streamers, Streams streams, Users users) {
        this.streamers = streamers;
        this.streams = streams;
        this.users = users;
    }

    public static Data createInstance(Streamers streamers, Streams streams, Users users) {
        if (instance == null)
            instance = new Data(streamers, streams, users);
        return instance;
    }

    public Streamers getStreamers() {
        return this.streamers;
    }

    public Streams getStreams() {
        return this.streams;
    }

    public Users getUsers() {
        return this.users;
    }

    public static void deleteInstance() {
        instance = null;
    }

    void runCommand(String command) {
        CommandFactory commandFactory = new CommandFactory();
        Command currentCommand = commandFactory.createCommand(command, this);
        currentCommand.execute();
    }
}