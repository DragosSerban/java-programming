public abstract class Produs implements Comparable {
    protected double pret;
    abstract double pretRaft();
    abstract void afiseaza();
    public int compareTo(Object p) {
        if(this.pret > ((Produs)p).pret)
            return 1;
        else if(this.pret < ((Produs)p).pret)
            return -1;
        else
            return 0;
    }
}

class ProdusAlimentar extends Produs {
    private double pretProducator;
    ProdusAlimentar(double pret) {
        pretProducator = pret;
        this.pret = 1.2 * pretProducator;
    }
    @Override
    double pretRaft() {
        return pret;
    }
    @Override
    void afiseaza() {
        System.out.println(pret);
    }
}

class ProdusCuratenie extends Produs {
    double pretProducator;
    ProdusCuratenie(double pret) {
        pretProducator = pret;
        this.pret = 1.15 * pretProducator;
    }
    @Override
    double pretRaft() {
        return pret;
    }
    @Override
    void afiseaza() {
        System.out.println(pret);
    }
}

class ProdusIgiena extends Produs {
    double pretProducator;
    ProdusIgiena(double pret) {
        pretProducator = pret;
        this.pret = 1.1 * pretProducator;
    }
    @Override
    double pretRaft() {
        return pret;
    }
    @Override
    void afiseaza() {
        System.out.println(pret);
    }
}