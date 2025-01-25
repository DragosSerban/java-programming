public final class PizzaFactory {
    private static PizzaFactory factory;
    enum TipPizza {
        DiavolaPizza,
        PepperoniPizza,
        HawaiiPizza
    }

    private PizzaFactory() { }

    public static PizzaFactory createPizzaFactory() {
        if(PizzaFactory.factory == null)
            factory = new PizzaFactory();
        return PizzaFactory.factory;
    }

    Pizza adaugarePizza(TipPizza tip, int dimensiune, int pret) {
        switch(tip) {
            case DiavolaPizza:
                return new DiavolaPizza(dimensiune, pret);
            case PepperoniPizza:
                return new PepperoniPizza(dimensiune, pret);
            case HawaiiPizza:
                return new HawaiiPizza(dimensiune, pret);
            default:
                return null;
        }
    }
}