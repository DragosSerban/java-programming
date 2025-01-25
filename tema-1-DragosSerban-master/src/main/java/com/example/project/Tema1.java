package com.example.project;

import java.io.*;

public class Tema1 {

    public static void main(final String[] args) {

        // cream fisierul cu datele userilor sau il cream, daca nu exista
        File users = new File("users.txt");
        if (!users.exists()) {
            try {
                users.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        // cream fisierul cu datele despre intrebari sau il cream, daca nu exista
        File questions = new File("questions.txt");
        if (!questions.exists()) {
            try {
                questions.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        // cream fisierul cu datele despre quizuri sau il cream, daca nu exista
        File quizzes = new File("quizzes.txt");
        if (!quizzes.exists()) {
            try {
                quizzes.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        // deschidem fisierul cu datele despre solutii sau il cream, daca nu exista
        File solutions = new File("solutions.txt");
        if (!solutions.exists()) {
            try {
                solutions.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        if (args == null) {
            // cazul in care nu sunt primiti parametrii
            System.out.print("Hello world!");
        } else if (args[0].compareTo("-cleanup-all") == 0) {
            // se sterg toate fisierele ce contin datele utilizatorilor, quizurilor,
            // datele despre intrebari si solutiile; de asemenea resetam nr intrebarilor si quizurilor
            users.delete();
            questions.delete();
            quizzes.delete();
            solutions.delete();
            Question.noOfQuestions = 0;
            Quizz.noOfQuizzes = 0;
        } else if (args[0].compareTo("-create-user") == 0) {
            // cream un nou obiect de tip user
            User u = new User();
            u.setFileName("users.txt");
            for (int i = 1; i < args.length; i++) {
                if (args[i].split(" ")[0].compareTo("-u") == 0)
                    u.setUsername(args[i].split(" ")[1].replace("'", ""));
                else if (args[i].split(" ")[0].compareTo("-p") == 0)
                    u.setPassword(args[i].split(" ")[1].replace("'", ""));
            }
            // il adaugam la fisierul txt
            u.addToTxtFile();
        } else if (args[0].compareTo("-create-question") == 0) {
            // cream un nou obiect de tip question
            Question q = new Question();
            //q.setUsersFile("users.txt");
            //q.setQuestionsFile("questions.txt");
            for (int i = 1; i < args.length; i++) {
                // obtinem informatiile necesare pt completarea campurilor question-ului
                if (args[i].split(" ")[0].compareTo("-u") == 0)
                    q.setUsername(args[i].split(" ")[1].replace("'", ""));
                else if (args[i].split(" ")[0].compareTo("-p") == 0)
                    q.setPassword(args[i].split(" ")[1].replace("'", ""));
                else if (args[i].split(" ")[0].compareTo("-text") == 0)
                    q.setText(args[i].split("'")[1]);
                else if (args[i].split(" ")[0].compareTo("-type") == 0)
                    q.setType(args[i].split("'")[1]);
                else if (args[i].split("-")[1].compareTo("answer") == 0
                        && args[i].split("-").length == 3) {
                    boolean temp = q.addAnswer(args[i]);
                    if (!temp)
                        return;
                } else if (args[i].split("-")[1].compareTo("answer") == 0
                        && args[i].split("-").length == 5) {
                    boolean temp = q.addAnswerResult(args[i]);
                    if (!temp)
                        return;
                }
            }
            // adaugam intrebarea la fisierul txt
            q.addQuestion();
        } else if (args[0].compareTo("-get-question-id-by-text") == 0) {
            // cream un obiect de tip question
            Question q = new Question();
            for (int i = 1; i < args.length; i++) {
                if (args[i].split(" ")[0].compareTo("-u") == 0)
                    q.setUsername(args[i].split(" ")[1].replace("'", ""));
                else if (args[i].split(" ")[0].compareTo("-p") == 0)
                    q.setPassword(args[i].split(" ")[1].replace("'", ""));
                else if (args[i].split(" ")[0].compareTo("-text") == 0)
                    q.setText(args[i].split("'")[1]);
            }
            // obtinem id-ul intrebarii in functie de textul acesteia
            q.getQuestionIdByText();
        } else if (args[0].compareTo("-get-all-questions") == 0) {
            // cream un obiect de tip question
            Question q = new Question();
            for (int i = 1; i < args.length; i++) {
                if (args[i].split(" ")[0].compareTo("-u") == 0)
                    q.setUsername(args[i].split(" ")[1].replace("'", ""));
                else if (args[i].split(" ")[0].compareTo("-p") == 0)
                    q.setPassword(args[i].split(" ")[1].replace("'", ""));
            }
            // obtinem toate intrebarile utilizatorului dat mai sus
            q.getAllQuestions();
        } else if (args[0].compareTo("-create-quizz") == 0) {
            // cream un obiect de tip quizz
            Quizz q = new Quizz();
            for (int i = 1; i < args.length; i++) {
                // obtinem informatiile necesare pt completarea campurilor quizzului
                if (args[i].split(" ")[0].compareTo("-u") == 0)
                    q.setUsername(args[i].split(" ")[1].replace("'", ""));
                else if (args[i].split(" ")[0].compareTo("-p") == 0)
                    q.setPassword(args[i].split(" ")[1].replace("'", ""));
                else if (args[i].split(" ")[0].compareTo("-name") == 0)
                    q.setName(args[i].split("'")[1]);
                else if (args[i].split("-")[1].compareTo("question") == 0) {
                    boolean temp = q.addQuestion(args[i]);
                    if (!temp)
                        return;
                }
            }
            // adaugam quizzul
            q.addQuizz();
        } else if (args[0].compareTo("-get-quizz-by-name") == 0) {
            // cream un nou obiect de tip quizz
            Quizz q = new Quizz();
            for (int i = 1; i < args.length; i++) {
                if (args[i].split(" ")[0].compareTo("-u") == 0)
                    q.setUsername(args[i].split(" ")[1].replace("'", ""));
                else if (args[i].split(" ")[0].compareTo("-p") == 0)
                    q.setPassword(args[i].split(" ")[1].replace("'", ""));
                else if (args[i].split(" ")[0].compareTo("-name") == 0
                        || args[i].split(" ")[0].compareTo("-text") == 0)
                    q.setName(args[i].split("'")[1]);
            }
            // obtinem quizzul in functie de numele introdus mai sus
            q.getQuizzByName();
        } else if (args[0].compareTo("-get-all-quizzes") == 0) {
            // cream un obiect de tip quizz
            Quizz q = new Quizz();
            for (int i = 1; i < args.length; i++) {
                if (args[i].split(" ")[0].compareTo("-u") == 0)
                    q.setUsername(args[i].split(" ")[1].replace("'", ""));
                else if (args[i].split(" ")[0].compareTo("-p") == 0)
                    q.setPassword(args[i].split(" ")[1].replace("'", ""));
            }
            // obtinem toate quizzurile ce apartin utilizatorului introdus mai sus
            q.getAllQuizzes();
        } else if (args[0].compareTo("-get-quizz-details-by-id") == 0) {
            // cream un obiect de tip quizz
            Quizz q = new Quizz();
            for (int i = 1; i < args.length; i++) {
                if (args[i].split(" ")[0].compareTo("-u") == 0)
                    q.setUsername(args[i].split(" ")[1].replace("'", ""));
                else if (args[i].split(" ")[0].compareTo("-p") == 0)
                    q.setPassword(args[i].split(" ")[1].replace("'", ""));
                else if (args[i].split(" ")[0].compareTo("-id") == 0)
                    q.setId(Integer.parseInt(args[i].split("'")[1]));
            }
            // obtinem detalii cu privire la quizz in functie de id-ul primit
            q.getDetails();
        } else if (args[0].compareTo("-submit-quizz") == 0) {
            // cream un nou obiect de tip quizz
            Quizz q = new Quizz();
            for (int i = 1; i < args.length; i++) {
                if (args[i].split(" ")[0].compareTo("-u") == 0)
                    q.setUsername(args[i].split(" ")[1].replace("'", ""));
                else if (args[i].split(" ")[0].compareTo("-p") == 0)
                    q.setPassword(args[i].split(" ")[1].replace("'", ""));
                else if (args[i].split(" ")[0].compareTo("-quiz-id") == 0)
                    q.setId(Integer.parseInt(args[i].split("'")[1]));
                    // obtinem raspunsurile la intrebari
                else if (args[i].split("-")[1].compareTo("answer") == 0
                        && args[i].split("-")[2].compareTo("id") == 0)
                    q.setAnswerId(args[i].split("-")[3].split(" ")[0], args[i].split("'")[1]);
            }
            // salvam raspunsurile utilizatorului la quizz
            q.submit();
        } else if (args[0].compareTo("-delete-quizz-by-id") == 0) {
            // cream un nou obiect de tip quizz
            Quizz q = new Quizz();
            for (int i = 1; i < args.length; i++) {
                if (args[i].split(" ")[0].compareTo("-u") == 0)
                    q.setUsername(args[i].split(" ")[1].replace("'", ""));
                else if (args[i].split(" ")[0].compareTo("-p") == 0)
                    q.setPassword(args[i].split(" ")[1].replace("'", ""));
                else if (args[i].split(" ")[0].compareTo("-id") == 0)
                    q.setId(Integer.parseInt(args[i].split("'")[1]));
            }
            // dupa ce retinem datele despre quizz, il cautam si il stergem
            q.delete();
        } else if (args[0].compareTo("-get-my-solutions") == 0) {
            // cream obiect de tip quizz
            Quizz q = new Quizz();
            for (int i = 1; i < args.length; i++) {
                if (args[i].split(" ")[0].compareTo("-u") == 0)
                    q.setUsername(args[i].split(" ")[1].replace("'", ""));
                else if (args[i].split(" ")[0].compareTo("-p") == 0)
                    q.setPassword(args[i].split(" ")[1].replace("'", ""));
            }
            // obtinem solutiile utilizatorului dat mai sus la toate quizzurile
            q.getSolutions();
        }
    }
}
