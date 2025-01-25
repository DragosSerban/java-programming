public class Pacient {
    private String nume;
    private int id;
    private static int iter = 1;
    public Pacient() {
        this.id = iter++;
    }
    public Pacient(String numePacient) {
        this.nume = numePacient;
        this.id = iter++;
    }

    public void setNume(String x) {
        this.nume = x;
    }
    public String getNume() {
        return this.nume;
    }
    public void setID(int x) {
        this.id = x;
    }
    public int getID() {
        return this.id;
    }

    public String afisare() {
        String afis = new String();
        afis = this.id + ". " + this.nume;
        return afis;
    }
}