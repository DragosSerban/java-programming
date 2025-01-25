package org.example;

import java.io.File;
import java.io.IOException;

public class Functionar<T> {
    private String nume;
    // fisierul in care se vor salva cererile rezolvate de functionarul curent
    private File f;

    Functionar(String nume) throws IOException {
        this.nume = nume;
        // se creeaza fisierul cu raportul functionarului curent
        f = new File("src/main/resources/output/functionar_" + nume + ".txt");
        f.createNewFile();
    }

    String getNume() {
        return nume;
    }

    File getFile() {
        return f;
    }
}