# Laboratorul 9

## JaxWS
   - A fost creat un serviciu de tip JaxWS `/Laborator8/src/main/java/JAXRS/ViewDocumentService.java` care returneaza:
        - in cazul in care parametrul id nu este null, obiectul Upload corespunzator id-ului dat
        - in cazul in care parametrul id nu este dat, lista tuturor obiectelor de tip Upload
        - Rezultate (rulate din Tester):
        ![Screenshot](/tester_ws_no_params.png)
        ![Screenshot](/tester_ws_with_params.png)
   - A fost creata o aplicatie de tip client care sa testeze functionalitatea serviciului
        - implementare : `/Lab9Client/src/main/java/ClientJaxWS.java`
        - Package-ul Artefacts a fost generat cu ajutorul IDE-ului, folosind Wsdl-ul generat pe baza serviciului
        - Rezultatele rularii :
            Cu parametru dat:
                ![Screenshot](/client_response_with_param.png)
            Fara parametru:
                ![Screenshot](/client_response_no_param.png)
   - A fost implementat si un message handler (`/Laborator8/src/main/java/JaxWS/LogicalHandlerImplementation.java`, si xml de configurare `/Laborator8/src/main/java/JaxWS/handler-chain.xml` )

## JaxRS
   - S-au implementat serviciile cerute in package-ul `/Laborator8/src/main/java/JAXRS` - Add, Delete, Update
   - A fost creat si serviciul de View (similar cu serviciul WS)
   - Requestul si Response-ul au fost reprezentate ca JSON (@Produces/@Consumes(MediaType.APPLICATION_JSON))
   - Exemplu de raspuns pentru invocarea /webresources/view_document/2 (upload-ul cu id-ul 2)
    ![Screenshot](/rs_example_of_usage.png)
    