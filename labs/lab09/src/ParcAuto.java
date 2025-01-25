import java.io.*;

public class ParcAuto implements Serializable {
    private String numeFisier;

    ParcAuto(String numeFisier) {
        this.numeFisier = new String(numeFisier);
    }

    public int numaraMasiniNoi() throws IOException {
        int ret = 0;
        File f = new File(numeFisier);
        FileReader fr = new FileReader(f);
        BufferedReader br = new BufferedReader(fr);
        String line;
        while((line = br.readLine()) != null) {
            Auto a = new Auto(line.split(" ")[0], Integer.parseInt(line.split(" ")[1]), Integer.parseInt(line.split(" ")[2]), Double.parseDouble(line.split(" ")[3]));
            if(a.getKm() == 0)
                ret++;
        }
        fr.close();
        br.close();
        return ret;
    }

    public Auto celMaiScumpAuto() throws IOException {
        double max = -1;
        Auto maxAuto = null;
        File f = new File(numeFisier);
        FileReader fr = new FileReader(f);
        BufferedReader br = new BufferedReader(fr);
        String line;
        while((line = br.readLine()) != null) {
            Auto a = new Auto(line.split(" ")[0], Integer.parseInt(line.split(" ")[1]), Integer.parseInt(line.split(" ")[2]), Double.parseDouble(line.split(" ")[3]));
            if(a.getPret() > max) {
                max = a.getPret();
                maxAuto = a;
            }
        }
        fr.close();
        br.close();
        return maxAuto;
    }

    public void adaugaAuto(Auto a) throws IOException {
        String model = a.getModel();
        int an = a.getAn();
        int km = a.getKm();
        double pret = a.getPret();

        FileWriter fw = new FileWriter(numeFisier, true);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(model + " " + an + " " + km + " " + pret + "\n");
        bw.close();
        fw.close();
    }

    public boolean cauta(Auto a) throws IOException {
        File f = new File(numeFisier);
        FileReader fr = new FileReader(f);
        BufferedReader br = new BufferedReader(fr);
        String line;
        while((line = br.readLine()) != null) {
            Auto aAux = new Auto(line.split(" ")[0], Integer.parseInt(line.split(" ")[1]), Integer.parseInt(line.split(" ")[2]), Double.parseDouble(line.split(" ")[3]));
            if(a.identic(aAux))
                return true;
        }
        fr.close();
        br.close();
        return false;
    }

    public void afiseazaParcAuto() throws IOException {
        Auto a = null;
        File f = new File(numeFisier);
        BufferedReader br = new BufferedReader(new FileReader(f));
        do {
            a = Auto.citeste(br);
            if(a != null)
                System.out.println(a);
        } while(a != null);
        br.close();
    }
}