package org.example;

import java.io.*;
import java.text.ParseException;

public class ManagementPrimarie {
    public static void main(String[] args) throws IOException, ParseException {
        if (args[0] == null)
            return;

        FileReader fr = new FileReader("src/main/resources/input/" + args[0]);
        BufferedReader br = new BufferedReader(fr);
        FileWriter fw = new FileWriter("src/main/resources/output/" + args[0], true);
        BufferedWriter bw = new BufferedWriter(fw);

        Birou<Persoana> bPers = new Birou<>();
        Birou<Angajat> bA = new Birou<>();
        Birou<Elev> bElev = new Birou<>();
        Birou<Pensionar> bPens = new Birou<>();
        Birou<EntitateJuridica> bEJ = new Birou<>();

        String line = null;
        while ((line = br.readLine()) != null) {
            // se vor apela functiile corespunzatoare actiunii curente
            String[] actiune = line.split(";");

            if (actiune[0].equals("adauga_utilizator"))
                adaugaUtilizator(actiune, bPers, bA, bElev, bPens, bEJ);

            if (actiune[0].equals("cerere_noua"))
                cerereNoua(bw, actiune, bPers, bA, bElev, bPens, bEJ);

            if (actiune[0].equals("retrage_cerere"))
                retrageCerere(actiune, bPers, bA, bElev, bPens, bEJ);

            if (actiune[0].equals("afiseaza_cereri_in_asteptare"))
                afiseazaCereriInAsteptare(bw, actiune, bPers, bA, bElev, bPens, bEJ);

            if (actiune[0].equals("afiseaza_cereri"))
                afiseazaCereri(bw, actiune, bPers, bA, bElev, bPens, bEJ);

            if (actiune[0].equals("adauga_functionar"))
                adaugaFunctionar(actiune, bPers, bA, bElev, bPens, bEJ);

            if (actiune[0].equals("rezolva_cerere"))
                rezolvaCerere(actiune, bPers, bA, bElev, bPens, bEJ);

            if (actiune[0].equals("afiseaza_cereri_finalizate"))
                afiseazaCereriFinalizate(bw, actiune, bPers, bA, bElev, bPens, bEJ);
        }

        bw.close();
        fw.close();
        br.close();
        fr.close();
    }

    /*
    metoda utilizata pt adaugarea unui noi utilizator
     */
    static void adaugaUtilizator(String[] actiune, Birou<Persoana> bPers, Birou<Angajat> bA,
                                 Birou<Elev> bElev, Birou<Pensionar> bPens, Birou<EntitateJuridica> bEJ) {
        if (actiune[1].substring(1).equals("persoana"))
            bPers.u.add((Persoana) Persoana.adaugaUtilizator(actiune));
        if (actiune[1].substring(1).equals("angajat"))
            bA.u.add((Angajat) Angajat.adaugaUtilizator(actiune));
        if (actiune[1].substring(1).equals("elev"))
            bElev.u.add((Elev) Elev.adaugaUtilizator(actiune));
        if (actiune[1].substring(1).equals("pensionar"))
            bPens.u.add((Pensionar) Pensionar.adaugaUtilizator(actiune));
        if (actiune[1].substring(1).equals("entitate juridica"))
            bEJ.u.add((EntitateJuridica) EntitateJuridica.adaugaUtilizator(actiune));
    }

    /*
    metoda utilizata pt crearea unei noi cereri
     */
    static void cerereNoua(BufferedWriter bw, String[] actiune, Birou<Persoana> bPers, Birou<Angajat> bA,
                           Birou<Elev> bElev, Birou<Pensionar> bPens, Birou<EntitateJuridica> bEJ) {
        for (int i = 0; i < bPers.u.size(); i++)
            if (bPers.u.get(i).nume.equals(actiune[1].substring(1))) {
                bPers.u.get(i).adaugaCerere(bw, actiune);
                break;
            }
        for (int i = 0; i < bA.u.size(); i++)
            if (bA.u.get(i).nume.equals(actiune[1].substring(1))) {
                bA.u.get(i).adaugaCerere(bw, actiune);
                break;
            }
        for (int i = 0; i < bElev.u.size(); i++)
            if (bElev.u.get(i).nume.equals(actiune[1].substring(1))) {
                bElev.u.get(i).adaugaCerere(bw, actiune);
                break;
            }
        for (int i = 0; i < bPens.u.size(); i++)
            if (bPens.u.get(i).nume.equals(actiune[1].substring(1))) {
                bPens.u.get(i).adaugaCerere(bw, actiune);
                break;
            }
        for (int i = 0; i < bEJ.u.size(); i++)
            if (bEJ.u.get(i).nume.equals(actiune[1].substring(1))) {
                bEJ.u.get(i).adaugaCerere(bw, actiune);
                break;
            }
    }

    /*
    metoda utilizata pt retragerea unei cereri
     */
    static void retrageCerere(String[] actiune, Birou<Persoana> bPers, Birou<Angajat> bA,
                              Birou<Elev> bElev, Birou<Pensionar> bPens, Birou<EntitateJuridica> bEJ) {
        for (int k = 0; k < bPers.u.size(); k++)
            if (bPers.u.get(k).nume.equals(actiune[1].substring(1))) {
                bPers.u.get(k).retrageCerere(actiune[2].substring(1));
                break;
            }
        for (int k = 0; k < bA.u.size(); k++)
            if (bA.u.get(k).nume.equals(actiune[1].substring(1))) {
                bA.u.get(k).retrageCerere(actiune[2].substring(1));
                break;
            }
        for (int k = 0; k < bElev.u.size(); k++)
            if (bElev.u.get(k).nume.equals(actiune[1].substring(1))) {
                bElev.u.get(k).retrageCerere(actiune[2].substring(1));
                break;
            }
        for (int k = 0; k < bPens.u.size(); k++)
            if (bPens.u.get(k).nume.equals(actiune[1].substring(1))) {
                bPens.u.get(k).retrageCerere(actiune[2].substring(1));
                break;
            }
        for (int k = 0; k < bEJ.u.size(); k++)
            if (bEJ.u.get(k).nume.equals(actiune[1].substring(1))) {
                bEJ.u.get(k).retrageCerere(actiune[2].substring(1));
                break;
            }
    }

    /*
    metoda utilizata pt afisarea cererilor in asteptare ale unui utilizator
     */
    static void afiseazaCereriInAsteptare(BufferedWriter bw, String[] actiune, Birou<Persoana> bPers, Birou<Angajat> bA,
                                          Birou<Elev> bElev, Birou<Pensionar> bPens, Birou<EntitateJuridica> bEJ)
            throws IOException, ParseException {
        for (int k = 0; k < bPers.u.size(); k++)
            if (bPers.u.get(k).nume.equals(actiune[1].substring(1))) {
                bPers.u.get(k).afisareCereriAsteptare(bw);
                break;
            }
        for (int k = 0; k < bA.u.size(); k++)
            if (bA.u.get(k).nume.equals(actiune[1].substring(1))) {
                bA.u.get(k).afisareCereriAsteptare(bw);
                break;
            }
        for (int k = 0; k < bElev.u.size(); k++)
            if (bElev.u.get(k).nume.equals(actiune[1].substring(1))) {
                bElev.u.get(k).afisareCereriAsteptare(bw);
                break;
            }
        for (int k = 0; k < bPens.u.size(); k++)
            if (bPens.u.get(k).nume.equals(actiune[1].substring(1))) {
                bPens.u.get(k).afisareCereriAsteptare(bw);
                break;
            }
        for (int k = 0; k < bEJ.u.size(); k++)
            if (bEJ.u.get(k).nume.equals(actiune[1].substring(1))) {
                bEJ.u.get(k).afisareCereriAsteptare(bw);
                break;
            }
    }

    /*
    metoda utilizata pt afisarea cererilor unui tip de utilizator
     */
    static void afiseazaCereri(BufferedWriter bw, String[] actiune, Birou<Persoana> bPers, Birou<Angajat> bA,
                               Birou<Elev> bElev, Birou<Pensionar> bPens, Birou<EntitateJuridica> bEJ)
            throws ParseException, IOException {
        if (actiune[1].substring(1).equals("persoana"))
            bPers.afiseaza(bw, actiune);
        else if (actiune[1].substring(1).equals("angajat"))
            bA.afiseaza(bw, actiune);
        else if (actiune[1].substring(1).equals("elev"))
            bElev.afiseaza(bw, actiune);
        else if (actiune[1].substring(1).equals("pensionar"))
            bPens.afiseaza(bw, actiune);
        else if (actiune[1].substring(1).equals("entitate juridica"))
            bEJ.afiseaza(bw, actiune);
    }

    /*
    metoda utilizata pt adaugarea unui functionar
     */
    static void adaugaFunctionar(String[] actiune, Birou<Persoana> bPers, Birou<Angajat> bA,
                                 Birou<Elev> bElev, Birou<Pensionar> bPens, Birou<EntitateJuridica> bEJ)
            throws IOException {
        if (actiune[1].substring(1).equals("persoana"))
            bPers.adaugaFunctionar(actiune);
        else if (actiune[1].substring(1).equals("angajat"))
            bA.adaugaFunctionar(actiune);
        else if (actiune[1].substring(1).equals("elev"))
            bElev.adaugaFunctionar(actiune);
        else if (actiune[1].substring(1).equals("pensionar"))
            bPens.adaugaFunctionar(actiune);
        else if (actiune[1].substring(1).equals("entitate juridica"))
            bEJ.adaugaFunctionar(actiune);
    }

    /*
    metoda utilizata pt rezolvarea unei cereri
     */
    static void rezolvaCerere(String[] actiune, Birou<Persoana> bPers, Birou<Angajat> bA,
                              Birou<Elev> bElev, Birou<Pensionar> bPens, Birou<EntitateJuridica> bEJ)
            throws ParseException, IOException {
        if (actiune[1].substring(1).equals("persoana"))
            bPers.rezolvaCerere(actiune);
        else if (actiune[1].substring(1).equals("angajat"))
            bA.rezolvaCerere(actiune);
        else if (actiune[1].substring(1).equals("elev"))
            bElev.rezolvaCerere(actiune);
        else if (actiune[1].substring(1).equals("pensionar"))
            bPens.rezolvaCerere(actiune);
        else if (actiune[1].substring(1).equals("entitate juridica"))
            bEJ.rezolvaCerere(actiune);
    }

    /*
    metoda utilizata pt afisarea cererilor finalizate ale unui utilizator
     */
    static void afiseazaCereriFinalizate(BufferedWriter bw, String[] actiune, Birou<Persoana> bPers, Birou<Angajat> bA,
                                         Birou<Elev> bElev, Birou<Pensionar> bPens, Birou<EntitateJuridica> bEJ)
            throws IOException, ParseException {
        for (int i = 0; i < bPers.u.size(); i++)
            if (actiune[1].substring(1).equals(bPers.u.get(i).nume))
                bPers.u.get(i).afisareCereriFinalizate(bw);
        for (int i = 0; i < bA.u.size(); i++)
            if (actiune[1].substring(1).equals(bA.u.get(i).nume))
                bA.u.get(i).afisareCereriFinalizate(bw);
        for (int i = 0; i < bElev.u.size(); i++)
            if (actiune[1].substring(1).equals(bElev.u.get(i).nume))
                bElev.u.get(i).afisareCereriFinalizate(bw);
        for (int i = 0; i < bPens.u.size(); i++)
            if (actiune[1].substring(1).equals(bPens.u.get(i).nume))
                bPens.u.get(i).afisareCereriFinalizate(bw);
        for (int i = 0; i < bEJ.u.size(); i++)
            if (actiune[1].substring(1).equals(bEJ.u.get(i).nume))
                bEJ.u.get(i).afisareCereriFinalizate(bw);
    }
}
