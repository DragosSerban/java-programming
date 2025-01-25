public class Doctor {
    private static int MAX = 128;
    final private String nume;
    int[] rating = new int[MAX];
    private static int crtRatings = 0;
    double medieRating;
    public Doctor() {
        this.nume = new String();
    }
    public Doctor(String x) {
        this.nume = x;
    }
/*
    public void setNume(String x) {
        this.nume = x;
    }
 */
    public String getNume() {
        return this.nume;
    }

    public double adaugaRating(int x) {
        this.rating[crtRatings++] = x;
        this.medieRating = 0;
        for (int i = 0; i < this.crtRatings; i++)
            this.medieRating += this.rating[i];
        this.medieRating /= crtRatings;
        return this.medieRating;
    }
    public double getMedieRating() {
        return this.medieRating;
    }

    private Pacient[] pacienti = new Pacient[this.MAX];
    private static int crtPacients = 0;

    public void adagaPacient(Pacient x) {
        pacienti[this.crtPacients++] = x;
    }

    public void afisare() {
        System.out.println("Nume doctor:\t" + this.nume + "\n");
        System.out.println("Rating:\t" + this.medieRating + "\n");
        System.out.println("Lista pacienti:");
        for(int i = 0; i < this.crtPacients; i++) {
            System.out.println(this.pacienti[i].afisare());
        }
        System.out.println();
    }
}
