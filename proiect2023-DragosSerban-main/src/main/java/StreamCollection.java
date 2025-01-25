import java.util.ArrayList;
import java.util.List;

interface Collection {
    Iterator createIteratorRecommended();

    Iterator createIteratorSurprise();
}

/*
    clasa ce va fi folosita pt a crea colectii de obiecte de tip Stream
    prin care vom itera folosind Iterator design pattern
 */
public class StreamCollection implements Collection {
    private List<Stream> streams = new ArrayList<>();

    public StreamCollection() {
    }

    public void addStream(Stream stream) {
        streams.add(stream);
    }

    public Iterator createIteratorRecommended() {
        return new RecommendedStreamIterator(streams);
    }

    public Iterator createIteratorSurprise() {
        return new SurpriseStreamIterator(streams);
    }
}