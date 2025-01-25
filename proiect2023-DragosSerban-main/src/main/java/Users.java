import java.util.*;

// clasa ce va crea obiecte in care vom stoca elemente de tipul User
public class Users {
    private HashMap<Integer, User> users; // id + obiect

    /*
        metoda prin care sunt create obiectele de tip User; userii sunt introdusi in lista de utilizatori
    */
    public Users(List<String> content, Streams streams) {
        users = new HashMap<>();
        content.remove(0);
        for (String line : content) {
            String[] token = line.split("\",\"");
            for (int i = 0; i < token.length; i++)
                token[i] = token[i].replace("\"", "");

            String[] streamsArray = token[2].split(" ");
            List<Integer> streamIds = new ArrayList<>();
            for (String temp : streamsArray)
                streamIds.add(Integer.parseInt(temp));

            User user = new User(Integer.parseInt(token[0]), token[1]);
            for (Integer streamId : streamIds)
                user.addStream(streams.getStream(streamId));
            users.put(user.getId(), user);
        }
    }

    public HashMap<Integer, User> getUsers() {
        return users;
    }
}