/*
    comanda pt stergerea unui streamer
 */
public class CommandStreamerDelete extends Command {
    private Streamer streamer;
    private int streamId;

    public CommandStreamerDelete(Streamer streamer, int streamId, Data data) {
        super(data);
        this.streamer = streamer;
        this.streamId = streamId;
    }

    public void execute() {
        // se executa stergerea streamului, atat din lista de streamuri a streamerului,
        // cat si din lista de streamuri
        streamer.delete(streamId);
        data.getStreams().deleteStream(streamId);
    }
}
