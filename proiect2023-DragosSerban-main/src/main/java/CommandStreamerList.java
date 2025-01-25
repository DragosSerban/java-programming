import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

/*
    comanda pt listarea streamerilor
 */
public class CommandStreamerList extends Command {
    private Streamer streamer;

    public CommandStreamerList(Streamer streamer, Data data) {
        super(data);
        this.streamer = streamer;
    }

    public void execute() {
        boolean once = false;
        String text = null;

        List<Stream> streams1 = streamer.getStreams();
        for (Stream stream : streams1) {
            // se seteaza formatul afisarii datei
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));

            // se obtine formatul lungimii streamului
            TimeDuration time = new TimeDuration(stream.getLength());
            CommandDurationFormat commandDurationFormat = new CommandDurationFormat();
            commandDurationFormat.execute(time);

            if (once)
                text += ",";
            else
                text = "[";

            // se adauga o noua intrare in afisarea streamerilor
            text += "{\"id\":\"" + stream.getId() + "\",\"name\":\"" + stream.getName() + "\",\"streamerName\":\""
                    + streamer.getName() + "\",\"noOfListenings\":\"" + stream.getNoOfStreams() + "\",\""
                    + "length\":\"" + time.getFormattedDuration() + "\",\"dateAdded\":\""
                    + dateFormat.format(new Date(stream.getDateAdded() * 1000)) + "\"}";

            once = true;
        }
        if (once)
            text += "]";
        System.out.println(text);
    }
}