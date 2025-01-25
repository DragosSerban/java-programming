# tema-1-DragosSerban
tema-1-DragosSerban created by GitHub Classroom

- La inceput vom deschide cele 4 fisiere in care vom stoca datele (users.txt, questions.txt, quizzes.txt, solutions.txt)
- in functie de argumentele primite vom face diverse actiuni:
- cleanup-all (sterge toate fisierele si reseteaza nr intrebarilor si al quizurilor
- create-user: creeaza un obiect user, foloseste functiile de set pt username si parola; adauga la fisierul txt
- pt a se adauga la fisierul users.txt se verifica daca s-au introdus username-ul si parola si daca a mai fost introdus
odata acelasi user sau nu in fisier. daca nu a mai fost introdus, atunci il introducem acum
- create-question: creeaza un obiect de tip question, se foloseste de setters pt a completa campurile, adauga la
fisierul questions.txt
- mai intai se verifica credentialele, apoi se verifica daca intrebarea are nr corect de raspunsuri corecte, daca are
macar 2 optiuni de raspuns, daca a mai fost introdusa aceeasi intrebare in fisier
- daca totul merge bine, se deschide fisierul si se salveaza datele despre intrebarea curenta
- get-question-id-by-text: creeaza un obiect de tip question, populeaza campurile user, parola si text cu setters,
iar apoi apeleaza functia getQestionIdByText
- se verifica user-ul si parola, se cauta intrebarea in fisierul questions.txt in functie de user si text si va fi
afisat id-ul intrebarii
- get-all-questions: creeaza un obiect question, ii seteaza campurile username si parola, afiseaza prin getAllQuestions
toate intrebarile utilizatorului dat mai sus
- se verifica userul si parola; se vor afisa toate intrebarile din questions.txt ce au fost create de userul dat
- create-quizz creeaza un obiect de tip quizz, ii populeaza campurile de user, parola, text si adauga intrebarile;
il adauga la fisierul quizzes.txt
- functia de addQuestion este folosita pt a adauga intrebari la quiz; verifica sa nu se fi introdus mai mult de 10
intrebari, se cauta intrebarea dupa id in questions.txt si se adauga in vectorul de intrebari
- functia addQuizz adauga quizul la fisierul txt; verifica credentialele; se verifica daca a mai fost creat quizul;
daca nu a mai fost creat, se adauga datele quizului la quizzes.txt
- get-quizz-by-name intoarce chestionarul in functie de nume; prin setters sunt completate campurile user, parola, nume;
este apelata functia getQuizzByName
- functia verifica credentialele si se cauta in quizzes.txt un quizz cu datele username si name si se afiseaza id-ul
- get-all-quizzes afiseaza toate quizurile unui utilizator; sunt furnizate user-ul si parola; este apelata functia
getAllQuizzes
- getAllQuizzes verifica credentialele si cauta prin quizzes.txt quizurile utilizatorului dat; le afiseaza
- get-quizz-details-by-id afiseaza detaliile quizului cu id-ul dat
- functia getDetails verifica credentialele; se cauta quizul cu id-ul furnizat in quizzes.txt si se afiseaza detaliile;
se cauta intrebarile in questions.txt in functie de id-urile lor din quizzes.txt; se afiseaza intrebarile si
raspunsurile
- submit-quizz primeste userul, parola, quizul ce va fi rezolvat de user, raspunsurile date de acesta si va apela
functia submit a obiectului de tip quizz creat mai sus
- functia submit verifica credentialele; se verifica id-ul quizului; se verifica sa nu fie rezolvat de cel care l-a
creat; se trece prin fiecare intrebare din questions.txt in parte; se cauta intrebarile din quizul nostru, se calculeaza
punctajele pt raspunsuri corecte / gresite, se calculeaza punctajul pe fiecare intrebare; se salveaza in solutions.txt
- delete-quizz sterge quizul cu id-ul dat folosit metoda delete()
- metoda delete() verifica corectitudinea username-ului si a parolei, cauta quiz-ul in quizzes.txt usernamein functie de id
- toate celelalte quizuri vor afla datele copiate intr-un fisier temporar ce va fi redenumit mai apoi in quizzes.txt
- get-my-solutions afiseaza solutiile de la toate quizurile rezolvate de userul dat, folosind metoda getSolutions
- getSolutions verifica credentialele si cauta in solutions.txt solutiile ce apartin userului; le afiseaza
