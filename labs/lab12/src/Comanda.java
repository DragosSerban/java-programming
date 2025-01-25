public abstract class Comanda {
    abstract void executa(Imagine imagine);
    abstract void anuleaza(Imagine imagine);
}

class ComandaRedimensionare extends Comanda {

    @Override
    void executa(Imagine imagine) {
        imagine.lungime += (int)(0.5 * imagine.lungime);
        imagine.latime += (int)(0.5 * imagine.latime);
    }

    @Override
    void anuleaza(Imagine imagine) {
        imagine.lungime -= (int)(0.5 * imagine.lungime);
        imagine.latime -= (int)(0.5 * imagine.latime);
    }
}

class ComandaDecupare extends Comanda {

    @Override
    void executa(Imagine imagine) {
        imagine.lungime -= 30;
        imagine.latime -= 20;
    }

    @Override
    void anuleaza(Imagine imagine) {
        imagine.lungime += 30;
        imagine.latime += 20;
    }
}

class ComandaLuminiozitate extends Comanda {

    @Override
    void executa(Imagine imagine) {
        imagine.nivelLuminiozitate++;
    }

    @Override
    void anuleaza(Imagine imagine) {
        imagine.nivelLuminiozitate--;
    }
}