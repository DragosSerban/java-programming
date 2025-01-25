import java.util.*;

// clasa ce va crea obiecte in care vom stoca elemente de tipul Stream
public class Streams {
    private HashMap<Integer, Stream> musicStreams;
    private HashMap<Integer, Stream> podcastStreams;
    private HashMap<Integer, Stream> audiobookStreams;

    /*
        metoda prin care sunt create obiectele de tip Stream (folosind clasa StreamFactory)
        si sunt introduse in hashmapurile corespunzatoare tipului lor
     */
    public Streams(List<String> content, Streamers streamers) {
        musicStreams = new HashMap<>();
        podcastStreams = new HashMap<>();
        audiobookStreams = new HashMap<>();
        content.remove(0);
        for (int j = content.size() - 1; j >= 0; j--) {
            String line = content.get(j);
            String[] token = line.split("\",\"");
            for (int i = 0; i < token.length; i++)
                token[i] = token[i].replace("\"", "");
            Streamer streamer = this.findStreamer(Integer.parseInt(token[0]), Integer.parseInt(token[4]), streamers);
            StreamFactory streamFactory = StreamFactory.createInstance();
            Stream stream = streamFactory.createStream(Integer.parseInt(token[0]), Integer.parseInt(token[1]),
                    Integer.parseInt(token[2]), Long.parseLong(token[3]), Integer.parseInt(token[4]),
                    Long.parseLong(token[5]), Long.parseLong(token[6]), token[7], streamer);
            if (stream instanceof MusicStream)
                musicStreams.put(stream.getId(), stream);
            else if (stream instanceof PodcastStream)
                podcastStreams.put(stream.getId(), stream);
            else if (stream instanceof AudiobookStream)
                audiobookStreams.put(stream.getId(), stream);
        }
    }

    public HashMap<Integer, Stream> getMusicStreams() {
        return musicStreams;
    }

    public HashMap<Integer, Stream> getPodcastStreams() {
        return podcastStreams;
    }

    public HashMap<Integer, Stream> getAudiobookStreams() {
        return audiobookStreams;
    }

    /*
        metoda utilizata pt a obtine streamurile in functie de tip
     */
    public HashMap<Integer, Stream> getStreamsByType(int streamType) {
        if (streamType == 1) {
            return getMusicStreams();
        } else if (streamType == 2) {
            return getPodcastStreams();
        } else if (streamType == 3) {
            return getAudiobookStreams();
        }
        return null;
    }

    /*
        metoda prin care se obtine tipul unui stream
     */
    public int getStreamType(Stream stream) {
        if (stream instanceof MusicStream)
            return 1;
        else if (stream instanceof PodcastStream)
            return 2;
        else if (stream instanceof AudiobookStream)
            return 3;
        return 0;
    }

    /*
        metoda prin care se returneaza un stream pe baza unui id
     */
    public Stream getStream(int streamId) {
        if (getMusicStreams().containsKey(streamId))
            return getMusicStreams().get(streamId);

        if (getPodcastStreams().containsKey(streamId))
            return getPodcastStreams().get(streamId);

        if (getAudiobookStreams().containsKey(streamId))
            return getAudiobookStreams().get(streamId);

        return null;
    }

    /*
        metoda utilizata pt a gasi streamerul (folosita pt a conecta streamul la creatorul sau)
     */
    private Streamer findStreamer(int streamType, int streamerId, Streamers streamers) {
        if (streamers.getStreamersByType(streamType).containsKey(streamerId))
            return streamers.getStreamersByType(streamType).get(streamerId);
        return null;
    }

    /*
        metoda utilizata pt a sterge un stream
     */
    public void deleteStream(int streamId) {
        Stream stream = getStream(streamId);
        int streamType = getStreamType(stream);
        getStreamsByType(streamType).remove(streamId);
    }
}