# Laboratorul 5

## Exercitiul 1

  - `Locations.java` class - adaugata pentru a putea asocia unui Meeting o locatie - cu tabela Locations aferenta<br/>
                           - `meetings.xhtml` a fost modificat astfel incat sa contina un select de o locatie - lista obtinuta din tabela Locations
  - `templates` - folder pentru stocarea template-urilor<br/>
                - `footer.xhtml` - footer template - contine Project Version & Copyright notice<br/>
                - `menuBar.xhtml` - meniu care permite navigarea intre pagini<br/>
                - `header.xhtml` - inlude `menuBar.xhtml` si insereaza "pageTitle"<br/>
                - `page.xhtml` - include `header.xhtml` si `footer.xhtml`; insereaza "content" <br/>
                - `dataView.xhtml` - implementeaza `page.xhtml`, defineste "content" si creaza un form care permite inserarea de dataViewContent =>  template-ul este implementat de test in pagina `test_view.xhtml`<br/>
                - `dataEdit.xhtml` - implementeaza `page.xhtml`, defineste "content" si creaza un form care permite inserarea de dataEditContent =>  - template-ul este implementat de test in pagina `test_edit.xhtml`<br/>
                - `dataEdit.xhtml` contine `p:message`, care este updatat cu un mesaj corespunzator cand este apelat Close, implementare facuta prin `p:ajax`
   - `Converters` - folder pentru stocarea de converters<br/>
                  - `LocationConveter` = clasa care converteste numele Locatiei in upperCase
                  ```<h:inputText id="locationName" value="#{locationBean.name}" converter="locationConverter"
                     required="true"/> ```
   - `Validators` - folder pentru stocarea de validators<br/>
                  - `TopicValidator` verifica ca meeting Topic sa aiba minim 3 caractere
                   ```        <h:inputText id="meetingTopic" value="#{meetingBean.topic}" validator="topicValidator"
                     required="true"/>```

## Exercitiul 2

  - `webapp\resources\components\meetingDescr.xhtml` - o componenta composite care afiseaza informatii despre un meeting, cu parametrii necesari
  - `poll_util.xhtml` - updateaza numarul de sesiuni in lucru - `CounterListener` implemnteaza HttpSessionListener si asigura updatarea numarului de sesiuni,
                                                                  la fiecare creare/distrugere de sesiune
  - Internationalizarea - `webapp\WEB-INF\classes\Messages_en.properties` si `webapp\WEB-INF\classes\Messages_ro.properties` - exemplu de aplicare
                          in menuBar.xml
    
## Exercitiul 3

  - Crearea de connection pool si JDBC resource -  `webapp\WEB-INF\glassfish-resources.xml`
  - Crearea de DataSource, la initializarea Manager - ```dataSource = (DataSource) ic.lookup("java:app/custom");```<br/>
      - Updatarea clasei DbActions astfel incat sa preia conexiuni din DataSource (nu cum crea inainte o conexiune pentru fiecare operatie) 
