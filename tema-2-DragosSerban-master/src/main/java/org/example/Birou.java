package org.example;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Birou<T extends Utilizator> {
    List<T> u = new ArrayList<>();
    List<Functionar<T>> f = new ArrayList<>();

    /*
    functie pt sortarea tuturor cererilor in asteptare ce apartin unei anumite categorii de utilizator
     */
    List<Cerere> sorteaza() throws ParseException {

        // lista in care se vor salva elementele
        List<Cerere> c = new ArrayList<>();

        for (int i = 0; i < u.size(); i++)
            for (int j = 0; j < u.get(i).cerereAsteptare.size(); j++)
                c.add(u.get(i).cerereAsteptare.get(j));

        // se face sortarea propriu-zisa (mai intai in functie de prioritate, apoi de data)
        for (int i = 0; i < c.size() - 1; i++) {
            for (int j = i + 1; j < c.size(); j++) {
                Date d1 = new SimpleDateFormat("dd-MMM-yyyy hh:mm:ss").parse(c.get(i).getData());
                Date d2 = new SimpleDateFormat("dd-MMM-yyyy hh:mm:ss").parse(c.get(j).getData());
                if (c.get(i).getPrioritate() < c.get(j).getPrioritate()) {
                    Cerere temp = c.get(i);
                    c.set(i, c.get(j));
                    c.set(j, temp);
                } else if ((c.get(i).getPrioritate() == c.get(j).getPrioritate()) && d1.compareTo(d2) > 0) {
                    Cerere temp = c.get(i);
                    c.set(i, c.get(j));
                    c.set(j, temp);
                }
            }
        }
        return c;
    }

    /*
    functie pt afisarea tuturor cererilor in asteptare ce apartin unei anumite categorii de utilizator
     */
    void afiseaza(BufferedWriter bw, String[] actiune) throws ParseException, IOException {
        List<Cerere> c = sorteaza();
        bw.write(actiune[1].substring(1) + " - cereri in birou:\n");
        for (int i = 0; i < c.size(); i++) {
            // se va trece prin cereri si se va afisa in functie de tipul utilizatorului
            if (c.get(i) instanceof CererePersoana)
                bw.write(c.get(i).getPrioritate() + " - " + c.get(i).getData() + " - Subsemnatul "
                        + c.get(i).getNume() + ", va rog sa-mi aprobati urmatoarea solicitare: " + c.get(i).getCerere()
                        + "\n");
            else if (c.get(i) instanceof CerereAngajat)
                bw.write(c.get(i).getPrioritate() + " - " + c.get(i).getData() + " - Subsemnatul "
                        + c.get(i).getNume() + ", angajat la compania " + ((CerereAngajat) c.get(i)).getCompanie()
                        + ", va rog sa-mi aprobati urmatoarea solicitare: " + c.get(i).getCerere() + "\n");
            else if (c.get(i) instanceof CererePensionar)
                bw.write(c.get(i).getPrioritate() + " - " + c.get(i).getData() + " - Subsemnatul "
                        + c.get(i).getNume() + ", va rog sa-mi aprobati urmatoarea solicitare: " + c.get(i).getCerere()
                        + "\n");
            else if (c.get(i) instanceof CerereElev)
                bw.write(c.get(i).getPrioritate() + " - " + c.get(i).getData() + " - Subsemnatul "
                        + c.get(i).getNume() + ", elev la scoala " + ((CerereElev) c.get(i)).getScoala()
                        + ", va rog sa-mi aprobati urmatoarea solicitare: " + c.get(i).getCerere() + "\n");
            else if (c.get(i) instanceof CerereEntitateJuridica)
                bw.write(c.get(i).getPrioritate() + " - " + c.get(i).getData() + " - Subsemnatul "
                        + ((CerereEntitateJuridica) c.get(i)).getReprezentant() + ", reprezentant legal al companiei "
                        + c.get(i).getNume() + ", va rog sa-mi aprobati urmatoarea solicitare: " + c.get(i).getCerere()
                        + "\n");
        }
    }

    /*
    metoda pt adaugarea unui nou functionar in birou
     */
    void adaugaFunctionar(String[] actiune) throws IOException {
        f.add(new Functionar<T>(actiune[2].substring(1)));
    }

    /*
    metoda utilizata pt a rezolva o cerere de catre un anumit functionar dintr-un birou
     */
    void rezolvaCerere(String[] actiune) throws ParseException, IOException {
        for (int i = 0; i < f.size(); i++)
            if (actiune[2].substring(1).equals(f.get(i).getNume())) {
                Functionar<T> functionar = (Functionar<T>) f.get(i);
                List<Cerere> c = sorteaza();
                if (c.get(0) == null)
                    return;
                FileWriter fw = new FileWriter(functionar.getFile(), true);
                BufferedWriter bw = new BufferedWriter(fw);
                // se scriu in fisier datele utilizatorului al carei cereri a fost rezolvata de functionar
                bw.write(c.get(0).getData() + " - " + c.get(0).getNume() + "\n");
                bw.close();
                fw.close();
                for (int ii = 0; ii < u.size(); ii++) {
                    for (int j = 0; j < u.get(ii).cerereAsteptare.size(); j++) {
                        if (c.get(0) == u.get(ii).cerereAsteptare.get(j)) {
                            // se va elimina cererea rezolvata din lista de cereri in asteptare
                            u.get(ii).cerereFinalizata.add(c.get(0));
                            for (int k = j; k < u.get(ii).cerereAsteptare.size() - 1; k++)
                                u.get(ii).cerereAsteptare.set(k, u.get(ii).cerereAsteptare.get(k + 1));
                            u.get(ii).cerereAsteptare.remove(u.get(ii).cerereAsteptare.size() - 1);
                        }
                    }
                }
            }
    }
}