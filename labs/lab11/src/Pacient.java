public class Pacient implements Comparable<Pacient> {
    String nume;
    int prioritate;

    Pacient(String nume, int prioritate) {
        this.nume = nume;
        this.prioritate = prioritate;
    }

    public int compareTo(Pacient pacient) {
        if(this.prioritate > pacient.prioritate) {
            return -1;
        } else if(this.prioritate < pacient.prioritate) {
            return 1;
        } else {
            return 0;
        }
    }

    public String toString() {
        return nume + " " + prioritate;
    }
}