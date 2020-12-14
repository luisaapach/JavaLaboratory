# Laboratorul 7

## Exercitiul 1
    - Repository `DAO` : Repositories pentru fiecare entitate, fiecare clasa extinzand clasa abstracta DataRepository (LocationManager, PersonManager, MeetingManager)
    - Repository-urile sunt mapate la managed bean-ul aferent (folosindu-se adnotarea @EJB)
    - Adaugarea proprietatii de capacity pentru Meeting
    - `reservation.xhtml` - 2 tabele din care se pot alege : un meeting si una/mai multe persoane
    - Beans/AssignmentBean - Managed bean pt gestionare reservation.xhtml
    - DAO/AssignmentCheckAvailability - stateless session bean - care verifica daca se poate adauga un numar de persoane la un meeting prin verificarea capacitatii si al numarului de persoane deja asignate la meeting
    - DAO/AssignmentManager - stateful session bean - responsabila cu updatarea meeting-ului cu lista de persoane aferenta, daca testul de disponibilitate trece
    - Beans/AssignmentsStorage - singleton session bean - initiata la application startup - retine in memorie un hashmap, prin care se stocheaza assignments. Este actualizata odata ce un assignment a reusit.

## Exercitiul 2

    - Repository Testing : InterceptorCustom.java & TimerSessionBean.java
    - InterceptorCustom este legat de clasa AssignmentCheckAvailability - afiseaza numarul de ms necesare executiei functiei `available`
    - TimerSessionBean apeleaza functia `available` folosind un schedule
    - Interpretare rezultate : 
        - atunci cand optimizatorul cache shared este dezactivat, se observa pe logging ca functia are timp de rulare peste 1 ms
        - atunci cand optimizatorul cache shared este activat, se observa pe logging ca functia are timp de rulare, la primul apel, de 4 ms, dupa, datorita cacheului si pentru ca rezultatul nu se mai schimba, timpul de rulare devine de 0 ms
```
When `<property name="eclipselink.cache.shared.default" value="false" />`:

[2020-12-14T21:32:23.095+0200] [glassfish 5.0] [INFO] [] [] [tid: _ThreadID=165 _ThreadName=Thread-8] [timeMillis: 1607974343095] [levelValue: 800] [[
  DAO.AssignmentCheckAvailability.available() took 1ms to execute]]

[2020-12-14T21:32:24.105+0200] [glassfish 5.0] [INFO] [] [] [tid: _ThreadID=166 _ThreadName=Thread-8] [timeMillis: 1607974344105] [levelValue: 800] [[
  Working hard...]]

{...}

[2020-12-14T21:32:24.110+0200] [glassfish 5.0] [INFO] [] [] [tid: _ThreadID=166 _ThreadName=Thread-8] [timeMillis: 1607974344110] [levelValue: 800] [[
  DAO.AssignmentCheckAvailability.available() took 1ms to execute]]

[2020-12-14T21:32:25.119+0200] [glassfish 5.0] [INFO] [] [] [tid: _ThreadID=167 _ThreadName=Thread-8] [timeMillis: 1607974345119] [levelValue: 800] [[
  Working hard...]]

{...}

[2020-12-14T21:32:25.129+0200] [glassfish 5.0] [INFO] [] [] [tid: _ThreadID=167 _ThreadName=Thread-8] [timeMillis: 1607974345129] [levelValue: 800] [[
  DAO.AssignmentCheckAvailability.available() took 2ms to execute]]

[2020-12-14T21:32:26.145+0200] [glassfish 5.0] [INFO] [] [] [tid: _ThreadID=168 _ThreadName=Thread-8] [timeMillis: 1607974346145] [levelValue: 800] [[
  Working hard...]]
```
```
When `<property name="eclipselink.cache.shared.default" value="true" />`:

[2020-12-14T21:37:17.125+0200] [glassfish 5.0] [INFO] [] [] [tid: _ThreadID=139 _ThreadName=Thread-8] [timeMillis: 1607974637125] [levelValue: 800] [[
  DAO.AssignmentCheckAvailability.available() took 4ms to execute]]

[2020-12-14T21:37:18.178+0200] [glassfish 5.0] [INFO] [] [] [tid: _ThreadID=140 _ThreadName=Thread-8] [timeMillis: 1607974638178] [levelValue: 800] [[
  Working hard...]]

{...}

[2020-12-14T21:37:18.184+0200] [glassfish 5.0] [INFO] [] [] [tid: _ThreadID=140 _ThreadName=Thread-8] [timeMillis: 1607974638184] [levelValue: 800] [[
  DAO.AssignmentCheckAvailability.available() took 0ms to execute]]

[2020-12-14T21:37:19.196+0200] [glassfish 5.0] [INFO] [] [] [tid: _ThreadID=162 _ThreadName=Thread-8] [timeMillis: 1607974639196] [levelValue: 800] [[
  Working hard...]]

{...}

[2020-12-14T21:37:19.200+0200] [glassfish 5.0] [INFO] [] [] [tid: _ThreadID=162 _ThreadName=Thread-8] [timeMillis: 1607974639200] [levelValue: 800] [[
  DAO.AssignmentCheckAvailability.available() took 0ms to execute]]

[2020-12-14T21:37:20.211+0200] [glassfish 5.0] [INFO] [] [] [tid: _ThreadID=163 _ThreadName=Thread-8] [timeMillis: 1607974640211] [levelValue: 800] [[
  Working hard...]]


```