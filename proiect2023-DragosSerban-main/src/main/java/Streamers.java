import java.util.HashMap;
import java.util.List;

public class Streamers {
    private HashMap<Integer, Streamer> musicStreamers;
    private HashMap<Integer, Streamer> podcastStreamers;
    private HashMap<Integer, Streamer> audiobookStreamers;

    /*
        metoda prin care sunt create obiectele de tip Streamer prin intermediul clasei StreamerFactory
        si sunt introduse in hashmapurile corespunzatoare tipului lor
     */
    public Streamers(List<String> content) {
        musicStreamers = new HashMap<>();
        podcastStreamers = new HashMap<>();
        audiobookStreamers = new HashMap<>();

        content.remove(0);
        for (String line : content) {
            String[] token = line.split("\",\"");
            for (int i = 0; i < token.length; i++)
                token[i] = token[i].replace("\"", "");
            StreamerFactory streamerFactory = StreamerFactory.createInstance();
            Streamer streamer = streamerFactory.createStreamer(Integer.parseInt(token[0]),
                    Integer.parseInt(token[1]), token[2]);
            if (streamer instanceof MusicStreamer)
                musicStreamers.put(streamer.getId(), streamer);
            else if (streamer instanceof PodcastStreamer)
                podcastStreamers.put(streamer.getId(), streamer);
            else if (streamer instanceof AudiobookStreamer)
                audiobookStreamers.put(streamer.getId(), streamer);
        }
    }

    public HashMap<Integer, Streamer> getMusicStreamers() {
        return musicStreamers;
    }

    public HashMap<Integer, Streamer> getPodcastStreamers() {
        return podcastStreamers;
    }

    public HashMap<Integer, Streamer> getAudiobookStreamers() {
        return audiobookStreamers;
    }

    /*
        metoda utilizata pt a obtine streamerii in functie de tip
    */
    public HashMap<Integer, Streamer> getStreamersByType(int streamerType) {
        if (streamerType == 1) {
            return getMusicStreamers();
        } else if (streamerType == 2) {
            return getPodcastStreamers();
        } else if (streamerType == 3) {
            return getAudiobookStreamers();
        }
        return null;
    }
}
