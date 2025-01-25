public class Main {
    public static void main(String[] args) {
        // Ex 1:
        System.out.println("Ex 1:");
        PizzaFactory factory = PizzaFactory.createPizzaFactory();
        Pizza[] pizzas = {
        factory.adaugarePizza(PizzaFactory.TipPizza.PepperoniPizza, 15, 25),
        factory.adaugarePizza(PizzaFactory.TipPizza.DiavolaPizza, 15, 25)};

        for(int i = 0; i < pizzas.length; i++) {
            System.out.println(pizzas[i]);
        }
        System.out.println();

        // Ex 2:
        System.out.println("Ex 2:");
        SecventaCuvant secventa = new SecventaCuvant("acesta este un text");
        SecventaMajuscule secventaMajuscule = new SecventaMajuscule(secventa);
        System.out.println(secventaMajuscule.parcurge());
        System.out.println(secventaMajuscule.parcurge());
        System.out.println(secventaMajuscule.parcurge());
        System.out.println(secventaMajuscule.parcurge());
        System.out.println();

        // Ex 3:
        System.out.println("Ex 3:");
        Imagine imagine = new Imagine();
        imagine.latime = 50;
        imagine.lungime = 80;
        imagine.nivelLuminiozitate = 10;

        System.out.println("latime initiala = " + imagine.latime);
        System.out.println("lungime initiala = " + imagine.lungime);
        System.out.println("luminiozitatea initiala = " + imagine.nivelLuminiozitate);
        System.out.println();

        Editor editor = new Editor();
        editor.adaugaComanda(new ComandaLuminiozitate());
        editor.adaugaComanda(new ComandaDecupare());
        editor.adaugaComanda(new ComandaDecupare());
        editor.adaugaComanda(new ComandaLuminiozitate());
        editor.executaComanda(imagine);

        System.out.println("latime finala = " + imagine.latime);
        System.out.println("lungime finala = " + imagine.lungime);
        System.out.println("luminiozitatea finala = " + imagine.nivelLuminiozitate);
        System.out.println();

        editor.reexecuta();
        System.out.println("reexecuta -> " + imagine.nivelLuminiozitate);
        editor.anuleaza();
        System.out.println("anuleaza -> " + imagine.nivelLuminiozitate);
    }
}