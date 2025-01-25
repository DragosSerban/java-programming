import java.util.HashMap;

/*
    comanda pt adaugarea unui stream
 */
public class CommandStreamerAdd extends Command {
    private Streamer streamer;
    private int streamerId;
    private int streamType;
    private int id;
    private int streamGenre;
    private long length;
    private String name;

    public CommandStreamerAdd(Streamer streamer, int streamerId, int streamType, int id,
                              int streamGenre, long length, String name, Data data) {
        super(data);
        this.streamer = streamer;
        this.setStreamDetails(streamerId, streamType, id, streamGenre, length, name);
    }

    public void execute() {
        StreamFactory streamFactory = StreamFactory.createInstance();
        long milliSeconds = System.currentTimeMillis();
        HashMap<Integer, Stream> streams = data.getStreams().getStreamsByType(streamType);
        // se adauga streamul
        streams.put(id, streamFactory.createStream(streamType, id, streamGenre, 0, streamerId, length,
                milliSeconds / 1000, name, streamer));
    }

    // metoda utilizata pt setarea detaliilor streamului ce va fi adaugat de catre streamer
    private void setStreamDetails(int streamerId, int streamType, int id, int streamGenre, long length, String name) {
        this.streamerId = streamerId;
        this.streamType = streamType;
        this.id = id;
        this.streamGenre = streamGenre;
        this.length = length;
        this.name = name;
    }
}