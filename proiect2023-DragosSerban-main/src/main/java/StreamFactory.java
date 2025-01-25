import java.util.ArrayList;
import java.util.List;

/*
    clasa pt a crea obiecte Stream (foloseste design patternul "Factory", deoarece poate crea diverse
    obiecte, instante ale unor clase ce extind clasa de baza Stream

    de asemenea, a fost utilizat si pattern-ul Singleton pt a nu putea crea mai multe obiecte de tip StreamFactory
    la fiecare creare de stream
 */
public final class StreamFactory {
    private static StreamFactory instance;

    private StreamFactory() {
    }

    public static StreamFactory createInstance() {
        if (instance == null)
            instance = new StreamFactory();
        return instance;
    }

    public Stream createStream(int streamType, int id, int streamGenre, long noOfStreams,
                               int streamerId, long length, long dateAdded, String name, Streamer streamer) {
        switch (streamType) {
            case 1:
                return new MusicStream(id, streamGenre, noOfStreams, streamerId, length, dateAdded, name, streamer);
            case 2:
                return new PodcastStream(id, streamGenre, noOfStreams, streamerId, length, dateAdded, name, streamer);
            case 3:
                return new AudiobookStream(id, streamGenre, noOfStreams, streamerId, length, dateAdded, name, streamer);
            default:
                return null;
        }
    }
}

/*
    clasa abstracta Stream, ce va fi extinsa de alte clase (MusicStream, PodcastStream, AudiobookStream)
 */
abstract class Stream {
    private int id;
    private int streamGenre;
    private long noOfStreams;
    private int streamerId;
    private long length;
    private long dateAdded;
    private String name;
    private Streamer streamer;
    private List<User> users;

    public Stream(int id, int streamGenre, long noOfStreams,
                  int streamerId, long length, long dateAdded, String name, Streamer streamer) {
        this.id = id;
        this.streamGenre = streamGenre;
        this.noOfStreams = noOfStreams;
        this.streamerId = streamerId;
        this.length = length;
        this.dateAdded = dateAdded;
        this.name = name;
        addStreamer(streamer);
        users = new ArrayList<>();
    }

    public abstract int getStreamType();

    public int getId() {
        return id;
    }

    public int getStreamGenre() {
        return streamGenre;
    }

    public long getNoOfStreams() {
        return noOfStreams;
    }

    public void setNoOfStreams(long noOfStreams) {
        this.noOfStreams = noOfStreams;
    }

    public int getStreamerId() {
        return streamerId;
    }

    public long getLength() {
        return length;
    }

    public long getDateAdded() {
        return dateAdded;
    }

    public String getName() {
        return name;
    }

    public Streamer getStreamer() {
        return streamer;
    }

    // ataseaza un subiect Streamer
    public void addStreamer(Streamer streamer) {
        this.streamer = streamer;
        this.streamer.addStream(this);
    }

    // elimina Streamerul
    public void removeStreamer() {
        this.streamer = null;
    }

    // actualizeaza obiectul Stream (sterge Streamerul si userii)
    public void updateDeleteStream() {
        removeStreamer();
        this.removeUsers(); // actualizeaza starea observatorului (observatorul sterge subiectul)
    }

    // metoda de atasare a unui subiect de tip User
    public void addUser(User user) {
        this.users.add(user);
        user.addStream(this);
    }

    // metoda de eliminare a unui subiect de tip User
    public void removeUser(User user) {
        this.users.remove(user);
        user.removeStream(this);
    }

    // metoda de eliminare a tuturor subiectilor de tip User
    public void removeUsers() {
        for (User user : this.users)
            removeUser(user);
        this.users.clear();
    }

    // metoda de actualizare stream -> creste numarul ascultarilor
    public void updateListenStream() {
        this.noOfStreams += 1;
    }

}

/*
    tipul MusicStream, extinde clasa Stream
 */
class MusicStream extends Stream {
    public int getStreamType() {
        return 1;
    }

    public MusicStream(int id, int streamGenre, long noOfStreams,
                       int streamerId, long length, long dateAdded, String name, Streamer streamer) {
        super(id, streamGenre, noOfStreams, streamerId, length, dateAdded, name, streamer);
    }
}

/*
    tipul PodcastStream, extinde clasa Stream
 */
class PodcastStream extends Stream {
    public int getStreamType() {
        return 2;
    }

    public PodcastStream(int id, int streamGenre, long noOfStreams,
                         int streamerId, long length, long dateAdded, String name, Streamer streamer) {
        super(id, streamGenre, noOfStreams, streamerId, length, dateAdded, name, streamer);
    }
}

/*
    tipul AudiobookStream, extinde clasa Stream
 */
class AudiobookStream extends Stream {
    public int getStreamType() {
        return 3;
    }

    public AudiobookStream(int id, int streamGenre, long noOfStreams,
                           int streamerId, long length, long dateAdded, String name, Streamer streamer) {
        super(id, streamGenre, noOfStreams, streamerId, length, dateAdded, name, streamer);
    }
}