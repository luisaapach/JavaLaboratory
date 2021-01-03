# Laboratorul 8


- S-a creat o noua aplicatie - pentru upload de documente

- Pages : 
    - *login.xhtml* - pagina de pornire
    - *registration.xhtml* - pagina de inregistrare a noilor useri
	- *adminUploads.xhtml* - pagina accesibila doar utilizatorului cu drept de admin (dupa ce se termina login-ul cu succes) - functionalitate creata prin implementarea filtrului AdminRoleFilter 
	- *upload.xhtml* - pagina accesibila doar utilizatorului cu drept de guest (dupa ce se termina login-ul cu succes) - functionalitate creata prin implementarea filtrului GuestRoleFilter

- Detaliile de implementare :
	1. @Inject
	    - S-a renuntat la folosirea adnotarii @EJB si s-a folosit @Inject pentru preluarea de clase DAO (din pachetul *Services*), fiind necesara crearea de interfete specifice pentru fiecare tip de Service (pachetul *Interfaces*)
	    - Exemplu : 
	    ![Screenshot](/ex1.png)
	2.  @Produces
	    - S-a creat o producer method pentru generarea de numere unice de submitere a fisierelor (*Beans/IDGenerator*)
	    - Valoarea s-a generat la efectuarea de upload
	3. @Interceptor
	    - S-a folosit un interceptor pentru scrierea de log in fisier la fiecare upload reusit de fisier
	    - Implementare : *Interceptor\LoggedInterceptor*
	    - Exemplu de fisier generat *MyLogFile_1-3_201340.log*
	4. @Decorator
	    - Folosit pentru a verifica daca submiterea de fisier s-a facut in time frame-ul setat
	    - Implementare : *Decorators/TimeFrameValidationDecorator* - clasa abstracta care implementeaza interfata UploadInterface
	5. @Observes
	    - Folosit pentru a updata Cache-ul (*Beans/UploadsCacheBean*) responsabil cu retinerea fisierelor uploadate (caci dupa terminarea perioadei de submitere, acesta nu se va mai schimba)
	    ```
	    @Logged
        public void onUploadsUpdate(@Observes Upload upload){
            uploads = null;
        }
	    ```
        - CacheBean va fi preluat in momentul apelarii paginii *adminUploads.xhtml*, odata ce admin-ul reuseste logarea
    6. Data Validation
        - S-au folosit adnotarile de validare a datelor pe fiecare membru din clasele de tip Entity
        - Exemplu - orice incercare de inregistrare cu un username care avea mai putin de 3 caractere nu reusea, transmitand un mesaj corespunzator
        ```    @NotNull(message = "Username cannot be null")
               @Size(min = 3, max = 50, message= "Username must be between 3 and 50 characters")
               private String username;
        ```
       ![Screenshot](/ex2.png)
       