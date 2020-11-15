# Laboratorul 4

## Exercitiul 1
	- `persons.xhtml` cu formularul aferent inregistrarii in baza de date a unei Persoane
		cu submit in care se adauga noul record in tabela Persons
	- `meetings.xhtml` cu formularul aferent inregistrarii in baza de date a unui Meeting
		cu lista(datatable din primefaces) cu suport de checkbox (pt alegerea persoanelor participante)
		si un submit in care noul record se adauga in tabela MeetingsTable
		(a mai fost folosit si calendarul din primefaces pentru alegerea Starting_Time-ului)
	- Beans : Persons, Meetings si Manager
	- Manager - preia lista de persoane pt formarea checkbox list-ului si 
		  - preia listele de persoane & meeting-urilor pentru a afisa rezultatele
		  - adauga record-uri - preia submit-urile din formulare
	- PostgreSQL - a fost folosit pentru crearea bazei de date si a tabelelor
	
 	Extra Page - `displayTables` - pentru afisarea tabelelor

## Exercitiul 2
	- `ChocoSolver` - implementare pentru rezolvarea problemei Meeting Scheduling Problem cu distante
	- `displayResults` - pentru afisarea rezultatelor generate de ChocoSolver
	- `import_file` - pentru importarea unui fisier de tipul fisierului `test_chocosolver.txt`(aflat in root-ul repo-ului)
		- format fisier : 
			- nrMeetings nrPersons nrTimeSlots
			- Matrice care spune daca pers x participa la meeting-ul y (m[x][y]=1)
			- Matrice care spune distanta intre meeting-ul x si meeting-ul y (d[x][y]=z, cu d[x][x]=0) 
				= cat timp necesita deplasarea de la un meeting la celalalt
		- dupa citirea fisierului si gasirea solutiei, rezolvarea se afiseaza in pagina `displayResults`
	
