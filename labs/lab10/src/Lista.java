public class Lista<E> {
    private E[] tablou;
    private int nr;
    public Lista(int dim) {
        if(dim <= 0)
            throw new IllegalArgumentException();
        tablou = (E[]) new Object[dim];
        nr = 0;
    }
    public void adauga(E x) {
        if(nr == tablou.length) {
            E[] temp = (E[]) new Object[2 * tablou.length];
            for(int i = 0; i < tablou.length; i++)
                temp[i] = tablou[i];
            tablou = temp;
        }
        tablou[nr] = x;
        nr++;
    }
    public void afiseaza() {
        for(int i = 0; i < tablou.length; i++) {
            if(tablou[i] != null)
                System.out.print(tablou[i] + " ");
        }
        System.out.println();
    }
    public boolean cauta(E x) {
        for(int i = 0; i < tablou.length; i++) {
            if(tablou[i] == null)
                return false;
            if(tablou[i].equals(x))
                return true;
        }
        return false;
    }
}