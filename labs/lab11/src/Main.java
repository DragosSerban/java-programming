import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("Ex 1:");
        System.out.println("Nr studenti:");
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();

        if(n > 0)
            System.out.println("Introduceti nume student + media de " + n + " ori:");
        StudentsMap map = new StudentsMap();
        for (int i = 0; i < n; i++) {
            String nume = s.next();
            float media = s.nextFloat();
            Student student = new Student(nume, media);
            map.adaugaStudent(student);
        }
        map.afiseaza();
        s.close();

        System.out.println("\nEx 2:");
        PriorityQueue<Pacient> pacientsQueue = new PriorityQueue<>();
        pacientsQueue.add(new Pacient("Maria", 3));
        pacientsQueue.add(new Pacient("Andrei", 13));
        pacientsQueue.add(new Pacient("Ioana", 11));
        System.out.println(pacientsQueue);
        pacientsQueue.remove();
        System.out.println(pacientsQueue);
        pacientsQueue.remove();
        System.out.println(pacientsQueue);
        pacientsQueue.remove();
        System.out.println(pacientsQueue);
    }
}