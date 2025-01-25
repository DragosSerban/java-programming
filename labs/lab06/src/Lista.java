public class Lista {
    private Nod varf;
    private Nod coada;
    private int contor;

    public class Nod {
        public Nod() {

        }

        public Nod(int x) {
            val = x;
            urm = null;
        }

        private int val;
        private Nod urm;
    }

    public static class ContorListe {
        private static int numarListe;
        public static void incrementNumarListe() {
            numarListe++;
        }
    }

    public Lista() {
        ContorListe.incrementNumarListe();
        this.varf = null;
        this.coada = this.varf;
        this.contor = 0;
    }

    public void adauga(int x) {
        Nod nod = this.new Nod(x);
        this.contor++;
        if(this.varf == null) {
            this.varf = nod;
            this.coada = nod;
        } else {
            this.coada.urm = nod;
            this.coada = nod;
        }
    }

    public int dimensiune() {
        return contor;
    }

    public void afisareLista() {
        if(ContorListe.numarListe == 1)
            System.out.println("Exista o lista.");
        else
            System.out.println("Exista " + ContorListe.numarListe + " liste.");
        System.out.println("Elementele listei sunt:");
        Nod nod = varf;
        while(nod != null) {
            System.out.print(nod.val + " ");
            nod = nod.urm;
        }
        System.out.println();
    }

    public void adaugaPozitie(int index, int x) {
        if(index > dimensiune() || index < 0)
            return;
        Nod nod = this.new Nod(x);
        this.contor++;
        if(this.varf == null) {
            this.varf = nod;
            this.coada = this.varf;
            return;
        }
        if(index == 0) {
            nod.urm = this.varf;
            this.varf = nod;
            return;
        }
        Nod aux = this.varf;
        for(int i = 0; i < index-1; i++)
            aux = aux.urm;
        nod.urm = aux.urm;
        aux.urm = nod;
    }

    public int elementPozitie(int index) {
        if(index >= dimensiune() || index < 0 || this.dimensiune() == 0)
            return -1;
        if(index == 0)
            return varf.val;
        Nod aux = this.varf;
        for(int i = 0; i < index; i++)
            aux = aux.urm;
        return aux.val;
    }

    public int elimina(int index) {
        if(index >= dimensiune() || index < 0 || this.dimensiune() == 0)
            return -1;
        this.contor--;
        if(index == 0) {
            int temp = varf.val;
            varf = varf.urm;
            return temp;
        }
        Nod aux = this.varf;
        Nod ant = null;
        for(int i = 0; i < index; i++) {
            ant = aux;
            aux = aux.urm;
        }
        if(aux == this.coada) {
            ant.urm = null;
            this.coada = ant;
            return aux.val;
        }
        int temp = aux.val;
        ant.urm = aux.urm;
        return temp;
    }

    public void seteaza(int index, int x) {
        if(index >= dimensiune() || index < 0 || this.dimensiune() == 0)
            return;

        Nod aux = this.varf;
        for(int i = 0; i < index; i++)
            aux = aux.urm;
        aux.val = x;
        return;
    }
}
