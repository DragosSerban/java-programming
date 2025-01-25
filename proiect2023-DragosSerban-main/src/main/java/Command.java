/*
    se va folosi design patternul Command pt implementarea comenzilor date
    de catre utilizator in fisierul primit ca parametru de catre aplicatie
    Command transforma requestul intr-un obiect cu ajutorul caruia se executa comanda propriu-zisa
 */

public abstract class Command {
    protected Data data;

    public Command(Data data) {
        this.data = data;
    }

    public abstract void execute();
}
