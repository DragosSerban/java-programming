/*
    implementare cu Factory a modului in care se creeaza noi obiecte de tipul Command
    se va folosi pt returnarea unui obiect instanta a unei clase care are ca superclasa clasa Command
    in functie de inputul pe care il oferim
 */
public class CommandFactory {
    public Command createCommand(String command, Data data) {
        switch (command.split(" ")[1]) {
            case "LIST":
                return CreateCommandObjects.createListCommand(command, data);

            case "ADD":
                return CreateCommandObjects.createStreamerAddCommand(command, data);

            case "DELETE":
                return CreateCommandObjects.createStreamerDeleteCommand(command, data);

            case "LISTEN":
                return CreateCommandObjects.createUserListenCommand(command, data);

            case "RECOMMEND":
                return CreateCommandObjects.createUserRecommendCommand(command, data);

            case "SURPRISE":
                return CreateCommandObjects.createUserSurpriseCommand(command, data);

            default:
                break;
        }
        return null;
    }
}