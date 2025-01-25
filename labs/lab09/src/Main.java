import java.io.BufferedReader;
import java.io.IOException;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("Ex 1:");
        Auto a1 = new Auto("BMW", 2012, 12000, 2500);
        Auto a2 = new Auto("Mercedes", 2013, 14500, 3000);
        Auto a3 = new Auto("BMW", 2012, 12000, 2500);

        System.out.println("Afisare automobil a2:\n" + a2);
        System.out.println("a3 == a1 ? -> " + a3.identic(a1));

        File f = new File("input.txt");
        if(f == null)
            f.createNewFile();

        BufferedReader br = new BufferedReader(new FileReader(f));
        Auto a4 = Auto.citeste(br);
        System.out.println("Afisare automobil a4:\n" + a4);

        ParcAuto parc = new ParcAuto("input.txt");
        System.out.println("Numar masini noi: " + parc.numaraMasiniNoi() + "\n");
        Auto scump = parc.celMaiScumpAuto();
        System.out.println("Cea mai scumpa masina: " + scump.toString());
        parc.adaugaAuto(a2);
        System.out.println("Exista a2 ? -> " + parc.cauta(a2) + "\nExista a1 ? -> " + parc.cauta(a1) + "\n");

        System.out.println("Afisare parc auto:");
        parc.afiseazaParcAuto();

        System.out.println("Ex 2:");

        // Serializare
        ObjectOutputStream os = null;
        try {
            os = new ObjectOutputStream(new
                    FileOutputStream("out.bin"));
            os.writeObject(parc);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (os != null)
                try {
                    os.close();
                } catch (IOException e) {
                }
        }

        // Deserializare
        ObjectInputStream is = null;
        try {
            is = new ObjectInputStream(new
                    FileInputStream("out.bin"));
            parc = (ParcAuto) is.readObject();
            System.out.println("Deserializat: " + parc);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (is != null)
                try {
                    is.close();
                } catch (IOException e) {
                }
        }
    }
}