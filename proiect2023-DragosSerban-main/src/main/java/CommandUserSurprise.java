import java.text.SimpleDateFormat;
import java.util.*;

/*
    comanda pt afisarea streamurilor surpriza conform preferintelor userului
 */
public class CommandUserSurprise extends Command {
    private User user;
    private int type;
    private StreamCollection streamCollection = new StreamCollection();

    public CommandUserSurprise(User user, String type, Data data) {
        super(data);
        this.user = user;
        this.setType(type);
    }

    public void execute() {
        // se itereaza doar prin streamurile de tipul cerut in comanda
        for (Streamer streamer : data.getStreamers().getStreamersByType(type).values()) {
            boolean isKnown = false;
            for (Stream stream : user.getStreams())
                // se verifica streamerii deja ascultati
                if (stream.getStreamer() == streamer)
                    isKnown = true;

            if (!isKnown)
                // vor fi luate in considerare in colectie doar streamurile ce apartin streamerilor neascultati
                for (Stream stream : streamer.getStreams())
                    streamCollection.addStream(stream);
        }

        // vom crea un iterator special cu care vom itera prin colectie in modul prezentat in enunt
        Iterator streamIterator = streamCollection.createIteratorSurprise();
        // counter -> se pot printa maxim 3 streamuri de tipul Surprise
        int maxPrinted = 3;
        boolean once = false;
        String text = "";
        while (streamIterator.hasNext() && maxPrinted > 0) {
            maxPrinted--;
            Stream stream = (Stream) streamIterator.next();

            // regulile pt afisarea datei
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));

            // formatul pt durata streamului
            TimeDuration time = new TimeDuration(stream.getLength());
            CommandDurationFormat commandDurationFormat = new CommandDurationFormat();
            commandDurationFormat.execute(time);

            if (!once)
                text = "[";
            else
                text += ",";

            // se adauga un nou stream la recomandarile de tip Surprise
            text += ("{\"id\":\"" + stream.getId() + "\",\"name\":\"" + stream.getName() + "\",\"streamerName\":\""
                    + stream.getStreamer().getName() + "\",\"noOfListenings\":\"" + stream.getNoOfStreams() + "\",\""
                    + "length\":\"" + time.getFormattedDuration() + "\",\"dateAdded\":\""
                    + dateFormat.format(new Date(stream.getDateAdded() * 1000)) + "\"}");

            once = true;
        }
        if (once)
            text += "]";
        System.out.println(text);
    }

    private void setType(String type) {
        // functie care seteaza tipul streamurilor dorite
        if (type.equals("SONG")) {
            this.type = 1;
        } else if (type.equals("PODCAST")) {
            this.type = 2;
        } else if (type.equals("AUDIOBOOK")) {
            this.type = 3;
        }
    }
}
