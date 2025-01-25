public abstract class Pizza {
    int dimensiune;
    int pret;
    Pizza(int dimensiune, int pret) {
        this.dimensiune =dimensiune;
        this.pret = pret;
    }
}

class DiavolaPizza extends Pizza {
    DiavolaPizza(int dimeniune, int pret) {
        super(dimeniune, pret);
    }
    public String toString() {
        return "Diavola Pizza " + dimensiune + " " + pret;
    }
}

class PepperoniPizza extends Pizza {
    PepperoniPizza(int dimeniune, int pret) {
        super(dimeniune, pret);
    }
    public String toString() {
        return "Pepperoni Pizza " + dimensiune + " " + pret;
    }
}

class HawaiiPizza extends Pizza {
    HawaiiPizza(int dimeniune, int pret) {
        super(dimeniune, pret);
    }
    public String toString() {
        return "Hawaii Pizza " + dimensiune + " " + pret;
    }
}