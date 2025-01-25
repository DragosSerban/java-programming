package org.example;

public class CerereEntitateJuridica extends Cerere {
    private String reprezentant;

    // constructor pt cererile utilizatorilor de tip Entitate Juridica
    CerereEntitateJuridica(String nume, String reprezentant, String cerere, String data, int prioritate) {
        super(nume, cerere, data, prioritate);
        this.reprezentant = reprezentant;
    }

    String getReprezentant() {
        return reprezentant;
    }
}