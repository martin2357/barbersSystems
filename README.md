# barbersSystems

Názov a popis: 
BabrerSystems je webová aplikácia založená pomovou Springu a Mavenu. Jej úlohou je pracovať s dátami od Emploee, Customer, Action, Order. Aplikácia dokáže prijímať objekty vo formáte JSON a spracovať ich podla potreby, dokáže ukladať, overovať, vypisovať a to aj s podmienkami. Spolupracuje s PostgreSQL. Program je zodpovedný za výpočet ceny objednávky na základe zvolených akcií. 

Inštalácia:
Spring version: 3.0.11
Java version: 17
Maven version: 3.8.3
Libery: lombok, postgresql-42.2.8.jar

Aktuálne je z aplikácie dokončený back-end, neskôr by sa mal pripájať na Front-end, pričom si pomocou JSON budú posielať potrebne dáta.  Pomocou kontrolerov a aktívnej DB si môžete pozrieť záznami a reporty alebo detajl jednotlivých entitných záznamov ako napr. Order, Customer, Employee. Aplikácia automaticky generuje nové čísla objenávok a čísla pre customer a emp podľa posledného záznamu z db a následnej inkrementácií. Aplikácia je tiež schopná príjímať data cez DataInit- pre testovanie alebo plnenie db.

Na adresách môžete nájsť: 

Detajl objednávky
http://localhost:8080/order/31

tímto spôsobom môžeme editovať akcie v obejdnávke cez GET
http://localhost:8080/order/{orderID}/actions/{actionID}


Pridanie novej obejdnávky cez POST		      
http://localhost:8080/customer/add 	 

Registrácia nového zamestnanca cez POST
http://localhost:8080/emp/add
 	
Overenie prihlásenia a následné uloženie objektu do Session
http://localhost:8080/emp/login

Zobrazí všetkých zamestnancov, ale len vybrané dáta ktoré obsahuje DTO trieda
http://localhost:8080/emp/all 

Zobrazí objednavky z konkrétnym statusom pre konkrétneho zamestnanca
http://localhost:8080/emp/id/status

Samozrejme existuje možnosť DELETE pre každú entitu.


