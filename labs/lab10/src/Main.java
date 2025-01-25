import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        // Ex 1:

        Lista<Integer> l1 = new Lista<>(3);
        l1.adauga(4);
        l1.adauga(12);
        System.out.println("Lista (int):");
        l1.afiseaza();
        System.out.println(l1.cauta(13));

        Lista<Double> l2 = new Lista<>(3);
        l2.adauga(3.5);
        l2.adauga(12.8);
        l2.adauga(4.8);
        System.out.println("Lista (double):");
        l2.afiseaza();
        System.out.println(l2.cauta(12.8));

        Lista<String> l3 = new Lista<>(3);
        l3.adauga("ABC");
        l3.adauga("DEF");
        System.out.println("Lista (String):");
        l3.afiseaza();
        System.out.println(l3.cauta("ABC"));

        // Ex 2
        System.out.println("\nEx 2:");
        ArrayList<Produs> p = new ArrayList<Produs>();
        p.add(new ProdusAlimentar(12));
        p.add(new ProdusCuratenie(14.5));
        p.add(new ProdusIgiena(16.25));
        pretRaftMaxim(p);
    }

    public static void pretRaftMaxim(ArrayList<? extends Produs> lista) {
        double pret = 0;
        for(int i = 0; i < lista.size(); i++) {
            if(lista.get(i).pret > pret) {
                pret = lista.get(i).pret;
            }
        }
        System.out.println("Pretul maxim la raft este: " + pret);
    }
}