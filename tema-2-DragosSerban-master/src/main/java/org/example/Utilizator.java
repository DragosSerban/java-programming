package org.example;

import java.io.BufferedWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public abstract class Utilizator {
    protected String nume;
    /*
    listele de cereri + dimensiunea lor
     */
    List<Cerere> cerereAsteptare = new ArrayList<Cerere>();
    List<Cerere> cerereFinalizata = new ArrayList<Cerere>();

    /*
    functie statica pt crearea unui nou utilizator
     */
    static Utilizator adaugaUtilizator(String[] actiune) {
        // se va folosi constructorul din clasa corespunzatoare tipului utilizatorului
        if ((actiune[1].substring(1)).equals("persoana")) {
            return new Persoana(actiune[2].substring(1));
        } else if ((actiune[1].substring(1)).equals("angajat")) {
            return new Angajat(actiune[2].substring(1), actiune[3].substring(1));
        } else if ((actiune[1].substring(1)).equals("elev")) {
            return new Elev(actiune[2].substring(1), actiune[3].substring(1));
        } else if ((actiune[1].substring(1)).equals("pensionar")) {
            return new Pensionar(actiune[2].substring(1));
        } else if ((actiune[1].substring(1)).equals("entitate juridica")) {
            return new EntitateJuridica(actiune[2].substring(1), actiune[3].substring(1));
        }
        return null;
    }

    /*
    functie utilizata pt a arunca exceptie in cazul unei actiuni ce nu corespunde cu tipul utilizatorului curent
     */
    static void actiuneIncorecta(BufferedWriter bw, String tip, String cerere)
            throws IOException, ExceptieActiuneIncorecta {
        throw new ExceptieActiuneIncorecta(bw, tip, cerere);
    }

    abstract void adaugaCerere(BufferedWriter bw, String[] actiune);

    abstract void afisareCereriAsteptare(BufferedWriter bw) throws IOException, ParseException;

    abstract void afisareCereriFinalizate(BufferedWriter bw) throws IOException, ParseException;

    /*
    functie pt sortarea cererilor
     */
    protected void sorteazaCereri() throws ParseException {
        // sorteaza cererile in asteptare
        for (int i = 0; i < cerereAsteptare.size() - 1; i++) {
            for (int j = i + 1; j < cerereAsteptare.size(); j++) {
                Date d1 = new SimpleDateFormat("dd-MMM-yyyy hh:mm:ss").parse(cerereAsteptare.get(i).getData());
                Date d2 = new SimpleDateFormat("dd-MMM-yyyy hh:mm:ss").parse(cerereAsteptare.get(j).getData());
                if (d1.compareTo(d2) > 0) {
                    Cerere temp = cerereAsteptare.get(i);
                    cerereAsteptare.set(i, cerereAsteptare.get(j));
                    cerereAsteptare.set(j, temp);
                }
            }
        }
        // sorteaza cererile finalizate
        for (int i = 0; i < cerereFinalizata.size() - 1; i++) {
            for (int j = i + 1; j < cerereFinalizata.size(); j++) {
                Date d1 = new SimpleDateFormat("dd-MMM-yyyy hh:mm:ss").parse(cerereFinalizata.get(i).getData());
                Date d2 = new SimpleDateFormat("dd-MMM-yyyy hh:mm:ss").parse(cerereFinalizata.get(j).getData());
                if (d1.compareTo(d2) > 0) {
                    Cerere temp = cerereFinalizata.get(i);
                    cerereFinalizata.set(i, cerereFinalizata.get(j));
                    cerereFinalizata.set(j, temp);
                }
            }
        }
    }

    /*
    functie pt retragerea unei cereri cu parametru data la care a fost facuta cererea
     */
    void retrageCerere(String data) {
        for (int i = 0; i < cerereAsteptare.size(); i++) {
            if (data.equals(cerereAsteptare.get(i).getData())) {
                for (int j = i; j < cerereAsteptare.size() - 1; j++)
                    cerereAsteptare.set(j, cerereAsteptare.get(j + 1));
                cerereAsteptare.remove(cerereAsteptare.size() - 1);
                break;
            }
        }
    }
}