import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/*
    se va implementa design patterul Iterator pt a oferi o modalitate standard de iterare prin streamuri
    atunci cand se doreste recomandarea de tip Recommend sau Surprise a streamurilor pt un user
 */
interface Iterator {
    boolean hasNext();

    Object next();
}

/*
    implementam iterator design pattern pt a itera prin stream-uri
    in functie de numarul de ascultari (util la functia de recommend streams)
 */
class RecommendedStreamIterator implements Iterator {
    private List<Stream> streams;
    private int index = 0;

    public RecommendedStreamIterator(List<Stream> streams) {
        this.streams = new ArrayList<>(streams);
        Collections.sort(this.streams, new RecommendedComparator());
    }

    public Object next() {
        Stream ret = streams.get(index);
        index++;
        return ret;
    }

    public boolean hasNext() {
        if (index >= streams.size())
            return false;
        return true;
    }
}

/*
    implementam iterator design pattern pt a itera prin stream-uri
    in functie de data cand au fost publicate (util la functia de surprise streams)
 */
class SurpriseStreamIterator implements Iterator {
    private List<Stream> streams;
    private int index = 0;

    public SurpriseStreamIterator(List<Stream> streams) {
        this.streams = new ArrayList<>(streams);
        Collections.sort(this.streams, new SurpriseComparator());
    }

    public Object next() {
        Stream ret = streams.get(index);
        index++;
        return ret;
    }

    public boolean hasNext() {
        if (index >= streams.size())
            return false;
        return true;
    }
}

/*
    se vor implementa comparatoare pt a aranja obiectele in ordinea dorita inainte de iterare
 */
class RecommendedComparator implements Comparator<Stream> {
    public int compare(Stream s1, Stream s2) {
        if(s1.getNoOfStreams() == s2.getNoOfStreams())
            return 0;
        else if(s1.getNoOfStreams() < s2.getNoOfStreams())
            return 1;
        else
            return -1;
    }
}

class SurpriseComparator implements Comparator<Stream> {
    public int compare(Stream s1, Stream s2) {
        if(s1.getDateAdded() == s2.getDateAdded()) {
            if(s1.getNoOfStreams() == s2.getNoOfStreams())
                return 0;
            else if(s1.getNoOfStreams() < s2.getNoOfStreams())
                return 1;
            else
                return -1;
        } else if(s1.getDateAdded() < s2.getDateAdded()) {
            return 1;
        } else {
            return -1;
        }
    }
}