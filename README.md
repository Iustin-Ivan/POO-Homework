# tema-2-Iustin-Ivan 323CB
tema-2-Iustin-Ivan created by GitHub Classroom
Colectia aleasa de mine a fost ArrayList pentru a putea stoca dinamic numarul de utilizatori functionari, cat si cererile acestora
In implementare am facut clasa Utilizator abstracta pentru a putea permine celorlalte tipuri de utilizatori sa mosteneasca anumite
metode pe care sa le implementeze in diferite feluri cum ar fi toString
Pentru sortarea cererilor la afisare am implementat interfata Comparator deoarece a trebuit sa sortez in 2 feluri diferite asa ca
am folosit Comparator pentru a putea selecta dinamic metoda pe care vreau sa o folosesc
Atat birourile cat si utilizatorii stocheaza o lista cu cereri iar cand o cerere e creata, aceasta e adaugata in ambele.
Utilizatorii mai contin si o lista cu cererile solutionate. Fiecare dintre acestea e folosita in functie de comanda primita.
Acum dupa labul 11 cu Colectii realizez ca un PriorityQueue ar fi fost mai bun pentru rezolvarea cererilor si sa nu fac toata tema
cu ArrayList dar deja e prea tarziu sa mai reconstruiesc tema, asa ca varianta cu DynamicComparator ramane
