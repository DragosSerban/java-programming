import java.util.ArrayList;
import java.util.List;

/*
    clasa pt a crea obiecte Streamer (foloseste design patternul "Factory", deoarece poate crea diverse
    obiecte, instante ale unor clase ce extind clasa de baza Streamer

    de asemenea, a fost utilizat si pattern-ul Singleton pt a nu crea mai multe obiecte de tip StreamerFactory
    la fiecare creare de streamer
 */
public final class StreamerFactory {
    private static StreamerFactory instance;

    private StreamerFactory() {
    }

    public static StreamerFactory createInstance() {
        if (instance == null)
            instance = new StreamerFactory();
        return instance;
    }

    public Streamer createStreamer(int streamerType, int id, String name) {
        switch (streamerType) {
            case 1:
                return new MusicStreamer(id, name);
            case 2:
                return new PodcastStreamer(id, name);
            case 3:
                return new AudiobookStreamer(id, name);
            default:
                return null;
        }
    }
}

/*
    clasa abstracta Streamer, ce va fi extinsa de alte clase (MusicStreamer, PodcastStreamer, AudiobookStreamer)
 */
abstract class Streamer {
    private int id;
    private String name;
    private List<Stream> streams; // Streamurile obiectului de tip Streamer

    public Streamer(int id, String name) {
        this.id = id;
        this.name = name;
        streams = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    // metoda de atasare a unui observator de tip Stream
    public void addStream(Stream stream) {
        this.streams.add(stream);
    }

    // metoda de eliminare a unui observator de tip Stream
    public void removeStream(Stream stream) {
        this.streams.remove(stream);
    }

    // returneaza o lista cu streamurile
    public List<Stream> getStreams() {
        return this.streams;
    }

    public void notifyOfDeletion(Stream stream) {
        this.removeStream(stream);
        stream.updateDeleteStream();
    }

    // metoda utilizata pt stergerea unui stream din lista de streamuri ale streamerului;
    // este notificat si streamul de aceasta stergere pt a fi sters definitiv
    public void delete(int streamId) {
        for (Stream stream : streams) {
            if (stream.getId() == streamId) {
                notifyOfDeletion(stream);
                break;
            }
        }
    }
}

/*
    tipul MusicStreamer, extinde clasa Streamer
 */
class MusicStreamer extends Streamer {
    public int getStreamerType() {
        return 1;
    }

    public MusicStreamer(int id, String name) {
        super(id, name);
    }
}

/*
    tipul PodcastStreamer, extinde clasa Streamer
 */
class PodcastStreamer extends Streamer {
    public int getStreamerType() {
        return 2;
    }

    public PodcastStreamer(int id, String name) {
        super(id, name);
    }
}

/*
    tipul AudiobookStreamer, extinde clasa Streamer
 */
class AudiobookStreamer extends Streamer {
    public int getStreamerType() {
        return 3;
    }

    public AudiobookStreamer(int id, String name) {
        super(id, name);
    }
}