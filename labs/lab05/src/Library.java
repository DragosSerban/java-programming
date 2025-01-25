import java.util.Scanner;

public class Library implements AgeRecommendation, Cloneable {
    private final int MAX = 16;
    private Book[] books = new Book[MAX];
    private User[] users = new User[MAX];
    private static int crt;
    private static int crtU;
    private static int recommendedUsers;

    public Book[] readBooks() {
        System.out.println("Introduceti: titlu + an + publisher + varsta recomandata + pret + autori."
            + "\nLinie vida la final.");
        Scanner s = new Scanner(System.in);
        while(true) {
            String line = s.nextLine();
            if(line.length() == 0)
                break;
            String[] lineArray = line.split(" ");
            this.books[crt] = new Book(lineArray[0], Integer.valueOf(lineArray[1]), lineArray[2], Integer.valueOf(lineArray[3]), Float.valueOf(lineArray[4]));
            for(int i = 5; i < lineArray.length; i++)
                this.books[crt].addAuthor(lineArray[i]);
            crt++;
        }
        return this.books;
    }

    public User[] readUsers() {
        System.out.println("Introduceti: tip + nume + varsta."
                + "\nLinie vida la final.");
        Scanner s = new Scanner(System.in);
        while(true) {
            String line = s.nextLine();
            if(line.length() == 0)
                break;
            String[] lineArray = line.split(" ");
            if(lineArray[0].charAt(0) == 'S')
                this.users[crtU] = new Student(lineArray[1], Integer.valueOf(lineArray[2]));
            else if(lineArray[0].charAt(0) == 'T')
                this.users[crtU] = new Teacher(lineArray[1], Integer.valueOf(lineArray[2]));
            else if(lineArray[0].charAt(0) == 'L')
                this.users[crtU] = new Librarian(lineArray[1], Integer.valueOf(lineArray[2]));
            crtU++;
        }
        return this.users;
    }

    public Book[] sortBooks() {
        for(int i = 0; i < crt - 1; i++) {
            for(int j = i + 1; j < crt; j++) {
                if(books[i].getRecommendedAge() > books[j].getRecommendedAge()) {
                    Book temp = books[i];
                    books[i] = books[j];
                    books[j] = temp;
                } else if(Integer.valueOf(books[i].getYear()).compareTo(books[j].getYear()) > 0) {
                    Book temp = books[i];
                    books[i] = books[j];
                    books[j] = temp;
                } else if(books[i].getTitle().compareTo(books[j].getTitle()) > 0) {
                    Book temp = books[i];
                    books[i] = books[j];
                    books[j] = temp;
                }
            }
        }
        return books;
    }

    public Book[] recommendBooksForUser(User user) {
        Book[] b = new Book[MAX];
        for(int i = 0; i < MAX; i++) {
            if(books[i] == null) {
                return b;
            }
            if(books[i].getRecommendedAge() == user.getAge()) {
                b[recommendedUsers++] = (Book)((Object)books[i].clone());
            }
        }
        return b;
    }
}
