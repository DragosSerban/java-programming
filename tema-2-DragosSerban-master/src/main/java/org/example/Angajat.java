package org.example;

import java.io.BufferedWriter;
import java.io.IOException;
import java.text.ParseException;

public class Angajat extends Utilizator {
    // utilizator tip angajat
    private String companie;
    private String formulare;

    Angajat(String nume, String companie) {
        this.nume = nume;
        this.companie = companie;
    }

    @Override
    void afisareCereriAsteptare(BufferedWriter bw) throws IOException, ParseException {
        // sorteaza cererile, apoi le afiseaza pe cele aflate in asteptare
        this.sorteazaCereri();
        bw.write(nume + " - cereri in asteptare:\n");
        for (int i = 0; i < this.cerereAsteptare.size(); i++)
            bw.write(cerereAsteptare.get(i).getData() + " - Subsemnatul " + cerereAsteptare.get(i).getNume()
                    + ", angajat la compania " + ((CerereAngajat) cerereAsteptare.get(i)).getCompanie()
                    + ", va rog sa-mi aprobati urmatoarea solicitare: " + cerereAsteptare.get(i).getCerere() + "\n");
    }

    @Override
    void afisareCereriFinalizate(BufferedWriter bw) throws IOException, ParseException {
        // sorteaza cererile, apoi le afiseaza pe cele finalizate
        this.sorteazaCereri();
        bw.write(nume + " - cereri in finalizate:\n");
        for (int i = 0; i < this.cerereFinalizata.size(); i++)
            bw.write(cerereFinalizata.get(i).getData() + " - Subsemnatul "
                    + cerereFinalizata.get(i).getNume() + ", angajat la compania "
                    + ((CerereAngajat) cerereFinalizata.get(i)).getCompanie()
                    + ", va rog sa-mi aprobati urmatoarea solicitare: " + cerereFinalizata.get(i).getCerere() + "\n");

    }

    @Override
    void adaugaCerere(BufferedWriter bw, String[] actiune) {
        // adauga o cerere
        String companie = this.companie;
        String cerere = (actiune[2].substring(1));
        // se verifica daca cererea corespunde tipului de utilizator
        if (!cerere.equals("inlocuire buletin")
                && !cerere.equals("inlocuire carnet de sofer")
                && !cerere.equals("inregistrare venit salarial"))
            try {
                actiuneIncorecta(bw, "angajat", cerere);
            } catch (ExceptieActiuneIncorecta e) {
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        else {
            String data = (actiune[3].substring(1));
            int prioritate = Integer.parseInt(actiune[4].substring(1));
            // se adauga la lista de cereri in asteptare
            cerereAsteptare.add(new CerereAngajat(nume, companie, cerere, data, prioritate));
        }
    }
}
