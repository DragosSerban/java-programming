- se deschid fisierele de input / output si se creeaza obiecte de tipul BufferedReader si BufferedWriter
- se creeaza cele 5 birouri (pt fiecare tip de utilizator)
- vom citi comenzile din fisierul de input si vom apela functiile corespunzatoare
- utilizatorii cor fi tinuti in biroul corespunzator tipului lor
- fiecare utilizator are cate o lista de cereri in asteptare si una cu cereri finalizate
- vom folosi liste ArrayList atat pentru introducerea utilizatorilor in birou,
cat si pt introducerea cererilor in obiectul reprezentat de utilizator
- fiecare utilizator va extinde 5 clase (Persoana, Angajat, Elev, Pensionar, EntitateJuridica), pt fiecare tip
- metoda adaugaUtilizator adauga un utilizator nou folosindu-se de constructorii claselor ce extind Utilizator
- functia sorteazaCereri se va ocupa de sortarea cererilor utilizatorului curent in functie de data
- exista si functia de retrage cerere, care itereaza prin lista pana ajunge la cererea pe care dorim sa o stergem
- metoda actiuneIncorecta este utilizata pt a arunca exceptia pt actiune incorecta
- fiecare din clasele ce extind Utilizator implementeaza functiile de afisare a cererilor (asteptare + finalizate),
dar si functiile de adaugare a unei cereri, care verifica corectitudinea actiunii dorite
si arunca exceptie daca cererea nu corespunde cu tipul de utilizator
- se va crea clasa abstracta Cerere, ce va contine datele corespunzatoare campurilor unei cereri,
cateva get-ere pt data, cerere propriu-zisa, prioritate si nume, un constructor
- aceasta clasa va fi extinsa de cateva clase pt toate tipurile de utilizator, pt a mai adauga campuri utile
si pt a rescrie constructorul
- avem si o clasa speciala pt exceptie; aceasta arunca exceptia pt actiune incorecta.
- vom folosi concepte de genericitate in implementarea claselor Birou si Functionar
- asadar, in birou se va crea o lista de tip ArrayList de utilizatori de un anumit tip, in conformitate cu tipul
biroului, dar si o lista de functionari
- se foloseste functia de sortare pt a sorta toate cererile din birou ce apartin unei anumite categorii de utilizator
- acestea se vor sorta in functie de prioritate; daca p1 == p2 => sortam in functie de data
- de asemenea, vom folosi o functie pt afisarea tuturor cererilor in asteptare ce apartin unui anumit tip de utilizator
- vom folosi o functie pt adaugarea unui functionar in birou
- vom folosi o metoda pt rezolvarea unei cereri de catre un anumit functionar din birou
- am ales sa folosim colectia de ArrayList deoarece este una dintre cele mai eficiente moduri de a stoca si de a accesa
datele, utilizand indecsi ca in vectori, ne putem folosi de asemenea de functia size() pt a afla dimensiunea curenta
a listei; functiile de get(index) si remove(index) sunt foarte folositoare atunci cand vrem sa accesam / stergem
un index; de asemenea add(element) adauga un element la final de lista.
