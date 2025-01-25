import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Library l = new Library();

        Book[] b = l.readBooks();
        l.sortBooks();
        System.out.println("Cartile sortate:");
        for(int i = 0; i < b.length; i++) {
            if(b[i] == null)
                break;
            System.out.println(b[i].toString());
        }
        System.out.println();

        User[] u = l.readUsers();
        System.out.println("Userii sortati:");
        for(int i = 0; i < u.length; i++) {
            if(u[i] == null)
                break;
            System.out.println(u[i].toString());
        }
        System.out.println();

        System.out.println("Pt ce utilizator se doreste verificarea in functie de varsta recomandata? (int)");
        Scanner s = new Scanner(System.in);
        int crt2 = s.nextInt();
        System.out.println("Cartile recomandate pentru userul " + u[crt2].getName() + ":");
        Book[] recommended = l.recommendBooksForUser(u[crt2]);
        for(int i = 0; i < b.length; i++) {
            if(recommended[i] == null)
                break;
            System.out.print(recommended[i].toString());
            System.out.println("; pret nou: " + recommended[i].buyBook(u[crt2]));
        }
    }
}