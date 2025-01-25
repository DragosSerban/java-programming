import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws ExceptieStartCnpInvalid, ExceptieFormulaCnpInvalid, ExceptieFonduriInsuficiente {
        // ContBancar c = new ContBancar(751200, 35);
        // try {
        //     c.retrage(40);
        // } catch (ExceptieFonduriInsuficiente e) {
        // }

        // Persoana p = new Persoana("Ana", "2830703460094", c);
        // p.afiseazaInformatii();
        // Persoana q = new Persoana("Irina", "6021114035579", c);
        // q.afiseazaInformatii();

        // CNP Corect: 6021114035579
        // CNP Gresit: 2830703460094
        Persoana[] persoane = new Persoana[10];
        Scanner s = new Scanner(System.in);
        System.out.print("MAX = ");
        int max = s.nextInt();
        int n = (int)((max-2)*Math.random()+2);
        for(int i = 0; i < n; i++) {
            System.out.println((i+1) + ". Nume + CNP + nrCont + depozit");
            String nume = s.next();
            String cnp = s.next();
            int numarCont = s.nextInt();
            int depozit = s.nextInt();
            ContBancar cont = new ContBancar(numarCont, depozit);
            persoane[i] = new Persoana(nume, cnp, cont);
        }

        System.out.println("\nAfiseaza informatii:");
        for(int i = 0; i < n; i++) {
            if(persoane[i] == null)
                continue;
            persoane[i].afiseazaInformatii();
        }
        System.out.println("Retragere suma:");
        for(int i = 0; i < n; i++) {
            if(persoane[i] == null)
                continue;
            try {
                persoane[i].cont().retrage(1000);
            } catch (ExceptieFonduriInsuficiente e) {
            } catch (NullPointerException e) {
            }
            persoane[i].afiseazaInformatii();
        }
    }
}