/*
    comanda cu care un user asculta un stream
 */
public class CommandUserListen extends Command {
    private User user;
    private int streamId;

    public CommandUserListen(User user, int streamId, Data data) {
        super(data);
        this.user = user;
        this.streamId = streamId;
    }

    public void execute() {
        Stream stream = data.getStreams().getStream(streamId);
        // se realizeaza ascultarea streamului
        user.listen(stream);
    }
}