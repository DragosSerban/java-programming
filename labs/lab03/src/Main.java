public class Main {
    final static int MAX = 128;

    public static void main(String[] args) {
        // ex1
        System.out.println("\nEX 1:\n");

        // pct a -> rezolvat in Student

        // pct b -> rezolvat in Student

        // pct c
        Student s1 = new Student();
        s1.setNrMatricol(1);
        s1.setNume("Ion");
        s1.setPrenume("Ionescu");
        s1.setMedieFinala(9.8);

        Student s2 = new Student();
        s2.setNrMatricol(2);
        s2.setNume("Popescu");
        s2.setPrenume("Maria");
        s2.setMedieFinala(9.93);

        Student s3 = new Student();
        s3.setNrMatricol(3);
        s3.setNume("Popa");
        s3.setPrenume("Andrei");
        s3.setMedieFinala(8.76);

        // pct d
        Student[] semigrupa = new Student[MAX];
        semigrupa[0] = s1;
        semigrupa[1] = s2;
        semigrupa[2] = s3;

        int i = 0;
        while(true) {
            if(semigrupa[i] == null || i == MAX-1)
                break;
            System.out.println(semigrupa[i++].detaliiStudent());
        }
        System.out.println();

        // pct e
        semigrupa[(byte)(Math.random()*i)].setMedieFinala(Math.random()*10);
        i = 0;
        while(true) {
            if(semigrupa[i] == null || i == MAX-1)
                break;
            System.out.println(semigrupa[i++].detaliiStudent());
        }
        System.out.println();

        // pct f
        i = 0;
        double medie = 0;
        while(true) {
            if(semigrupa[i] == null || i == MAX-1)
                break;
            medie += semigrupa[i++].getMedieFinala();
        }
        medie /= i;
        System.out.println("Media este: " + medie);
        System.out.println();



        // ex2
        System.out.println("EX 2:\n");
        String[] numePacienti = {"Ana", "Ion", "Mihai"};
        Pacient[] pacienti = new Pacient[MAX];
        for(i = 0; i < 3; i++) {
            pacienti[i] = new Pacient(numePacienti[i]);
        }

        System.out.println("Pacienti:");
        for(i = 0; i < 3; i++) {
            System.out.println(pacienti[i].afisare());
        }
        System.out.println();

        Doctor doc = new Doctor("Dr. Ionescu");
        doc.adaugaRating(4);
        doc.adaugaRating(5);
        doc.adaugaRating(2);

        for(i = 0; i < 3; i++) {
            doc.adagaPacient(pacienti[i]);
        }

        doc.afisare();
    }
}
