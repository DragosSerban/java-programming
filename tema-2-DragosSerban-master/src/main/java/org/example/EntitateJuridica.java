package org.example;

import java.io.BufferedWriter;
import java.io.IOException;
import java.text.ParseException;

public class EntitateJuridica extends Utilizator {
    // utilizator tip entitate juridica
    private String reprezentant;

    EntitateJuridica(String nume, String reprezentant) {
        this.nume = nume;
        this.reprezentant = reprezentant;
    }

    @Override
    void afisareCereriAsteptare(BufferedWriter bw) throws IOException, ParseException {
        // sorteaza cererile, apoi le afiseaza pe cele aflate in asteptare
        this.sorteazaCereri();
        bw.write(nume + " - cereri in asteptare:\n");
        for (int i = 0; i < this.cerereAsteptare.size(); i++)
            bw.write(cerereAsteptare.get(i).getData() + " - Subsemnatul "
                    + ((CerereEntitateJuridica) cerereAsteptare.get(i)).getReprezentant()
                    + ", reprezentant legal al companiei " + cerereAsteptare.get(i).getNume()
                    + ", va rog sa-mi aprobati urmatoarea solicitare: " + cerereAsteptare.get(i).getCerere() + "\n");
    }

    @Override
    void afisareCereriFinalizate(BufferedWriter bw) throws IOException, ParseException {
        // sorteaza cererile, apoi le afiseaza pe cele finalizate
        this.sorteazaCereri();
        bw.write(nume + " - cereri in finalizate:\n");
        for (int i = 0; i < this.cerereFinalizata.size(); i++)
            bw.write(cerereFinalizata.get(i).getData() + " - Subsemnatul "
                    + ((CerereEntitateJuridica) cerereFinalizata.get(i)).getReprezentant()
                    + ", reprezentant legal al companiei " + cerereFinalizata.get(i).getNume()
                    + ", va rog sa-mi aprobati urmatoarea solicitare: " + cerereFinalizata.get(i).getCerere() + "\n");
    }

    @Override
    void adaugaCerere(BufferedWriter bw, String[] actiune) {
        // adauga o cerere
        String reprezentant = this.reprezentant;
        String cerere = (actiune[2].substring(1));
        // se verifica daca cererea corespunde tipului de utilizator
        if (!cerere.equals("creare act constitutiv")
                && !cerere.equals("reinnoire autorizatie"))
            try {
                actiuneIncorecta(bw, "entitate juridica", cerere);
            } catch (ExceptieActiuneIncorecta e) {
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        else {
            String data = (actiune[3].substring(1));
            Integer prioritate = Integer.parseInt(actiune[4].substring(1));
            // se adauga la lista de cereri in asteptare
            cerereAsteptare.add(new CerereEntitateJuridica
                    (nume, reprezentant, cerere, data, prioritate));
        }
    }
}