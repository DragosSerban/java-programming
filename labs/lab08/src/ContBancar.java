class ExceptieFonduriInsuficiente extends Exception {
    ExceptieFonduriInsuficiente() {
        System.out.println("Eroare! Fonduri insuficiente!");
    }
}

public class ContBancar {
    private int numarCont;
    private int depozit;
    public ContBancar(int numarCont, int depozit) {
        this.numarCont = numarCont;
        this.depozit = depozit;
    }
    public void depune(int suma) {
        this.depozit += suma;
    }
    public void retrage(int suma) throws ExceptieFonduriInsuficiente {
        if(suma > this.depozit)
            throw new ExceptieFonduriInsuficiente();
        this.depozit -= suma;
    }
    public int numarCont() {
        return numarCont;
    }
    public int depozit() {
        return depozit;
    }
}
