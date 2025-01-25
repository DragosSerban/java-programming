package com.example.project;

import java.io.*;

public class Question {
    static int noOfQuestions = 0;
    private String username;
    private String password;
    private String text;
    private String type;
    private String[] answer;
    private int[] answerIsCorrect;
    private int noOfAnswers;
    private String usersFile = "users.txt";
    private String questionsFile = "questions.txt";

    public Question() {
        // initializarea vectorilor de raspusuri
        this.answer = new String[5];
        this.answerIsCorrect = new int[5];
        // vom completa cu -1 vectorul corespunzator corectitudinii raspunsurilor
        for (int i = 0; i < 5; i++)
            this.answerIsCorrect[i] = -1;
    }

    /* setters & getters pt username, parola, text, tipul intrebarii */
    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return this.username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return this.password;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getText() {
        return this.text;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return this.type;
    }

    /* functie ce adauga raspunsul in vectorul de raspunsuri */
    public boolean addAnswer(String command) {
        if (Integer.parseInt(command.split("-")[2].split(" ")[0]) > 5) {
            System.out.print("{'status':'error','message':'More than 5 answers were submitted'}");
            return false;
        }

        // pozitia din vector la care se va afla raspunsul va fi pos-1
        int pos = Integer.parseInt(command.split("-")[2].split(" ")[0]);
        if (this.answerIsCorrect[pos - 1] == -1)
            noOfAnswers++;

        String answer = command.split("'")[1];
        this.answer[pos - 1] = answer;
        return true;
    }

    /* functie ce adauga daca raspunsul este corect sau nu in vectorul de raspunsuri */
    public boolean addAnswerResult(String command) {
        if (Integer.parseInt(command.split("-")[2].split(" ")[0]) > 5) {
            System.out.print("{'status':'error','message':'More than 5 answers were submitted'}");
            return false;
        }

        // pozitia din vector la care se va afla raspunsul va fi pos-1
        int pos = Integer.parseInt(command.split("-")[2].split(" ")[0]);
        if (this.answer[pos - 1] == null)
            noOfAnswers++;

        int answerIsCorrect = Integer.parseInt(command.split("'")[1]);
        this.answerIsCorrect[pos - 1] = answerIsCorrect;
        return true;
    }

    /* functie ce adauga datele intrebarii la fisierul txt */
    public void addQuestion() {
        if (this.verifyCredentials()) {
            if (this.verifyQuestion()) {
                try (FileWriter fw = new FileWriter(questionsFile, true);
                     BufferedWriter bw = new BufferedWriter(fw);
                     PrintWriter out = new PrintWriter(bw)) {
                    // s-a deschis fisierul si se vor scrie date despre intrebarea introdusa
                    // (user, text, tip, raspunsuri)
                    out.print((noOfQuestions + 1) + "," + this.username + "," + this.text + "," + this.type);
                    for (int i = 0; i < 5; i++) {
                        if (this.answerIsCorrect[i] != -1) {
                            out.print("," + this.answer[i] + "," + this.answerIsCorrect[i]);
                        }
                    }
                    out.println();
                    System.out.print("{'status':'ok','message':'Question added successfully'}");
                    noOfQuestions++;
                } catch (IOException e) {
                }
            }
        }
    }

    /* functia verifica username-ul si parola; daca au fost introduse si daca exista userul cautat */
    public boolean verifyCredentials() {
        if (this.username == null || this.password == null) {
            System.out.print("{'status':'error','message':'You need to be authenticated'}");
            return false;
        }
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(usersFile));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        String line;
        while (true) {
            try {
                if (!((line = br.readLine()) != null)) break;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            String[] lineArray = line.split(",");
            if (lineArray[0].compareTo(this.username) == 0 && lineArray[1].compareTo(this.password) == 0) {
                return true;
            }
        }
        System.out.print("{'status':'error','message':'Login failed'}");
        return false;
    }

    /*
        functia verifica intrebarea; daca a mai fost salvata in fisier;
        daca au fost introduse macar 2 raspunsuri; daca a fost introdus textul intrebarii;
        daca vectorii de raspunsuri si de corectitudine a raspunsurilor contin acelasi nr de elemente;
        daca o intrebare de tip single are mai mult de un raspuns corect;
        daca acelasi raspuns a fost adaugat de mai multe ori
    */
    public boolean verifyQuestion() {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(questionsFile));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        String line;
        while (true) {
            try {
                if (!((line = br.readLine()) != null)) break;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            if (line.split(",")[1].compareTo(this.username) == 0
                    && line.split(",")[2].compareTo(this.text) == 0) {
                System.out.print("{'status':'error','message':'Question already exists'}");
                return false;
            }
        }
        if (this.noOfAnswers == 0) {
            System.out.print("{'status':'error','message':'No answer provided'}");
            return false;
        } else if (this.noOfAnswers == 1) {
            System.out.print("{'status':'error','message':'Only one answer provided'}");
            return false;
        }
        if (this.text == null) {
            System.out.print("{'status':'error','message':'No question text provided'}");
            return false;
        }
        for (int i = 0; i < this.noOfAnswers; i++) {
            if (!(this.answer[i] == null) && (this.answerIsCorrect[i] == -1)) {
                System.out.print("{'status':'error','message':'Answer " + (i + 1) + " has no answer correct flag'}");
                return false;
            } else if ((this.answer[i] == null) && (this.answerIsCorrect[i] != -1)) {
                System.out.print("{'status':'error','message':'Answer " + (i + 1) + " has no answer description'}");
                return false;
            }
        }
        int noOfCorrectAnswers = 0;
        for (int i = 0; i < this.noOfAnswers; i++) {
            if (this.answerIsCorrect[i] == 1)
                noOfCorrectAnswers++;
        }
        if (this.type.equals("single") && noOfCorrectAnswers > 1) {
            System.out.print("{'status':'error','message':"
                    + "'Single correct answer question has more than one correct answer'}");
            return false;
        }
        for (int i = 0; i < this.noOfAnswers - 1; i++) {
            for (int j = i + 1; j < this.noOfAnswers; j++) {
                if (this.answer[i].equals(this.answer[j])) {
                    System.out.print("{'status':'error','message':'Same answer provided more than once'}");
                    return false;
                }
            }
        }
        return true;
    }

    /*
        functie ce intoarce id-ul intrebarii in functie de textul sau
     */
    public void getQuestionIdByText() {
        // verifica user-ul si parola
        if (this.username == null || this.password == null) {
            System.out.print("{'status':'error','message':'You need to be authenticated'}");
            return;
        }

        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(usersFile));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        boolean userExists = false;
        String line;
        while (true) {
            try {
                if (!((line = br.readLine()) != null)) break;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            if (line.split(",")[0].compareTo(this.username) == 0
                    && line.split(",")[1].compareTo(this.password) == 0) {
                userExists = true;
            }
        }

        if (userExists) {
            BufferedReader brAux = null;
            try {
                brAux = new BufferedReader(new FileReader(questionsFile));
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }

            // cautam textul intrebarii in fisierul cu datele intrebarilor si verificam ca user-ul sa coincida
            String lineAux;
            while (true) {
                try {
                    if (!((lineAux = brAux.readLine()) != null)) break;
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                if (lineAux.split(",")[1].compareTo(this.username) == 0
                        && lineAux.split(",")[2].compareTo(this.text) == 0) {
                    // returnam id-ul
                    System.out.print("{'status':'ok','message':'" + lineAux.split(",")[0] + "'}");
                    return;
                }
            }
            System.out.print("{'status':'error','message':'Question does not exist'}");
            return;
        } else {
            System.out.print("{'status':'error','message':'Login failed'}");
        }
    }

    /*
        functie ce intoarce toate intrebarile unui user
     */
    public void getAllQuestions() {
        // se verifica utilizatorul si parola
        if (this.username == null || this.password == null) {
            System.out.print("{'status':'error','message':'You need to be authenticated'}");
            return;
        }

        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(usersFile));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        boolean userExists = false;
        String line;
        while (true) {
            try {
                if (!((line = br.readLine()) != null)) break;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            if (line.split(",")[0].compareTo(this.username) == 0
                    && line.split(",")[1].compareTo(this.password) == 0) {
                userExists = true;
            }
        }

        if (userExists) {
            // daca exista userul, se vor afisa toate intrebarile utilizatorului dat
            BufferedReader brAux = null;
            try {
                brAux = new BufferedReader(new FileReader(questionsFile));
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
            System.out.print("{'status':'ok','message':'[");
            boolean ok = false;
            String lineAux;
            while (true) {
                try {
                    if (!((lineAux = brAux.readLine()) != null)) break;
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                if (lineAux.split(",")[1].compareTo(this.username) == 0) {
                    if (ok)
                        System.out.print(", ");
                    System.out.print("{\"question_id\" : \"" + lineAux.split(",")[0]
                            + "\", \"question_name\" : \"" + lineAux.split(",")[2] + "\"}");
                    if (!ok)
                        ok = true;
                }
            }
            System.out.print("]'}");
            return;
        } else {
            System.out.print("{'status':'error','message':'Login failed'}");
        }
    }
}
