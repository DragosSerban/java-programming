public class Editor {
    Imagine imagine;
    int i = 0;
    public Comanda[] listaComenzi = new Comanda[100];

    public void adaugaComanda(Comanda comanda) {
        listaComenzi[i] = comanda;
        i++;
    }

    public void executaComanda(Imagine imagine) {
        this.imagine = imagine;
        for(int j = 0; j < i; j++) {
            listaComenzi[j].executa(imagine);
        }
    }

    public void reexecuta() {
        listaComenzi[i - 1].executa(imagine);
    }

    public void anuleaza() {
        listaComenzi[i - 1].anuleaza(imagine);
    }
}