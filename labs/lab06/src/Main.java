public class Main {
    public static void main(String[] args) {
        Lista l = new Lista();
        l.adauga(5);
        l.adauga(8);
        l.adauga(3);
        l.afisareLista();
        System.out.printf("In lista se afla %d elemente.\n", l.dimensiune());

        l.adaugaPozitie(2, 10);
        l.afisareLista();
        System.out.printf("Elementul de pe pozitia %d este %d.\n", 1, l.elementPozitie(1));

        System.out.printf("Elementul de pe pozitia %d (%d) a fost eliminat\n", 0, l.elimina(0));
        l.afisareLista();

        l.seteaza(1, 2);
        l.afisareLista();

        Lista newL = new Lista();
        newL.adauga(8);
        newL.adaugaPozitie(0, 2);
        newL.afisareLista();
    }
}