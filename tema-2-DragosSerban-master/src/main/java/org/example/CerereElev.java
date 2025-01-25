package org.example;

public class CerereElev extends Cerere {
    private String scoala;

    // constructor pt cererile utilizatorilor de tip Elev
    CerereElev(String nume, String scoala, String cerere, String data, int prioritate) {
        super(nume, cerere, data, prioritate);
        this.scoala = scoala;
    }

    String getScoala() {
        return scoala;
    }
}