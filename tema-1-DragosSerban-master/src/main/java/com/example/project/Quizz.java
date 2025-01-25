package com.example.project;

import java.io.*;

public class Quizz {
    String username;
    String password;
    String name;
    int[] questionIds;
    static int noOfQuizzes = 0;
    int id;
    String[] answers;
    private int noOfAnswers;
    private String usersFile = "users.txt";
    private String questionsFile = "questions.txt";
    private String quizzesFiles = "quizzes.txt";

    public Quizz() {
        questionIds = new int[10];
        answers = new String[10];
    }

    /* setters & getters pt username, parola, numele quizzului, id si answerId */
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

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setId(Integer id) {
        this.id = (int) id;
    }

    public int getId() {
        return this.id;
    }

    public void setAnswerId(String id, String answer) {
        this.answers[Integer.parseInt(id)] = answer;
        this.noOfAnswers++;
    }

    /* functie ce adauga o intrebare la quizz */
    public boolean addQuestion(String command) {
        Integer questionNo = Integer.parseInt(command.split("-")[2].split(" ")[0]);
        // se verifica daca s-a trecut de a zecea intrebare; nu avem voie la mai mult de 10 intrebari
        if (questionNo > 10) {
            System.out.print("{'status':'error','message':'Quizz has more than 10 questions'}");
            return false;
        }
        Integer questionId = Integer.parseInt(command.split("'")[1]);
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(questionsFile));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        boolean questionExists = false;
        String line;
        while (true) {
            try {
                if (!((line = br.readLine()) != null)) break;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            // se verifica intrebarea in functie de id; daca exista, se adauga la quizz
            String[] lineArray = line.split(",");
            if (lineArray[0].compareTo(questionId.toString()) == 0) {
                this.questionIds[questionNo - 1] = questionId;
                questionExists = true;
                break;
            }
        }
        if (!questionExists) {
            System.out.print("{'status':'error','message':'Question ID for question "
                    + questionNo + " does not exist'}");
            return false;
        }
        return true;
    }

    /* functie ce adauga quizz-ul la fisierul txt */
    public void addQuizz() {
        // se verifica numele utilizatorului si parola; mai intai daca au fost introduse
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
        String line;
        while (true) {
            try {
                if (!((line = br.readLine()) != null)) break;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            String[] lineArray = line.split(",");
            // se cauta utilizatorul in fisierul "users.txt"
            if (lineArray[0].compareTo(this.username) == 0 && lineArray[1].compareTo(this.password) == 0) {
                BufferedReader brAux = null;
                try {
                    brAux = new BufferedReader(new FileReader(quizzesFiles));
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
                String lineAux;
                while (true) {
                    try {
                        if (!((lineAux = brAux.readLine()) != null)) break;
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    // se verifica daca a mai fost creat un quizz cu numele actual; daca da => eroare
                    lineArray = lineAux.split(",");
                    if (lineArray[1].compareTo(this.username) == 0 && lineArray[2].compareTo(this.name) == 0) {
                        System.out.print("{'status':'error','message':'Quizz name already exists'}");
                        return;
                    }
                }
                try (FileWriter fw = new FileWriter(quizzesFiles, true);
                     BufferedWriter bw = new BufferedWriter(fw);
                     PrintWriter out = new PrintWriter(bw)) {
                    // se adauga quizzul la fisierul txt; cu toate datele sale
                    // (user, nume, id-uri intrebari)
                    out.print((noOfQuizzes + 1) + "," + this.username + "," + this.name);
                    for (int i = 0; i < 10; i++) {
                        if (questionIds[i] != 0) {
                            out.print("," + questionIds[i]);
                        }
                    }
                    out.println();
                    System.out.print("{'status':'ok','message':'Quizz added succesfully'}");
                    noOfQuizzes++;
                } catch (IOException e) {
                }
                return;
            }
        }
        // in cazul in care nu a fost gasit userul cu parola introdusa, se afiseaza "Login failed"
        System.out.print("{'status':'error','message':'Login failed'}");
        return;
    }

    /* functie ce cauta quizzul in functie de nume */
    public void getQuizzByName() {
        // se verifica user-ul si parola
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
        String line;
        while (true) {
            try {
                if (!((line = br.readLine()) != null)) break;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            String[] lineArray = line.split(",");
            // se cauta in fisierul "users.txt" un utilizator cu user-ul si parola introdusa, altfel, "Login failed"
            if (lineArray[0].compareTo(this.username) == 0 && lineArray[1].compareTo(this.password) == 0) {
                BufferedReader brAux = null;
                try {
                    brAux = new BufferedReader(new FileReader(quizzesFiles));
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
                String lineAux;
                while (true) {
                    try {
                        if (!((lineAux = brAux.readLine()) != null)) break;
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    // se cauta in "quizzes.txt" un quizz cu datele aflate (username + nume) si se afiseaza id-ul
                    lineArray = lineAux.split(",");
                    if (lineArray[1].compareTo(this.username) == 0 && lineArray[2].compareTo(this.name) == 0) {
                        System.out.print("{'status':'ok','message':'" + lineArray[0] + "'}");
                        return;
                    }
                }
                System.out.print("{'status':'error','message':'Quizz does not exist'}");
                return;
            }
        }
        System.out.print("{'status':'error','message':'Login failed'}");
        return;
    }

    /* functie ce returneaza toate quizz-urile unui user */
    public void getAllQuizzes() {
        // se verifica daca au fost introduse username-ul si parola
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
        String line;
        while (true) {
            try {
                if (!((line = br.readLine()) != null)) break;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            // se cauta user-ul in fisier si se verifica parola
            String[] lineArray = line.split(",");
            if (lineArray[0].compareTo(this.username) == 0 && lineArray[1].compareTo(this.password) == 0) {
                BufferedReader brAux = null;
                try {
                    brAux = new BufferedReader(new FileReader(quizzesFiles));
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
                String lineAux;
                System.out.print("{'status':'ok','message':'[");
                boolean ok = false;
                while (true) {
                    try {
                        if (!((lineAux = brAux.readLine()) != null)) break;
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    // se cauta in "quizzes.txt" quizz-urile user-ului dat si se afiseaza
                    lineArray = lineAux.split(",");
                    if (lineArray[1].compareTo(this.username) == 0) {
                        if (ok)
                            System.out.print(", ");
                        System.out.print("{\"quizz_id\" : \"" + lineArray[0] + "\", \"quizz_name\" : \""
                                + lineArray[2] + "\", \"is_completed\" : ");

                        // verificam daca quizul a fost completat sau nu
                        boolean is_completed = false;
                        BufferedReader br1 = null;
                        try {
                            br1 = new BufferedReader(new FileReader("solutions.txt"));
                        } catch (FileNotFoundException e) {
                            throw new RuntimeException(e);
                        }

                        String line1;
                        while (true) {
                            try {
                                if (!((line1 = br1.readLine()) != null)) break;
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                            if (line1.split(",")[1].compareTo(String.valueOf(this.id)) == 0) {
                                is_completed = true;
                            }
                        }
                        if (is_completed)
                            System.out.print("\"True\"}");
                        else
                            System.out.print("\"False\"}");
                        ok = true;
                    }
                }
                System.out.print("]'}");
                return;
            }
        }
        System.out.print("{'status':'error','message':'Login failed'}");
        return;
    }

    /* functie ce returneaza detaliile unui quizz in functie de id-ul dat */
    public void getDetails() {
        // se verifica daca au fost furnizate username-ul si parola
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
        String line;
        while (true) {
            try {
                if (!((line = br.readLine()) != null)) break;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            // se cauta un user care sa aiba username-ul si parola data in fisierul "users.txt"
            String[] lineArray = line.split(",");
            if (lineArray[0].compareTo(this.username) == 0 && lineArray[1].compareTo(this.password) == 0) {
                BufferedReader brAux = null;
                try {
                    brAux = new BufferedReader(new FileReader(quizzesFiles));
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
                String lineAux;
                while (true) {
                    try {
                        if (!((lineAux = brAux.readLine()) != null)) break;
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    // se cauta in "quizzes.txt" quizz-ul cu id-ul dat
                    lineArray = lineAux.split(",");
                    if (lineArray[0].compareTo(String.valueOf(this.id)) == 0) {
                        int[] temp = new int[10];
                        System.out.print("{'status':'ok','message':'[");
                        boolean ok = false;
                        int answerId = 1;
                        for (int i = 3; i < lineArray.length; i++) {
                            BufferedReader brAux2 = null;
                            try {
                                brAux2 = new BufferedReader(new FileReader(questionsFile));
                            } catch (FileNotFoundException e) {
                                throw new RuntimeException(e);
                            }
                            String lineAux2;
                            while (true) {
                                try {
                                    if (!((lineAux2 = brAux2.readLine()) != null)) break;
                                } catch (IOException e) {
                                    throw new RuntimeException(e);
                                }

                                // se cauta intrebarile in "questions.txt" in functie de id-urile lor
                                if (lineAux2.split(",")[0].compareTo(lineArray[i]) == 0) {
                                    if (ok)
                                        System.out.print(", ");
                                    ok = true;
                                    // se afiseaza intrebarea si raspunsurile corespunzatoare
                                    System.out.print("{\"question-name\":\"" + lineAux2.split(",")[2]
                                            + "\", \"question_index\":\"" + lineArray[i] + "\", \"question_type\":\""
                                            + lineAux2.split(",")[3] + "\", \"answers\":\"[");
                                    boolean ok1 = false;
                                    for (int j = 4; j < lineAux2.split(",").length; j += 2) {
                                        if (ok1)
                                            System.out.print(", ");
                                        System.out.print("{\"answer_name\":\"" + lineAux2.split(",")[j]
                                                + "\", \"answer_id\":\"" + (answerId++) + "\"}");
                                        ok1 = true;
                                    }
                                    System.out.print("]\"}");
                                }
                            }
                        }
                        System.out.print("]'}");
                    }
                }
                return;
            }
        }
        System.out.print("{'status':'error','message':'Login failed'}");
        return;
    }

    /* functie folosita pt a da submit la quizz */
    public void submit() {
        // se verifica username + parola; daca au fost furnizate
        if (this.username == null || this.password == null) {
            System.out.print("{'status':'error','message':'You need to be authenticated'}");
            return;
        }
        // in result vom pastra rezultatul quizzului
        double result = 0;
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
            // se cauta user-ul in fisierul "users.txt"
            String[] lineArray = line.split(",");
            if (lineArray[0].compareTo(this.username) == 0 && lineArray[1].compareTo(this.password) == 0) {
                // se verifica daca a fost furnizat id-ul quizzului ce urmeaza a fi rezolvat
                if (this.id == 0) {
                    System.out.print("{'status':'error','message':'No quizz identifier was provided'}");
                    return;
                }
                BufferedReader brAux = null;
                try {
                    brAux = new BufferedReader(new FileReader(quizzesFiles));
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
                String lineAux;
                while (true) {
                    try {
                        if (!((lineAux = brAux.readLine()) != null)) break;
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    lineArray = lineAux.split(",");
                    Integer aux = this.id;
                    if (lineArray[0].compareTo(aux.toString()) == 0) {
                        // s-a gasit quizzul ce urmeaza a fi rezolvat
                        this.name = lineArray[2];
                        if (lineArray[1].compareTo(this.username) == 0) {
                            // un quizz nu poate fi rezolvat de cel care l-a creat
                            System.out.print("{'status':'error','message':'You cannot answer your own quizz'}");
                            return;
                        }

                        int counter = 0;
                        for (int i = 3; i < lineArray.length; i++) {
                            // se va trece prin fiecare intrebare in parte
                            BufferedReader brAux1 = null;
                            try {
                                brAux1 = new BufferedReader(new FileReader(questionsFile));
                            } catch (FileNotFoundException e) {
                                throw new RuntimeException(e);
                            }
                            String lineAux1;
                            String[] lineArray1;
                            while (true) {
                                try {
                                    if (!((lineAux1 = brAux1.readLine()) != null)) break;
                                } catch (IOException e) {
                                    throw new RuntimeException(e);
                                }
                                lineArray1 = lineAux1.split(",");

                                // se cauta intrebarea in fisierul "questions.txt"
                                if (Integer.parseInt(lineArray[i]) == Integer.parseInt(lineArray1[0])) {
                                    // intrebarea a fost gasita
                                    int isCorrect = 0;
                                    int isIncorrect = 0;
                                    // se numara raspunsurile corecte si cele incorecte
                                    for (int j = 4; j < lineArray1.length; j += 2) {
                                        if (lineArray1[j + 1] == "")
                                            System.out.print("{'status':'error','message':'Answer ID for answer "
                                                    + ((j - 4) / 2 + 1) + " does not exist'}");
                                        if (Integer.parseInt(lineArray1[j + 1]) == 1)
                                            isCorrect++;
                                        else
                                            isIncorrect++;
                                    }
                                    // se calculeaza nr de puncte in functie de raspunsurile date
                                    double pointsForCorrectAnswer = 1.0 / isCorrect;
                                    double pointsForIncorrectAnswer = 1.0 / isIncorrect;

                                    for (int j = 1; j < noOfAnswers + 1; j++) {
                                        if (answers[j] == null)
                                            continue;
                                        if (Integer.parseInt(answers[j]) > isCorrect + isIncorrect) {
                                            for (int k = 1; k < j; k++)
                                                answers[k] = null;
                                            for (int k = j; k < noOfAnswers + 1; k++)
                                                answers[k] = String.valueOf
                                                        (Integer.parseInt(answers[k]) - isCorrect - isIncorrect);
                                            break;
                                        }
                                        // se calculeaza rezultatul pt intrebarea actuala
                                        if (Integer.parseInt(lineArray1[3 + 2 * Integer.parseInt(answers[j])]) == 1) {
                                            result += pointsForCorrectAnswer;
                                        } else {
                                            result -= pointsForIncorrectAnswer;
                                        }
                                    }
                                    // se incrementeaza nr intrebarilor
                                    counter++;
                                }
                            }
                        }
                        // rezultatul nu poate fi mai mic decat zero
                        if (result < 0)
                            result = 0;

                        // se vor scrie solutiile in fisierul "solutions.txt"
                        BufferedReader br2 = null;
                        try {
                            br2 = new BufferedReader(new FileReader("solutions.txt"));
                        } catch (FileNotFoundException e) {
                            throw new RuntimeException(e);
                        }
                        String line2;
                        while (true) {
                            try {
                                if (!((line2 = br2.readLine()) != null)) break;
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                            // se verifica daca a mai fost submitat odata quizzul
                            String[] lineArray2 = line2.split(",");
                            if (lineArray2[0].compareTo(this.username) == 0
                                    && lineArray2[1].compareTo(String.valueOf(this.id)) == 0) {
                                System.out.print("{'status':'error','message':'You already submitted this quizz'}");
                                return;
                            }
                        }
                        // se scrie rezultatul in fisier
                        try (FileWriter fw = new FileWriter("solutions.txt", true);
                             BufferedWriter bw = new BufferedWriter(fw);
                             PrintWriter out = new PrintWriter(bw)) {
                            out.print(this.username + "," + this.id + ","
                                    + this.name + "," + Math.round(100 * result / counter));
                            out.println();
                        } catch (IOException e) {
                        }

                        // se afiseaza mesajul de succes si rezultatul
                        System.out.print("{'status':'ok','message':'" + Math.round(100 * result / counter)
                                + " points'}");
                        return;
                    }
                }
                System.out.print("{'status':'error','message':'No quiz was found'}");
                return;
            }
        }
        System.out.print("{'status':'error','message':'Login failed'}");
        return;
    }

    /* functie pt stergerea quizzului dupa id */
    public void delete() {
        // se verifica username-ul si parola
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
        String line;
        while (true) {
            try {
                if (!((line = br.readLine()) != null)) break;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            // se gaseste user-ul in "users.txt"
            String[] lineArray = line.split(",");
            if (lineArray[0].compareTo(this.username) == 0 && lineArray[1].compareTo(this.password) == 0) {
                BufferedReader brAux = null;
                try {
                    brAux = new BufferedReader(new FileReader(quizzesFiles));
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
                if (this.id == 0) {
                    // daca nu a fost introdus id-ul quizz-ului => eroare
                    System.out.print("{'status':'error','message':'No quizz identifier was provided'}");
                    return;
                }
                String lineAux;
                boolean found = false;
                while (true) {
                    try {
                        if (!((lineAux = brAux.readLine()) != null)) break;
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    lineArray = lineAux.split(",");
                    if (lineArray[0].compareTo(String.valueOf(this.id)) == 0) {
                        // s-a gasit quizzul
                        found = true;
                    } else {
                        // datele despre celelalte quizzuri vor fi stocate intr-un fisier temporar
                        String temp_id = lineArray[0];
                        String temp_user = lineArray[1];
                        String temp_name = lineArray[2];
                        try (FileWriter fw = new FileWriter("temp.txt", true);
                             BufferedWriter bw = new BufferedWriter(fw);
                             PrintWriter out = new PrintWriter(bw)) {
                            out.print(temp_id + "," + temp_user + "," + temp_name);
                            int i = 3;
                            for (int j = 3; j < lineArray.length; j++) {
                                out.print("," + lineArray[j]);
                            }
                            out.println();
                        } catch (IOException e) {
                        }
                    }
                }
                // fisierul ce contine datele despre celelalte quizzuri va fi redenumit
                File f = new File("temp.txt");
                f.renameTo(new File(quizzesFiles));
                if (found) {
                    System.out.print("{'status':'ok','message':'Quizz deleted successfully'}");
                    return;
                }
                System.out.print("{'status':'error','message':'No quiz was found'}");
                return;
            }
        }
        System.out.print("{'status':'error','message':'Login failed'}");
        return;
    }

    /* functie ce returneaza solutiile ce apartin unui user */
    public void getSolutions() {
        // se verifica username-ul si parola
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
        String line;
        while (true) {
            try {
                if (!((line = br.readLine()) != null)) break;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            // se cauta user-ul in "users.txt"
            String[] lineArray = line.split(",");
            if (lineArray[0].compareTo(this.username) == 0 && lineArray[1].compareTo(this.password) == 0) {
                BufferedReader brAux = null;
                try {
                    brAux = new BufferedReader(new FileReader("solutions.txt"));
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
                String lineAux;
                int indexInList = 1;
                boolean ok = false;
                System.out.print("{'status':'ok','message':'[");
                while (true) {
                    try {
                        if (!((lineAux = brAux.readLine()) != null)) break;
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    // se afiseaza toate solutiile salvate de user in "solutions.txt"
                    lineArray = lineAux.split(",");
                    if (lineArray[0].compareTo(String.valueOf(this.username)) == 0) {
                        if (ok)
                            System.out.print(", ");
                        else
                            ok = true;
                        System.out.print("{\"quiz-id\" : \"" + lineArray[1] + "\", \"quiz-name\" : \"" + lineArray[2]
                                + "\", \"score\" : \"" + lineArray[3] + "\", \"index_in_list\" : \""
                                + indexInList + "\"}");
                        indexInList++;
                    }
                }
                System.out.print("]'}");
                if (ok)
                    return;
            }
        }
        System.out.print("{'status':'error','message':'Login failed'}");
        return;
    }
}
