package org.example;

public abstract class Cerere {
    // clasa ce va fi utilizata pt lucrul cu obiectele de tip Cerere
    private String nume;
    private String cerere;
    private String data;
    private int prioritate;

    Cerere(String nume, String cerere, String data, int prioritate) {
        this.nume = nume;
        this.cerere = cerere;
        this.data = data;
        this.prioritate = prioritate;
    }

    String getNume() {
        return nume;
    }

    String getCerere() {
        return cerere;
    }

    String getData() {
        return data;
    }

    int getPrioritate() {
        return prioritate;
    }
}