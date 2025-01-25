package org.example;

import java.io.BufferedWriter;
import java.io.IOException;

public class ExceptieActiuneIncorecta extends Exception {
    // exceptia aruncata in cazul incercarii de adaugare a unei cereri invalide
    ExceptieActiuneIncorecta(BufferedWriter bw, String tip, String cerere) throws IOException {
        bw.write("Utilizatorul de tip " + tip + " nu poate inainta o cerere de tip " + cerere + "\n");
    }
}