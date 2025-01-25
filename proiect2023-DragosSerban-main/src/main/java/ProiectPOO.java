public class ProiectPOO {
    public static void main(String[] args) {
        if (args == null || args.length != 4) {
            System.out.println("Nothing to read here");
            return;
        }

        AppInterface appInterface = new AppInterface(args[0], args[1], args[2], args[3]);
        appInterface.readAppData();
        appInterface.saveAppData();
        appInterface.runCommands();
        appInterface.deleteAppData();
    }
}
