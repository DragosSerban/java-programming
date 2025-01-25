package com.example.project;

import java.io.*;

public class User {
    private String username;
    private String password;

    private String fileName;

    public User() {
    }

    public String getUsername() {
        return this.username;
    }

    /* setters & getters pt username, parola si numele fisierului unde se vor stoca datele despre utilizatori */
    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFileName() {
        return this.fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /* functie pt adaugarea datelor utilizatorului curent in fisierul txt */
    public void addToTxtFile() {
        // se verifica numele utilizatorului si parola
        if (this.username == null) {
            System.out.print("{'status':'error','message':'Please provide username'}");
        } else if (this.password == null) {
            System.out.print("{'status':'error','message':'Please provide password'}");
        } else {
            BufferedReader br = null;
            try {
                br = new BufferedReader(new FileReader(fileName));
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }

            // se verifica daca exista alt utilizator cu acelasi nume; daca da => eroare
            String line;
            while (true) {
                try {
                    if (!((line = br.readLine()) != null)) break;
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                if (line.split(",")[0].compareTo(this.username) == 0) {
                    System.out.print("{'status':'error','message':'User already exists'}");
                    return;
                }
            }

            // daca nu s-a gasit vreun utilizator cu acest username, se introduc in fisier username-ul si parola
            try (FileWriter fw = new FileWriter(fileName, true);
                 BufferedWriter bw = new BufferedWriter(fw);
                 PrintWriter out = new PrintWriter(bw)) {
                out.println(this.username + "," + this.password);
                System.out.println("{'status':'ok','message':'User created successfully'}");
            } catch (IOException e) {
            }
        }
    }
}
