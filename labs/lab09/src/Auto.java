import java.io.BufferedReader;
import java.io.IOException;

public class Auto {
    private String model;
    private int an;
    private int km;
    private double pret;

    Auto(String model, int an, int km, double pret) {
        this.model = new String(model);
        this.an = an;
        this.km = km;
        this.pret = pret;
    }

    void setModel(String model) {
        this.model = new String(model);
    }

    String getModel() {
        return this.model;
    }

    void setAn(int an) {
        this.an = an;
    }

    int getAn() {
        return this.an;
    }

    void setKm(int km) {
        this.km = km;
    }

    int getKm() {
        return this.km;
    }

    void setPret(double pret) {
        this.pret = pret;
    }

    double getPret() {
        return this.pret;
    }

    @Override
    public String toString() {
        return "Model: " + this.model + "\nKm: " + this.km + "\nAn: " + this.an + "\nPret: " + this.pret + "\n";
    }

    public boolean identic(Auto a) {
        if(this.model.equals(a.model) && a.km == this.km && a.pret == this.pret && a.an == this.an)
            return true;
        return false;
    }

    public static Auto citeste(BufferedReader br) throws IOException {
        String line = br.readLine();
        if(line == null)
            return null;
        String model = line.split(" ")[0];
        int an = Integer.parseInt(line.split(" ")[1]);
        int km = Integer.parseInt(line.split(" ")[2]);
        double pret = Double.parseDouble(line.split(" ")[3]);
        Auto a = new Auto(model, an, km, pret);
        return a;
    }
}