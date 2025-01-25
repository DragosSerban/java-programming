public class CreateCommandObjects {
    public static Command createListCommand(String command, Data data) {
        int userId = Integer.parseInt(command.split(" ")[0]);
        // se verifica lista de useri:
        if (data.getUsers().getUsers().containsKey(userId))
            return new CommandUserList
                    (data.getUsers().getUsers().get(userId), data);

        int streamerId = Integer.parseInt(command.split(" ")[0]);
        // se verifica listele de streameri, fiecare in parte
        if (data.getStreamers().getMusicStreamers().containsKey(streamerId))
            return new CommandStreamerList(data.getStreamers().getMusicStreamers().get(streamerId), data);

        if (data.getStreamers().getPodcastStreamers().containsKey(streamerId))
            return new CommandStreamerList(data.getStreamers().getPodcastStreamers().get(streamerId), data);

        if (data.getStreamers().getAudiobookStreamers().containsKey(streamerId))
            return new CommandStreamerList(data.getStreamers().getAudiobookStreamers().get(streamerId), data);

        return null;
    }

    public static CommandStreamerAdd createStreamerAddCommand(String command, Data data) {
        // se preiau informatiile din comanda
        int streamerId = Integer.parseInt(command.split(" ")[0]);
        int streamType = Integer.parseInt(command.split(" ")[2]);
        int id = Integer.parseInt(command.split(" ")[3]);
        int streamGenre = Integer.parseInt(command.split(" ")[4]);
        long length = Integer.parseInt(command.split(" ")[5]);
        String name = command.split(" ")[6];
        for (int i = 7; i < command.split(" ").length; i++)
            name += " " + command.split(" ")[i];

        // se gaseste streamerul ce va fi subiectul pt stream
        Streamer streamer = data.getStreamers().getStreamersByType(streamType).get(streamerId);

        return new CommandStreamerAdd(streamer, streamerId, streamType, id, streamGenre, length, name, data);
    }

    public static CommandStreamerDelete createStreamerDeleteCommand(String command, Data data) {
        // se preiau informatiile din comanda
        int streamerId = Integer.parseInt(command.split(" ")[0]);
        int streamId = Integer.parseInt(command.split(" ")[2]);

        // se verifica hashmapurile de streameri in parte; pana se gaseste
        // streamerul caruia trebuie sa ii stergem un stream
        if (data.getStreamers().getMusicStreamers().containsKey(streamerId))
            return new CommandStreamerDelete
                    (data.getStreamers().getMusicStreamers().get(streamerId), streamId, data);

        if (data.getStreamers().getPodcastStreamers().containsKey(streamerId))
            return new CommandStreamerDelete
                    (data.getStreamers().getPodcastStreamers().get(streamerId), streamId, data);

        if (data.getStreamers().getAudiobookStreamers().containsKey(streamerId))
            return new CommandStreamerDelete
                    (data.getStreamers().getAudiobookStreamers().get(streamerId), streamId, data);

        return null;
    }

    public static CommandUserListen createUserListenCommand(String command, Data data) {
        int userId = Integer.parseInt(command.split(" ")[0]);
        int streamId = Integer.parseInt(command.split(" ")[2]);

        // returnam obiectul cu care vom executa comanda de listen
        if(data.getUsers().getUsers().containsKey(userId))
            return new CommandUserListen(data.getUsers().getUsers().get(userId), streamId, data);

        return null;
    }

    public static CommandUserRecommend createUserRecommendCommand(String command, Data data) {
        // se cauta userul
        // se intoarce obiectul corespunzator userului
        if (data.getUsers().getUsers().containsKey(Integer.parseInt(command.split(" ")[0])))
            return new CommandUserRecommend
                (data.getUsers().getUsers().get(Integer.parseInt(command.split(" ")[0])),
                        command.split(" ")[2], data);
        return null;
    }

    public static CommandUserSurprise createUserSurpriseCommand(String command, Data data) {
        // se cauta userul; se intoarce obiectul corespunzator userului
        if (data.getUsers().getUsers().containsKey(Integer.parseInt(command.split(" ")[0])))
            return new CommandUserSurprise
                    (data.getUsers().getUsers().get(Integer.parseInt(command.split(" ")[0])),
                            command.split(" ")[2], data);
        return null;
    }
}