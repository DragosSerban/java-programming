package org.example;

public class CerereAngajat extends Cerere {
    private String companie;

    // constructor pt cererile utilizatorilor de tip Angajat
    CerereAngajat(String nume, String companie, String cerere, String data, int prioritate) {
        super(nume, cerere, data, prioritate);
        this.companie = companie;
    }

    String getCompanie() {
        return companie;
    }
}