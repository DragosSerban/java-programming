import java.text.SimpleDateFormat;
import java.util.*;

/*
    comanda pt afisarea userilor
 */
public class CommandUserList extends Command {
    private User user;

    public CommandUserList(User user, Data data) {
        super(data);
        this.user = user;
    }

    public void execute() {
        boolean once = false;
        String text = null;

        for (Stream stream : user.getStreams()) {
            Streamer streamer = stream.getStreamer();

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

            // se adauga o noua intrare in json-ul ce va fi afisat
            text += ("{\"id\":\"" + stream.getId() + "\",\"name\":\"" + stream.getName() + "\",\"streamerName\":\""
                    + streamer.getName() + "\",\"noOfListenings\":\"" + stream.getNoOfStreams() + "\",\""
                    + "length\":\"" + time.getFormattedDuration() + "\",\"dateAdded\":\""
                    + dateFormat.format(new Date(stream.getDateAdded() * 1000)) + "\"}");

            once = true;
        }
        if (once)
            text += "]";
        System.out.println(text);
    }
}
