import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/*
    clasa folosita pt a crea obiecte User
 */
public class User {
    private int id;
    private String name;
    // se foloseste Observer design pattern pt actualizarea streamurilor ce sunt actualizate in urma unei noi ascultari
    private List<Stream> streams;  // observatorii subiectului (streamurile ascultate)

    public User(int id, String name) {
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

    public List<Stream> getStreams() {
        return streams;
    }

    // metoda de adaugare observator al subiectului User
    public void addStream(Stream stream) {
        this.streams.add(stream);
    }

    // metoda de eliminare observator al subiectului User
    public void removeStream(Stream stream) {
        this.streams.remove(stream);
    }

    // metoda de adaugare observator nou in urma actiunii listen;
    public void listen(Stream stream) {
        if(!streams.contains(stream))
            stream.addUser(this);
        this.notifyObserverOfListen(stream);
    }

    // subiectul notifica observatorul, ce va fi actualizat (nr ascultari ++)
    public void notifyObserverOfListen(Stream stream) {
        stream.updateListenStream();
    }
}