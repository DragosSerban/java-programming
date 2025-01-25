public class Student {
    private long nrMatricol;
    private String nume;
    private String prenume;
    private double medieFinala;
    public Student() {
    }
    public void setNrMatricol(long x) {
        this.nrMatricol = x;
    }
    public long getNrMatricol() {
        return this.nrMatricol;
    }

    public void setNume(String x) {
        this.nume = x;
    }
    public String getNume() {
        return this.nume;
    }

    public void setPrenume(String x) {
        this.prenume = x;
    }
    public String getPrenume() {
        return this.prenume;
    }

    public void setMedieFinala(double x) {
        this.medieFinala = x;
    }
    public double getMedieFinala() {
        return this.medieFinala;
    }

    public String detaliiStudent() {
        String detalii = new String();
        detalii = "[" + this.nrMatricol + "] " + this.prenume + " " + this.nume + ": " + this.medieFinala;
        return detalii;
    }
}
