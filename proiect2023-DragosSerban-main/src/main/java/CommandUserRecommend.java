import java.text.SimpleDateFormat;
import java.util.*;

/*
    comanda pt recomandarea streamurilor in functie de preferintele userului
 */
public class CommandUserRecommend extends Command {
    private User user;
    private int type;

    public CommandUserRecommend(User user, String type, Data data) {
        super(data);
        this.user = user;
        this.setType(type);
    }

    public void execute() {
        List<Stream> streams = user.getStreams();
        StreamCollection streamCollection = new StreamCollection();
        for (Stream stream : streams) {
            // pastram streamurile relevante (cele ale streamerilor ascultati) intr-o lista
            List<Stream> streamersStreams = stream.getStreamer().getStreams();
            for (Stream streamersStream : streamersStreams)
                // daca streamul nu a mai fost ascultat si face parte din tipul de streamuri pe care userul le
                // doreste a fi recomandate -> se adauga la colectie
                if (!user.getStreams().contains(streamersStream) && type == streamersStream.getStreamType())
                    streamCollection.addStream(streamersStream);
        }

        String text = "";
        // vom crea un iterator special cu care vom itera prin colectie in modul prezentat in enunt
        Iterator streamIterator = streamCollection.createIteratorRecommended();
        // counter -> se pot printa maxim 5 recomandari
        int maxPrinted = 5;
        boolean once = false;
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

            // se adauga un nou stream la recomandari
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